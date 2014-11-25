package violin.emulator;

/**
 *  Creates an opening screen to summarize the game
 *
 *  @author Joe
 *  @version Dec 9, 2012
 */

public enum Notes
{
    A,
    A_SHARP,
    B,
    C,
    C_SHARP,
    D,
    E,
    F,
    F_SHARP,
    G,
    G_SHARP;

    /*
     * toString method for the strings
     */
    public String toString()
    {
        switch (this)
        {
            case A:
                return "A";
            case A_SHARP:
                return "A#";
            case B:
                return "B";
            case C:
                return "C";
            case C_SHARP:
                return "C#";
            case D:
                return "D";
            case E:
                return "E";
            case F:
                return "F";
            case F_SHARP:
                return " F#";
            case G:
                return "G";
            case G_SHARP:
                return "G#";
            default:
                return null;
        }
    }
}
