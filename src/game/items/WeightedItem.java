package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.statistics.BaseStatistic;
import game.enums.ItemStatistics;

/**
 * Abstract class for items that have weight.
 * Ensures all weighted items initialise their weight correctly
 * and prevents invalid weights
 * Chatgpt helped suggest the creation of this class
 * to reduce duplication of weight creation
 *
 * @author Fatimah Zahra
 */
public abstract class WeightedItem extends Item {

    /**
     * Constructor for weighted items
     *
     * @param name the name of the item
     * @param displayChar the character representing the item
     * @param weight the weight of the item that is non-negative
     */
    public WeightedItem(String name, char displayChar, int weight) {
        super(name, displayChar);

        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }

        addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(weight));
    }
}
