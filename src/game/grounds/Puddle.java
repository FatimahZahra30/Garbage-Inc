package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.statuses.DamageOverTimeStatus;
import game.actions.ConsumeAction;
import game.enums.GroundAbility;
import game.interfaces.Consumable;

import static game.utils.ItemUtils.hasSterilisationBox;

/**
 * A small, stationary body of mysterious liquid on the ground.
 * In a standard video game, this would just be water. On a deprecated moon
 * in the Eclipse Nebula, it could be anything from spilled engine coolant to
 * highly corrosive alien saliva. Step in it at your own risk.
 *
 * @author Fatimah Zahra
 */
public class Puddle extends Ground implements Consumable {
    public Puddle() {
        super('~', "Puddle");
        enableAbility(GroundAbility.FLAMMABLE);
    }

    /**
     * Method that provides a string representation for an
     * actor consuming a particular item depending on whether
     * they have a sterilisation box in their inventory or not
     *
     * @param actor - the worker that consumes the item
     * @param map
     * @return a string representation of the outcome of the consumption
     */
    @Override
    public String consumedBy(Actor actor, GameMap map){
        String result;
        if (hasSterilisationBox(actor)){
            actor.heal(1);
            result = "The puddle was safe to drink";
        } else {
            actor.addStatus(new DamageOverTimeStatus(3, 1, actor));
            result = "The puddle has poisoned you";
        }
        return result;
    }

    /**
     * Method that adds an action to be performed on the puddle
     * depending on whether the actor is directly on the puddle
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a list of the actions that can be performed on the puddle
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (direction.isEmpty()){
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }
}
