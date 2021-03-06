package eu.blazesoft.robox.universe.tile;

import static org.lwjgl.opengl.GL11.*;
import eu.blazesoft.robox.World;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(id);
	}

	public void render(float x, float y) {
		glPushMatrix();
			glColor3f(0.75f, 0.75f, 0.75f);
	        glTranslatef(x, y, 0);
	        glBegin(GL_QUADS);
	        //glTexCoord2f(0, 0);
	        glVertex2f(-(World.TILE_SIZE*World.SCALE/2), -(World.TILE_SIZE*World.SCALE/2));
	        //glTexCoord2f(1, 0);
	        glVertex2f(World.TILE_SIZE*World.SCALE/2, -(World.TILE_SIZE*World.SCALE/2));
	        //glTexCoord2f(1, 1);
	        glVertex2f(World.TILE_SIZE*World.SCALE/2, World.TILE_SIZE*World.SCALE/2);
	        //glTexCoord2f(0, 1);
	        glVertex2f(-(World.TILE_SIZE*World.SCALE/2), World.TILE_SIZE*World.SCALE/2);
	        glEnd();
		glPopMatrix();
	}
	
	public void update() {
		
	}
	
}
