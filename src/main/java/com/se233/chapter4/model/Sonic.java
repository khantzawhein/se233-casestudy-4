package com.se233.chapter4.model;

import com.se233.chapter4.Launcher;
import com.se233.chapter4.view.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Sonic extends Pane {
    private static final Logger logger = LogManager.getLogger(Sonic.class);
    public static final int CHARACTER_WIDTH = 64;
    public static final int CHARACTER_HEIGHT = 64;
    private Image characterImg;
    private AnimatedSprite imageView;
    private int x,y;
    private KeyCode leftKey, rightKey, upKey;
    int xVelocity = 0;
    int yVelocity = 0;
    int xAcceleration = 2;
    int yAcceleration = 4;
    int xMaxVelocity = 17;
    int yMaxVelocity = 40;
    boolean isFalling = true;
    boolean canJump = false;
    boolean isJumping = false;
    boolean isMoveLeft, isMoveRight = false;

    public Sonic(int x, int y, int offsetX, int offsetY, KeyCode leftKey, KeyCode rightKey, KeyCode upKey) {
        this.x = x;
        this.y = y;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setScaleX(-1);
        this.characterImg = new Image(Launcher.class.getResourceAsStream("assets/SonicSheet.png"));
        this.imageView = new AnimatedSprite(characterImg, 5, 5, 1, offsetX, offsetY, 58, 61);
        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.getChildren().addAll(this.imageView);
    }

    public void trace() {
//        logger.info("x:{} y:{}, vx:{}, vy:{}", x, y, xVelocity, yVelocity);
    }

    public void moveY() {
        setTranslateY(y);
        if (isFalling) {
            yVelocity = yVelocity >= yMaxVelocity ? yMaxVelocity : yVelocity + yAcceleration;
            y = y + yVelocity;
        } else if (isJumping) {
            yVelocity = yVelocity <= 0 ? 0 : yVelocity - yAcceleration;
            y = y - yVelocity;
        }
    }

    public void moveX() {
        setTranslateX(x);
        if (isMoveLeft) {
            xVelocity = xVelocity >= xMaxVelocity ? xMaxVelocity : xVelocity + xAcceleration;
            x = x - xVelocity;
        } else if (isMoveRight) {
            xVelocity = xVelocity >= xMaxVelocity ? xMaxVelocity : xVelocity + xAcceleration;
            x = x + xVelocity;
        }
    }

    public void jump() {
        if (canJump) {
            yVelocity = yMaxVelocity;
            canJump = false;
            isJumping = true;
            isFalling = false;
        }
    }

    public void checkReachHighest() {
        if (isJumping && yVelocity <= 0) {
            isJumping = false;
            isFalling = true;
        }
    }

    public void moveLeft() {
        isMoveLeft = true;
        isMoveRight = false;
    }

    public void moveRight() {
        isMoveRight = true;
        isMoveLeft = false;
    }

    public void stop() {
        isMoveRight = isMoveLeft = false;
    }

    public void checkReachFloor() {
        if (isFalling && y >= Platform.GROUND - CHARACTER_HEIGHT) {
            isFalling = false;
            canJump = true;
            yVelocity = 0;
        }
    }

    public void checkReachGameWall() {
        if (x <= 0) {
            x = 0;
        } else if (x + getWidth() >= Platform.WIDTH) {
            x = Platform.WIDTH - (int) getWidth();
        }
    }

    public void repaint() {
        moveX();
        moveY();
    }

    public KeyCode getLeftKey() {
        return leftKey;
    }

    public KeyCode getRightKey() {
        return rightKey;
    }

    public KeyCode getUpKey() {
        return upKey;
    }

    public AnimatedSprite getImageView() {
        return imageView;
    }
}
