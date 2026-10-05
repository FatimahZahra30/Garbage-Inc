package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.actors.Actor;
import game.enums.GroundAbility;

/**
 * A class representing a solid wall. Yes, that's it.
 *
 * @author Fatimah Zahra
 */
public class Wall extends Ground {

    /**
     * Constructor for the gorund of type wall
     */
    public Wall() {
        super('#', "Wall");
        enableAbility(GroundAbility.FLAMMABLE);
    }

    /**
     * Method of whether the actor can pass through this ground
     *
     * @param actor the Actor to check
     * @return a boolean value of whether it can be passed through
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return false;
    }
}
