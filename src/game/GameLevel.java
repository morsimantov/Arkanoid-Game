/*
 * Mor Siman Tov
 * ID: 208682484
 */

package game;

import java.awt.Color;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collidable.Block;
import collidable.Collidable;
import collidable.Paddle;
import geometry.Point;
import geometry.Rectangle;
import level.LevelInformation;
import listener.BallRemover;
import listener.BlockRemover;
import listener.ScoreTrackingListener;
import screen.PauseScreen;
import sprite.Ball;
import sprite.Sprite;
import sprite.SpriteCollection;
import sprite.ScoreIndicator;

/**
 * @author Mor Siman Tov
 * GameLevel class, that is in charge of running the animation of a level in the game.
 */

public class GameLevel implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter scoreCounter;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private String levelName;

    /**
     * Construct a GameLevel object, given level information, an animation runner, a keyboard sensor, a gui and a
     * score counter.
     *
     * @param levelInformation the information on the level
     * @param keyboard the keyboard sensor
     * @param runner the animation runner
     * @param gui the gui animation
     * @param scoreCounter the counter of the score
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard, AnimationRunner runner, GUI gui,
                     Counter scoreCounter) {
        this.levelInformation = levelInformation;
        this.levelName = levelInformation.levelName();
        this.keyboard = keyboard;
        this.runner = runner;
        this.gui = gui;
        this.scoreCounter = scoreCounter;
    }

    /**
     * Add a new collidable to the game level.
     *
     * @param c the collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add a new sprite to the game.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove the given collidable from the game level.
     *
     * @param c the collidable object
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove the given sprite from the game level.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game level - create the Blocks and paddle, and the background and add them to the game.
     *
     */
    public void initialize() {

        // The sizes of the window
        final int windowWidth = 800;
        final int windowHeight = 600;

        // The thickness of the frame blocks around the window
        final int frameThickness = 25;

        // Create a new game window and add it to the game level
        Sprite gameBackground = levelInformation.getBackground();
        this.addSprite(gameBackground);

        // Create a paddle and add it to the game level
        Paddle paddle = new Paddle(this.keyboard, this.levelInformation.paddleWidth(),
                this.levelInformation.paddleSpeed());
        paddle.addToGame(this);

        // Create 4 blocks that will be the frames of the window and will keep the balls inside
        Block topBlock = new Block(new Rectangle(new Point(0, 20), windowWidth, frameThickness), Color.gray);
        Block downBlock = new Block(new Rectangle(new Point(0, windowHeight), windowWidth,
                frameThickness), Color.gray);
        Block rightBlock = new Block(new Rectangle(new Point(windowWidth - frameThickness, frameThickness - 5),
                frameThickness, windowHeight - frameThickness + 5), Color.gray);
        Block leftBlock = new Block(new Rectangle(new Point(0, frameThickness - 5), frameThickness,
                windowHeight - frameThickness + 5), Color.gray);

        // Create a new score listener to follow the scores of the game
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.scoreCounter);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter, new Rectangle(new Point(0, 0),
                windowWidth, frameThickness - 5), new Color(221, 235, 225), this.levelName);
        this.addSprite(scoreIndicator);

        // Add the blocks to the game
        topBlock.addToGame(this);
        downBlock.addToGame(this);
        rightBlock.addToGame(this);
        leftBlock.addToGame(this);

        // Define the number of the remaining balls at the start of the game
        this.remainingBalls = new Counter(this.levelInformation.numberOfBalls());

        // Create a new ballRemover listener
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);

        // Register the BallRemover as a listener of the death-region - the bottom block
        downBlock.addHitListener(ballRemover);

        // Define the number of the remaining blocks at the start of the game
        this.remainingBlocks = new Counter(this.levelInformation.numberOfBlocksToRemove());

        // Create a new blockRemover listener
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);

        // Add the blocks to the game
        for (int i = 0; i < this.remainingBlocks.getValue(); i++) {
            Block block = this.levelInformation.blocks().get(i);

            // Register the BlockRemover as a listener of the blocks
            block.addHitListener(blockRemover);
            block.addHitListener(scoreListener);
            block.addToGame(this);
        }
    }

    /**
     * Run the game level - start the animation loop.
     *
     */
    public void run() {
        final double numOfSeconds = 2.0;
        final int countFrom = 3;

        // Create the balls on top of the paddle
        this.createBallsOnTopOfPaddle();

        // Run the countdown animation (counts from 3 to 1 before the game starts)
        this.runner.run(new CountdownAnimation(numOfSeconds, countFrom, this.sprites));

        this.running = true;

        // Run the current animation which is one turn of the game
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        // Draw all the sprites on the screen
        this.sprites.drawAllOn(d);

        // Notify the sprites that time has passed and move them accordingly
        this.sprites.notifyAllTimePassed();

        // If the player presses "p", pause the game
        if (this.keyboard.isPressed("p")) {
                this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
                        new PauseScreen()));
        }

        // If the player cleared all the blocks of the level, increase the score by 100 points
        if (this.remainingBlocks.getValue() == 0) {
            this.scoreCounter.increase(100);
        }

        // Return when there are either no more blocks or no more balls in the game
        if (this.remainingBlocks.getValue() == 0 || this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Create the balls on top of the paddle of the game.
     *
     */
    public void createBallsOnTopOfPaddle() {

        // Define the ball's radius
        final int ballsRadius = 6;

        // Create the balls and add them to the game
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(400, 559), ballsRadius, Color.white,
                    this.levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);

            // Set the game environment of the balls
            ball.setGameEnvironment(this.environment);
        }
    }

    /**
     * Return the number of the remaining balls in the game.
     *
     * @return the remaining balls
     */
    public int getRemainingBalls() {
        return this.remainingBalls.getValue();
    }

    /**
     * Return the number of the remaining blocks in the game.
     *
     * @return the remaining blocks
     */
    public int getRemainingBlocks() {
        return this.remainingBlocks.getValue();
    }
}