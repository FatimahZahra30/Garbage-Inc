package game.utils;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.enums.ItemCapability;

/**
 * Utility class to check if the inventory of an actor has a sterilisation box
 *
 * @author Fatimah Zahra
 */
public class ItemUtils {

    /**
     * method to check if actor's inventory has a sterilisation box
     *
     * @param actor the actor whose inventory is checked
     * @return true if there is a sterilisation box and false other wise
     */
    public static boolean hasSterilisationBox(Actor actor) {
        if (actor == null || actor.getInventory() == null) {
            return false;
        }

        for (Item item : actor.getInventory().getItems()) {
            if (item != null && item.hasAbility(ItemCapability.CAN_STERILISE)) {
                return true;
            }
        }

        return false;
    }
}
