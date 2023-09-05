package com.se233.chapter4.controller;

import com.se233.chapter4.model.Mario;
import com.se233.chapter4.model.Sonic;
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

    private void update(Mario mario, Sonic sonic) {
        if (this.platform.getKeys().isPressed(mario.getLeftKey()) || this.platform.getKeys().isPressed(mario.getRightKey())) {
            mario.getImageView().tick();
            mario.trace();
        }

        if (this.platform.getKeys().isPressed(sonic.getLeftKey()) || this.platform.getKeys().isPressed(sonic.getRightKey())) {
            sonic.getImageView().tick();
            sonic.trace();
        }

        if (this.platform.getKeys().isPressed(mario.getLeftKey())) {
            mario.setScaleX(-1);
            mario.moveLeft();
        }

        if (this.platform.getKeys().isPressed(sonic.getLeftKey())) {
            sonic.setScaleX(-1);
            sonic.moveLeft();
        }

        if (this.platform.getKeys().isPressed(mario.getRightKey())) {
            mario.setScaleX(1);
            mario.moveRight();
        }

        if (this.platform.getKeys().isPressed(sonic.getRightKey())) {
            sonic.setScaleX(1);
            sonic.moveRight();
        }

        if (!this.platform.getKeys().isPressed(mario.getLeftKey()) && !this.platform.getKeys().isPressed(mario.getRightKey())) {
            mario.stop();
        }
        if (this.platform.getKeys().isPressed(mario.getUpKey())) {
            mario.jump();
        }

        if (!this.platform.getKeys().isPressed(sonic.getLeftKey()) && !this.platform.getKeys().isPressed(sonic.getRightKey())) {
            sonic.stop();
        }
        if (this.platform.getKeys().isPressed(sonic.getUpKey())) {
            sonic.jump();
        }
    }

    @Override
    public void run() {
        while (running) {
            float time = System.currentTimeMillis();
            update(platform.getMario(), platform.getSonic());
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
