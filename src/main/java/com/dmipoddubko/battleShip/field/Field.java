package com.dmipoddubko.battleShip.field;

public interface Field {
    Field addUserShips();

    Field generateShips();

    void printField();

    boolean[][] getField();

    void clearField();
}
