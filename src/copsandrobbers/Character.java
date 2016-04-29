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
import javafx.scene.shape.Circle;

/**
 *
 * @author BBC132
 */
public class Character {

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

    public Character(int x, int y, double angleRadians, CharacterType type) {
        this.x = x;
        this.y = y;
        speed = 5;
        this.type = type;
        loadImages();
    }
    //</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Draw">
    public void draw(Graphics graphics) {
//        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angleRadians));
//        at.setToRotation(getAngleRadians() - 90, x + (getCharacterImage().getWidth(null) / 2), y + (getCharacterImage().getHeight(null) / 2));

        if (getType() == CharacterType.RobberDallas) {
            graphics.setColor(Color.RED);
        }
        if (getType() == CharacterType.RobberWolf) {
            graphics.setColor(Color.BLUE);
        }
        if (getType() == CharacterType.RobberHouston) {

            graphics.setColor(Color.GREEN);
        }
        if (getType() == CharacterType.RobberChains) {
            graphics.setColor(Color.BLACK);
        } else {
            graphics.setColor(Color.WHITE);
        }

        Graphics2D g2d = (Graphics2D) graphics;
        AffineTransform olde = g2d.getTransform();

        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angleRadians));
        at.setToRotation(getAngleRadians() - 90, x + (getCharacterImage().getWidth(null) / 2), y + (getCharacterImage().getHeight(null) / 2));
        g2d.setTransform(at);
