/*
 * Mor Siman Tov
 * ID: 208682484
 */

package listener;

import game.Counter;
import collidable.Block;
import sprite.Ball;

/**
 * @author Mor Siman Tov
 * ScoreTrackingListener class, that is in charge of keeping the scores of the game.
 */

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Construct a ScoreTrackingListener given counter of the scores.
     *
     * @param scoreCounter the counter of scores
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}