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
        this.x = -500; //-400; //starting x = -400
        this.y = -1100;//-2100; // starting y = -2100
        boundries = new ArrayList<>();
        boundries.add(new Rectangle(x, y, image.getWidth(null) * 6, image.getHeight(null) * 6));
        boundries.add(new Rectangle(x + 600, y + 1817, 18, 765));
        boundries.add(new Rectangle(x + 325, y + 1595, 820, 18));
        boundries.add(new Rectangle(x + 1290, y + 1543, 385, 70));
        boundries.add(new Rectangle(x + 1656, y + 1308, 18, 360));
        boundries.add(new Rectangle(x + 1092, y + 1308, 18, 305));
        boundries.add(new Rectangle(x + 325, y + 1595, 18, 80));
        boundries.add(new Rectangle(x + 600, y + 1817, 72, 18));
        boundries.add(new Rectangle(x + 815, y + 1817, 155, 18));
        boundries.add(new Rectangle(x + 953, y + 1817, 18, 115));
        boundries.add(new Rectangle(x + 953, y + 1817, 18, 115));
        boundries.add(new Rectangle(x + 953, y + 2057, 18, 18));
        boundries.add(new Rectangle(x + 953, y + 2346, 18, 18));
        boundries.add(new Rectangle(x + 953, y + 2490, 18, 90));
        boundries.add(new Rectangle(x + 600, y + 2561, 370, 18));
        boundries.add(new Rectangle(x + 324, y + 1805, 18, 1008));
        boundries.add(new Rectangle(x + 324, y + 2795, 840, 18));
        boundries.add(new Rectangle(x + 1464, y + 2795, 210, 18));
        boundries.add(new Rectangle(x + 1656, y + 1805, 18, 1008));
        boundries.add(new Rectangle(x + 1530, y + 1925, 125, 235));
        boundries.add(new Rectangle(x + 1530, y + 2305, 125, 235));
        boundries.add(new Rectangle(x + 1550, y + 2165, 125, 135));
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y, image.getWidth(null) * 6, image.getHeight(null) * 6, null);
        //boundries test
        graphics.setColor(Color.red);
        graphics.drawRect(x, y, image.getWidth(null) * 6, image.getHeight(null) * 6);
        graphics.drawRect(x + 600, y + 1817, 18, 762);
        graphics.drawRect(x + 325, y + 1595, 820, 18);
        graphics.drawRect(x + 1290, y + 1543, 385, 70);
        graphics.drawRect(x + 1560, y + 1525, 95, 70);
        graphics.drawRect(x + 1656, y + 1308, 18, 360);
        graphics.drawRect(x + 1092, y + 1308, 18, 305);
        graphics.drawRect(x + 978, y + 1308, 535, 18);
        graphics.drawRect(x + 325, y + 1595, 18, 80);
        graphics.drawRect(x + 600, y + 1817, 72, 18);
        graphics.drawRect(x + 815, y + 1817, 155, 18);
        graphics.drawRect(x + 953, y + 1817, 18, 115);
        graphics.drawRect(x + 953, y + 2057, 18, 18);
        graphics.drawRect(x + 953, y + 2202, 18, 18);
        graphics.drawRect(x + 953, y + 2346, 18, 18);
        graphics.drawRect(x + 953, y + 2490, 18, 90);
        graphics.drawRect(x + 600, y + 2561, 370, 18);
        graphics.drawRect(x + 324, y + 1805, 18, 1008);
        graphics.drawRect(x + 324, y + 2795, 840, 18);
        graphics.drawRect(x + 1464, y + 2795, 210, 18);
        graphics.drawRect(x + 1656, y + 1805, 18, 1008);
        graphics.drawRect(x + 1530, y + 1925, 125, 235);
        graphics.drawRect(x + 1530, y + 2305, 125, 235);
        graphics.drawRect(x + 1550, y + 2165, 125, 135);
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
