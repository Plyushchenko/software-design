package model.gamemap.creature;

import model.ability.Abilities;
import model.modifier.DamageValueCalculator;
import model.modifier.Modifier;

public class CreatureAttack implements Modifier {
    private final Abilities abilities;

    private CreatureAttack(Abilities abilities) {
        this.abilities = abilities;
    }

    static CreatureAttack createCreatureAttack(Creature a, Creature b) {
        int damageValue = DamageValueCalculator.calculate(a, b);
        return new CreatureAttack(Abilities.newBuilder().setHealthPoints(-damageValue));
    }

    @Override
    public Abilities getAbilities() {
        return abilities;
    }
}
