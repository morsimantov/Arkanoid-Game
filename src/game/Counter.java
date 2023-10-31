/*
 * Mor Siman Tov
 * ID: 208682484
 */

package game;

/**
 * @author Mor Siman Tov
 * Counter class that is used for counting things in the game (like blocks, balls and score of the game).
 */

public class Counter {
    private int counter;

    /**
     * Construct a Counter given an integer.
     *
     * @param number an integer, a counting number
     */
    public Counter(int number) {
        this.counter = number;
    }

    /**
     * Add a number to current count.
     *
     * @param number the number that being added
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Subtract a number from current count.
     *
     * @param number the number that being subtracted
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Get the current count.
     *
     * @return the number of the current count
     */
    public int getValue() {
        return this.counter;
    }
}