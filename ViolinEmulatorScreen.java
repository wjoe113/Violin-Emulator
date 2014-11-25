package violin.emulator;

import sofia.graphics.TextShape;
import sofia.graphics.RectangleShape;
import android.view.MotionEvent;
import sofia.graphics.Color;
import sofia.graphics.LineShape;
import android.widget.TextView;
import sofia.app.ShapeScreen;
import sofia.graphics.ImageShape;
import android.os.Bundle;
import android.content.Intent;

// -------------------------------------------------------------------------
/**
 * This class describes the interaction between the user and the separate
 * ShapeViews as well as TextFields of the ViolinEmulator.
 *
 * @author Joe
 * @author Vasily
 * @version 2012.11.12
 */
public class ViolinEmulatorScreen
    extends ShapeScreen
{

    // ~ Fields.................................................................

    private GuitarPlayer   player;

    private float          cellSizeString;
    private float          cellSizeFret;
    private float          baseNoteSize;
    private float          strumSize;

    private Note[]         baseNotes = { Note.g(3), Note.d(4), Note.a(4),
        Note.e(5)                   };
    private TextShape[]    notes;
    private RectangleShape strum;
    private LineShape[]    violinStrings;

    private Note           shiftNote;
    private Strings        stringArray;
    private Bundle          screenA; //connects open screen classes
    private Intent          screenB;


    // ~ Methods................................................................

    // ----------------------------------------------------------
    /**
     * Creates the GUI.
     */
    public void initialize()
    {
        screenB = getIntent();
        screenA = screenB.getExtras();

        this.player = new GuitarPlayer(this);
        LineShape[] frets = new LineShape[6];
        this.violinStrings = new LineShape[4];
        this.notes = new TextShape[4];

        stringArray = new Strings();
        stringArray.addObserver(this);

        float min = Math.min(getWidth(), getHeight());
        float max = Math.max(getWidth(), getHeight());
        float difference = max - min;
        this.cellSizeString = min / 4.0f;
        this.cellSizeFret = getHeight() * 0.7f / 6.0f;
        this.baseNoteSize = difference * .33f;
        this.strumSize = difference * .66f;


        ImageShape image =
            new ImageShape(
                "dark_wood_texture",
                0.0f,
                0.0f,
                this.getWidth(),
                this.getHeight());

        add(image);

        // Drawing the frets
        for (int y = 0; y < 6; y++)
        {
            float startY = y * cellSizeFret + cellSizeFret;

            LineShape fret =
                new LineShape(getWidth() * 0.125f, startY, this.getWidth() * 0.875f, startY);

            fret.setColor(Color.darkGoldenRod);
            fret.setStrokeWidth(4.0f);

            add(fret);
            frets[y] = fret;
        }
        // Drawing the strings
        for (int x = 0; x < 4; x++)
        {
            float startX = x * cellSizeString;

            LineShape string =
                new LineShape(
                    (startX + (cellSizeString / 2.0f)),
                    baseNoteSize,
                    (startX + (cellSizeString / 2.0f)),
                    this.getHeight() - this.strumSize);

            string.setColor(Color.white);
            string.setStrokeWidth(4.0f);

            add(string);
            this.violinStrings[x] = string;
// shapeView.
        }

        // Drawing the field for the base notes
        RectangleShape baseNoteView =
            new RectangleShape(0.0f, 0.0f, this.getWidth(), baseNoteSize);

        baseNoteView.setColor(Color.gray);
        baseNoteView.setFillColor(Color.gray);
        baseNoteView.setFilled(true);
        add(baseNoteView);

        // Drawing the notes
        for (int x = 0; x < 4; x++)
        {
            float startX = x * cellSizeString;

            TextShape note =
                new TextShape(
                    "" + (x + 1),
                    startX + (cellSizeString / 2.0f),
                    baseNoteSize / 2.0f);

            note.setColor(Color.white);
            note.setTypeSize(7.0f);

            add(note);
            this.notes[x] = note;

        }

        // Drawing the strum field
        this.strum =
            new RectangleShape(
                0.0f,
                this.getHeight() - strumSize,
                this.getWidth(),
                this.getHeight());

        this.strum.setColor(Color.black);
        this.strum.setFillColor(Color.black);
        this.strum.setFilled(true);

        add(this.strum);

    }


    // ----------------------------------------------------------
    /**
     * Plays the specified note once the string is touched.
     *
     * @return = the player.
     */
    public GuitarPlayer getGuitarPlayer()
    {
        return this.player;
    }


    // ----------------------------------------------------------
    /**
     * Detects if the user has touched the shapeView, but not the strum. If the
     * user is only touching the string, the string specific note will be
     * created.
     *
     * @param event
     *            = the touch event.
     */
    public void onTouchDown(MotionEvent event)
    {

        for (int i = 0; i < event.getPointerCount(); i++)
        {
            float touchPositionX = event.getX(i);
            float touchPositionY = event.getY(i);

            System.out.println("Bounds: " + this.strum.getBounds());
            System.out.println("Coords " + i + ": " + touchPositionX + ", "
                + touchPositionY);
            if (!this.strum.getBounds()
                .contains(touchPositionX, touchPositionY))
            {
                int stringNumber = (int)(touchPositionX / cellSizeString);
                int fretNumber = (int)(touchPositionY / cellSizeFret);
                System.out.println("hit it");
                this.violinStrings[stringNumber].setColor(Color.yellow);
                Note baseNote = baseNotes[stringNumber];
                this.shiftNote = baseNote.shiftBy(fretNumber);

                stringArray.setNote(stringNumber, fretNumber);

                // ignoring every finger set down after the first
                break;
            }
        }

    }


    // ----------------------------------------------------------
    /**
     * Detects if the finger has been lifted from the string. If so, return the
     * string to its original color.
     *
     * @param event
     *            The motion event
     */
    public void onTouchUp(MotionEvent event)
    {

        int actionIndex = event.getActionIndex();
        float touchPositionX = event.getX(actionIndex);
        float touchPositionY = event.getY(actionIndex);

        System.out.println("Bounds: " + this.strum.getBounds());
        System.out.println("OnTouchUp Coords " + actionIndex + ": "
            + touchPositionX + ", " + touchPositionY);
        if (!this.strum.getBounds().contains(touchPositionX, touchPositionY))
        {
            System.out.println("UpQ");
            int stringNumber = (int)(touchPositionX / cellSizeString);
            this.violinStrings[stringNumber].setColor(Color.white);
            this.shiftNote = null;
            int stringValue = (int)(touchPositionX / cellSizeString);
            stringArray.setBlankNote(stringValue);

        }

    }


    // ----------------------------------------------------------
    /**
     * Detects if the user is moving his finger on the strum. If the user is
     * only moving on the strum, the note created by onTouchDown() will be
     * played.
     *
     * @param event
     *            = the move event.
     */
    public void onTouchMove(MotionEvent event)
    {
        for (int i = 0; i < event.getPointerCount(); i++)
        {
            System.out.println("Coords " + i + ": " + event.getX(i) + ", "
                + event.getY(i));
            if (this.strum.getBounds().contains(event.getX(i), event.getY(i))
                && shiftNote != null)
            {
                ViolinString string =
                    new ViolinString(this.shiftNote.frequency());
                this.player.play(string);

                break;
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Displays the note being played.
     *
     * @param stringArray2
     *            The stringArray
     */

    public void changeWasObserved(Strings stringArray2)
    {
        for (int x = 0; x < 4; x++)
        {
            this.notes[x]
                .setText(stringArray2.getNote(x));
        }
    }

}
