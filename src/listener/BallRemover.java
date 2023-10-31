/*
 * Mor Siman Tov
 * ID: 208682484
 */

package listener;

import collidable.Block;
import game.Counter;
import game.GameLevel;
import sprite.Ball;

/**
 * @author Mor Siman Tov
 * BallRemover class, that is in charge of removing balls from the game, as well as keeping count of the number of
 * balls that remain.
 */

public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Construct a BallRemover given a game and a counter of the remaining balls.
     *
     * @param gameLevel the game
     * @param remainingBalls the counter of the remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        // Remove the ball from the game
        hitter.removeFromGame(this.gameLevel);

        // Update the counter of the remaining balls (decrease by one)
        this.remainingBalls.decrease(1);
    }
}
