package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.enums.GroundAbility;

/**
 * Not lava. Not spikes. Not an elaborate trap. Just a perfectly flat surface
 * whose sole responsibility is preventing the {@code ContractedWorker} from
 * plummeting into the infinite vacuum of the Eclipse Nebula.
 *
 * @author Adrian Kristanto
 */
public class Floor extends Ground {

    /**
     * Constructor for the ground of type floor
     */
    public Floor() {
        super('_', "Floor");
        enableAbility(GroundAbility.FLAMMABLE);
    }
}
