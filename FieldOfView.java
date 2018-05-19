package game;

public class FieldOfView {
	private World world;
	private int depth;
	
	private boolean[][] visible;
	
	public boolean isVisible(int x, int y, int z){
		return z == depth && x >= 0 && y >= 0 && x < visible.length && y < visible[0].length && visible[x][y];
	}
	
	private Tile[][][] tiles;
	public Tile tile(int x, int y, int z){
		return tiles[x][y][z];
	}
	
	public FieldOfView(World world){
		this.world = world;
		this.visible = new boolean[world.width()][world.height()];
		this.tiles = new Tile[world.width()][world.height()][world.depth()];
		
		for (int x = 0; x < world.width(); x++){
			for (int y = 0; y < world.height(); y++){
				for (int z = 0; z < world.depth(); z++){
					tiles[x][y][z] = Tile.UNKNOWN;
				}
			}
		}
	}
	
	public void update(int wx, int wy, int wz, int r){
		depth = wz;
		visible = new boolean[world.width()][world.height()];
		
		for (int x = -r; x < r; x++){
			for (int y = -r; y < r; y++){
				
				if (isPointNotInRadius(x,y,r) || isPointNotInWorld(x,wx,y,wy))
					continue;
				
				for (Point p : new Line(wx, wy, wx + x, wy + y)){
					Tile tile = world.tile(p.x, p.y, wz);
					visible[p.x][p.y] = true;
					tiles[p.x][p.y][wz] = tile; 
					
					if (!tile.isGround())
						break;
				}
			}
		}
	}
	
	/**These could be static one day since they can be pretty much used anywhere.  
	 * Maybe add them to some library with essential functions*/
	
	//Is this point not inside a circle with radius r starting from the origin
	public boolean isPointNotInRadius(int x, int y, int r)
	{
		return x*x + y*y > r*r ? true : false;
	}
	
	/**Is this point not inside the bounds of the game world?
	 * @param x - X coordinate of point to be checked
	 * @param wx - X coordinate of world point reference
	 * @param y - Y coordinate of point to be checked
	 * @param wy - Y coordinate of world point reference
	 */
	public boolean isPointNotInWorld(int x, int wx, int y, int wy)
	{
		return wx + x < 0 || wx + x >= world.width() || wy + y < 0 || wy + y >= world.height() ? true : false;
	}
}
