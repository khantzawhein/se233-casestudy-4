package com.se233.chapter4.controller;

import com.se233.chapter4.model.Mario;
import com.se233.chapter4.model.Sonic;
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

    private void checkDrawCollisions(Mario mario, Sonic sonic) {
        mario.checkReachGameWall();
        mario.checkReachHighest();
        mario.checkReachFloor();

        sonic.checkReachGameWall();
        sonic.checkReachHighest();
        sonic.checkReachFloor();
    }

    private void paint(Mario mario, Sonic sonic) {
        mario.repaint();
        sonic.repaint();
    }

    @Override
    public void run() {
        while (running) {
            float time = System.currentTimeMillis();
            checkDrawCollisions(platform.getMario(), platform.getSonic());
            paint(platform.getMario(), platform.getSonic());
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
