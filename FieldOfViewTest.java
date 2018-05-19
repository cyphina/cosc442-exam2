package game;

import org.junit.*;

import ui.PlayScreen;

import static org.junit.Assert.*;

/**
 * The class <code>FieldOfViewTest</code> contains tests for the class <code>{@link FieldOfView}</code>.
 *
 * @generatedBy CodePro at 5/19/18 10:39 AM
 * @author Cyphina
 * @version $Revision: 1.0 $
 */
public class FieldOfViewTest {
	
	FieldOfView fov;
	World world;
	
	@Before
	public void setup() throws Exception {
		World world = new World(new Tile[50][50][3]);
		fov = new FieldOfView(world);
	}
	
	/**
	 * Run the FieldOfView(World) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/19/18 10:39 AM
	 */
	@Test
	public void testFOVConstructor()
		throws Exception {
		assertEquals(fov.tile(5, 5, 2), Tile.UNKNOWN);
	}

	/**
	 * Run the boolean isPointInRadius(int,int,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/19/18 10:39 AM
	 */
	@Test
	public void testIsPointInRadius() {
		int x = 1;
		int y = 1;
		int r = 2;

		boolean result = fov.isPointNotInRadius(x, y, r);

		assertFalse(result);
		x = 2;
		result = fov.isPointNotInRadius(x, y, r);
		assertTrue(result);
	}

	/**
	 * Run the boolean isPointInWorld(int,int,int,int,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/19/18 10:39 AM
	 */
	@Test
	public void testIsPointInWorld()
		throws Exception {

		int x = 1;
		int wx = 1;
		int y = 1;
		int wy = 1;
		int r = 1;

		boolean result = fov.isPointNotInWorld(x, wx, y, wy);

		assertFalse(result);
		x = 100;
		result = fov.isPointNotInWorld(x, wx, y, wy);
		assertTrue(result);
	}

	/**
	 * Run the boolean isVisible(int,int,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/19/18 10:39 AM
	 *
	@Test
	public void testIsVisible()
		throws Exception {
	
		int x = 1;
		int y = 1;
		int z = 1;

		boolean result = fov.isVisible(x, y, z);
		assertTrue(result);
		z = 2;
		result = fov.isVisible(x, y, z);
		assertFalse(result);
	}
	*/
	
	/**
	 * Test tile accessor
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/19/18 10:39 AM
	 */
	@Test
	public void testTile()
		throws Exception {

		int x = 1;
		int y = 1;
		int z = 1;

		Tile result = fov.tile(x, y, z);
		
		assertNotNull(result);
	}

	/**
	 * Run the void update(int,int,int,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/19/18 10:39 AM
	 
	@Test
	public void testUpdate()
		throws Exception {
		fov.update(1, 1, 1, 5);
		assertTrue(fov.isVisible(1, 1, 1));
	}
*/
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(FieldOfViewTest.class);
	}
}