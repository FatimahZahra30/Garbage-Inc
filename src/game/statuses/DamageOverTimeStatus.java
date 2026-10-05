package game.statuses;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.Status;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class to centralise
 * the damage for certain number of turns for
 * some items.
 * @author Fatimah Zahra
 */
public class DamageOverTimeStatus implements Status {
    private int turns;
    private int damage;
    private Actor actor;

    /**
     * Constructor for the status that keeps track of
     * the number of turns each game turn
     *
     * @param turns the number of turns the effect lasts for
     * @param damage the damage the actor takes
     * @param actor the actor affected by the effect
     */
    public DamageOverTimeStatus(int turns, int damage, Actor actor) {
        this.turns = turns;
        this.damage = damage;
        this.actor = actor;
    }

    /**
     * Method that keeps track of how many game rounds have passed
     * since the start of the effect on the player
     *
     * @param currEntity the entity this status is attached to
     * @param location the location of the action taking place on
     */
    public void tickStatus(GameEntity currEntity, Location location) {
        if (turns > 0) {
            actor.hurt(damage);
            turns--;
        }
    }

    /**
     * Method that tells whether the effect is still active
     * @return true if there are more than 0 turns and false otherwise
     */
    public boolean isStatusActive() {
        return turns > 0;
    }
}
