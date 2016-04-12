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
import javafx.scene.shape.Shape;
import javax.lang.model.type.TypeKind;
import path.TrigonometryCalculator;

/**
 *
 * @author WyattCampbell
 */
class Heist extends Environment {
//we need a more than one person game with the camera following the person and the gun follows the mouse.

    Bank bank;
    Projectile shoot;
    Character character;
    CrossHairs crossHairs;
    private ArrayList<Projectile> bullets;
    private ArrayList<Character> cops;
    private Point mousePosition;
    private Point wall;
    int characterSpeed = 2;

    public Heist() {
        character = new Character(650, 700, 0.0, CharacterType.RobberWolf);
        bank = new Bank();
        bullets = new ArrayList<>();
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePosition = e.getPoint();
                character.setAngleRadians(TrigonometryCalculator.calculateAngle(character.centerOfMass(), mousePosition) + .75);
                repaint();
            }
        });
        setUpSound();
        cops = new ArrayList<>();
        cops.add(new Character(100, 200, 0.0, CharacterType.CopWhiteBlackHair));
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

        if (bullets != null) {
            for (Projectile projectile : bullets) {
                projectile.move();
            }
            if (character != null) {
                character.move();
                for (Rectangle boundary : bank.boundries) {
//                    character.border(boundary);
                }
                character.move();

            }
            if (cops != null) {
                for (Character character : cops) {
                    character.move();
                }
            }
        }
        contact();
        assault();

    }

    //<editor-fold defaultstate="collapsed" desc="Contact and Assault">
    private void contact() {
        if (bullets != null) {
            if (cops != null) {
                ArrayList<Character> toCopRemoves = new ArrayList<>();
                ArrayList<Projectile> toBulletRemoves = new ArrayList<>();
                for (Projectile projectile : bullets) {
                    for (Character cop : cops) {
                        if (cop.hitBox().intersects(projectile.hitBox())) {
                            System.out.println("hit");
                            toCopRemoves.add(cop);
                            toBulletRemoves.add(projectile);
                        }
                        if (cop.circle().intersects(character.hitBox().x, character.hitBox().y, character.hitBox().width, character.hitBox().height)) {
                            character.danger("visible");
                        }
                    }
                    for (Rectangle boundary : bank.boundries) {
                        if (boundary.intersects(projectile.hitBox())) {
                            toBulletRemoves.add(projectile);
                        }
                    }
                }
                cops.removeAll(toCopRemoves);
                bullets.removeAll(toBulletRemoves);
            }
        }
        if (character != null) {
            character.move();
        }
    }

    public void assault() {
        //if cop can see character, the shoot randomlyd

        if ((cops != null) && (character != null)) {
            for (Character cop : cops) {
                if (character.mode == "assault") {
                    if (cop.circle().intersects(character.hitBox().x, character.hitBox().y, character.hitBox().width, character.hitBox().height)) {
                        if (Math.random() < .05) {
                            if (bullets != null) {
                                cop.setAngleRadians(TrigonometryCalculator.calculateAngle(new Point(cop.getX(), cop.getY()), character.centreOfMass()) + .75);
                                bullets.add(new Projectile(cop.centreOfMass(), TrigonometryCalculator.calculateVelocity(cop.centreOfMass(), character.centreOfMass(), 80), cop.getAngleRadians()));
                            }
                        }
                    }
                }
            }
        }
    }
//</editor-fold>

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            character.setVelocity(new Velocity(-characterSpeed, 0));
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            character.setVelocity(new Velocity(characterSpeed, 0));
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            character.setVelocity(new Velocity(0, -characterSpeed));
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            character.setVelocity(new Velocity(0, characterSpeed));
        }
        if (e.getKeyCode() == KeyEvent.VK_G || character.mode == "Engaging") {
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    character.mode = "Suspicious";
                    mousePosition = e.getPoint();
                    repaint();
                    crossHairs = new CrossHairs(mousePosition);
                }
            });
        }

        if ((character.mode == "Engaging" || character.mode == "Suspicious") && e.getKeyCode() == KeyEvent.VK_R) {
            if (character.magCount > 0) {
                if (character.bulletCount < 9) {
                    soundManager.play(RELOAD, 1);
                    character.reload();
                }
            }
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e
    ) {
        if ((e.getKeyCode() == KeyEvent.VK_A)
                || (e.getKeyCode() == KeyEvent.VK_D)
                || (e.getKeyCode() == KeyEvent.VK_W)
                || (e.getKeyCode() == KeyEvent.VK_S)) {
            character.stop();
        }
    }

    @Override
    public void environmentMouseClicked(MouseEvent e
    ) {
        if (character.bulletCount > 0 && character.mode == "Suspicious") {
            System.out.println("shot");
            bullets.add(new Projectile(character.centreOfMass(), TrigonometryCalculator.calculateVelocity(character.centreOfMass(), mousePosition, 80), -character.getAngleRadians()));
            character.bulletCount = character.bulletCount - 1;
            soundManager.play(SILENCESHOT, 1);
            soundManager.play(BULLETDROP, 1);
        } else if (character.bulletCount == 0) {
            soundManager.play(EMPTYCLIP, 1);
        }
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (bank != null) {
            bank.draw(graphics);
            graphics.drawString("Bullets" + character.bulletCount + "/" + character.magCount, 300, 300);
        }
        if (crossHairs != null) {
            crossHairs.draw(graphics);
        }
        if (bullets != null) {
            for (Projectile bulleting : bullets) {
                bulleting.draw(graphics);
            }
        }
        if (cops != null) {
            for (Character cop : cops) {
                cop.draw(graphics);
            }
        }
        if (character != null) {
            character.draw(graphics);
        }
    }
}
