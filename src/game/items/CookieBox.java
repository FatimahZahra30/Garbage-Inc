package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.ActorStatistics;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.statistics.StatisticOperations;
import game.actions.ConsumeAction;
import game.interfaces.Consumable;
import edu.monash.fit2099.engine.actors.Actor;

import static game.utils.ItemUtils.hasSterilisationBox;

/**
 * Class for the item cookie box that has 5 individual cookies in it
 *
 * @author Fatimah Zahra
 */
public class CookieBox extends WeightedItem implements Consumable {
    private int cookies = 5;

    /**
     * Constructor for the cookie box
     */
    public CookieBox(){
        super("Cookies", '◍', 2);
        makePortable();
    }

    /**
     * Method that tells if the cookie box still has cookies in it
     * @return true if there are still cookies and false otherwise
     */
    public boolean hasCookies(){
        return cookies > 0;
    }

    /**
     * Method to decrease the number of cookies in the box
     * each time a piece is eaten
     */
    public void eat(){
        if (cookies > 0){
            cookies--;
        }
    }

    /**
     * Method for the string representation of the action
     * of consuming the cookies based on what condition is met
     *
     * @param actor - the worker that consumes the item
     * @param map the game map that the actor is in
     * @return a string representation of the outcome
     */
    @Override
    public String consumedBy(Actor actor, GameMap map){
        if (!hasCookies()) {
            return actor + " has no more cookies";
        }

        String result;

        if (hasSterilisationBox(actor)){
            actor.heal(1);
            result = actor + " eats a purified cookie and heals HP by 1";
        } else {
            actor.modifyStatisticMaximum(ActorStatistics.HEALTH, StatisticOperations.DECREASE, 1);

            int maxHealth = actor.getMaximumStatistic(ActorStatistics.HEALTH);
            actor.modifyStatistic(ActorStatistics.HEALTH, StatisticOperations.UPDATE, maxHealth);

            result = actor + " eats a cookie and loses 1 max HP";
        }

        eat();

        if (!hasCookies()){
            actor.getInventory().remove(this);

            Location location = map.locationOf(actor);

            if (location.getItems().contains(this)) {
                location.removeItem(this);
            }

            result += " The cookie box is now empty";
        }

        return result;

    }

    /**
     * Method to add actions to be performed on the cookie depending
     * on if there are any cookies left
     *
     * @param owner the actor that owns the item
     * @param map the map where the actor is performing the action on
     * @return a list of the actions that can be performed on the cookies
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map){
        ActionList actions = new ActionList();
        if (hasCookies()){
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }
}
