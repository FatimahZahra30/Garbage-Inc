package game.items;

/**
 * Class for the item CRTMonitor that is present in the map
 *
 * @author Fatimah Zahra
 */
public class CRTMonitor extends WeightedItem {

    /**
     * Constructor for the crt monitor
     */
    public CRTMonitor() {
        super("CRT Monitor", '◙', 30);
        makePortable();
    }
}
