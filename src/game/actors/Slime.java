package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.behaviours.WanderBehaviour;
import game.interfaces.Consumable;
import game.inventories.BasicInventory;

/**
 * Class for the actor slime which consumes items on the ground
 * and wanders around the map
 *
 * @author Fatimah Zahra
 */
public class Slime extends Actor {
    private WanderBehaviour wanderBehaviour = new WanderBehaviour();

    /**
     * Constructor for setting the specific slime
     */
    public Slime(){
        super("Slime", '⍾', 25, new BasicInventory());
    }

    /**
     * The actions that occur by the slime each game turn
     *
     * @param actions collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do
     * interesting things in conjunction with Action.getNextAction()
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the particular action the slime had performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        Location location = map.locationOf(this);
        for (Item item : location.getItems()) {
            var consumable = item.asCapability(Consumable.class);

            if (consumable.isPresent()) {
                return new ConsumeAction(consumable.get());
            }
        }

        Action action = wanderBehaviour.operate(this, location);

        if (action != null){
            return action;
        }
        return new DoNothingAction();
    }
}
