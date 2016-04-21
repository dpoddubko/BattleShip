package com.dmipoddubko.battleShip.ships;

public abstract class AbstractShip implements Ship {
    private final String name;
    private int life;

    public AbstractShip(String name, int life) {
        this.name = name;
        this.life = life;
    }

    public void decreaseLife() {
       life--;
    }

    public boolean isAlive() {
        return life > 0;
    }
}
