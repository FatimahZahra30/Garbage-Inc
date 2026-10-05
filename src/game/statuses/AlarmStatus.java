package game.statuses;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.capabilities.Status;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class for the alarm status that performs the activation of the alarm
 * and handles the effect of the trigger for a particular
 * number of game rounds
 *
 * @author Fatimah Zahra
 */
public class AlarmStatus implements Status {
    private boolean active;
    private int doorLockedTurns;
    private int activeTurns;

    private static final int LOCK_TURNS = 3;
    private static final int ACTIVE_DURATION = 15;

    /**
     * Constructor for the alarm status
     */
    public AlarmStatus(){
        this.active = false;
        this.doorLockedTurns = 0;
        this.activeTurns = 0;
    }

    /**
     * Method that sounds the alarm depending on
     * whether the trigger trap tile is stepped on
     *
     * @return a string message that tells that the alarm is on
     */
    public String activate(){
        this.active = true;
        this.doorLockedTurns = LOCK_TURNS;
        this.activeTurns = ACTIVE_DURATION;
        return "Alarm has been triggered!";
    }

    /**
     * A tracker that takes not of the number of game rounds
     * the effect has been applied for
     *
     * @param entity the entity this status is attached to
     * @param location the location on the map
     */
    @Override
    public void tickStatus(GameEntity entity, Location location){
        if (activeTurns > 0){
            activeTurns--;
        }

        if (doorLockedTurns > 0){
            doorLockedTurns--;
        }

        if (activeTurns == 0){
            active = false;
        }
    }

    /**
     * A method which indicates whether the alarm is still
     * active or whether it has deactivated
     *
     * @return true if active and false otherwise
     */
    @Override
    public boolean isStatusActive(){
        return active;
    }

    /**
     * Method that informs whether the door is locked based
     * on how many turns have passed by since the alarm was
     * triggered
     *
     * @return true if 3 turns aren't over and false otherwise
     */
    public boolean isDoorLocked(){
        return doorLockedTurns > 0;
    }

}
