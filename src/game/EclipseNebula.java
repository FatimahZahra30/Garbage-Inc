package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Inventory;
import edu.monash.fit2099.engine.positions.DefaultGroundCreator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.ContractedWorker;
import game.grounds.*;
import game.inventories.WeightLimitedInventory;
import game.items.*;
import game.statuses.AlarmStatus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles the miracle of creation, translating a bunch of periods
 * and hashtags into a sprawling, functional sci-fi facility.
 */
public class EclipseNebula extends World {
    private static Map<GameMap, AlarmStatus> alarmByMap = new HashMap<>();

    public static AlarmStatus getAlarmStatus(GameMap map){
        // chatgpt helped ensure no null pointer errors
        return alarmByMap.computeIfAbsent(map, m -> new AlarmStatus());
    }

    public EclipseNebula(Display display) {
        super(display);
    }

    /**
     * Initialise maps, actors, items, and grounds of the game world.
     * @throws Exception in case if anything goes wrong...
     */
    public void initialise() throws Exception {
        DefaultGroundCreator groundCreator = new DefaultGroundCreator();
        groundCreator.registerGround('.', Dirt::new);
        groundCreator.registerGround('#', Wall::new);
        groundCreator.registerGround('~', Puddle::new);
        groundCreator.registerGround('_', Floor::new);
        groundCreator.registerGround('=', Door::new);
        groundCreator.registerGround('o', Hole::new);
        groundCreator.registerGround('%', MouseTrap::new);

        List<String> moon99Deprecated = Arrays.asList(
                "....................########################################",
                "...#######..........#__________________#___%____________o__#",
                "...#_____#..........=__________________=___________________#",
                "...#_____=...~......#__________________#___________________#",
                "...#_____#..~~~.....########=#####=#####___#############___#",
                "...#######.~~~~.....#______#_#_________#___#___________#___#",
                ".........~~~~.......#______#_#_________#####___________#####",
                "....................#______=_#_________#___________________#",
                "......~.............#______#_#_________#___________________#",
                ".....~~~............#______#_###########___#############___#",
                ".....~..............#______#___________#___#___________#___#",
                "....................=______#___________=___=_____o_____=___#",
                "...%................#______#############___#############___#",
                ".........~~~~.......#______#___________#####################",
                "........~~~~~~......#______#___________=___________________#",
                ".........~~~~.......#______#___________#___________________#",
                "....................#______#############___#############___#",
                "....................#______#_____o_____#___#___________#_o_#",
                "..~...............%.#______=___________=___=___________=___#",
                "....................########################################"
        );

        GameMap moon99DeprecatedMap = new GameMap("99-Deprecated", groundCreator, moon99Deprecated);
        this.addGameMap(moon99DeprecatedMap);
        alarmByMap.put(moon99DeprecatedMap, new AlarmStatus());

        moon99DeprecatedMap.at(7, 2).addItem(new AccessCard());
        moon99DeprecatedMap.at(23, 5).addItem(new Lantern());
        moon99DeprecatedMap.at(10, 4).addItem(new FirstAidKit());
        moon99DeprecatedMap.at(8, 3).addItem(new SterilisationBox());
        moon99DeprecatedMap.at(25, 15).addItem(new Apple());
        moon99DeprecatedMap.at(28, 11).addItem(new CookieBox());
        moon99DeprecatedMap.at(30, 1).addItem(new FloppyDisk());
        moon99DeprecatedMap.at(41, 7).addItem(new CRTMonitor());
        moon99DeprecatedMap.at(16, 8).addItem(new Lantern());

        Inventory inventory1 = new WeightLimitedInventory(50);
        Inventory inventory2 = new WeightLimitedInventory(50);
        Inventory inventory3 = new WeightLimitedInventory(50);
        Inventory inventory4 = new WeightLimitedInventory(50);
        Inventory inventory5 = new WeightLimitedInventory(50);

        inventory1.add(new Flask());
        inventory2.add(new Flask());
        inventory3.add(new Flask());
        inventory4.add(new Flask());
        inventory5.add(new Flask());
        // BEHOLD, LOCAL MULTIPLAYER!!!
        ContractedWorker contractedWorker1 = new ContractedWorker("#1 Bob", 'ඞ', 10, inventory1);
        ContractedWorker contractedWorker2 = new ContractedWorker("#2 Tom", 'ඞ', 10, inventory2);
        ContractedWorker contractedWorker3 = new ContractedWorker("#3 Sarah", 'ඞ', 10, inventory3);
        ContractedWorker contractedWorker4 = new ContractedWorker("#4 Julie", 'ඞ', 10, inventory4);
        ContractedWorker contractedWorker5 = new ContractedWorker("#5 Rick", 'ඞ', 10, inventory5);
        this.addPlayer(contractedWorker1, moon99DeprecatedMap.at(6, 2));
        this.addPlayer(contractedWorker2, moon99DeprecatedMap.at(7, 2));
        this.addPlayer(contractedWorker3, moon99DeprecatedMap.at(8, 2));
        this.addPlayer(contractedWorker4, moon99DeprecatedMap.at(6, 4));
        this.addPlayer(contractedWorker5, moon99DeprecatedMap.at(8, 4));
    }
}
