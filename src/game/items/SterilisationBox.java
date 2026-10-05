package game.items;

import game.enums.ItemCapability;

/**
 * Class for the item sterilisation box which determines whether
 * a lot of the items are poisonous or safe to consume depending on
 * whether this is available in the actor's inventory
 *
 * @author Fatimah Zahra
 */
public class SterilisationBox extends WeightedItem{

    /**
     * Constructor for the sterilisation box
     */
    public SterilisationBox(){
        super("Sterilisation Box", '▣', 7);
        makePortable();
        enableAbility(ItemCapability.CAN_STERILISE);
    }
}
