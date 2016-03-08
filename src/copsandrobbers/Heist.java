/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copsandrobbers;

import audio.Playlist;
import audio.SoundManager;
import audio.Source;
import audio.Track;
import environment.Environment;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import path.TrigonometryCalculator;

/**
 *
 * @author WyattCampbell
 */
class Heist extends Environment {
//we need a more than one person game with the camera following the person and the gun follows the mouse.

    Bank bank;
    Projectile shoot;
    Robber robber;
    CrossHairs crossHairs;
    CheckIntercetion checkIntercetion;
    private ArrayList<Projectile> bullet;
    private ArrayList<Cop> cops;
    private String direction;
    private Point mousePosition;
    int robberSpeed = 2;

    public Heist() {
        bank = new Bank();
        robber = new Robber(0, 0, 0.0);
        bullet = new ArrayList<>();
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePosition = e.getPoint();
                repaint();
                robber.setAngleRadians(TrigonometryCalculator.calculateAngle(robber.centreOfMass(), mousePosition) + .75);
            }
        });
        setUpSound();
        cops = new ArrayList<>();
        cops.add(new Cop(100, 200, PROPERTIES));
    }
    SoundManager soundManager;
    public static final String RELOAD = "Relaod";
    public static final String EMPTYCLIP = "Empty Clip";
    public static final String SILENCESHOT = "Silence Shot";
    public static final String BULLETDROP = "Bullet Drop";

    private void setUpSound() {
//        set up list of trackes in a playlist
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(RELOAD, Source.FILE, "/sounds/Gun_reload.wav"));
        tracks.add(new Track(EMPTYCLIP, Source.FILE, "/sounds/Gun_empty.wav"));
        tracks.add(new Track(SILENCESHOT, Source.FILE, "/sounds/Silence_Shot.wav"));
        tracks.add(new Track(BULLETDROP, Source.FILE, "/sounds/Empty_bullet_drop.wav"));

//        Playlist
        Playlist playlist = new Playlist(tracks);
//        pass the playlist to a sound manager
        soundManager = new SoundManager(playlist);
    }

    @Override
    public void initializeEnvironment() {
    }

    @Override
    public void timerTaskHandler() {
        if (bullet != null) {
            for (Projectile projectile : bullet) {
                projectile.move();
            }
        }
        if (robber != null) {
            robber.move();
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
//            robber.move(-1,0);
            robber.setVelocity(new Velocity(-robberSpeed, 0));
            direction = "Left";
            System.out.println(robber.getX());
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
//            robber.move(1,0);
            robber.setVelocity(new Velocity(robberSpeed, 0));
            direction = "Right";
            System.out.println(robber.getX());

        } else if (e.getKeyCode() == KeyEvent.VK_W) {
//            robber.move(0,-1);
            robber.setVelocity(new Velocity(0, -robberSpeed));
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
//            robber.move(0,1);
            robber.setVelocity(new Velocity(0, robberSpeed));
        }
//        bulleting for now
        if (e.getKeyCode() == KeyEvent.VK_G || robber.mode == "Engaging") {
            addMouseMotionListener(new MouseAdapter() {
//                robber.setImage();
                @Override
                public void mouseMoved(MouseEvent e) {
                    mousePosition = e.getPoint();
                    repaint();
//                    System.out.println("Angle = " + TrigonometryCalculator.calculateAngle(robber.centreOfMass(), e.getPoint()) + " radians");
                    crossHairs = new CrossHairs(mousePosition);
                    robber.mode = "Suspicious";
                }
            });
        }
        
        if (robber.mode == "Engaging" || robber.mode == "Suspicious") {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            if (robber.magCount > 0) {
                if (robber.bulletCount < 25) {
                    soundManager.play(RELOAD, 1);
                    robber.reload();
                }
            }
        }
    }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_A)
                || (e.getKeyCode() == KeyEvent.VK_D)
                || (e.getKeyCode() == KeyEvent.VK_W)
                || (e.getKeyCode() == KeyEvent.VK_S)) {
            robber.stop();
//            robber.setVelocity(new Velocity(0, 0));
        }
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        if (robber.bulletCount > 0 && robber.mode == "Suspicious") {
            System.out.println("shot");
            bullet.add(new Projectile(robber.centreOfMass(), TrigonometryCalculator.calculateVelocity(robber.centreOfMass(), mousePosition, 50), -robber.getAngleRadians()));
            robber.bulletCount = robber.bulletCount - 1;
            soundManager.play(SILENCESHOT, 1);
                soundManager.play(BULLETDROP, 1);
            
        } else if (robber.bulletCount == 0) {
            soundManager.play(EMPTYCLIP, 1);
        }
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (bank != null) {
            bank.draw(graphics);
            graphics.drawString("Bullets" + robber.bulletCount + "/" + robber.magCount, 300, 300);
        }
        if (crossHairs != null) {
            crossHairs.draw(graphics);
        }
        if (bullet != null) {
            for (Projectile bulleting : bullet) {
                bulleting.draw(graphics);
            }
        }
        if (cops != null) {
            for (Cop cops : cops) {
                cops.draw(graphics);
            }
        }
        if (robber != null) {
            robber.draw(graphics);
        }
    }
}
