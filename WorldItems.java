package game;

/**List of items that are on a the 3D tile array corresponding to the indices in items*/

public class WorldItems {
	private Item[][][] items;

	public Item[][][] getItems() {
		return items;
	}

	public void setItems(Item[][][] items) {
		this.items = items;
	}

	public Item item(int x, int y, int z) {
		return items[x][y][z];
	}

	public void remove(int x, int y, int z) {
		items[x][y][z] = null;
	}
}