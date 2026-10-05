package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.enums.GroundAbility;

/**
 * While other classes get to be security doors, mysterious flasks, or highly
 * stressed {@code ContractedWorker}s, this class humbly accepts its role as
 * the thing everyone walks all over.
 *
 * @author Adrian Kristanto
 */
public class Dirt extends Ground {

    /**
     * Constructor for the ground of type dirt
     */
    public Dirt() {
        super('.', "Dirt");
        enableAbility(GroundAbility.FLAMMABLE);
    }
}
