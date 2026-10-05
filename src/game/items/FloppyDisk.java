package game.items;

/**
 * Class for the item floppy disk that lies on the map
 *
 * @author Fatimah Zahra
 */
public class FloppyDisk extends WeightedItem {

    /**
     * Constructor for the floppy disk
     */
    public FloppyDisk() {
        super("Floppy Disk", '⊟', 1);
        makePortable();
    }
}
