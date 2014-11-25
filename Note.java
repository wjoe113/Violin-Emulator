package violin.emulator;

import android.util.SparseArray;

//-------------------------------------------------------------------------
/**
* <p>
* This class provides frequency information for a musical note and permits the
* construction of notes based on octave and location on the scale, and also to
* get notes that are offset from other notes.
* </p><p>
* Instances of this class cannot be created directly using the {@code new}
* keyword. Instead, use static methods like {@link #a(int)} and
* {@link #aSharp(int)} to create the scale notes, passing in the desired
* octave.
* </p>
*
* @author Tony
* @author Joe
* @version 2012.07.09
*/
public class Note
{
  //~ Instance/static fields ................................................

  // The octave that contains the origin frequency (440 Hz).
  private static final int ORIGIN_OCTAVE = 4;

  // The origin note (A4) that is used to compute frequencies for all other
  // notes.
  private static final double A4_FREQ = 440.0;

  // Offsets of each scale note from the origin, within the same octave.
  private static final int C = -9;
  private static final int C_SHARP = -8;
  private static final int D = -7;
  private static final int D_SHARP = -6;
  private static final int E = -5;
  private static final int F = -4;
  private static final int F_SHARP = -3;
  private static final int G = -2;
  private static final int G_SHARP = -1;
  private static final int A = 0;
  private static final int A_SHARP = 1;
  private static final int B = 2;

  // Human-readable note names returned by the toString method.
  private static final String[] NOTE_NAMES = {
      "C", "C\u266f", "D", "D\u266f", "E", "F", "F\u266f", "G", "G\u266f",
      "A", "A\u266f", "B"
  };

  // A sparse array that caches Note instances for better performance.
  private static SparseArray<Note> instances = new SparseArray<Note>();

  // The numerical index of the note (a raw integer used to calculate the
  // frequency with respect to the origin).
  private int index;


  //~ Constructors ..........................................................

  // ----------------------------------------------------------
  /**
   * Initializes a new {@code Note} with the specified index (a displacement
   * that represents the number of half-steps from the origin).
   *
   * @param index the index of the note
   */
  private Note(int index)
  {
      this.index = index;
  }


  //~ Methods ...............................................................

  // ----------------------------------------------------------
  /**
   * Used internally to retrieve the cached note with the specified index,
   * or create a new one and cache it if it does not already exist.
   *
   * @param index the index of the note
   * @return a {@code Note} object representing the note
   */
  private static Note make(int index)
  {
      Note note = instances.get(index);

      if (note == null)
      {
          note = new Note(index);
          instances.put(index, note);
      }

      return note;
  }


  // ----------------------------------------------------------
  /**
   * Used internally to retrieve the cached note with the specified raw note
   * number and octave, or create a new one and cache it if it does not
   * already exist.
   *
   * @param noteNumber the raw note number (the offset from A)
   * @param octave the octave of the desired note
   * @return a {@code Note} object representing the note
   */
  private static Note make(int noteNumber, int octave)
  {
      return make((octave - ORIGIN_OCTAVE) * 12 + noteNumber);
  }


  // ----------------------------------------------------------
  /**
   * Gets the scale note A in the specified octave.
   *
   * @param octave the desired octave of the note
   * @return the scale note A in the specified octave
   */
  public static Note a(int octave)
  {
      return make(A, octave);
  }


  // ----------------------------------------------------------
  /**
   * Gets the scale note A# in the specified octave.
   *
   * @param octave the desired octave of the note
   * @return the scale note A# in the specified octave
   */
  public static Note aSharp(int octave)
  {
      return make(A_SHARP, octave);
  }


  // ----------------------------------------------------------
  /**
   * Gets the scale note B in the specified octave.
   *
   * @param octave the desired octave of the note
   * @return the scale note B in the specified octave
   */
  public static Note b(int octave)
  {
      return make(B, octave);
  }


  // ----------------------------------------------------------
  /**
   * Gets the scale note C in the specified octave.
   *
   * @param octave the desired octave of the note
   * @return the scale note C in the specified octave
   */
  public static Note c(int octave)
  {
      return make(C, octave);
  }


