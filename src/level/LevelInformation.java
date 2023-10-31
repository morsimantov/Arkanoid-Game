/*
 * Mor Siman Tov
 * ID: 208682484
 */

package level;

import collidable.Block;
import game.Velocity;
import sprite.Sprite;

import java.util.List;

/**
 * @author Mor Siman Tov
 * LevelInformation interface, includes all the information equired to fully describe a level.
 */

public interface LevelInformation {

    /**
     * Return the number of balls in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * Return a list of the initial velocity of each ball.
     *
     * @return a list of the balls' velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Return the speed of the paddle.
     *
     * @return the speed
     */
    int paddleSpeed();

    /**
     * Return the width of the paddle.
     *
     * @return the paddle's speed
     */
    int paddleWidth();

    /**
     * Return the level's name, that will be displayed at the top of the screen.
     *
     * @return the level's name
     */
    String levelName();

    /**
     * Return a sprite with the background of the level.
     *
     * @return the level's background
     */
    Sprite getBackground();

    /**
     * Return a list of the Blocks that make up this level, each block contains its size, color and location.
     *
     * @return a list of blocks
     */
    List<Block> blocks();

    /**
     * Return the number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return the numbers of blocks to remove
     */
    int numberOfBlocksToRemove();
}