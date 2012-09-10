package eu.blazesoft.robox.universe.tile;

public class Tile {

	public static Tile[] tiles = new Tile[128];
	
	public static Tile dirt = new DirtTile(0);
	public static Tile rock = new RockTile(1);
	public static Tile water = new WaterTile(2);
	
	public final byte id;
	
	public Tile(int id) {
		this.id = (byte) id;
		if (tiles[id] != null) throw new RuntimeException("Duplicate tile ids!");
		tiles[id] = this;
	}
	
	public void render(int x, int y) {	
	}
	
	public void update() {
		
	}
	
}
