package com.vanillastorm.gameplay.story;

import com.vanillastorm.creatures.Character;
import com.vanillastorm.creatures.HallOfFame;
import com.vanillastorm.util.SavedCharacter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Story {

    private String characterName;
    private List<Chapter> chapters;

    private List characters;
    private Character hero;
    private Character currentVillain;

    private SavedCharacter savedCharacter = new SavedCharacter();
    private SavedCharacter savedVillain = new SavedCharacter();

    private int checkPoint;
    private int chapterNumber;

    private static List<Chapter> parseChapters(String xmlStoryName) {
        List<Chapter> chapters = new ArrayList<>();
        Chapter chapter = null;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            InputStream inputStream = ClassLoader.getSystemClassLoader().
                    getSystemResourceAsStream("com/vanillastorm/xml/stories/" + xmlStoryName);
            Document document = builder.parse(inputStream);

            document.getDocumentElement().normalize();

            NodeList chaptersList = document.getElementsByTagName("chapter");
            for (int temp = 0; temp < chaptersList.getLength(); temp++) {
                Node node = chaptersList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element chapterElement = (Element) chaptersList.item(temp);
                    chapter = new Chapter();

                    chapter.setFightChapter(Boolean.parseBoolean(chapterElement.getAttribute("isFight")));

                    chapter.setRequiresPassword(Boolean.parseBoolean(chapterElement.getAttribute("requiresPassword")));
                    chapter.setPassword(chapterElement.getAttribute("digitPassword"));

                    String openChapterN = chapterElement.getAttribute("opensChapterN");
                    if (openChapterN != null && !openChapterN.isEmpty()) {
                        chapter.setOpensChapterN(Integer.parseInt(openChapterN));
                    }

                    NodeList text = chapterElement.getElementsByTagName("text");
                    Element textElement = (Element) text.item(0);
                    chapter.setImageURL(textElement.getAttribute("imgURL"));
                    chapter.setText(textElement.getTextContent());

                    NodeList options = chapterElement.getElementsByTagName("option");
                    for (int i = 0; i < options.getLength(); i++) {
                        Element optionElement = (Element) options.item(i);
                        Option option = new Option();

                        option.setResultId(Integer.parseInt(optionElement.getAttribute("resultID")));
                        option.setText(optionElement.getTextContent());
                        option.setCanBeInactive(Boolean.parseBoolean(optionElement.getAttribute("canBeInactive")));

                        chapter.addOption(option);
                    }

                    NodeList results = chapterElement.getElementsByTagName("result");
                    for (int i = 0; i < results.getLength(); i++) {
                        Element resultElement = (Element) results.item(i);
                        Result result = new Result();
                        result.setText(resultElement.getTextContent());
                        result.setNextChapterID(Integer.parseInt(resultElement.getAttribute("nextChapterID")));

                        String itemToTakeName = resultElement.getAttribute("itemToTake");
                        if (itemToTakeName != null && !itemToTakeName.isEmpty()) {
                            result.setItemToTake(itemToTakeName);
                        }

                        String amountOfItems = resultElement.getAttribute("amountOfItems");
                        if (amountOfItems != null && !amountOfItems.isEmpty()) {
                            result.setAmountOfItems(Integer.parseInt(amountOfItems));
                        }

                        chapter.addResult(result);
                    }
                    chapters.add(chapter);
                }
            }

        } catch (Exception e) {
            System.out.println(e); //Loggers for exception
            e.printStackTrace();
        }

        return chapters;
    }

    public void setStory(String characterName) {
        if (characterName.equals("Detective Leń")) {
            characterName = "detective";
        } else if (characterName.equals("Scientist Mad")) {
            characterName = "scientist";
        } else {
            characterName = "ronin";
        }

        this.characterName = characterName;
        this.characters = HallOfFame.storyCharacters(characterName);

        this.hero = (Character) characters.get(0);
        this.currentVillain = (Character) characters.get(1);

        try {
            this.chapters = parseChapters(this.characterName + "Story.xml");
        } catch (Exception e) {
            System.out.println("Bad things are happened:(");
            e.printStackTrace();
        }
    }

    // Story
    public String characterInfo() {
        return hero.character() + "to see statistics type or press: /stats\n";
    }

    public String loadChapterText() {
        return chapters.get(this.chapterNumber).getText();
    }

    public String getOptionName(int buttonNum) {
        return chapters.get(this.chapterNumber).optionText(buttonNum);
    }

    public void updateChapterNumber(String oldMessage) {
        this.chapterNumber = chapters.get(this.chapterNumber).getNextChapter(oldMessage);
    }

    public String getAnswer(String message) {
        String item = chapters.get(chapterNumber).getResultsItemToTake(message);
        int amountOfItem = chapters.get(chapterNumber).getResultsAmountOfItemToTake(message);

        if (item != null) {
            if (item.equals("Gold")) {
                System.out.println("gold before: " + hero.getGold());
                hero.setGold(hero.getGold() + amountOfItem);
                System.out.println("gold after: " + hero.getGold());
            } else {
                hero.addItemInBackPack(item, amountOfItem);
            }
        }

        return chapters.get(this.chapterNumber).getAnswerResult(message);
    }

    public void setCheckpoint() {
        this.checkPoint = this.chapterNumber;
        chapters.get(0).getFirstResult().setNextChapterID(checkPoint);
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public boolean checkForFight() {
        if (chapters.get(this.chapterNumber).isFightChapter()) {
            this.checkPoint = this.chapterNumber;
            return true;
        } else return false;
    }

    public boolean checkForPhoto() {
        return chapters.get(this.chapterNumber).isPhotoChapter();
    }

    public boolean checkForPassword() {
        return chapters.get(this.chapterNumber).isPasswordChapter();
    }

    public int getAmountOfOptions() {
        return chapters.get(chapterNumber).getOptionsAmount();
    }

    public String restart(String text) {
        this.loadCharacters();
        if (text.equals("Yes, i want la revanche!")) {
            loadCharacters();
            saveCharacters();
            return "\n" + text + loadChapterText();
        } else {
            return "\nNo, you are a huge pussy!" + "\n" + finish();
        }
    }

    public void loadCharacters() {
        this.savedCharacter.loadCharacter(this.hero);
        this.savedVillain.loadCharacter(this.currentVillain);
    }

    public String finish() {
        return chapters.get(chapters.size() - 1).getText();
    }

    public String getImageURL() {
        return chapters.get(this.chapterNumber).getImageURL();
    }

    public void pickUpTheKeyAndSetItToOpenClosedDoor() {
        int val = this.chapters.get(this.chapterNumber).getOpensChapterN();
        if (val != 0) {
            this.chapters.get(val).swapFirstAndLastResults();
        }
    }

    public String validatePassword(String message) {
        if (message.equals(chapters.get(this.chapterNumber).getPassword())) {
            return "Password was corretto\n"+ loadNextChapter();
        } else if (message.equals("No, go back!")) {
            return message;
        } else {
            return "Wrong password";
        }
    }

    public boolean checkForWasUsed(int buttonNo) {
        return chapters.get(this.chapterNumber).getOptions().get(buttonNo).wasUsed();
    }

    // Fight
    public boolean heroIsAlive() {
        return hero.isAlive();
    }

    public boolean villainIsAlive() {
        return currentVillain.isAlive();
    }

    public String loadNextChapter() {
        hero.setMana(hero.getMaxMana());
        this.chapterNumber++;
        return loadChapterText();
    }

    public String simpleAttack() {
        return hero.attack(this.currentVillain);
    }

    public String villainMove() {
        String m = "\n";
        int rMove = (int) (Math.random() * 100);
        if (rMove <= 50) {
            m += currentVillain.attack(this.hero);
        } else if (rMove > 51 && rMove < 86) {
            m += currentVillain.attackWithWeapon(this.hero);
        } else {
            m += currentVillain.healVillaine(10 * currentVillain.getLevel());
        }

        if (m.equals("\nNot enough mana, chiiil, dude.") || m.equals("No heal.")) {
            m = "\n" + currentVillain.attack(this.hero);
        }

        return m;
    }

    public String charactersInfoInFight() {
        String m = hero.character() + "\n" + currentVillain.character();
        return m;
    }

    public String villainIsDead() {
        return "\n" + hero.killed(this.currentVillain);
    }

    public String attackWithWeapon() {
        return "" + hero.attackWithWeapon(currentVillain);
    }

    public String getWeaponManaUsage() {
        return hero.weaponMana();
    }

    public String getWeaponName() {
        return hero.weaponName();
    }

    public String skipMove() {
        return hero.restoreMana(1);
    }

    public void saveCharacters() {
        this.savedCharacter.saveCharacter(this.hero);
        this.savedVillain.saveCharacter(this.currentVillain);
    }

    //Backpack
    public String showBackpack() {
        return hero.printBackpackItems();
    }

    public int amountOfBackpackItems() {
        return hero.amountOfItemsInBackpack();
    }

    public String getItemName(int i) {
        return hero.getItemName(i);
    }

    public String useItem(String name) {
        return hero.useItem(name);
    }
}
