package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.behaviours.Behaviour;
import game.enums.ActorType;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 * @see edu.monash.fit2099.demo.mars.Application
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Fatimah Zahra
 *
 */
public class FollowBehaviour implements Behaviour<Actor, Action> {

    /**
     * Method to execute the following action on the actor,
     * based on the distance between the actors, wherein the closest
     * actor is chosen to be followed
     *
     * @param actor The entity performing the behaviour
     * @param location The location of the current entity
     * @return the action to perform on the actor
     */
    @Override
    public Action operate(Actor actor, Location location) {
        GameMap map = location.map();

        if (!map.contains(actor)) {
            return null;
        }

        Location here = map.locationOf(actor);

        Actor closestWorker = null;
        int minDistance = Integer.MAX_VALUE;

        // chatgpt helped get the location of all the
        // players on the map
        for (int x : map.getXRange()) {
            for (int y : map.getYRange()) {
                Location loc = map.at(x, y);

                if (map.isAnActorAt(loc)) {
                    Actor other = map.getActorAt(loc);

                    if (other.hasAbility(ActorType.WORKER)) {
                        int distance = distance(here, loc);

                        if (distance < minDistance) {
                            minDistance = distance;
                            closestWorker = other;
                        }
                    }
                }
            }
        }

        if (closestWorker == null) {
            return null;
        }

        Location targetLocation = map.locationOf(closestWorker);
        int currentDistance = distance(here, targetLocation);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, targetLocation);

                if (newDistance < currentDistance) {
                    return new MoveActorAction(destination, exit.getName());
                }
            }
        }

        return null;
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}

