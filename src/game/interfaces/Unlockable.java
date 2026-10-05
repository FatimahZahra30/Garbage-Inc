package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.enums.ItemCapability;

/**
 * Interface class for unlockable items
 * @author Fatimah Zahra
 */
public interface Unlockable {

    /**
     * Method for actor to unlock the item
     * @param actor - the worker that unlocks the item
     * @return description of the results after unlocking the item
     */
    public String unlockedBy(Actor actor);
}