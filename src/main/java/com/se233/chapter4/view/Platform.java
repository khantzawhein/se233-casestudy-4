package com.se233.chapter4.view;

import com.se233.chapter4.Launcher;
import com.se233.chapter4.model.Character;
import com.se233.chapter4.model.Keys;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class Platform extends Pane {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static final int GROUND = 300;
    private Image platformImg;
    private Character character;
    private Keys keys;

    public Platform() {
        this.keys = new Keys();
        this.platformImg = new Image(Launcher.class.getResourceAsStream("assets/Background.png"));
        ImageView backgroundImg = new ImageView(this.platformImg);
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        character = new Character(30, 30, 0, 0, KeyCode.A, KeyCode.D, KeyCode.W);
        this.getChildren().addAll(backgroundImg, character);
    }

    public Character getCharacter() {
        return character;
    }

    public Keys getKeys() {
        return keys;
    }
}
