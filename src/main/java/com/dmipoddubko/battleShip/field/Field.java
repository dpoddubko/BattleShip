package com.dmipoddubko.battleShip.field;

import com.dmipoddubko.battleShip.ships.Ship;

import java.util.List;
import java.util.Map;

public interface Field {
    boolean validateXY(int x, int y);
    int parseStr(String s);
    void addShip(String s, int y);
    int getCount1();
    Map<List<Integer>, Ship> getShipsXY();
    Ship getSingleFunnel1();
    Ship getSingleFunnel2();
    Ship getSingleFunnel3();
    Ship getSingleFunnel4();
    void addShip(String s, int y, int size, Direction dir);
    int getCount2();
    int getCount3();
    int getCount4();
    Ship getTwoFunnel1();
    Ship getTwoFunnel2();
    Ship getTwoFunnel3();
    Ship getThreeFunnel1();
    Ship getThreeFunnel2();
    Ship getFourFunnel();
    boolean[][] getField();
    void printField();
}
