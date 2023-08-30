package com.se233.chapter4.model;

import javafx.scene.input.KeyCode;

import java.util.HashMap;

public class Keys {
    private HashMap<KeyCode, Boolean> keys;

    public Keys() {
        this.keys = new HashMap<>();
    }

    public void add(KeyCode keyCode) {
        this.keys.put(keyCode, true);
    }

    public void remove(KeyCode keyCode) {
        this.keys.put(keyCode, false);
    }

    public boolean isPressed(KeyCode keyCode) {
        return this.keys.getOrDefault(keyCode, false);
    }
}
