package com.vanillastorm.gameplay.story;

import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Story {

    private static String storyName;
    private static List<Chapter> chapters;

    public Story(String storyName) {
        Story.storyName = checkStory(storyName);
        try {
            Story.chapters = parseChapters();
        }
        catch (Exception e) {
            System.out.println("Bad things are happened:(");
            e.printStackTrace();
        }
    }

    private static List<Chapter> parseChapters() {
        List<Chapter> chapters = new ArrayList<>();
        Chapter chapter = null;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            InputStream inputStream = ClassLoader.getSystemClassLoader().
                    getSystemResourceAsStream("com/vanillastorm/xml/" + Story.storyName);
            Document document = builder.parse(inputStream);

            document.getDocumentElement().normalize();

            NodeList chaptersList = document.getElementsByTagName("chapter");
            for (int temp = 0; temp < chaptersList.getLength(); temp++) {
                Node node = chaptersList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element chapterElement = (Element) chaptersList.item(temp);
                    chapter = new Chapter();

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

    private static String checkStory(String storyName) {
        if (storyName.equals("Detective Len")) {
            return "detectiveStory.xml";
        } else if (storyName.equals("Scientist Mad")) {
            return "scientistStory.xml";
        } else {
            return "roninStory.xml";
        }
    }

    public static Chapter loadChapterByNumber (int chapterNumber) {
        return chapters.get(chapterNumber);
    }

    public static String loadChapterText (int chapterNumber) {
        return chapters.get(chapterNumber).getText();
    }

    public static String getOptionName(int buttonNum, int chapterNumber) {
        return chapters.get(chapterNumber).optionText(buttonNum);
    }

    public static int getAmountOfResult(int chapterNumber) {
        return chapters.get(chapterNumber).getResultAmount();
    }

    public static int getNextChapterNumber(int oldChapter, String oldMessage) {
        return chapters.get(oldChapter).getNextChapter(oldMessage);
    }

    public static String getAnswer(int chapterNumber, String message) {
        return chapters.get(chapterNumber).getAnswerResult(message);
    }

    public static void setCheckpoint(int checkPoint) {
        chapters.get(0).getFirstResult().setNextChapterID(checkPoint);
    }

}
