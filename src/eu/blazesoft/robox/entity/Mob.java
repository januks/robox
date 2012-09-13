package eu.blazesoft.robox.entity;

public class Mob extends Entity {
	
	protected float x;
	protected float y;
	
	public Mob() {
	}

	public void setSpawnPos(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void update(long delta) {
	}
	
	public void render() {
	}

}
