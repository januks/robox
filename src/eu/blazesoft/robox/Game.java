package eu.blazesoft.robox;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import eu.blazesoft.robox.universe.Zone;
import static org.lwjgl.opengl.GL11.*;

public class Game {

	// frame calculation stuff, prolly should be elsewhere
	private long lastFrame;
	private int fps;
	private long lastFPS;
	
	private String gameTitle = "Robox";
	
    // screen size, will rework that later
	private int	screenWidth = 800;
	private int	screenHeight = 600;
	
	// Universe variable - contains everything
	private Zone zone;

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		init(); // init OpenGL
		init_objects(); // init ingame objects
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer

		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			
			update(delta);
			render();
			input();

			Display.update();
			Display.sync(60); // cap fps to 60fps
		}
		Display.destroy();
	}
	
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	    return delta;
	}
	
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle(gameTitle + " [FPS: " + fps+"]");
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	private void init() {
		// grab the mouse
		//Mouse.setGrabbed(true);
		//Mouse.setNativeCursor(Cursor);
		// enable textures since we're going to use these for our sprites
		glEnable(GL_TEXTURE_2D);
		// disable the OpenGL depth test since we're rendering 2D graphics
		glDisable(GL_DEPTH_TEST);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, screenWidth, 0, screenHeight, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glViewport(0, 0, screenWidth, screenHeight);
		//
	}
	
	private void init_objects() {
		zone = new Zone();
	}
	
	// function for handling input
	private void input() {
		
		//System.out.println(Mouse.getX() + " / " + Mouse.getY());
	}

	// main update function
	private void update(int delta) {
		zone.update(delta);
		updateFPS(); // update FPS Counter
	}
	
	// main render function
	private void render() {
		// Clear The Screen And The Depth Buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		zone.render();
		// R,G,B,A Set The Color To Blue One Time Only
		//glColor3f(0.5f, 0.5f, 1.0f);

		// draw quad
		//glPushMatrix();
			// translating current point to x,y
			//glTranslatef(x, y, 0);	
			// then rotating
			//glRotatef(rotation, 0, 0, 0.1f);
			// then translating point back to prev
			/**
			 lTranslatef(x, y, 0);	

			glScalef(scale, scale, 0);
			glTranslatef(-x, -y, 0);
			
			System.out.println(scale);
			glBegin(GL_QUADS);
				glVertex2f(x - 50, y - 50);
				glVertex2f(x + 50, y - 50);
				glVertex2f(x + 50, y + 50);
				glVertex2f(x - 50, y + 50);
			glEnd();
			*/
		//glPopMatrix();
	}
	
	public static void main(String[] argv) {
		Game game = new Game();
		game.start();
	}
}
