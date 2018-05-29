package model.modifier;

import model.gamemap.creature.Creature;

/**
 * Utility class to calculate damage value
 */
public class DamageValueCalculator {
    public static int calculate(Creature attacker, Creature defender) {
        int attackValue = attacker.getAbilities().getAttack();
        int defenceValue = defender.getAbilities().getDefence();
        if (attackValue + defenceValue == 0) {
            return 1;
        }
        return Math.max(1, attackValue * attackValue / (attackValue + defenceValue));
    }
}
