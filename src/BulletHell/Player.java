package BulletHell;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class Player {
    public static final int PLAYER_WIDTH = 40; 
    public static final int PLAYER_HEIGHT = 50; 
    public static final int PLAYER_SPEED = 200;
    public static final double MAX_IMMUNITY = 2; // The maximum number of seconds the player is immuned.

    private Rectangle playerShape;
    private CanvasWindow canvas;
    private double leftX;
    private double topY;
    private double remainingImmunity;

    private boolean frozen; // If true, prevents the player from moving.
    private boolean erasing; // If true, allows the player to erase bullets without losing lives. 


    public Player(CanvasWindow canvas) {
        this.canvas = canvas;
        playerShape = new Rectangle(canvas.getWidth() / 2, canvas.getHeight() / 2, PLAYER_WIDTH, PLAYER_HEIGHT);
        playerShape.setFillColor(Color.BLUE);
        leftX = playerShape.getX();
        topY = playerShape.getY();
    }

    /**
     * Moves the player left by a set amount when called.
     */
    public void moveLeft(double dt) {
        if (playerShape.getX() > 0 && !frozen) {
            leftX -= PLAYER_SPEED * dt;
            playerShape.setX(leftX);
        }
    }

    /**
     * Moves the player right by a set amount when called.
     */
    public void moveRight(double dt) {
        if (playerShape.getX() < canvas.getWidth() - PLAYER_WIDTH && !frozen){
            leftX += PLAYER_SPEED * dt;
            playerShape.setX(leftX);
        }
    }

    /**
     * Moves the player up by a set amount when called.
     */
    public void moveUp(double dt) {
        if (playerShape.getY() > 40 && !frozen) {
            topY -= PLAYER_SPEED * dt;
            playerShape.setY(topY);
        }
    }

    /**
     * Moves the player down by a set amount when called.
     */
    public void moveDown(double dt) {
        if (playerShape.getY() < canvas.getHeight() - PLAYER_HEIGHT && !frozen){
            topY += PLAYER_SPEED * dt;
            playerShape.setY(topY);
        }
    }

    /**
     * Gets the shape of the player.
     */
    public Rectangle getPlayerShape() {
        return playerShape;
    }

    /**
     * Changes the color of the player.
     */
    private void changeColor(Color color) {
        playerShape.setFillColor(color);
    }

    /**
     * Starts the immunity timer of the player. 
     * The frames of immunity are indicated by the purple color.
     */
    public void startImmunity() {
        remainingImmunity = MAX_IMMUNITY;
        changeColor(Color.MAGENTA);
    }

    /**
     * Returns true if the player is still immune, 
     * and calculates the remaining time.
     */
    public boolean isImmune() {
        return (remainingImmunity > 0);
    }

    /**
     * Reduces the remaining time of the immunity. 
     * Ends immunity if the player is no longer immune.
     * 
     * @param dt The number of seconds that will be deducted from the remaining immunity.
     */
    public void reduceImmunity(double dt) {
        if (isImmune()) {
            remainingImmunity -= dt;
            if (!isImmune()) {
                endImmunity();
            }
        }
    }
    
    /**
     * Changes the player's color back to blue to indicate the end of immunity.
     * Thaws out the player if they are frozen.
     */
    private void endImmunity() {
        thaw();
        changeColor(Color.BLUE);
    }

    /**
     * Freezes the player.
     */
    public void freeze() {
        frozen = true;
    }

    /**
     * Thaws the player.
     */
    public void thaw() {
        if (frozen) {
            frozen = false;
        }
    }

    /**
     * Changes the player's color to yellow and begins the 'eraser' state.
     */
    public void startErasing() {
        erasing = true;
        changeColor(Color.YELLOW);
    }

    /**
     * Checks if the player is still in the 'eraser' state.
     */
    public boolean isErasing() {
        return erasing;
    }

    /**
     * Ends the 'eraser' state and returns the player's color back to blue.
     */
    public void endErasing() {
        erasing = false;
        changeColor(Color.BLUE);
    }
}
