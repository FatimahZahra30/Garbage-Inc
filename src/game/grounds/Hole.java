package game.grounds;

import edu.monash.fit2099.engine.GameEngineException;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Slime;
import game.actors.Undead;
import game.enums.GroundAbility;

import java.util.Random;

/**
 * Class that represents a ground with a hole
 * in which creatures can spawn every 20 rounds
 *
 * @author Fatimah Zahra
 */
public class Hole extends Ground {
    private int turns = 0;
    private Random random = new Random();

    /**
     * Constructor for the hole on the ground
     */
    public Hole() {
        super('o', "Hole");
        enableAbility(GroundAbility.FLAMMABLE);
    }

    /**
     * Method that spawns a new creature on the particular hole
     * if 20 game turns have already passed
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        turns++;

        if (turns < 20){
            return;
        }

        turns = 0;

        if (!location.containsAnActor()){
            int choice = random.nextInt(2);

            Actor creature;
            if (choice == 0){
                creature = new Undead();
            } else {
                creature = new Slime();
            }

            try {
                location.addActor(creature);
            } catch (GameEngineException e) {
                e.printStackTrace();
            }
        }
    }
}
