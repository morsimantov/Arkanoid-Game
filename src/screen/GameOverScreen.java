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
 * PauseScreen class, that is in charge of the screen desplayed when the player loses the game. Displays the final score
 * of the game.
 */
public class GameOverScreen implements Animation {
    private Counter score;

    /**
     * Construct an GameOverScreen given a score counter.
     *
     * @param scoreCounter score of the game
     */
    public GameOverScreen(Counter scoreCounter) {
       this.score = scoreCounter;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        final int fontSize = 40;
        d.drawText(125, 300, "Game Over. Your score is " + this.score.getValue(), fontSize);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}