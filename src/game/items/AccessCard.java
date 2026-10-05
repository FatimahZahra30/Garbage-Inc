package game.items;

import game.enums.ItemCapability;

/**
 * A class representing a small rectangular piece of plastic that holds entirely
 * too much power over your ability to walk through doors.
 * Its primary function is to beep happily when the player has clearance, and beep
 * angrily when they don't.
 * Essential for progressing the plot,
 *
 * @author Adrian Kristanto
 * @author Fatimah Zahra
 */
public class AccessCard extends WeightedItem {

    /**
     * Constructor for the item access card which can be picked up or dropped
     * as well as allows unlocking of things
     */
    public AccessCard() {
        super("Access Card", '▤', 1);
        makePortable();
        enableAbility(ItemCapability.CAN_UNLOCK);
    }
}
