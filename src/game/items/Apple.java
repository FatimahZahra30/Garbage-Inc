package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.statuses.DamageOverTimeStatus;
import game.actions.ConsumeAction;
import game.interfaces.Consumable;

import static game.utils.ItemUtils.hasSterilisationBox;

/**
 * Class for the item apple that can be consumed/eaten
 * and affects the actor both negatively or positively depending
 * on whether they have a sterilisation box or not
 *
 * @author Fatimah Zahra
 */
public class Apple extends WeightedItem implements Consumable {

    /**
     * Constructor for an apple that can be picked
     * up or dropped, and eaten
     */
    public Apple(){
        super("Apple", 'ó', 1);
        makePortable();
    }

    /**
     * Method for the string representation of the action
     * of consuming the apple based on what condition is met
     *
     * @param actor - the worker that consumes the item
     * @param map the game map that the actor is in
     * @return a string representation of the outcome
     */
    @Override
    public String consumedBy(Actor actor, GameMap map){
        String result;
        if (hasSterilisationBox(actor)){
            actor.heal(3);
            result = "The apple was safe to eat";
        } else {
            actor.addStatus(new DamageOverTimeStatus(5, 1, actor));
            result = "The apple has poisoned you";
        }
        actor.getInventory().remove(this);

        Location location = map.locationOf(actor);

        if (location.getItems().contains(this)) {
            location.removeItem(this);
        }
        return result;

    }

    /**
     * Method for the actions that can be performed on the apple
     *
     * @param owner the actor that owns the item
     * @param map the map where the actor is performing the action on
     * @return a list of the actions that can be performed on the apple
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map){
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }
}
