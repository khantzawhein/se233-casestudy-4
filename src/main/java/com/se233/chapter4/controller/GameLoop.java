package com.se233.chapter4.controller;

import com.se233.chapter4.model.Character;
import com.se233.chapter4.view.Platform;

public class GameLoop implements Runnable {
    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean running;

    public GameLoop(Platform platform) {
        this.platform = platform;
        this.frameRate = 10;
        interval = 1000.0f / frameRate;
        running = true;
    }
    private void update(Character character) {
        if (this.platform.getKeys().isPressed(character.getLeftKey()) || this.platform.getKeys().isPressed(character.getRightKey())) {
            character.getImageView().tick();
        }
        if (this.platform.getKeys().isPressed(character.getLeftKey())) {
            character.setScaleX(-1);
            character.moveLeft();
//            platform.getCharacter().trace();
        }
        if (this.platform.getKeys().isPressed(character.getRightKey())) {
            character.setScaleX(1);
            character.moveRight();
//            platform.getCharacter().trace();
        }
        if (!this.platform.getKeys().isPressed(character.getLeftKey()) && !this.platform.getKeys().isPressed(character.getRightKey())) {
            character.stop();
        }
        if (this.platform.getKeys().isPressed(character.getUpKey())) {
            character.jump();
        }
    }
    @Override
    public void run() {
        while (running) {
            float time = System.currentTimeMillis();
            update(platform.getCharacter());
            time = System.currentTimeMillis() - time;

            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep((long) (interval - (interval % time)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
