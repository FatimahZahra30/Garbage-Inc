package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.EclipseNebula;
import game.actions.UnlockAction;
import game.enums.GroundAbility;
import game.enums.ItemCapability;
import game.interfaces.Unlockable;
import game.statuses.AlarmStatus;

/**
 * Its primary purpose in the universe is to halt the progress of underpaid
 * {@code ContractedWorker}s until they can produce the correct rectangular
 * piece of plastic.
 *
 * @author Fatimah Zahra
 */
public class Door extends Ground implements Unlockable {
    private boolean isUnlocked = false;
    private GameMap map;

    /**
     * Constructor for the ground of type door
     */
    public Door() {
        super('=', "Door");
        enableAbility(GroundAbility.FLAMMABLE);
        enableAbility(GroundAbility.UNLOCKABLE);
    }

    /**
     * Method that returns a boolean value of
     * the result of attempting to unlock the door
     */
    public void unlock(){
        isUnlocked = true;
    }

    /**
     * Gets the location of the map every game turn
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        this.map = location.map();
    }

    /**
     * Allows the actor perform the unlocking aciton on the item
     *
     * @param actor - the worker that unlocks the item
     * @return a string message informing who unlocked the door
     */
    @Override
    public String unlockedBy(Actor actor){
        unlock();
        return actor + " unlocked " + this;
    }

    /**
     * if the door is unlocked, any actor can step into the door
     *
     * @param actor the Actor to check
     * @return true if the door is unlocked, false otherwise.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        AlarmStatus trigger = EclipseNebula.getAlarmStatus(map);

        if (trigger.isDoorLocked()) {
            return false;
        }

        return isUnlocked;
    }

    /**
     * Method that adds a list of items that can be performed on the door
     * based on certain conditions
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a list of all the actions that can be performed on the door
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        for (Item item : actor.getInventory().getItems()) {
            if (item.hasAbility(ItemCapability.CAN_UNLOCK)){
                AlarmStatus trigger = EclipseNebula.getAlarmStatus(location.map());
                if (!isUnlocked && !trigger.isDoorLocked()) {
                    actions.add(new UnlockAction(this));
                }
            }
        }
        return actions;
    }
}
