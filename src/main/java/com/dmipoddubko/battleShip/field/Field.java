package com.dmipoddubko.battleShip.field;

public interface Field {
    Field addUserShips(ReadString rs);

    Field generateShips();

    void printField();

    void clearField();
}
