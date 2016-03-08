/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copsandrobbers;

import environment.ApplicationStarter;
import java.awt.Dimension;

/**
 *
 * @author WyattCampbell
 */
public class CopsAndRobbers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ApplicationStarter.run(new String[0], "Bank Heist!", new Dimension(1439, 900), new Heist());
    }
    
}
