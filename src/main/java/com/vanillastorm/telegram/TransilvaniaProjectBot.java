package com.vanillastorm.telegram;

import com.vanillastorm.gameplay.story.Story;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransilvaniaProjectBot extends TelegramLongPollingBot {

    private Story creatureStory;
    // save creature before fight
    private Map<Long, Story> stories = new HashMap<>();

    private ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

    @Override
    public void onUpdateReceived(Update update) {
        List<KeyboardRow> keyboard = new ArrayList<>();

        String message_text = update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();

        if (stories.containsKey(chat_id)) {
            creatureStory = stories.get(chat_id);
        } else {
            creatureStory = new Story();
            stories.put(chat_id, creatureStory);
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message;

            if (message_text.equals("/start") || message_text.equals("start new game") || message_text.equals("Characters story list?")) {
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Choose story:" +
                                "\n * Detective Len noir story." +
                                "\n * Scientist Mad mathematical story." +
                                "\n * Ronin Nona Me samurai story."
                        );

                keyboard = charactersKeyboard();

            } else if (message_text.equals("/stats")) {
                message = new SendMessage()
                        .setChatId(chat_id)
                        .setText(creatureStory.characterInfo());

                keyboard = storyKeyboard(creatureStory);
            } else if (message_text.equals("/description")) {
                message_text = "Transilvania project is a adventure text game.\n" +
                        "During the story you have to fight different creatures, make dicisions and see where your choices will take you.";
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(message_text);

            } else if (message_text.equals("Detective Len") || message_text.equals("Scientist Mad") || message_text.equals("Ronin Nona Me")) {
                creatureStory.setStory(message_text);
                creatureStory.setChapterNumber(1);

                message = new SendMessage()
                        .setChatId(chat_id)
                        .setText(creatureStory.characterInfo() + creatureStory.loadChapterText());

                keyboard = storyKeyboard(creatureStory);

            } else if (message_text.equals("/charactersStats")) {
                message_text = creatureStory.charactersInfoInFight();

                message = new SendMessage()
                        .setChatId(chat_id)
                        .setText(message_text);

            } else {
                if (creatureStory.checkForFight()) {
                    boolean moveWasMade = false;
                    boolean openBackpack = false;
                    boolean denial = false;

                    if (creatureStory.heroIsAlive()) {
                        if (message_text.equals("Casual attack")) {
                            message_text = creatureStory.simpleAttack();
                            moveWasMade = true;
                        } else if (message_text.equals(creatureStory.getWeaponName() + " attack(" + creatureStory.getWeaponManaUsage() + " mana)")) {
                            message_text = creatureStory.attackWithWeapon();
                            moveWasMade = !message_text.equals("Not enough mana, chiiil dude.");
                        } else if (message_text.equals("Backpack")) {
                            message_text = creatureStory.showBackpack();
                            openBackpack = true;
                        } else if (message_text.equals("<-Back")) {
                            message_text = "Back from BACK pack.";
                        } else if (message_text.equals("Skip and 1 mana")) {
                            message_text = creatureStory.skipMove();
                            moveWasMade = true;
                        } else {
                            String[] backpackMessage = message_text.split(" ");
                            if (backpackMessage[0].equals("Use")) {
                                String[] itemWithoutUse = message_text.split("Use ");
                                message_text = creatureStory.useItem(itemWithoutUse[1]);
                                moveWasMade = !(message_text.equals("No need to heal, chiiil.") || message_text.equals("No need to mana."));
                            }
                        }
                    } else {
                        denial = message_text.equals("No, im pussy.");
                        message_text = "\n" + creatureStory.restart(message_text);
                    }

                    if (creatureStory.villainIsAlive()) {
                        if (moveWasMade) {
                            message_text += "\n" + creatureStory.villainMove();
                        }

                        if (!creatureStory.heroIsAlive()) {
                            message_text += "\nRestart?";
                            keyboard = restartKeybpard(0);
                        } else if (openBackpack) {
                            keyboard = backpackKeyboard();
                        } else if (denial) {
                            keyboard = restartKeybpard(1);
                        } else {
                            keyboard = fightKeyboard();
                        }
                    } else {
                        message_text = creatureStory.villaneIsDead();
                        message_text += "\n" + creatureStory.loadNextChapter();

                        keyboard = storyKeyboard(creatureStory);
                    }

                    message = new SendMessage()
                            .setChatId(chat_id)
                            .setText(message_text); // hero attack + villain attack

                } else {
                    // story goes here
                    String oldMessageText = message_text;

                    message_text = creatureStory.getAnswer(oldMessageText); // after button "option text" pressed - loads (result text)
                    if (creatureStory.getChapterNumber() != 0) {
                        creatureStory.setCheckpoint();
                    }

                    creatureStory.upadateChapterNumber(oldMessageText);

                    message = new SendMessage()
                            .setChatId(chat_id)
                            .setText(message_text + "\n" + creatureStory.loadChapterText()); // result text + next chapter <text>

                    if (!creatureStory.checkForFight()) {
                        keyboard = storyKeyboard(creatureStory);
                    } else {
                        creatureStory.saveCharacters();
                        keyboard = fightKeyboard();
                    }
                }
            }

            keyboardMarkup.setKeyboard(keyboard);
            message.setReplyMarkup(keyboardMarkup);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }

    private List<KeyboardRow> restartKeybpard(int i) {
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        if (i == 0) {
            row.add("Yes, i want la revanche!");
            row.add("No, im pussy.");
        } else {
            row.add("Characters story list?");
        }
        keyboard.add(row);

        return keyboard;
    }

    private List<KeyboardRow> backpackKeyboard() {
        List<KeyboardRow> keyboard = new ArrayList<>();

        int amountOfButtons = creatureStory.amountOfBackpackItems();
        if (amountOfButtons == 0) {
            KeyboardRow row = new KeyboardRow();
            row.add("<-Back");
            keyboard.add(row);
        } else {
            for (int i = 0; i < amountOfButtons; i++) {
                KeyboardRow row = new KeyboardRow();
                row.add("Use " + creatureStory.getItemName(i));
                if (i + 1 != amountOfButtons) {
                    row.add("Use " + creatureStory.getItemName(i + 1));
                    i++;
                }
                keyboard.add(row);
            }
            KeyboardRow row = new KeyboardRow();
            row.add("<-Back");
            keyboard.add(row);
        }

        return keyboard;
    }

    private List<KeyboardRow> storyKeyboard(Story creatureStory) {
        List<KeyboardRow> keyboard = new ArrayList<>();
        for (int buttonNo = 0; buttonNo < creatureStory.getAmountOfOptions(); buttonNo++) {
            KeyboardRow row = new KeyboardRow();
            row.add(creatureStory.getOptionName(buttonNo));
            keyboard.add(row);
        }
        return keyboard;
    }

    private List<KeyboardRow> fightKeyboard() {
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("Casual attack");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add(creatureStory.getWeaponName() + " attack(" + creatureStory.getWeaponManaUsage() + " mana)");
        keyboard.add(row);

        row = new KeyboardRow();
        row.add("Backpack");
        row.add("Skip and 1 mana");
        keyboard.add(row);

        return keyboard;
    }

    private List<KeyboardRow> charactersKeyboard() {
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("Detective Len");
        keyboard.add(row);

        row = new KeyboardRow();
        row.add("Scientist Mad");
        keyboard.add(row);

        row = new KeyboardRow();
        row.add("Ronin Nona Me ");
        keyboard.add(row);

        return keyboard;
    }

    @Override
    public String getBotToken() {
        return "799006086:AAEXiZECmpDyMlpH9eiT-yaz2VV4PFv060c";
    }

    @Override
    public String getBotUsername() {
        return "TransilvaniaProjectBot";
    }
}