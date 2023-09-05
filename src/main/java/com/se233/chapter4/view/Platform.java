package com.se233.chapter4.view;

import com.se233.chapter4.Launcher;
import com.se233.chapter4.model.Mario;
import com.se233.chapter4.model.Keys;
import com.se233.chapter4.model.Sonic;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class Platform extends Pane {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static final int GROUND = 300;
    private Image platformImg;
    private Mario mario;
    private Sonic sonic;
    private Keys keys;

    public Platform() {
        this.keys = new Keys();
        this.platformImg = new Image(Launcher.class.getResourceAsStream("assets/Background.png"));
        ImageView backgroundImg = new ImageView(this.platformImg);
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        mario = new Mario(30, 30, 0, 0, KeyCode.A, KeyCode.D, KeyCode.W);
        sonic = new Sonic(700, 30, 0, 0, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP);

        this.getChildren().addAll(backgroundImg, sonic, mario);
    }

    public Mario getMario() {
        return mario;
    }

    public Sonic getSonic() {
        return sonic;
    }

    public Keys getKeys() {
        return keys;
    }
}
