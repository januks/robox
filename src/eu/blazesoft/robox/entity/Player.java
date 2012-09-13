package eu.blazesoft.robox.entity;

import static org.lwjgl.opengl.GL11.*;
import eu.blazesoft.robox.universe.Planet;
import eu.blazesoft.robox.World;

public class Player extends Mob {
	
	private Planet planet;

	public Player(Planet planet) {
		this.planet = planet;
	}
	
	// set player starting position
	public void setSpawnPos(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void currLoc() {
		System.out.println(x + ": "+ y);
	}
	
	public void update(long delta) {
		x += dx * delta * World.SCALE;
		y += dy * delta * World.SCALE;
	}
	
	public void setDx(float dx) {
		this.dx = dx;
	}
	
	public void setDy(float dy) {
		this.dy = dy;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public void render() {
		glPushMatrix();
			glColor3f(1f, 0f, 0f);
	        glTranslatef(planet.getScreenCenterX(), planet.getScreenCenterY(), 0);
	        glBegin(GL_QUADS);
	        //glTexCoord2f(0, 0);
	        glVertex2f(-16*World.SCALE, -16*World.SCALE);
	        //glTexCoord2f(1, 0);
	        glVertex2f(16*World.SCALE, -16*World.SCALE);
	        //glTexCoord2f(1, 1);
	        glVertex2f(16*World.SCALE, 16*World.SCALE);
	        //glTexCoord2f(0, 1);
	        glVertex2f(-16*World.SCALE, 16*World.SCALE);
	        glEnd();
	    glPopMatrix();		
	}
}
