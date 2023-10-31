/*
 * Mor Siman Tov
 * ID: 208682484
 */

package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Mor Siman Tov
 * KeyPressStoppableAnimation is a decorator class that wraps an existing animation and adds a key-press behavior to it.
 */

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Construct a KeyPressStoppableAnimation given a keyboard sensor, a key (string) and an animation object.
     *
     * @param sensor the keyboard sensor
     * @param key the key that is checked if being pressed
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        // Do one frame of the animation
        this.animation.doOneFrame(d);

        // If the key is pressed (and wasn't already pressed before), stop the animation
        if (this.sensor.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        } else if (!this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}