package violin.emulator;

import sofia.graphics.ShapeView;

// -------------------------------------------------------------------------
/**
 * Tests the ViolinEmulatorScreen
 *
 * @author Joe
 * @version 2012.11.15
 */
public class ViolinScreenTest
    extends student.AndroidTestCase<ViolinEmulatorScreen>
{
    /**
     * Create a new ViolinScreenTest object.
     */
    public ViolinScreenTest()
    {
        super(ViolinEmulatorScreen.class);
    }

    // ~ Fields ...............................................................

    private ShapeView    shapeView;
    private float        cellSize;
    private GuitarPlayer player;


    // ~ Methods ..............................................................

    public void setUp()
    {
        this.cellSize = getScreen().getWidth() / 4.0f;
        this.shapeView = getScreen().getShapeView();
        this.player = getScreen().getGuitarPlayer();
    }


    // ----------------------------------------------------------
    /**
     * Tests the base notes.
     */
    public void testBaseNotes()
    {
        touchDownCell(0,0);

    }
    // ~ Private methods .......................................................

    // ----------------------------------------------------------
    /**
     * Simulates touching down on the middle of the specified cell in the maze.
     */
    private void touchDownCell(int x, int y)
    {
        touchDown(shapeView, (x + 0.5f) * this.cellSize, (y + 0.5f)
            * this.cellSize);
    }


    // ----------------------------------------------------------
    /**
     * Simulates moving the finger instantaneously to the middle of the
     * specified cell in the maze.
     */
    private void touchMoveCell(int x, int y)
    {
        touchMove((x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }

    private void strum(int x, int y)
    {
       touchDown(shapeView, x, y);
       touchMoveCell(x, y);
       touchUp();
    }

    public void testOnTouchMove()
    {

    }
}
