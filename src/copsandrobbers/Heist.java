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
    private ArrayList<Item> items;
    private ArrayList<Projectile> bullets;
    private ArrayList<Character> cops;
    private Point mousePosition;
    private Point chase;
    int characterSpeed = 8;

    public Heist() {
        character = new Character(620, 400, 0.0, CharacterType.RobberDallas);
        bank = new Bank();
        bullets = new ArrayList<>();
        items = new ArrayList<>();
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePosition = e.getPoint();
                character.setAngleRadians(TrigonometryCalculator.calculateAngle(character.centerOfMass(), mousePosition) + .71);
                repaint();
            }
        });
        setUpSound();
        cops = new ArrayList<>();
        items.add(new Item(200, 50, Item.Drill_Bag));
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

    //<editor-fold defaultstate="collapsed" desc="TimerTaskHandler">
    @Override
    public void timerTaskHandler() {

        if (bullets != null) {
            for (Projectile projectile : bullets) {
                projectile.move();
            }
            if (character != null) {
                character.move();
                if (bank != null) {
                    if (cops != null) {
                      for (Character cop : cops) {
                        cop.move();
                        bank.move();
                        boundries();
                    }  
                    }
                    
                }

            }
        }
        contact();
        assault();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Contact, Boundries, and Assault">
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
                    }
                    for (Rectangle boundary : bank.boundries) {
                        if (projectile.hitBox().intersects(boundary)) {
                            toBulletRemoves.add(projectile);
                        }
                    }
                }

                cops.removeAll(toCopRemoves);
                bullets.removeAll(toBulletRemoves);
            }
            if (character != null) {
                character.move();
            }
        }
    }

    public void assault() {
        //if cop can see character, he shoots randomly
        if ((cops != null) && (character != null)) {
            for (Character cop : cops) {
                if (character.mode == "assault") {
                    if (cop.circle().intersects(character.hitBox().x, character.hitBox().y, character.hitBox().width, character.hitBox().height) || cop.sight().intersects(character.hitBox())) {

                        if (Math.random() < .05) {
                            if (bullets != null) {
                                cop.setAngleRadians(TrigonometryCalculator.calculateAngle(new Point(cop.getX(), cop.getY()), character.centerOfMass()) + .75);
                                if (cop.getState() == CharacterState.ASSAULT_RUN || cop.getState() == CharacterState.ASSAULT_STAND || cop.getSuspiciousMeter() > 60) {
                                    bullets.add(new Projectile(cop.centerOfMass(), TrigonometryCalculator.calculateVelocity(cop.centerOfMass(), character.centerOfMass(), 80), cop.getAngleRadians()));
                                } else if (cop.getState() == CharacterState.CALM_RUN || cop.getState() == CharacterState.CALM_STAND) {
                                    cop.setSuspiciousMeter(cop.getSuspiciousMeter() + 1);
                                }
                            }
                        }
                    } else if (cop.getSuspiciousMeter() < 60) {
                        cop.setSuspiciousMeter(cop.getSuspiciousMeter() - 1);
                    }
                }
            }
        }
    }

    public void boundries() {
        for (Rectangle boundary : bank.boundries) {
            if (character.hitBox().intersects(boundary)) {
                character.setVelocity(new Velocity(0, 0));
                if (character.getDirection() == CharacterMovement.Up) {
                    character.setDirection(CharacterMovement.StopUp);
                } else if (character.getDirection() == CharacterMovement.Down) {
                    character.setDirection(CharacterMovement.StopDown);
                } else if (character.getDirection() == CharacterMovement.Left) {
                    character.setDirection(CharacterMovement.StopLeft);
                } else if (character.getDirection() == CharacterMovement.Right) {
                    character.setDirection(CharacterMovement.StopRight);
                }
            }
        }
    }
//</editor-fold>

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A && character.getDirection() != CharacterMovement.StopLeft) {
            character.setDirection(CharacterMovement.Left);
            for (Character cop : cops) {
                cop.setVelocity(new Velocity(characterSpeed, 0));
                bank.setVelocity(new Velocity(characterSpeed, 0));
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D && character.getDirection() != CharacterMovement.StopRight) {
            character.setDirection(CharacterMovement.Right);
            for (Character cop : cops) {
                cop.setVelocity(new Velocity(-characterSpeed, 0));
                bank.setVelocity(new Velocity(-characterSpeed, 0));
            }
        } else if (e.getKeyCode() == KeyEvent.VK_W && character.getDirection() != CharacterMovement.StopUp) {
            character.setDirection(CharacterMovement.Up);
            for (Character cop : cops) {
                cop.setVelocity(new Velocity(0, characterSpeed));
                bank.setVelocity(new Velocity(0, characterSpeed));
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S && character.getDirection() != CharacterMovement.StopDown) {
            character.setDirection(CharacterMovement.Down);
            for (Character cop : cops) {
                cop.setVelocity(new Velocity(0, -characterSpeed));
                bank.setVelocity(new Velocity(0, -characterSpeed));
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_G || character.mode == "Engaging") {
            character.assaultStand();
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
            for (Character cop : cops) {
                cop.setVelocity(new Velocity(0, 0));
                bank.setVelocity(new Velocity(0, 0));
            }
        }
    }

    @Override
    public void environmentMouseClicked(MouseEvent e
    ) {
        if (character.bulletCount > 0) {
            System.out.println("shot");
            bullets.add(new Projectile(character.centerOfMass(), TrigonometryCalculator.calculateVelocity(character.centerOfMass(), mousePosition, 80), -character.getAngleRadians()));
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
            graphics.drawString("Bullets" + character.bulletCount + "/" + character.magCount, 1300, 800);
        }
        if (items !=null) {
            for (Item drillbag :items){
            drillbag.draw(graphics);
        }
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
