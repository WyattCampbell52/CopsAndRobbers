/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copsandrobbers;

import boundary.Boundary;
import com.apple.laf.AquaCaret;
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

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public Bank() {
        this.image
                = ResourceTools.loadImageFromResource("images/Bank_Heist_Map.png");
        this.x = -1500; //-400; //starting x = -400
        this.y = -100;//-2100; // starting y = -2100

        //<editor-fold defaultstate="collapsed" desc="Boundries">
        boundries = new ArrayList<>();
        boundries.add(new Rectangle(x, y, image.getWidth(null) * 6, image.getHeight(null) * 6)); // map boundary
        boundries.add(new Rectangle(x + 325, y + 1595, 820, 18)); // main room north wall left
        boundries.add(new Rectangle(x + 1290, y + 1543, 385, 70)); // main room north wall right
        boundries.add(new Rectangle(x + 324, y + 372, 18, 2441)); // main room west wall plus empty wall
        boundries.add(new Rectangle(x + 324, y + 2795, 840, 18)); // main room south wall left
        boundries.add(new Rectangle(x + 1464, y + 2795, 210, 18)); // main room south wall right
        boundries.add(new Rectangle(x + 1656, y + 1805, 18, 1008)); // main room east wall
        boundries.add(new Rectangle(x + 1530, y + 1925, 125, 235)); // main room couch north
        boundries.add(new Rectangle(x + 1530, y + 2305, 125, 235)); // main room couch south
        boundries.add(new Rectangle(x + 1550, y + 2165, 125, 135)); // main room plant
        boundries.add(new Rectangle(x + 325, y + 1595, 18, 80)); // main room left wall top bit
        boundries.add(new Rectangle(x + 600, y + 1817, 18, 762)); // counter back wall
        boundries.add(new Rectangle(x + 600, y + 1817, 72, 18)); // counter north wall left
        boundries.add(new Rectangle(x + 815, y + 1817, 155, 18)); // counter north wall right
        boundries.add(new Rectangle(x + 953, y + 1817, 18, 115)); // counter east wall top 
        boundries.add(new Rectangle(x + 953, y + 2057, 18, 18)); // counter east wall first square
        boundries.add(new Rectangle(x + 953, y + 2202, 18, 18)); // counter east wall second square
        boundries.add(new Rectangle(x + 953, y + 2346, 18, 18)); // counter east wall third square
        boundries.add(new Rectangle(x + 953, y + 2490, 18, 90)); // counter east wall bottom
        boundries.add(new Rectangle(x + 600, y + 2561, 370, 18)); // counter south wall
        boundries.add(new Rectangle(x + 1560, y + 1525, 95, 70)); // break room fridge
        boundries.add(new Rectangle(x + 1656, y + 1308, 18, 360)); // break room right wall
        boundries.add(new Rectangle(x + 1092, y + 1308, 18, 305)); // break room left wall
        boundries.add(new Rectangle(x + 978, y + 1308, 535, 18)); // break room north wall
        boundries.add(new Rectangle(x + 648, y + 587, 18, 1026)); // bank vault east wall
        boundries.add(new Rectangle(x + 648, y + 587, 720, 18)); // bank vault north wall
        boundries.add(new Rectangle(x + 1350, y + 587, 18, 378)); // bank vault east wall top
        boundries.add(new Rectangle(x + 1350, y + 1175, 18, 152)); // bank vault east wall bottom
        boundries.add(new Rectangle(x + 648, y + 1308, 162, 18)); // bank vault south door wall       
        boundries.add(new Rectangle(x + 1493, y + 1175, 18, 152)); // bank vault door wall bottom
        boundries.add(new Rectangle(x + 1493, y + 868, 18, 98)); // bank vault door wall top
        boundries.add(new Rectangle(x + 1350, y + 870, 1000, 18)); // bank entrence north wall and offices north wall left
        boundries.add(new Rectangle(x + 1350, y + 870, 595, 18)); // bank entrence north wall and offices north wall left
        boundries.add(new Rectangle(x + 2088, y + 870, 85, 18)); // offices north wall second from the left
        boundries.add(new Rectangle(x + 2315, y + 870, 18, 18)); // office north wall square third from the left\
        boundries.add(new Rectangle(x + 2585, y + 870, 30, 18)); // office north wall right bit
        boundries.add(new Rectangle(x + 1656, y + 1841, 959, 18)); // office south wall
        boundries.add(new Rectangle(x + 324, y + 372, 1602, 18)); // empty room north wall plus camera room north wall
        boundries.add(new Rectangle(x + 1908, y + 372, 18, 95)); // camera room east wall top
        boundries.add(new Rectangle(x + 1908, y + 593, 18, 295)); // camera room east wall bottom
        boundries.add(new Rectangle(x + 1655, y + 120, 468, 18)); // garbage room north wall
        boundries.add(new Rectangle(x + 2105, y + 120, 18, 30)); // garbage room east wall top
        boundries.add(new Rectangle(x + 2105, y + 275, 18, 613)); // manager office west wall
        boundries.add(new Rectangle(x + 2105, y + 383, 510, 18)); // manager office north wall
        boundries.add(new Rectangle(x + 2597, y + 383, 18, 505)); // manager office east wall
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Draw">
    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y, image.getWidth(null) * 6, image.getHeight(null) * 6, null);

        //<editor-fold defaultstate="collapsed" desc="Boundires Test">
        //boundries test
        graphics.setColor(Color.red);
        graphics.drawRect(x, y, image.getWidth(null) * 6, image.getHeight(null) * 6); // map boundry
        graphics.drawRect(x + 325, y + 1595, 820, 18); // main room north wall left
        graphics.drawRect(x + 1290, y + 1543, 385, 70); // main room north wall right
        graphics.drawRect(x + 324, y + 372, 18, 2441); // main room west wall plus empty wall
        graphics.drawRect(x + 324, y + 2795, 840, 18); // main room south wall left
        graphics.drawRect(x + 1464, y + 2795, 210, 18); // main room south wall right
        graphics.drawRect(x + 1656, y + 1805, 18, 1008); // main room east wall
        graphics.drawRect(x + 1530, y + 1925, 125, 235); // main room couch north
        graphics.drawRect(x + 1530, y + 2305, 125, 235); // main room couch south
        graphics.drawRect(x + 1550, y + 2165, 124, 130); // main room plant
        graphics.drawRect(x + 325, y + 1595, 18, 80); // main room left wall top bit
        graphics.drawRect(x + 600, y + 1817, 18, 762); // counter back wall
        graphics.drawRect(x + 600, y + 1817, 72, 18); // counter north wall left
        graphics.drawRect(x + 815, y + 1817, 155, 18); // counter north wall right
        graphics.drawRect(x + 953, y + 1817, 18, 115); // counter east wall top 
        graphics.drawRect(x + 953, y + 2057, 18, 18); // counter east wall first square
        graphics.drawRect(x + 953, y + 2202, 18, 18); // counter east wall second square
        graphics.drawRect(x + 953, y + 2346, 18, 18); // counter east wall third square
        graphics.drawRect(x + 953, y + 2490, 18, 90); // counter east wall bottom
        graphics.drawRect(x + 600, y + 2561, 370, 18); // counter south wall
        graphics.drawRect(x + 1560, y + 1525, 95, 70); // break room fridge
        graphics.drawRect(x + 1656, y + 1308, 18, 360); // break room right wall
        graphics.drawRect(x + 1092, y + 1308, 18, 305); // break room left wall
        graphics.drawRect(x + 978, y + 1308, 535, 18); // break room north wall
        graphics.drawRect(x + 648, y + 587, 18, 1026); // bank vault west wall
        graphics.drawRect(x + 648, y + 587, 720, 18); // bank vault north wall
        graphics.drawRect(x + 1350, y + 587, 18, 378); // bank vault east wall
        graphics.drawRect(x + 1350, y + 1175, 18, 152); // bank vault east wall bottom
        graphics.drawRect(x + 648, y + 1308, 162, 18); // bank vault south door wall
        graphics.drawRect(x + 1493, y + 1175, 18, 152); // bank vault door wall bottom
        graphics.drawRect(x + 1493, y + 870, 18, 98); // bank vault door wall top
        graphics.drawRect(x + 1350, y + 870, 595, 18); // bank entrence north wall and offices north wall left
        graphics.drawRect(x + 2088, y + 870, 85, 18); // offices north wall second from the left
        graphics.drawRect(x + 2315, y + 870, 18, 18); // office north wall square third from the left
        graphics.drawRect(x + 2585, y + 870, 30, 18); // office north wall right bit
        graphics.drawRect(x + 2597, y + 870, 18, 989); // office east wall 
        graphics.drawRect(x + 1656, y + 1841, 959, 18); // office south wall
        graphics.drawRect(x + 324, y + 372, 1602, 18); // empty room north wall plus camera room north wall
        graphics.drawRect(x + 1655, y + 120, 18, 750); // camera room and garabage room west wall
        graphics.drawRect(x + 1908, y + 372, 18, 95); // camera room east wall top
        graphics.drawRect(x + 1908, y + 593, 18, 295); // camera room east wall bottom
        graphics.drawRect(x + 1655, y + 120, 468, 18); // garbage room north wall
        graphics.drawRect(x + 2105, y + 120, 18, 30); // garbage room east wall top
        graphics.drawRect(x + 2105, y + 275, 18, 613); // manager office west wall
        graphics.drawRect(x + 2105, y + 383, 510, 18); // manager office north wall
        graphics.drawRect(x + 2597, y + 383, 18, 505); // manager office east wall
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    private Image image;
    private int x;
    private int y;
    private Velocity velocity;
    public final ArrayList<Rectangle> boundries;

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
    //</editor-fold>

}
