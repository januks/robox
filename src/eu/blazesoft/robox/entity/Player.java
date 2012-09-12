package eu.blazesoft.robox.entity;

import static org.lwjgl.opengl.GL11.*;

public class Player extends Mob {

	public Player(int x, int y) {
		super(x, y);
	}

	public void update(long delta) {
		x += dx * delta;
		y += dy * delta;
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
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        //glTexCoord2f(0, 0);
        glVertex2f(0, 0);
        //glTexCoord2f(1, 0);
        glVertex2f(32, 0);
        //glTexCoord2f(1, 1);
        glVertex2f(32, 32);
        //glTexCoord2f(0, 1);
        glVertex2f(0, 32);
        glEnd();
	glPopMatrix();		
	}
}
