    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copsandrobbers;

import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author WyattCampbell
 */
public class CrossHairs {

    public CrossHairs(Point point) {
        this.point = point;
        image = ResourceTools.loadImageFromResource("images/Cross_Hairs.png");
    }
    private Point point;
    private Image image;

    public void draw(Graphics graphics) {
        graphics.drawImage(image, point.x - 25, point.y - 25, image.getWidth(null) * 2, image.getHeight(null)*2, null);
    }

    public Point centreOfMass() {
        return new Point(point.x - 25 + (image.getWidth(null) / 2), point.y - 25 + (image.getHeight(null) / 2));
    }
}
