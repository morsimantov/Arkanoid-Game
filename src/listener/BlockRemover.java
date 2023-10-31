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
 * BlockRemover class, that is in charge of removing blocks from the game, as well as keeping count of the number of
 * blocks that remain.
 */

public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Construct a BlockRemover given a game and a counter of the remaining blocks.
     *
     * @param gameLevel the game
     * @param remainingBlocks the counter of the remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        // Remove this listener from the block that is being removed from the game
        beingHit.removeHitListener(this);

        // Remove the block that is being hit from the game
        beingHit.removeFromGame(this.gameLevel);

        // Update the counter of the remaining blocks (decrease by one)
        this.remainingBlocks.decrease(1);
    }
}