package BulletHell.Powerups;

import BulletHell.Bullet;
import BulletHell.BulletManager;

public class Slow {
    // all bullets on the screen slow down/come to a halt where they are.
    public final static double MAX_IMMUNITY = 5; // The maximum number of seconds the player is immuned.

    public double remainingSlow;

    public void startSlow() {
        remainingSlow = MAX_IMMUNITY;

    }

    /**
     * Returns true if the player is still immune, and calculates the remaining time.
     */
    public boolean isSlowed() {
        if (remainingSlow > 0) {
            return true;
        }
        return false;
    }

    /**
     * Reduces the remaining time of the immunity.
     * 
     * @param dt The number of seconds that will be deducted from the remaining immunity.
     */
    public void reduceSlow(double dt) {
        remainingSlow -= dt;
    }

    // public void slowBullets() {
    //     for (Bullet bullet : BulletManager.getAllBullets()) {
    //         if (bullet.isAlive()) {
    //             bullet.changeSpeed(0);
    //         }else{
    //             bullet.changeSpeed(1);
    //         }
    //     }
    // }

}