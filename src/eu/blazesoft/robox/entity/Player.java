package eu.blazesoft.robox.entity;

import static org.lwjgl.opengl.GL11.*;
import eu.blazesoft.robox.Game;
import eu.blazesoft.robox.World;

public class Player extends Mob {
	
	private Game game;

	public Player(int x, int y, Game game) {
		super(x, y);
		this.game = game;
	}

	public void update(long delta) {
		x += dx * delta * World.SCALE;
		y += dy * delta * World.SCALE;
	}
	
	public void moveX(float dx) {
		this.dx = dx;
	}
	
	public void moveY(float dy) {
		this.dy = dy;
	}
	
	public void render() {
		glPushMatrix();
		glColor3f(1f, 0f, 0f);
        glTranslatef(game.getScreenCenterX() + x, game.getScreenCenterY() + y, 0);
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
