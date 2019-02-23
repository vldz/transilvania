package com.vanillastorm.gameplay.story;

import com.vanillastorm.creatures.Character;
import com.vanillastorm.creatures.HallOfFame;
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

    public String villainAttack() {
        return "\n" + currentVillaine.attack(this.hero);
    }

    public String villaneIsDead() {
        return "\n" + hero.killed(this.currentVillaine);
    }

    public String attackWithWeapon() {
        return "\n" + hero.attackWithWeapon(currentVillaine);
    }

    public String getWeaponManaUsage() {
        return hero.weaponMana();
    }

    public String getWeaponName() {
        return hero.weaponName();
    }
}
