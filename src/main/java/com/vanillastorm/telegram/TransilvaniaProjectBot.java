package com.vanillastorm.telegram;

import com.vanillastorm.gameplay.story.Story;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class TransilvaniaProjectBot extends TelegramLongPollingBot {

    private Story creatureStory;
    private int chapterNumber;
    private int restartChapter;

    // Create ReplyKeyboardMarkup object
    private ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

    @Override
    public void onUpdateReceived(Update update) {

        String message_text = update.getMessage().getText();
        System.out.println("result update message: " + message_text);
        long chat_id = update.getMessage().getChatId();
        // We check if the update has a message and the message has text

        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();

        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message;
            if (message_text.equals("/start")) {
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Choose story:" +
                                "\n * Detective Len noir story." +
//                                "\n * 100 hp" +
//                                "\n * 25 strength" +
//                                "\n * 7 accuracy" +
//                                "\n * No shield\n" +

                                "\n * Mad Scientist mathematical story." +
//                                "\n * 125 hp" +
//                                "\n * 10 strength" +
//                                "\n * 15 accuracy" +
//                                "\n * Lab coat\n" +

                                "\n * Ronin Muramatsu Takanao samurai story."
//                                "\n * 80 hp" +
//                                "\n * 33 strength" +
//                                "\n * 13 accuracy" +
//                                "\n * Kimono"
                                );

                KeyboardRow row = new KeyboardRow();
                row.add("Detective Len");
                keyboard.add(row);

                row = new KeyboardRow();
                row.add("Mad Scientist");
                keyboard.add(row);

                row = new KeyboardRow();
                row.add("Ronin");
                keyboard.add(row);

                this.chapterNumber = 1;

            } else if (message_text.equals("/description")) {
                message_text = "Transilvania project is a adventure text game.\n" +
                        "During the story you have to fight different creatures, make dicisions and see where your choices will take you.";
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(message_text);

            } else if (message_text.equals("Detective Len") || message_text.equals("Mad Scientist") || message_text.equals("Ronin")) {
                creatureStory = new Story(message_text);
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(Story.loadChapterText(chapterNumber));

                for (int i = 0; i < Story.getAmountOfResult(chapterNumber); i++) {
                    KeyboardRow row = new KeyboardRow();
                    row.add(Story.getOptionName(i, chapterNumber));
                    keyboard.add(row);
                }

            }else if (message_text.equals("Restart, please")){
                message_text = Story.getAnswer(chapterNumber, message_text); // after button "option text" pressed - loads (result text)
                this.chapterNumber = Story.getNextChapterNumber(restartChapter, message_text); // finds <next chapter> thro optionText -> resultID -> nextChapter

                message = new SendMessage()
                        .setChatId(chat_id)
                        .setText(message_text + "\n" + Story.loadChapterText(restartChapter)); // result text + next chapter <text>

                for (int buttonNo = 0; buttonNo < Story.getAmountOfResult(chapterNumber); buttonNo++) {
                    KeyboardRow row = new KeyboardRow();
                    row.add(Story.getOptionName(buttonNo, chapterNumber));  // option text +
                    keyboard.add(row);
                }

            } else {
                String oldMessageText = message_text;
                message_text = Story.getAnswer(chapterNumber, oldMessageText); // after button "option text" pressed - loads (result text)
                this.chapterNumber = Story.getNextChapterNumber(chapterNumber, oldMessageText); // finds <next chapter> thro optionText -> resultID -> nextChapter

                if (Story.isRestartChapterN(chapterNumber)) {
                    restartChapter = chapterNumber;
                    Story.loadChapterByNumber(0).getResultN(0).setNextChapterID(restartChapter);
                }

                message = new SendMessage()
                        .setChatId(chat_id)
                        .setText(message_text + "\n" + Story.loadChapterText(chapterNumber)); // result text + next chapter <text>

                for (int buttonNo = 0; buttonNo < Story.getAmountOfResult(chapterNumber); buttonNo++) {
                    KeyboardRow row = new KeyboardRow();
                    row.add(Story.getOptionName(buttonNo, chapterNumber));  // option text +
                    keyboard.add(row);
                }
            }

            // Set the keyboard to the markup
            keyboardMarkup.setKeyboard(keyboard);
            // Add it to the message
            message.setReplyMarkup(keyboardMarkup);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
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

//    remove keyboard
//    ReplyKeyboardRemove keyboardMarkupremove = new ReplyKeyboardRemove();
//    message.setReplyMarkup(keyboardMarkupremove);