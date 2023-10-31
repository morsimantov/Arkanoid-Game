/*
 * Mor Siman Tov
 * ID: 208682484
 */

package screen;

import animation.Animation;
import biuoop.DrawSurface;

/**
 * @author Mor Siman Tov
 * PauseScreen class, that is in charge of the screen desplayed when pausing the game.
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {
        final int fontSize = 32;
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", fontSize);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}