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
        }
    }

    public static String loadFirstChapter () {
        return chapters.get(1).getText();
    }

    private static List<Chapter> parseChapters() throws ParserConfigurationException, SAXException, IOException {
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

                    chapter.setChapterID(Integer.parseInt(chapterElement.getAttribute("chapterID")));

                    NodeList text = chapterElement.getElementsByTagName("text");
                    Element textElement = (Element) text.item(0);
                    chapter.setText(textElement.getTextContent());

                    NodeList options = chapterElement.getElementsByTagName("option");
                    for (int i = 0; i < options.getLength(); i++) {
                        Element optionElement = (Element) options.item(i);
                        Option option = new Option();
                        option.setResultID(Double.parseDouble(optionElement.getAttribute("resultID")));
                        option.setTextOnButton(optionElement.getTextContent());
                        chapter.addOption(option);
                    }

                    NodeList results = chapterElement.getElementsByTagName("result");
                    for (int i = 0; i < results.getLength(); i++) {
                        Element resultElement = (Element) results.item(i);
                        Result result = new Result();
                        result.setText(resultElement.getTextContent());
                        result.setResultID(Double.parseDouble(resultElement.getAttribute("resultID")));
                        result.setNextChapterID(Integer.parseInt(resultElement.getAttribute("nextChapterID")));
                        chapter.addResult(result);
                    }
                    chapters.add(chapter);
                }
            }

        } catch (Exception e) {
            System.out.println(e); //Logery dlya vivoda exception
            e.printStackTrace();
        }

        return chapters;
    }

    private static String checkStory(String storyName) {
        if (storyName.equals("Detective Len")) {
            return "storyDetective.xml";
        } else if (storyName.equals("Mad Scientist")) {
            return "storyScientist.xml";
        } else {
            return "storyRonin.xml";
        }
    }
}
