/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copsandrobbers;

/**
 *
 * @author WyattCampbell
 */
public class CheckIntercetion {
    Robber robber;
    Cop cop;
    Projectile projectile;
    
    public void checkIntercetion(){
        if (projectile.hitBox().intersects(cop.hitBox())) {
            cop.setHealth(cop.getHealth()-1);
        }
        if (projectile.hitBox().intersects(cop.hitBox())) {
            robber.setHealth(robber.getHealth()-1);
        }
    }
    
}
