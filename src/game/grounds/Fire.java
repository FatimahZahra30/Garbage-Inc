package game.grounds;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.statuses.DamageOverTimeStatus;

/**
 * Class that represents a temporary fire on p
 * articular parts of the ground
 *
 * @author Fatimah Zahra
 */
public class Fire extends Ground {
    private int turns;
    private Ground originalGround;

    /**
     * Constructor for the fire
     *
     * @param originalGround the ground that the fire replaces
     */
    public Fire(Ground originalGround){
        super('^', "Fire");
        this.turns = 5;
        this.originalGround = originalGround;
    }

    /**
     * Each game round the fire is on for depending
     * on whether there are turns remaining
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        if (location.containsAnActor()){
            location.getActor().addStatus(new DamageOverTimeStatus(5, 1, location.getActor()));
            new Display().println(location.getActor() + " lost 1 HP from the fire");
        }

        turns--;

        if (turns <= 0){
            location.setGround(originalGround);
        }
    }
}
