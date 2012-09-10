package eu.blazesoft.robox.universe;

import java.util.Random;

import org.lwjgl.Sys;

import eu.blazesoft.robox.World;
import eu.blazesoft.robox.universe.tile.Tile;

public class ZoneGenerator {

	private static Random random;
	private long seed;
	private Tile[][] zone = new Tile[World.ZONE_WIDTH][World.ZONE_HEIGHT];
	private MapTile[][] map = new MapTile[World.ZONE_WIDTH][World.ZONE_HEIGHT];
	
	public ZoneGenerator() {
		this.seed = Sys.getTime();
		random = new Random(this.seed);
		initMap();
	}

	public ZoneGenerator(long seed) {
		this.seed = seed;
		random = new Random(this.seed);
		initMap();
	}
	
	private void initMap() {
		for (int x = 0; x < World.ZONE_WIDTH; x++) {
            for (int y = 0; y < World.ZONE_HEIGHT; y++) {
            	map[x][y] = new MapTile();
            }
		}
	}
	
	public long getSeed() {
		return this.seed;
	}
	
	public Tile[][] getZone() {
		// translate map into zone
		for (int x = 0; x < World.ZONE_WIDTH; x++) {
            for (int y = 0; y < World.ZONE_HEIGHT; y++) {
            	if (map[x][y].value < 1) { 
            		zone[x][y] = Tile.water;
            	} else if (map[x][y].value >= 1 && map[x][y].value <= 4 ) {
            		zone[x][y] = Tile.dirt;
            	} else {
            		zone[x][y] = Tile.rock;
            	}     
            }
		}
		return zone;
	}
	
	public void generateMap() {
		// first iteration
		for (int x = 0; x < World.ZONE_WIDTH; x++) {
            for (int y = 0; y < World.ZONE_HEIGHT; y++) {
            	double r = random.nextFloat() * 6;
            	setTileValue(x,y,r);
            }
		}
	}
	
	private void setTileValue(int x, int y, double value) {
		if (x < 0 || y < 0 || x > World.ZONE_WIDTH - 1 || y > World.ZONE_HEIGHT - 1) {
			return;
		}
		map[x][y].value += value;
//		double dval = 0.0;	
//		dval += getTileValue(x,y - 1);
//		dval += getTileValue(x - 1,y);
//		dval += getTileValue(x,y);
//		dval += getTileValue(x + 1,y);
//		dval += getTileValue(x,y + 1);
//		
//		map[x][y - 1].value = dval/5;
//		map[x - 1][y].value = dval/5;
//		map[x][y].value = dval/5;
//		map[x + 1][y].value = dval/5;
//		map[x][y + 1].value = dval/5;
	}

	private double getTileValue(int x, int y) {
		if (x < 0 || y < 0 || x > World.ZONE_WIDTH - 1 || y > World.ZONE_HEIGHT - 1) {
			return 0.0;
		}
			return map[x][y].value;
	}
	
	public void printTileValues() {
		for (int x = 0; x < World.ZONE_WIDTH; x++) {
            for (int y = 0; y < World.ZONE_HEIGHT; y++) {
            	System.out.print("["+map[x][y].value+"]");
            }
            System.out.println("");
		}	
	}
	
	/*
	 * Helper class for generating map
	 */
	public class MapTile {
		public double value = 0;
		public boolean locked = false;
		public MapTile() {
		}	
	}
}