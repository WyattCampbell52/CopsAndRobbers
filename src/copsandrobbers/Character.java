/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copsandrobbers;

import static copsandrobbers.Robber.SIDEARM_BULLET_COUNT;
import environment.Velocity;
import images.Animator;
import images.ImageManager;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 *
 * @author BBC132
 */
public class Character {
//<editor-fold defaultstate="collapsed" desc="Constructors">

    private int x;
    private int y;
    private CharacterState state = CharacterState.CALM_STAND;
    private CharacterType type = CharacterType.RobberDallas;
//    private Image image;
    private Animator animator;
    private int speed;
    private double angleRadians;
    private int suspiciousMeter;
    public int bulletCount;
    public int magCount;
    private Velocity velocity;
    private int health;
    public String mode;

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        x = 0;
        y = 0;

        velocity = new Velocity(0, 0);
        angleRadians = 0;

        bulletCount = SIDEARM_BULLET_COUNT;
        magCount = 5;
        mode = "Concealed";
    }

    //</editor-fold>
    public Character(int x, int y, double angleRadians, CharacterType type) {
        speed = 5;
        this.type = type;
        loadImages();
    }

    public Point centreOfMass() {
        return new Point(x + (getCharacterImage().getWidth(null) / 2), y + (getCharacterImage().getHeight(null) / 2));
    }

//<editor-fold defaultstate="collapsed" desc="Movement Methods">
    void move() {
        x += getVelocity().x;
        y += getVelocity().y;
    }

    void stop() {
        velocity.x = 0;
        velocity.y = 0;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Draw">
    public void draw(Graphics graphics) {
//        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angleRadians));
//        at.setToRotation(getAngleRadians() - 90, x + (getCharacterImage().getWidth(null) / 2), y + (getCharacterImage().getHeight(null) / 2));

        if (getType() == CharacterType.RobberWolf) {
            graphics.setColor(Color.RED);
        } else {
            graphics.setColor(Color.BLUE);
        }

//        graphics.drawImage(getCharacterImage(), getX(), getY(), null);
//        graphics.fillOval(getCenterOfMass().x, getCenterOfMass().y, 10, 10);
        Graphics2D g2d = (Graphics2D) graphics;
        AffineTransform olde = g2d.getTransform();

        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angleRadians));
        at.setToRotation(getAngleRadians() - 90, x + (getCharacterImage().getWidth(null) / 2), y + (getCharacterImage().getHeight(null) / 2));
        g2d.setTransform(at);
        g2d.drawImage(getCharacterImage(), x, y, null);
        g2d.drawImage(getCharacterImage(), x, y, null);
        graphics.drawRect(x, y, getCharacterImage().getWidth(null), getCharacterImage().getHeight(null));

        g2d.setTransform(olde);
        g2d.dispose();

    }

    public Rectangle rectangle() {
        return new Rectangle(getX(), getY(), getCharacterImage().getWidth(null), getCharacterImage().getHeight(null));
    }
