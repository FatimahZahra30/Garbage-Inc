package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.enums.ItemCapability;
import game.interfaces.Consumable;

/**
 * Due to severe budget cuts, the flask is only permitted to hold five (5)
 * mouthfuls of liquid per deployment. Employees are reminded not to consume
 * all five charges in a panic during a single encounter.
 *
 * @author Fatimah Zahra
 */
public class Flask extends WeightedItem implements Consumable {
    private int totalUsable = 5;

    /**
     * Constructor for the flask item
     */
    public Flask() {
        super("Flask", 'u', 3);
        makePortable();
        enableAbility(ItemCapability.DRINKABLE);
    }

    /**
     * Method to tell whether an actor can still drink
     * from the flask
     *
     * @return true if it is still usable and false otherwise
     */
    public boolean hasUses(){
        return totalUsable > 0;
    }

    /**
     * Method to reduce the uses of the flask
     * by 1 until the flask is empty
     */
    public void use() {
        if (totalUsable > 0) {
            totalUsable--;
        }
    }

    /**
     * Method for the string representation of the action
     * of consuming from the flask based on what condition is met
     *
     * @param actor - the worker that consumes the item
     * @param map the game map that the actor is in
     * @return a string representation of the outcome
     */
    @Override
    public String consumedBy(Actor actor, GameMap map){
        if (!hasUses()){
            return actor + " flask is empty";
        }

        use();
        actor.heal(1);

        Location location = map.locationOf(actor);

        if (location.getItems().contains(this)) {
            location.removeItem(this);
        }

        return actor + " drinks from flask, which heals them by 1 point of health";
    }

    /**
     * Method for adding the actions that can be performed on the flask
     * depending on whether the flask is empty or not
     *
     * @param owner the actor that owns the item
     * @param map the map where the actor is performing the action on
     * @return a list of the actions that can be performed on the flask
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map){
        ActionList actions = new ActionList();
        if (hasUses()){
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }
}
