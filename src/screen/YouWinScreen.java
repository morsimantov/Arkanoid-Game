/*
 * Mor Siman Tov
 * ID: 208682484
 */

package screen;

import animation.Animation;
import biuoop.DrawSurface;
import game.Counter;

/**
 * @author Mor Siman Tov
 * YouWinScreen class, that is in charge of the screen desplayed when the player wins the game. Displays the final score
 * of the game.
 */
public class YouWinScreen implements Animation {
    private Counter score;

    /**
     * Construct an YouWinScreen given a score counter.
     *
     * @param scoreCounter score of the game
     */
    public YouWinScreen(Counter scoreCounter) {
        this.score = scoreCounter;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        final int fontSize = 40;
        d.drawText(125, 300, "You win! Your score is " + this.score.getValue(), fontSize);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
