package com.vanillastorm.creatures;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HallOfFame {
    private static List characters;

    public HallOfFame() {
        try {
            HallOfFame.characters = parseCreatures();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Creature> parseCreatures() {
        List<Creature> characters = new ArrayList<>();
        Creature character = null;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            InputStream inputStream = ClassLoader.getSystemClassLoader().
                    getSystemResourceAsStream("com/vanillastorm/xml/characters.xml");
            Document document = builder.parse(inputStream);

            document.getDocumentElement().normalize();
            NodeList characterList = document.getElementsByTagName("character");
            for (int temp = 0; temp < characterList.getLength(); temp++) {
                Node node = characterList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element characterElement = (Element) characterList.item(temp);
                    character = new Creature();

                    character.setName(characterElement.getAttribute("name"));

                    character.setMaxHp(Integer.parseInt(characterElement.getElementsByTagName("hp").item(0).getTextContent()));
//                    character.setMana(Integer.parseInt(characterElement.getElementsByTagName("mana").item(0).getTextContent()))
                    character.setLevel(Integer.parseInt(characterElement.getElementsByTagName("level").item(0).getTextContent()));
                    character.setStrength(Integer.parseInt(characterElement.getElementsByTagName("strength").item(0).getTextContent()));
                    character.setAccuracy(Integer.parseInt(characterElement.getElementsByTagName("accuracy").item(0).getTextContent()));
                    character.setMaxDefencePointsByShieldName(characterElement.getElementsByTagName("shield").item(0).getTextContent());
                    character.setGold(Integer.parseInt(characterElement.getElementsByTagName("gold").item(0).getTextContent()));

                    characters.add(character);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return characters;
    }

    public static Creature getCharacter(int number) {
        return (Creature) HallOfFame.characters.get(number);
    }
}
