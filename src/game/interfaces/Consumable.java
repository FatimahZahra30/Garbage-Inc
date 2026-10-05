package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.ItemCapability;

/**
 * Interface class for consumable items
 * @author Fatimah Zahra
 */
public interface Consumable {

    /**
     * Method for actor to consume the item
     *
     * @param actor - the worker that consumes the item
     * @return description of the results after consuming the item
     */
    public String consumedBy(Actor actor, GameMap map);

}