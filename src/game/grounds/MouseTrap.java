package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.statistics.BaseStatistic;
import game.EclipseNebula;
import game.enums.ActorType;
import game.enums.GroundAbility;
import game.enums.ItemStatistics;
import game.statuses.AlarmStatus;

/**
 * Class that triggers the item if a worker stands on it
 *
 * @author Fatimah Zahra
 */
public class MouseTrap extends Ground {

    /**
     * Constructor for the mousetrap
     */
    public MouseTrap(){
        super('%', "Mouse Trap");
        addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(2));
        enableAbility(GroundAbility.FLAMMABLE);
    }

    /**
     * Method that triggers the alarm if the actor
     * on the tile is an actor
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        AlarmStatus alarm = EclipseNebula.getAlarmStatus(location.map());

        if (alarm.isStatusActive()) {
            alarm.tickStatus(null, location);
        }

        if (location.containsAnActor()){
            Actor actor = location.getActor();
            if (actor.hasAbility(ActorType.WORKER)){
                AlarmStatus trigger = EclipseNebula.getAlarmStatus(location.map());
                System.out.println(trigger.activate());
            }
        }
    }
}
