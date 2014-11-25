package violin.emulator;

import sofia.util.Random;

// -------------------------------------------------------------------------
/**
 * This class models a guitar string
 *
 * @author Joe
 * @version 2012.11.14
 */

public class ViolinString
{

    // ~ Fields ..................................................
    private double         frequency;
    private CircularBuffer buffer;
    private int            capacity;
    private int            totalSteps;


    // ----------------------------------------------------------
    /**
     * Create a new ViolinString object.
     *
     * @param frequency
     *            = frequency of the string.
     */
    public ViolinString(double frequency)
    {
        this.frequency = frequency;
        this.capacity = (int)(11025.0 / frequency);
        this.buffer = new CircularBuffer(capacity);

        Random         rand = new Random();
        while (!this.buffer.isFull())
        {
            this.buffer.enqueue(rand.nextDouble() - 0.5);
        }
    }


    // ----------------------------------------------------------
    /**
     * Performs one step in the Karplus-Strong algorithm: dequeue the first
     * element from the buffer, then peek at the next, average them, multiply by
     * the dissipation factor, and enqueue the result.
     */
    public void step()
    {
        double result = 0.00;
        result = ((this.buffer.dequeue() + this.buffer.peek()) / 2.0) * 0.996;
        this.buffer.enqueue(result);
        totalSteps++;
    }


    // ----------------------------------------------------------
    /**
     * Returns the sample currently at the front of the guitar string's buffer.
     *
     * @return = the sample.
     */
    public double sample()
    {
        return this.buffer.peek();
    }


    // ----------------------------------------------------------
    /**
     * Returns the number of times that step() was called.
     *
     * @return = number of times step() was called.
     */
    public int totalSteps()
    {
        return totalSteps;
    }


    // ----------------------------------------------------------
    /**
     * Returns the frequency that was passed into this string's constructor.
     *
     * @return = the frequency.
     */
    public double frequency()
    {
        return frequency;
    }
}
