package com.vanillastorm.creatures;

import com.vanillastorm.util.Color;

import java.util.HashMap;
import java.util.Map;

public class HallOfFame {
    private final static Map<String, Creature.CreatureBuilder> creatureBuilders = new HashMap<>();

    static {
        Creature.CreatureBuilder detective = new Creature.CreatureBuilder()
                .withName("Detective Len")
                .withHp(102)
                .withLevel(1)
                .withStrength(25)
                .withAccuracy(7)
                .withShieldName("None")
                .withGold(5)
                .withColor(Color.CYAN);

        Creature.CreatureBuilder werewolf = new Creature.CreatureBuilder()
                .withName("Werewolf John")
                .withHp(50)
                .withLevel(2)
                .withStrength(13)
                .withAccuracy(10)
                .withShieldName("Wooden")
                .withGold(12)
                .withColor(Color.RED);

        creatureBuilders.put("Detective Len", detective);
        creatureBuilders.put("Werewolf John", werewolf);
    };

    public static Creature getCreature(String creatureName) {
        Creature.CreatureBuilder creatureBuilder = creatureBuilders.get(creatureName);
        if (creatureBuilder != null) {
            return creatureBuilder.build();
        } else {
            throw new RuntimeException(creatureName + " is not implemented yet");
        }
    }
}
