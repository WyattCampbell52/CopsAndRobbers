/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copsandrobbers;

/**
 *
 * @author BBC132
 */
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author BBC132
 */
public class Item {
    private int x;
    private int y;
    private String type;
    private Image image;
    
    
    public Item(int x, int y, String type){
        this.x = x;
        this.y = y;
        this.type = type;
        

        
        if (type.equals(Item.Drill_Bag)) {
            image = ResourceTools.loadImageFromResource("images/Drill_Bag.png");
        }
    }
            public void draw(Graphics graphics){
                if (image != null) {
                    graphics.drawImage(image, x, y, null);
                }
        }
    public static final String Drill_Bag = "Drill_Bag";
    
}
