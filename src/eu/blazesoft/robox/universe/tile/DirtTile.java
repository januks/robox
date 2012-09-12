package eu.blazesoft.robox.universe.tile;

import static org.lwjgl.opengl.GL11.*;
import eu.blazesoft.robox.World;

public class DirtTile extends Tile {

	public DirtTile(int id) {
		super(id);
	}

	public void render(float x, float y) {
		glPushMatrix();
			glColor3f(0.7f, 0.2f, 0.2f);
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
