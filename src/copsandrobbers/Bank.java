/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copsandrobbers;

import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author WyattCampbell
 */
public class Bank {

    public Bank() {
        this.image
                = ResourceTools.loadImageFromResource("images/practice.png");
//                ResourceTools.loadImageFromResource("images/Bank_Heist.png");
        this.x = 0;
//                -1000;
        this.y = 0;
//                -1000;

    }

    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y, image.getWidth(null) * 3, image.getHeight(null) * 3, null);
//        graphics.drawRect(20, 20, 1309, 700);
    }
    private Image image;
    private int x;
    private int y;

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

    public void moveLeft() {
        x = x - 1;
    }

    public void moveRight() {
        x = x + 1;
    }

    public void mapBorder() {
        new Rectangle(0, 0, 1239, 900);
    }
}
