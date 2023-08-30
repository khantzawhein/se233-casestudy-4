package com.se233.chapter4.controller;

import com.se233.chapter4.model.Character;
import com.se233.chapter4.view.Platform;

public class DrawingLoop implements Runnable {
    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean running;

    public DrawingLoop(Platform platform) {
        this.platform = platform;
        this.frameRate = 30;
        interval = 1000.0f / frameRate;
        running = true;
    }

    private void checkDrawCollisions(Character character) {
        character.checkReachGameWall();
        character.checkReachHighest();
        character.checkReachFloor();
    }

    private void paint(Character character) {
        character.repaint();
    }

    @Override
    public void run() {
        while (running) {
            float time = System.currentTimeMillis();
            checkDrawCollisions(platform.getCharacter());
            paint(platform.getCharacter());
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
