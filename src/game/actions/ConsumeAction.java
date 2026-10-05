package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.Actor;
import game.interfaces.Consumable;

/**
 * Class representing the action of consuming consumable items
 *
 * @author Fatimah Zahra
 */
public class ConsumeAction extends Action {
    private Consumable consumable;

    /**
     * Constructor for ConsumeAction
     *
     * @param consumable
     */
    public ConsumeAction(Consumable consumable){
        this.consumable = consumable;
    }

    /**
     * Method for the string representation of the outcome
     * of a consume action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string representation of the outcome
     */
    public String execute(Actor actor, GameMap map){
        return consumable.consumedBy(actor, map);
    }

    /**
     * String representation of the menu option for consuming the
     * item
     *
     * @param actor The actor performing the action.
     * @return string representation of the menu option
     */
    public String menuDescription(Actor actor){
        return actor + " will consume " + consumable.getClass().getSimpleName();
    }
}
