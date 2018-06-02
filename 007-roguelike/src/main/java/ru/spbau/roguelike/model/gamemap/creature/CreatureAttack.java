package ru.spbau.roguelike.model.gamemap.creature;

import ru.spbau.roguelike.model.modifier.ability.Abilities;
import ru.spbau.roguelike.model.modifier.DamageValueCalculator;
import ru.spbau.roguelike.model.modifier.Modifier;

/**
 * Creature attack
 */
public class CreatureAttack implements Modifier {
    private final Abilities abilities;

    private CreatureAttack(Abilities abilities) {
        this.abilities = abilities;
    }

    /**
     * Calculate damage and create CreatureAttack instance
     */
    static CreatureAttack createCreatureAttack(Creature a, Creature b) {
        int damageValue = DamageValueCalculator.calculate(a, b);
        return new CreatureAttack(Abilities.newBuilder().setHealthPoints(-damageValue));
    }

    @Override
    public Abilities getAbilities() {
        return abilities;
    }
}
