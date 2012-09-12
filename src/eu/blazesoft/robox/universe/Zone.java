package eu.blazesoft.robox.universe;

import java.util.Random;

import org.lwjgl.Sys;

import eu.blazesoft.robox.Game;
import eu.blazesoft.robox.World;
import eu.blazesoft.robox.universe.tile.Tile;

import static org.lwjgl.opengl.GL11.*;

public class Zone {
	
	private float width;
	private float height;
	
	private Random random;
	private long zoneSeed;
		
	private Game game;
	
	private Tile[][] zone = new Tile[World.ZONE_WIDTH][World.ZONE_HEIGHT];
	
	public Zone(Game game) {
		this.game = game;
		generate();
	}
	
	public void generate() {
		zoneSeed = Sys.getTime();
		random = new Random(zoneSeed);
		System.out.println("Seed: "+zoneSeed);
		
		// testing things
		ZoneGenerator zg = new ZoneGenerator(zoneSeed);
		//zg.generateMap();
		zone = zg.getZone();
		//zg.printTileValues();
	}
	
	public void update(long delta) {
		// zone update things
	}
	
	public void render() {	
		for (int x = 0; x < World.ZONE_WIDTH; x++) {
            for (int y = 0; y < World.ZONE_HEIGHT; y++) {
                zone[x][y].render(x*World.TILE_SIZE*World.SCALE+game.getScreenCenterX(),y*World.TILE_SIZE*World.SCALE + game.getScreenCenterY());
            }
        }
	}

}

	