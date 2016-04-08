/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copsandrobbers;

import boundary.Boundary;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author WyattCampbell
 */
public class Bank {

    public Bank() {
        this.image
                = ResourceTools.loadImageFromResource("images/Bank_Heist_Map.png");
        this.x = -300; //-400; //starting x = -400
        this.y = -1500;//-2100; // starting y = -2100
        boundries = new ArrayList<>();
        boundries.add(new Rectangle(x, y, image.getWidth(null) * 6, image.getHeight(null) * 6));
        boundries.add(new Rectangle(x + 600, y + 1817, 18, 765)); // x must equal 200 and y must equal 15
        boundries.add(new Rectangle(x + 325, y + 1595, 473, 18)); // x must equal 25 and y must equal 95
        boundries.add(new Rectangle(x + 325, y + 1595, 18, 80)); // ||
        boundries.add(new Rectangle(x + 600, y + 1817, 120, 18));

    }

    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y, image.getWidth(null) * 6, image.getHeight(null) * 6, null);
        //boundries test
        graphics.setColor(Color.red);
        graphics.drawRect(x + 600, y + 1817, 18, 765);
        graphics.drawRect(x, y, image.getWidth(null) * 6, image.getHeight(null) * 6);
        graphics.drawRect(x + 325, y + 1595, 473, 18);
        graphics.drawRect(x + 325, y + 1595, 18, 80);
        graphics.drawRect(x + 600, y + 1817, 75, 18);
    }
    private Image image;
    private int x;
    private int y;
    private Velocity velocity;
    private ArrayList<Rectangle> boundries;

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

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

    void move() {
        x += velocity.x;
        y += velocity.y;
    }

    void stop() {
        velocity.x = 0;
        velocity.y = 0;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * @return the velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }

}
