/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copsandrobbers;

import static copsandrobbers.Robber.PRIMARY_BULLET_COUNT;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 *
 * @author WyattCampbell
 */
public class Cop {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        setVelocity(new Velocity(0, 0));

        setImage(ResourceTools.loadImageFromResource("images/White_Guard_HairBlonde_Standing.png"));

        bulletCount = PRIMARY_BULLET_COUNT;
        magCount = 5;
        mode = "Patrol";
        health = 1;
    }

    public Cop(int x, int y, double angleRadians) {
        this.setX(x);
        this.setY(y);
        this.setAngleRadians(angleRadians);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Drawing">
    public void draw(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        AffineTransform olde = g2d.getTransform();

        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(getAngleRadians()));
        at.setToRotation(getAngleRadians() - 90, getX() + (getImage().getWidth(null) / 2), getY() + (getImage().getHeight(null) / 2));
        g2d.setTransform(at);
        g2d.drawImage(getImage(), getX(), getY(), null);
        graphics.drawRect(getX(), getY(), getImage().getWidth(null), getImage().getHeight(null));
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private int x;
    private int y;
    private int bulletCount;
    private int magCount;
    private int health;
    private String mode;
    private Velocity velocity;
    private Image image;
    private double angleRadians;

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * @param velocity the velocity to set
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @return the angleRadians
     */
    public double getAngleRadians() {
        return angleRadians;
    }

    /**
     * @param angleRadians the angleRadians to set
     */
    public void setAngleRadians(double angleRadians) {
        this.angleRadians = angleRadians;
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    public Rectangle hitBox() {
        return new Rectangle(x, y, getImage().getWidth(null), getImage().getHeight(null) / 8);
    }
}
//</editor-fold>
