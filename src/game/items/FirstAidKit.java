package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorStatistics;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.statistics.StatisticOperations;
import game.actions.ConsumeAction;
import game.enums.ItemCapability;
import game.interfaces.Consumable;

/**
 * A class used to increase the worker's health by 1,
 * whilst allowing their health to be fully restored.
 * There is a cool down period between uses.
 * @author Fatimah Zahra
 */
public class FirstAidKit extends WeightedItem implements Consumable {
    private int cooldown = 0;
    private static final int MAX_COOLDOWN = 20;

    /**
     * Constructor for the first aid kit
     */
    public FirstAidKit(){
        super("First Aid Kit", '+', 25);
        makePortable();
        enableAbility(ItemCapability.CAN_HEAL);
    }

    /**
     * Method for the string representation of the action
     * of using the first aid kit and heals the actor that uses it
     *
     * @param actor - the worker that consumes the item
     * @param map the game map that the actor is in
     * @return a string representation of the outcome
     */
    @Override
    public String consumedBy(Actor actor, GameMap map){
        if (!canUse()) {
            return "First aid kit is on cooldown";
        }

        actor.modifyStatisticMaximum(ActorStatistics.HEALTH, StatisticOperations.INCREASE, 1);

        int maxHealth = actor.getMaximumStatistic(ActorStatistics.HEALTH);
        actor.modifyStatistic(ActorStatistics.HEALTH, StatisticOperations.UPDATE, maxHealth);

        startCooldown();

        Location location = map.locationOf(actor);

        if (location.getItems().contains(this)) {
            location.removeItem(this);
        }

        return actor + " uses First Aid Kit";
    }

    /**
     * Cooldown timer for the first aid kit where in, it can't be used
     * for 20 rounds after being used
     *
     * @param location The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location location, Actor actor){
        if (cooldown > 0){
            cooldown--;
        }
    }

    /**
     * Method to tell if the first aid kit can be used again
     * which is when the cooldown is no longer above 0
     *
     * @return true if it can be used and false otherwise
     */
    public boolean canUse(){
        return cooldown == 0;
    }

    /**
     * Method to reset the cooldown back to start from 20
     */
    public void startCooldown(){
        cooldown = MAX_COOLDOWN;
    }

    /**
     * Method for the actions that can be performed on the first aid kit
     * depending on whether the cooldown is over
     *
     * @param owner the actor that owns the item
     * @param map the map where the actor is performing the action on
     * @return a list of the actions that can be performed on the kit
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map){
        ActionList actions = new ActionList();
        if (canUse()){
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }
}
