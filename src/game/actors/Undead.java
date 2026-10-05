package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.EclipseNebula;
import game.actions.AttackAction;
import game.behaviours.FollowBehaviour;
import game.statuses.AlarmStatus;
import game.weapons.BareFist;
import game.behaviours.WanderBehaviour;
import game.enums.ActorType;
import game.inventories.BasicInventory;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that represents undead hostile characters that
 * attack workers and wander around the map
 *
 * @author Fatimah Zahra
 */
public class Undead extends Actor {
    private WanderBehaviour wanderBehaviour = new WanderBehaviour();
    private FollowBehaviour followBehaviour = new FollowBehaviour();
    private Random random = new Random();

    /**
     * Constructor to create the particular undead actor
     */
    public Undead(){
        super("Undead", 'Ѫ', 15, new BasicInventory());
        this.setIntrinsicWeapon(new BareFist());
    }

    /**
     * The actions occurred by the undead actor each game round
     *
     * @param actions collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do
     * interesting things in conjunction with Action.getNextAction()
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the action performed by the undead the specific round
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){

        Location location = map.locationOf(this);

        ArrayList<Actor> possibleTargets = new ArrayList<>();
        ArrayList<Exit> exitsForTargets = new ArrayList<>();

        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor target = destination.getActor();

                if (target.hasAbility(ActorType.WORKER)) {
                    possibleTargets.add(target);
                    exitsForTargets.add(exit);
                }
            }
        }

        // chatgpt helped suggest the idea of checking if there are
        // multiple actors in the same vicinity
        if (!possibleTargets.isEmpty()) {
            int index = random.nextInt(possibleTargets.size());
            Actor chosenTarget = possibleTargets.get(index);
            Exit chosenExit = exitsForTargets.get(index);

            return new AttackAction(chosenTarget, chosenExit.getName());
        }

        AlarmStatus trigger = EclipseNebula.getAlarmStatus(location.map());

        if (trigger.isStatusActive()){
            Action action = followBehaviour.operate(this, location);

            if (action != null) {
                return action;
            }

        } else {
            Action action = wanderBehaviour.operate(this, location);
            if (action != null) {
                return action;
            }
        }

        return new DoNothingAction();
    }
}