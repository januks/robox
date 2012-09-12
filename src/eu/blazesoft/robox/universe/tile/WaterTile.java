package eu.blazesoft.robox.universe.tile;

import static org.lwjgl.opengl.GL11.*;
import eu.blazesoft.robox.World;

public class WaterTile extends Tile {

	public WaterTile(int id) {
		super(id);
	}
	
	public void render(int x, int y) {
		glPushMatrix();
			glColor3f(0.0f, 0.0f, 0.66f);
	        glTranslatef(x*World.TILE_SIZE*World.SCALE, y*World.TILE_SIZE*World.SCALE, 0);
	        glBegin(GL_QUADS);
	        //glTexCoord2f(0, 0);
	        glVertex2f(0, 0);
	        //glTexCoord2f(1, 0);
	        glVertex2f(World.TILE_SIZE*World.SCALE, 0);
	        //glTexCoord2f(1, 1);
	        glVertex2f(World.TILE_SIZE*World.SCALE, World.TILE_SIZE*World.SCALE);
	        //glTexCoord2f(0, 1);
	        glVertex2f(0, World.TILE_SIZE*World.SCALE);
	        glEnd();
		glPopMatrix();
	}
	
	public void update() {
		
	}
	

}
