package eu.blazesoft.robox.universe;

import java.util.ArrayList;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

import eu.blazesoft.robox.entity.Entity;
import eu.blazesoft.robox.entity.Player;

public class Planet {

	private String name;
	private long seed;
	private ArrayList<Zone> zones = new ArrayList<Zone>();
	
	private Player player;
	
	public Planet() {
		// create or load existing planet
		// loading means if there exists save - will do it later
		
		// it's new planet
		
		// generating new planet
		this.seed = Sys.getTime(); // set seed, potentially you can set Your own seed
		player = new Player(this);
		player.setSpawnPos(0, 0);
		
		
		
		
		// it's saved planet
	}
	
	public void setPlayerDx(float dx) {
		player.setDx(dx);
	}
	
	public void setPlayerDy(float dy) {
		player.setDy(dy);
	}
	
	public int getScreenCenterX() {
		return Display.getWidth()/2;
	}
	
	public int getScreenCenterY() {
		return Display.getHeight()/2;
	}
	
	public void update(long delta) {
		player.update(delta);
		player.currLoc();
	}
	
	public void render() {
		player.render();
	}
}