//</editor-fold>
//
//<editor-fold defaultstate="collapsed" desc="States">

    public void calmRun() {
        setState(CharacterState.CALM_RUN);
    }

    public void assaultRun() {
        setState(CharacterState.ASSAULT_RUN);
    }

    public void calmStand() {
        setState(CharacterState.CALM_STAND);
    }

    public void assaultStand() {
        setState(CharacterState.ASSAULT_STAND);
    }

    public boolean isAlive() {
        return ((state != CharacterState.DEAD));
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Images">
    private static String COP_WHITE_BLACKHAIR_Holster_STANDING = "COP_WHITE_BLACKHAIR_Holster_STANDING";

    private static String ROBBER_DALLAS_Holster_STANDING = "ROBBER_DALLAS_Holster_STANDING";

    private static String ROBBER_HOXTON_Holster_STANDING = "ROBBER_HOXTON_Holster_STANDING";

    private static String ROBBER_WOLF_Holster_STANDING = "ROBBER_WOLF_Holster_STANDING";

    private static String ROBBER_CHAINS_Holster_STANDING = "ROBBER_CHAINS_Holster_STANDING";

    private final static ArrayList<String> calmStandCopWhiteBlackHairHolster = new ArrayList<>();
    private final static ArrayList<String> calmStandRobberDallasHolster = new ArrayList<>();
    private final static ArrayList<String> calmStandRobberHoxtonHolster = new ArrayList<>();
    private final static ArrayList<String> calmStandRobberWolfHolster = new ArrayList<>();
    private final static ArrayList<String> calmStandRobberChainsHolster = new ArrayList<>();
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Other Methods">
    public boolean reload() {
        if (magCount > 0) {
            magCount--;
            bulletCount = SIDEARM_BULLET_COUNT;
            return true;
        }
        return false;
    }

    public void danger(String string) {
        if (string == "visible") {
            setSuspiciousMeter(getSuspiciousMeter() + 1);
        } else if (getSuspiciousMeter() > 0) {
            setSuspiciousMeter(getSuspiciousMeter() - 1);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Animator">
    private void loadImages() {
        calmStandCopWhiteBlackHairHolster.add(COP_WHITE_BLACKHAIR_Holster_STANDING);
        calmStandRobberDallasHolster.add(ROBBER_DALLAS_Holster_STANDING);
        calmStandRobberHoxtonHolster.add(ROBBER_HOXTON_Holster_STANDING);
        calmStandRobberWolfHolster.add(ROBBER_WOLF_Holster_STANDING);
        calmStandRobberChainsHolster.add(ROBBER_CHAINS_Holster_STANDING);

        String[] imageNames = {COP_WHITE_BLACKHAIR_Holster_STANDING, ROBBER_DALLAS_Holster_STANDING,
            ROBBER_HOXTON_Holster_STANDING, ROBBER_WOLF_Holster_STANDING,
            ROBBER_CHAINS_Holster_STANDING};

        Image[] images = new Image[5];
        images[0] = ResourceTools.loadImageFromResource("images/White_Guard_HairBlack_One.png");
        images[1] = ResourceTools.loadImageFromResource("images/Dallas_Unmasked.png");
        images[2] = ResourceTools.loadImageFromResource("images/Hoxton_Unmasked.png");
        images[3] = ResourceTools.loadImageFromResource("images/Wolf_Unmasked.png");
        images[4] = ResourceTools.loadImageFromResource("images/Chains_Unmasked.png");

        ImageManager imageManager = new ImageManager(imageNames, images);
        if (getType() == CharacterType.RobberWolf) {
            animator = new Animator(imageManager, calmStandRobberWolfHolster, 200);
        } else if (getType() == CharacterType.RobberDallas) {
            animator = new Animator(imageManager, calmStandRobberDallasHolster, 200);

        }else if (getType() == CharacterType.RobberHoxton) {
            animator = new Animator(imageManager, calmStandRobberHoxtonHolster, 200);

        }else if (getType() == CharacterType.RobberChains) {
            animator = new Animator(imageManager, calmStandRobberChainsHolster, 200);

        }else if (getType() == CharacterType.CopWhiteBlackHair) {
            animator = new Animator(imageManager, calmStandCopWhiteBlackHairHolster, 200);

        }
    }

    private Image getCharacterImage() {
//        return image;
        return animator.getCurrentImage();
    }
//</editor-fold> 

//<editor-fold defaultstate="collapsed" desc="property">
    /**
     * @return the x
     */
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

    /**
     * @return the angleRadiansRadians
     */
    public double getAngleRadians() {
        return angleRadians;
    }

    /**
     * @param angleRadiansRadians the angleRadiansRadians to set
     */
    public void setAngleRadians(double angleRadiansRadians) {
        this.angleRadians = angleRadiansRadians;
    }

    /**
     * @return the velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * @param velocity the velocity to set
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * @return the suspiciousMeter
     */
    public int getSuspiciousMeter() {
        return suspiciousMeter;
    }

    /**
     * @param suspiciousMeter the suspiciousMeter to set
     */
    public void setSuspiciousMeter(int suspiciousMeter) {
        this.suspiciousMeter = suspiciousMeter;
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the state
     */
    public CharacterState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(CharacterState state) {
        if (this.state != state) {
            this.state = state;

            if (animator != null) {
                if (getType() == CharacterType.CopWhiteBlackHair) {
                    if (state == CharacterState.CALM_STAND) {
                        animator.setImageNames(calmStandCopWhiteBlackHairHolster);
                    } else {
                        animator.setImageNames(calmStandCopWhiteBlackHairHolster);
                    }
                }
                if (getType() == CharacterType.RobberDallas) {
                    if (state == CharacterState.CALM_STAND) {
                        animator.setImageNames(calmStandRobberDallasHolster);
                    } else {
                        animator.setImageNames(calmStandRobberDallasHolster);
                    }
                }
                if (getType() == CharacterType.RobberChains) {
                    if (state == CharacterState.CALM_STAND) {
                        animator.setImageNames(calmStandRobberChainsHolster);
                    } else {
                        animator.setImageNames(calmStandRobberChainsHolster);
                    }
                }
                if (getType() == CharacterType.RobberHoxton) {
                    if (state == CharacterState.CALM_STAND) {
                        animator.setImageNames(calmStandRobberHoxtonHolster);
                    } else {
                        animator.setImageNames(calmStandRobberHoxtonHolster);
                    }
                }
                if (getType() == CharacterType.RobberWolf) {
                    if (state == CharacterState.CALM_STAND) {
                        animator.setImageNames(calmStandRobberWolfHolster);
                    } else {
                        animator.setImageNames(calmStandRobberWolfHolster);
                    }
                } else {
                    animator.setImageNames(calmStandRobberHoxtonHolster);

                }
            }
        }

    }

    /**
     * @return the type
     */
    public CharacterType getType() {
        return type;
    }

//</editor-fold>
}
