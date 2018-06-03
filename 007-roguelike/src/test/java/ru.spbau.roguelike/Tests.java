package ru.spbau.roguelike;

import org.junit.Test;
import ru.spbau.roguelike.model.modifier.ability.Abilities;
import ru.spbau.roguelike.model.modifier.ability.AbilityName;

import static org.junit.Assert.assertEquals;

public class Tests {
    @Test
    public void abilitiesTest() {
        Abilities a = Abilities.newBuilder().set(AbilityName.DEFENCE, 50);
        Abilities b = Abilities.newBuilder().setAttack(25);
        Abilities c = a.compose(b);
        assertEquals(25, c.getAttack());
        assertEquals(c.get(AbilityName.ATTACK), c.getAttack());
        assertEquals(a, c.decompose(b));
        assertEquals(0, c.getHealthPoints());
    }
}