//        g2d.drawImage(getCharacterImage(), x, y, null);
        g2d.drawImage(getCharacterImage(), x, y, getCharacterImage().getWidth(null) * 2, getCharacterImage().getHeight(null) * 2, null);
        graphics.drawRect(x + 67, y + 60, getCharacterImage().getWidth(null) / 2 + 20, getCharacterImage().getHeight(null) - 30);
        graphics.drawRect(x + 67, y + 60, getCharacterImage().getWidth(null) / 2 + 20, getCharacterImage().getHeight(null) * 4);

        g2d.setTransform(olde);
    }

    public Point centerOfMass() {
        return new Point(x + getCharacterImage().getWidth(null) / 2, y + getCharacterImage().getHeight(null) / 2);
    }

    public Rectangle hitBox() {
        return new Rectangle(getX(), getY(), getCharacterImage().getWidth(null) * 2, getCharacterImage().getHeight(null) * 2);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="States">
    public void Run() {
        setState(CharacterState.CALM_RUN);
    }

    public void assaultRun() {
        setState(CharacterState.ASSAULT_RUN);
    }

    public void Stand() {
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
    private static String COP_WHITE_BLACKHAIR_Unholster_STANDING_01 = "COP_WHITE_BLACKHAIR_Unholster_STANDING";
    private static String COP_WHITE_BLACKHAIR_Unholster_STANDING_02 = "COP_WHITE_BLACKHAIR_Unholster_STANDING";

    private static String ROBBER_DALLAS_Holster_STANDING = "ROBBER_DALLAS_Holster_STANDING";
    private static String ROBBER_DALLAS_Unholster_STANDING_01 = "ROBBER_DALLAS_Unolster_STANDING";
    private static String ROBBER_DALLAS_Unholster_STANDING_02 = "ROBBER_DALLAS_Unolster_STANDING";

    private static String ROBBER_HOUSTON_Holster_STANDING = "ROBBER_HOUSTON_Holster_STANDING";
    private static String ROBBER_HOUSTON_Unholster_STANDING_01 = "ROBBER_HOUSTON_Unolster_STANDING";
    private static String ROBBER_HOUSTON_Unholster_STANDING_02 = "ROBBER_HOUSTON_Unolster_STANDING";

    private static String ROBBER_WOLF_Holster_STANDING = "ROBBER_WOLF_Holster_STANDING";
    private static String ROBBER_WOLF_Unholster_STANDING_01 = "ROBBER_WOLF_Unolster_STANDING";
    private static String ROBBER_WOLF_Unholster_STANDING_02 = "ROBBER_WOLF_Unolster_STANDING";

    private static String ROBBER_CHAINS_Holster_STANDING = "ROBBER_CHAINS_Holster_STANDING";
    private static String ROBBER_CHAINS_Unholster_STANDING_01 = "ROBBER_CHAINS_Unolster_STANDING";
    private static String ROBBER_CHAINS_Unholster_STANDING_02 = "ROBBER_CHAINS_Unolster_STANDING";

    private final static ArrayList<String> StandCopWhiteBlackHairHolster = new ArrayList<>();
    private final static ArrayList<String> StandCopWhiteBlackHairUnholster = new ArrayList<>();

    private final static ArrayList<String> StandRobberDallasHolster = new ArrayList<>();
    private final static ArrayList<String> StandRobberDallasUnholster = new ArrayList<>();

    private final static ArrayList<String> StandRobberHoustonHolster = new ArrayList<>();
    private final static ArrayList<String> StandRobberHoustonUnholster = new ArrayList<>();

    private final static ArrayList<String> StandRobberWolfHolster = new ArrayList<>();
    private final static ArrayList<String> StandRobberWolfUnholster = new ArrayList<>();

    private final static ArrayList<String> StandRobberChainsHolster = new ArrayList<>();
    private final static ArrayList<String> StandRobberChainsUnholster = new ArrayList<>();
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
        StandCopWhiteBlackHairHolster.add(COP_WHITE_BLACKHAIR_Holster_STANDING);
        StandCopWhiteBlackHairUnholster.add(COP_WHITE_BLACKHAIR_Unholster_STANDING_01);
        StandCopWhiteBlackHairUnholster.add(COP_WHITE_BLACKHAIR_Unholster_STANDING_02);

        StandRobberDallasHolster.add(ROBBER_DALLAS_Holster_STANDING);
        StandRobberDallasUnholster.add(ROBBER_DALLAS_Unholster_STANDING_01);
        StandRobberDallasUnholster.add(ROBBER_DALLAS_Unholster_STANDING_02);

        StandRobberHoustonHolster.add(ROBBER_HOUSTON_Holster_STANDING);
        StandRobberHoustonUnholster.add(ROBBER_HOUSTON_Unholster_STANDING_01);
        StandRobberHoustonUnholster.add(ROBBER_HOUSTON_Unholster_STANDING_02);

        StandRobberWolfHolster.add(ROBBER_WOLF_Holster_STANDING);
        StandRobberWolfUnholster.add(ROBBER_WOLF_Unholster_STANDING_01);
        StandRobberWolfUnholster.add(ROBBER_WOLF_Unholster_STANDING_02);

        StandRobberChainsHolster.add(ROBBER_CHAINS_Holster_STANDING);
        StandRobberChainsUnholster.add(ROBBER_CHAINS_Unholster_STANDING_01);
        StandRobberChainsUnholster.add(ROBBER_CHAINS_Unholster_STANDING_02);

        String[] imageNames = {
            COP_WHITE_BLACKHAIR_Holster_STANDING, COP_WHITE_BLACKHAIR_Unholster_STANDING_01, COP_WHITE_BLACKHAIR_Unholster_STANDING_02,
            ROBBER_DALLAS_Holster_STANDING, ROBBER_DALLAS_Unholster_STANDING_01, ROBBER_DALLAS_Unholster_STANDING_02,
            ROBBER_HOUSTON_Holster_STANDING, ROBBER_HOUSTON_Unholster_STANDING_01, ROBBER_HOUSTON_Unholster_STANDING_02,
            ROBBER_WOLF_Holster_STANDING, ROBBER_WOLF_Unholster_STANDING_01, ROBBER_WOLF_Unholster_STANDING_02,
            ROBBER_CHAINS_Holster_STANDING, ROBBER_CHAINS_Unholster_STANDING_01, ROBBER_CHAINS_Unholster_STANDING_02};

        Image[] images = new Image[15];
        images[0] = ResourceTools.loadImageFromResource("images/Cop_White_BlackHair_Holster.png");
        images[1] = ResourceTools.loadImageFromResource("images/Cop_White_BlackHair_Holster.png");
        images[2] = ResourceTools.loadImageFromResource("images/Cop_White_BlackHair_Holster.png");
        images[3] = ResourceTools.loadImageFromResource("images/Dallas_Unmasked.png");
        images[4] = ResourceTools.loadImageFromResource("images/Dallas_Primary_01.png");
        images[5] = ResourceTools.loadImageFromResource("images/Dallas_Primary_02.png");
        images[6] = ResourceTools.loadImageFromResource("images/Houston_Unmaksed.png");
        images[7] = ResourceTools.loadImageFromResource("images/Houston_Primary_01.png");
        images[8] = ResourceTools.loadImageFromResource("images/Geb.png");
        images[9] = ResourceTools.loadImageFromResource("images/Wolf_Unmasked.png");
        images[10] = ResourceTools.loadImageFromResource("images/Ted.png");
        images[11] = ResourceTools.loadImageFromResource("images/Wolf_Primary_02.png");
        images[12] = ResourceTools.loadImageFromResource("images/Chains_Unmasked.png");
        images[13] = ResourceTools.loadImageFromResource("images/Chains_Primary_01.png");
        images[14] = ResourceTools.loadImageFromResource("images/Chains_Primary_02.png");

        ImageManager imageManager = new ImageManager(imageNames, images);
        if (getType() == CharacterType.RobberDallas) {
            animator = new Animator(imageManager, StandRobberDallasHolster, 200);
        } else if (getType() == CharacterType.RobberWolf) {
            animator = new Animator(imageManager, StandRobberWolfHolster, 200);
        } else if (getType() == CharacterType.RobberChains) {
            animator = new Animator(imageManager, StandRobberChainsHolster, 200);
        } else if (getType() == CharacterType.CopWhiteBlackHair) {
            animator = new Animator(imageManager, StandCopWhiteBlackHairHolster, 200);

        } else if (getType() == CharacterType.RobberHouston) {
            animator = new Animator(imageManager, StandRobberHoustonHolster, 200);
        } else {
            animator = new Animator(imageManager, StandCopWhiteBlackHairHolster, 200);
        }

    }

    public Image getCharacterImage() {
        if (animator != null) {
            return animator.getCurrentImage();
        } else {
            return null;
        }
    }
//</editor-fold> 

//<editor-fold defaultstate="collapsed" desc="property">
    private int x;
    private int y;
    private CharacterState state = CharacterState.CALM_STAND;
    private CharacterType type = CharacterType.RobberWolf;
    private CharacterMovement direction;
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
                        animator.setImageNames(StandCopWhiteBlackHairHolster);
                    } else if (state == CharacterState.ASSAULT_STAND) {
                        animator.setImageNames(StandCopWhiteBlackHairUnholster);
                    } else {
                        animator.setImageNames(StandCopWhiteBlackHairHolster);
                    }
                }
                if (getType() == CharacterType.RobberDallas) {
                    if (state == CharacterState.CALM_STAND) {
                        animator.setImageNames(StandRobberDallasHolster);
                    } else if (state == CharacterState.ASSAULT_STAND) {
                        animator.setImageNames(StandRobberDallasUnholster);
                    } else {
                        animator.setImageNames(StandRobberDallasHolster);
                    }
                }
                if (getType() == CharacterType.RobberChains) {
                    if (state == CharacterState.CALM_STAND) {
                        animator.setImageNames(StandRobberChainsHolster);
                    } else if (state == CharacterState.ASSAULT_STAND) {
                        animator.setImageNames(StandRobberChainsUnholster);
                    } else {
                    }
                    if (getType() == CharacterType.RobberHouston) {
                        if (state == CharacterState.CALM_STAND) {
                            animator.setImageNames(StandRobberHoustonHolster);
                        } else if (state == CharacterState.ASSAULT_STAND) {
                            animator.setImageNames(StandRobberHoustonUnholster);
                        } else {
                            animator.setImageNames(StandRobberHoustonHolster);
                        }
                    }
                    if (getType() == CharacterType.RobberWolf) {
                        if (state == CharacterState.CALM_STAND) {
                            animator.setImageNames(StandRobberWolfHolster);
                        } else if (state == CharacterState.ASSAULT_STAND) {
                            animator.setImageNames(StandRobberWolfUnholster);
                        } else {
                            animator.setImageNames(StandRobberWolfHolster);
                        }
                    }
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

    public Point centreOfMass() {
        return new Point(x + (getCharacterImage().getWidth(null) / 2), y + (getCharacterImage().getHeight(null) / 2));
    }

    public Circle circle() {
        return new Circle(x + getCharacterImage().getWidth(null) / 2, y + getCharacterImage().getHeight(null) / 2, getCharacterImage().getWidth(null) * 6);
    }

    public Rectangle sight() {
        return new Rectangle(x, y, getCharacterImage().getWidth(null) * 2, getCharacterImage().getHeight(null) * 10);
    }
//</editor-fold>

    /**
     * @return the direction
     */
    public CharacterMovement getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(CharacterMovement direction) {
        this.direction = direction;
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

}
