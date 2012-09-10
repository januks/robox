package eu.blazesoft.robox.universe;

import java.util.Random;

import org.lwjgl.Sys;

import eu.blazesoft.robox.World;
import eu.blazesoft.robox.universe.tile.Tile;

import static org.lwjgl.opengl.GL11.*;

public class Zone {
	
	private float width;
	private float height;
	
	private Random random;
	private long zoneSeed;
	
	private Tile[][] zone = new Tile[World.ZONE_WIDTH][World.ZONE_HEIGHT];
	
	public Zone() {
		generate();
	}
	
	public void generate() {
		zoneSeed = Sys.getTime();
		random = new Random(zoneSeed);
		System.out.println("Seed: "+zoneSeed);
		// generate ground
//		for (int x = 0; x < World.ZONE_WIDTH - 1; x++) {
//            for (int y = 0; y < World.ZONE_HEIGHT - 1; y++) {
//            	int r = random.nextInt(5);
//            	if (r < 1) {
//            		zone[x][y] = Tile.water;
//            	} else if (r >= 1 && r < 2) {
//            		zone[x][y] = Tile.rock;
//            	} else {
//            		zone[x][y] = Tile.dirt;
//            	}
//            }
//        }	
		
		// testing things
		ZoneGenerator zg = new ZoneGenerator(zoneSeed);
		zg.generateMap();
		zone = zg.getZone();
		//zg.printTileValues();
	}
	
	public void update(long delta) {
		// zone update things
	}
	
	public void render() {	
		for (int x = 0; x < World.ZONE_WIDTH; x++) {
            for (int y = 0; y < World.ZONE_HEIGHT; y++) {
                zone[x][y].render(x,y);
            }
        }
	}

}

	