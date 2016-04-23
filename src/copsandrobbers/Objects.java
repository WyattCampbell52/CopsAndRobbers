/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copsandrobbers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

/**
 *
 * @author WyattCampbell
 */
public class Objects {
    public Objects(ObjectState objectState, Double angle){
        object = objectState;
        this.angle = angle;
    }
    
        public void draw(Graphics graphics){
             Graphics2D g2d = (Graphics2D) graphics;
        AffineTransform olde = g2d.getTransform();

        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle), x , y);
//        at.setToTranslation(13, 13);
        at.setToRotation(angle - 90, x, y);

        g2d.setTransform(at);
        g2d.drawImage(image, x, y, image.getWidth(null) * 2, image.getHeight(null) * 2, null);

        g2d.setTransform(olde);
        }
    
    private ObjectState object;
    private Double angle;
    private Image image;
    private int x;
    private int y;
    
    
    
    

    /**
     * @return the object
     */
    public ObjectState getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(ObjectState object) {
        this.object = object;
    }

    /**
     * @return the angle
     */
    public Double getAngle() {
        return angle;
    }

    /**
     * @param angle the angle to set
     */
    public void setAngle(Double angle) {
        this.angle = angle;
    }
}
