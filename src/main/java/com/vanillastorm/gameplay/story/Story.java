package com.vanillastorm.gameplay.story;

import com.vanillastorm.creatures.Character;
import com.vanillastorm.creatures.HallOfFame;
import com.vanillastorm.creatures.stuff.Items.Items;
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
    private Character currentVillaine;

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

                    NodeList text = chapterElement.getElementsByTagName("text");
                    Element textElement = (Element) text.item(0);
                    chapter.setText(textElement.getTextContent());

                    NodeList options = chapterElement.getElementsByTagName("option");
                    for (int i = 0; i < options.getLength(); i++) {
                        Element optionElement = (Element) options.item(i);
                        Option option = new Option();

                        option.setResultId(Integer.parseInt(optionElement.getAttribute("resultID")));
                        option.setText(optionElement.getTextContent());

                        chapter.addOption(option);
                    }

                    NodeList results = chapterElement.getElementsByTagName("result");
                    for (int i = 0; i < results.getLength(); i++) {
                        Element resultElement = (Element) results.item(i);
                        Result result = new Result();
                        result.setText(resultElement.getTextContent());
                        result.setNextChapterID(Integer.parseInt(resultElement.getAttribute("nextChapterID")));

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
        if (characterName.equals("Detective Len")) {
            characterName = "detective";
        } else if (characterName.equals("Scientist Mad")) {
            characterName = "scientist";
        } else {
            characterName = "ronin";
        }

        this.characterName = characterName;
        this.characters = HallOfFame.storyCharacters(characterName);

        this.hero = (Character) characters.get(0);
        this.currentVillaine = (Character) characters.get(1);

        try {
            this.chapters = parseChapters(this.characterName + "Story.xml");
        } catch (Exception e) {
            System.out.println("Bad things are happened:(");
            e.printStackTrace();
        }
    }

    // Story
    public String characterInfo() {
        return hero.character() + "to see statistics: /stats\n";
    }

    public String loadChapterText() {
        return chapters.get(this.chapterNumber).getText();
    }

    public String getOptionName(int buttonNum) {
        return chapters.get(this.chapterNumber).optionText(buttonNum);
    }

    public int getAmountOfResult() {
        return chapters.get(this.chapterNumber).getResultsAmount();
    }

    public void upadateChapterNumber(String oldMessage) {
        this.chapterNumber = chapters.get(this.chapterNumber).getNextChapter(oldMessage);
    }

    public String getAnswer(String message) {
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
        return chapters.get(this.chapterNumber).isFightChapter();
    }

    public int getAmountOfOptions() {
        return chapters.get(chapterNumber).getOptionsAmount();
    }

    // Fight
    public boolean heroIsAlive() {
        return hero.isAlive();
    }

    public boolean villainIsAlive() {
        return currentVillaine.isAlive();
    }

    public String loadNextChapter() {
        this.chapterNumber++;
        return loadChapterText();
    }

    public String simpleAttack() {
        return hero.attack(this.currentVillaine);
    }

    public String villainMove() {
        String m = "\n";
        int rMove = (int) (Math.random() * 100);
        if (rMove <= 50) {
            m += currentVillaine.attack(this.hero);
        } else if (rMove > 51 && rMove < 86) {
            m += currentVillaine.attackWithWeapon(this.hero);
        } else {
            m += currentVillaine.healVillaine(10 * currentVillaine.getLevel());
        }

        if (m.equals("\nNot enough mana, chiiil, dude.") || m.equals("No heal.")) {
            m = "\n" + currentVillaine.attack(this.hero);
        }
        return m;
    }

    public String villaneIsDead() {
        return "\n" + hero.killed(this.currentVillaine);
    }

    public String attackWithWeapon() {
        return "" + hero.attackWithWeapon(currentVillaine);
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
