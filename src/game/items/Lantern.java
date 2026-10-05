package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.GroundAbility;
import game.grounds.Fire;

/**
 * Class for the item lantern that could leak and cause
 * the ground beneath it to catch on fire and burns actors
 * that stand there for several rounds
 *
 * @author Fatimah Zahra
 */
public class Lantern extends WeightedItem {
    private int oilFuel;
    private double chance;

    /**
     * Constructor for the lantern item
     */
    public Lantern(){
        super("Lantern", '&', 7);
        makePortable();
        oilFuel = 10;
    }

    /**
     * Method such that for each round the actor still carries the latnern,
     * there is a 5% chance of oil leakage that results on the ground catching
     * on fire for 5 consecutive rounds per tile
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor){
        chance = 0.05;
        if (currentLocation.getGround().hasAbility(GroundAbility.FLAMMABLE)){
            if (Math.random() < chance){
                new Display().println("Oil has leaked from the lantern");
                oilFuel--;
                Ground old = currentLocation.getGround();
                currentLocation.setGround(new Fire(old));
            }
        }
    }
}
