package violin.emulator;

/**
 * Creates the notes and strings for the violin
 * @author Joe
 */
public class Strings extends sofia.util.Observable
{

    private Notes[] gString     = new Notes[7];
    private Notes[] dString  = new Notes[7];
    private Notes[] aString = new Notes[7];
    private Notes[] eString    = new Notes[7];
    private String note1;
    private String note2;
    private String note3;
    private String note4;

/*
 * Creates each string and the notes corresponding to that string
 */
    public Strings()
    {
        gString[0] = Notes.G;
        gString[1] = Notes.A;
        gString[2] = Notes.A_SHARP;
        gString[3] = Notes.B;
        gString[4] = Notes.C;
        gString[5] = Notes.D;
        gString[6] = Notes.E;

        dString[0] = Notes.D;
        dString[1] = Notes.E;
        dString[2] = Notes.F;
        dString[3] = Notes.F_SHARP;
        dString[4] = Notes.G;
        dString[5] = Notes.A;
        dString[6] = Notes.B;

        aString[0] = Notes.A;
        aString[1] = Notes.B;
        aString[2] = Notes.C;
        aString[3] = Notes.C_SHARP;
        aString[4] = Notes.D;
        aString[5] = Notes.E;
        aString[6] = Notes.F;

        eString[0] = Notes.E;
        eString[1] = Notes.F_SHARP;
        eString[2] = Notes.G;
        eString[3] = Notes.G_SHARP;
        eString[4] = Notes.A;
        eString[5] = Notes.B;
        eString[6] = Notes.C;

    }

    /*
     * Sets the notes corresponding to the string
     */
    public void setNote(int string, int fret)
    {
        if(string == 0)
        {
            setNote1(gString[fret].toString());

        }
        else if(string == 1)
        {
            setNote2(dString[fret].toString());

        }
        else if(string == 2)
        {
            setNote3(aString[fret].toString());

        }
        else if(string == 3)
        {

            setNote4(eString[fret].toString());

        }

    }

    public String getNote(int note)
    {
        if(note == 0)
        {
            return note1;
        }
        else if(note ==1)
        {
            return note2;
        }
        else if(note == 2)
        {
            return note3;
        }
        else if(note == 3)
        {
            return note4;
        }
        else
        {
            return null;
        }
    }

    public void setBlankNote(int string)
    {
        if(string == 0)
        {
            note1 = "";
        }
        else if(string ==1)
        {
            note2 = "";
        }
        else if(string == 2)
        {
            note3 = "";
        }
        else if(string == 3)
        {
            note4 = "";
        }
        notifyObservers();
    }

    public void setNote1(String note1)
    {
        this.note1 = note1;
        notifyObservers();
    }

    public String getNote2()
    {
        return note2;
    }

    public void setNote2(String note2)
    {
        this.note2 = note2;
        notifyObservers();
    }

    public String getNote3()
    {
        return note3;
    }

    public void setNote3(String note3)
    {
        this.note3 = note3;
        notifyObservers();
    }

    public String getNote4()
    {
        return note4;
    }

    public void setNote4(String note4)
    {
        this.note4 = note4;
        notifyObservers();
    }



}
