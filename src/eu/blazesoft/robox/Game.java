package eu.blazesoft.robox;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import eu.blazesoft.robox.entity.Player;
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
	private Player player;

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
		zone = new Zone(this);
		player = new Player(0,0, this);
	}
	
	// function for handling input
	private void input() {
		// player input handling
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) player.moveX(-0.2f);
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) player.moveX(0.2f);
		if (!(Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_D))) {
			player.moveX(0);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) player.moveY(0.2f);
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) player.moveY(-0.2f);
		if (!(Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_S))) {
			player.moveY(0);
		}
		while (Keyboard.next()) {		
            if (Keyboard.getEventKey() == Keyboard.KEY_C && Keyboard.getEventKeyState()) {
                zone.generate();
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_ADD && Keyboard.getEventKeyState()) {
            	World.SCALE += 0.2;
            	if (World.SCALE > 1)
            		World.SCALE = 1;
            	// after changing scale need to load/unload zones
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_SUBTRACT && Keyboard.getEventKeyState()) {
            	World.SCALE -= 0.2;
            	if (World.SCALE < 0.1f)
            		World.SCALE = 0.1f;
            	// after changing scale need to load/unload zones
            	// full zoomout could be used as map... need to think about black portions - unexplored ones
            }
        }
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            Display.destroy();
            System.exit(0);
        }
		//System.out.println(Mouse.getX() + " / " + Mouse.getY());
	}

	// main update function
	private void update(int delta) {
		zone.update(delta);
		player.update(delta);
		updateFPS(); // update FPS Counter
	}
	
	// main render function
	private void render() {
		// Clear The Screen And The Depth Buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		zone.render();
		player.render();
	}
	
	public int getScreenCenterX() {
		return screenWidth/2;
	}
	
	public int getScreenCenterY() {
		return screenHeight/2;
	}
	
	public static void main(String[] argv) {
		Game game = new Game();
		game.start();
	}
}
