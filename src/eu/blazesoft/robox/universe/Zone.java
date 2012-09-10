package eu.blazesoft.robox.universe;

import java.util.Random;

import org.lwjgl.Sys;

import eu.blazesoft.robox.entity.Entity;

import static org.lwjgl.opengl.GL11.*;

public class Zone {
	
	private float width;
	private float height;
	
	private Random random;
	private long zoneSeed ;
	
	
	
	public Zone() {
		zoneSeed = Sys.getTime();
		random = new Random(zoneSeed);
		// generate ground
		
		
		
		
	}
	
	public void update(long delta) {
		
	}
	
	public void render() {
		glPushMatrix();
		glRectf(10, 10, 50, 50);
		glPopMatrix();
	}

}