  // ----------------------------------------------------------
  /**
   * Gets the scale note C# in the specified octave.
   *
   * @param octave the desired octave of the note
   * @return the scale note C# in the specified octave
   */
  public static Note cSharp(int octave)
  {
      return make(C_SHARP, octave);
  }


  // ----------------------------------------------------------
  /**
   * Gets the scale note D in the specified octave.
   *
   * @param octave the desired octave of the note
   * @return the scale note D in the specified octave
   */
  public static Note d(int octave)
  {
      return make(D, octave);
  }


  // ----------------------------------------------------------
  /**
   * Gets the scale note D# in the specified octave.
   *
   * @param octave the desired octave of the note
   * @return the scale note D# in the specified octave
   */
  public static Note dSharp(int octave)
  {
      return make(D_SHARP, octave);
  }


  // ----------------------------------------------------------
  /**
   * Gets the scale note E in the specified octave.
   *
   * @param octave the desired octave of the note
   * @return the scale note E in the specified octave
   */
  public static Note e(int octave)
  {
      return make(E, octave);
  }


  // ----------------------------------------------------------
  /**
   * Gets the scale note F in the specified octave.
   *
   * @param octave the desired octave of the note
   * @return the scale note F in the specified octave
   */
  public static Note f(int octave)
  {
      return make(F, octave);
  }


  // ----------------------------------------------------------
  /**
   * Gets the scale note F# in the specified octave.
   *
   * @param octave the desired octave of the note
   * @return the scale note F# in the specified octave
   */
  public static Note fSharp(int octave)
  {
      return make(F_SHARP, octave);
  }


  // ----------------------------------------------------------
  /**
   * Gets the scale note G in the specified octave.
   *
   * @param octave the desired octave of the note
   * @return the scale note G in the specified octave
   */
  public static Note g(int octave)
  {
      return make(G, octave);
  }


  // ----------------------------------------------------------
  /**
   * Gets the scale note G# in the specified octave.
   *
   * @param octave the desired octave of the note
   * @return the scale note G# in the specified octave
   */
  public static Note gSharp(int octave)
  {
      return make(G_SHARP, octave);
  }


  // ----------------------------------------------------------
  /**
   * Gets a new {@code Note} that represents the receiver shifted by the
   * specified number (positive or negative) of half-steps. For example:
   * <pre>
   *     Note note  = Note.f(4);         // note = F4
   *     Note note2 = note.shiftBy(1);   // note = F#4
   *     Note note3 = note.shiftBy(-1);  // note = E4
   * </pre>
   *
   * @param shift the number of half-steps to shift by
   * @return a new note representing the receiver shifted by the specified
   *     number of half-steps
   */
  public Note shiftBy(int shift)
  {
      return make(index + shift);
  }


  // ----------------------------------------------------------
  /**
   * Gets the frequency of the note, in hertz.
   *
   * @return the frequency of the note
   */
  public double frequency()
  {
      return A4_FREQ * Math.pow(2, index / 12.0);
  }


  // ----------------------------------------------------------
  /**
   * Gets the octave that the note is in.
   *
   * @return the octave that the note is in
   */
  public int octave()
  {
      if (index > 2)
      {
          return (index - 2) / 12 + ORIGIN_OCTAVE + 1;
      }
      else
      {
          return (index - 2) / 12 + ORIGIN_OCTAVE;
      }
  }


  // ----------------------------------------------------------
  /**
   * Returns a string containing the name and octave of the note. Sharp signs
   * will be returned as the actual sharp sign (Unicode U+266F '\u266f'), not
   * the ASCII pound sign ('#'). For example:
   * <pre>
   *     Note note = Note.d(4);
   *     System.out.println(note.toString());  // "D4"
   * </pre>
   *
   * @return a {@code String} containing the name and octave of the note
   */
  @Override
  public String toString()
  {
      int note = mod((index - C), 12);
      int octave = octave();

      return NOTE_NAMES[note] + Integer.toString(octave);
  }


  // ----------------------------------------------------------
  /**
   * The modulo operation implemented in the proper mathematical way
   * (negative dividends are handled correctly).
   *
   * @param a the dividend
   * @param b the divisor
   * @return the result of the operation (a modulo b)
   */
  private static int mod(int a, int b)
  {
      return (a % b + b) % b;
  }
}
