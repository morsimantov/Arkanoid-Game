/*
 * Mor Siman Tov
 * ID: 208682484
 */

package animation;

import biuoop.DrawSurface;
import sprite.SpriteCollection;
import java.awt.Color;

/**
 * @author Mor Siman Tov
 * CountdownAnimation class, displays the given game screen with a countdown of few seconds before the game starts.
 */

public class CountdownAnimation implements Animation {
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean running;
    private long startTime;
    private double countTime;

    /**
     * Construct a CountdownAnimation given a number of seconds, a number of seconds to count from and the game screen.
     *
     * @param numOfSeconds the number of seconds to count
     * @param countFrom the starting count of the countdown
     * @param gameScreen the gui animation
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.startTime = System.currentTimeMillis();
        this.countTime = (numOfSeconds * 1000) / countFrom;
        this.running = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        final int fontSize = 60;

        // Draw all the sprites on the screen
        this.gameScreen.drawAllOn(d);

        // If the countFrom is zero or negative, stop the countdown
        if (this.countFrom <= 0) {
            this.running = false;
        }

        // If the current time is less than the starting time and the count time
        if (System.currentTimeMillis() <= this.startTime + this.countTime) {
            d.setColor(Color.white);
            d.drawText(382, 415, Integer.toString(this.countFrom), fontSize);
        } else {

            // Reduce the countFrom by one
            this.countFrom = this.countFrom - 1;
            this.startTime = System.currentTimeMillis();
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}