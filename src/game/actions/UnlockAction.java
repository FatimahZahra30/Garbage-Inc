package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Unlockable;

/**
 * Class for the action of unlocking things in the map
 *
 * @author Fatimah Zahra
 */
public class UnlockAction extends Action {
    private Unlockable unlockable;

    /**
     * Constructor for UnlockAction
     *
     * @param unlockable
     */
    public UnlockAction(Unlockable unlockable){
        this.unlockable = unlockable;
    }

    /**
     * Method for the string representation of the
     * outcome of unlocking things
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string representation of the outcome
     */
    public String execute(Actor actor, GameMap map){
        return unlockable.unlockedBy(actor);
    }

    /**
     * Method for the string representation of menu option
     * for unlocking things
     *
     * @param actor The actor performing the action.
     * @return string representation of the menu option
     */
    public String menuDescription(Actor actor){
        return actor + " will unlock " + unlockable.getClass().getSimpleName();
    }
}
