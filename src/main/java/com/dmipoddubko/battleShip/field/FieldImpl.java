package com.dmipoddubko.battleShip.field;

import com.dmipoddubko.battleShip.ships.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class FieldImpl implements Field {

    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int count4 = 0;

    private Ship singleFunnel1;
    private Ship singleFunnel2;
    private Ship singleFunnel3;
    private Ship singleFunnel4;

    private Ship twoFunnel1;
    private Ship twoFunnel2;
    private Ship twoFunnel3;

    private Ship threeFunnel1;
    private Ship threeFunnel2;

    private Ship fourFunnel;

    Map<List<Integer>, Ship> shipsXY;

    private boolean[][] field;

    public FieldImpl() {
        singleFunnel1 = new SingleFunnel();
        singleFunnel2 = new SingleFunnel();
        singleFunnel3 = new SingleFunnel();
        singleFunnel4 = new SingleFunnel();

        twoFunnel1 = new TwoFunnel();
        twoFunnel2 = new TwoFunnel();
        twoFunnel3 = new TwoFunnel();
        threeFunnel1 = new ThreeFunnel();
        threeFunnel2 = new ThreeFunnel();
        fourFunnel = new FourFunnel();
        shipsXY = new HashMap<>(27);
        field = new boolean[12][12];
    }

    public Field generateShips() {
        while (count4 != 1) {
            generateShip(4);
        }
        while (count3 != 2) {
            generateShip(3);
        }
        while (count2 != 3) {
            generateShip(2);
        }
        while (count1 != 4) {
            addShip(reParseStr(random(1, 10)), random(1, 10));
        }
        return this;
    }

    public void generateShip(int num) {
        addShip(reParseStr(random(1, 10)), random(1, 10), num, randomDir());
    }

    public Field addUserShips() {
        System.out.println("Let's add ships");
        System.out.println("First add single-funnel ships");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (count1 != 4) {
            try {
                System.out.println("Enter one letter in the range a-j");
                String x = br.readLine();
                System.out.println("Enter one number in the range 1-10");
                int y = Integer.parseInt(br.readLine());
                addShip(x, y);
            } catch (NumberFormatException ne) {
                System.out.println("Enter correct parameters");
                continue;
            } catch (IOException e) {
                e.printStackTrace();
            }
            printField();
        }
        System.out.println("Now let's add multi-funnel ships");
        System.out.println("You have to add: 1 - four-funnel ship;\n 2 - three-funnel ships;\n3 - two-funnel ships;\n");
        while (!checkAllShips()) {
            try {
                System.out.println("Enter one letter in the range a-j");
                String x = br.readLine();
                System.out.println("Enter one number in the range 1-10");
                int y = Integer.parseInt(br.readLine());
                System.out.println("Enter a size: 2, 3, 4");
                int size = Integer.parseInt(br.readLine());
                System.out.println("Select the direction of adding a ship. \n" +
                        "If you want to draw to right - enter 'r';\n to left - enter 'l';\n" +
                        " to down - enter 'd';\n to upp - enter 'u'.");
                String dir = br.readLine();
                try {
                    addShip(x, y, size, checkDir(dir));
                } catch (ConsoleInputException pe) {
                    System.out.println("Enter correct parameters");
                    continue;
                }
            } catch (NumberFormatException ne) {
                System.out.println("Enter correct parameters");
                continue;
            } catch (IOException e) {
                e.printStackTrace();
            }
            printField();
            System.out.println("Your ships: single-funnel - " + count1 + ", two-funnel - " + count2 + ", three-funnel - " + count3 + ", four-funnel - " + count4);

        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void addShip(String s, int y) {
        int x = parseStr(s);
        if (validateXY(y, x) && count1 < 4) {
            if ((field[y][x] != true) && checkArea(y, x)) {
                field[y][x] = true;
                switch (count1) {
                    case 0:
                        shipsXY.put(asList(x, y), singleFunnel1);
                        break;
                    case 1:
                        shipsXY.put(asList(x, y), singleFunnel2);
                        break;
                    case 2:
                        shipsXY.put(asList(x, y), singleFunnel3);
                        break;
                    case 3:
                        shipsXY.put(asList(x, y), singleFunnel4);
                        break;
                }
                count1++;
            } else System.out.println("Input correct parameters");
        } else System.out.println("Input correct parameters");
    }

    public void addShip(String s, int y, int size, Direction dir) {
        int x = parseStr(s);
        if (validateXY(y, x) && validateSize(size) && (field[y][x] != true) && checkArea(y, x)) {
            if (dir == Direction.DOWN && (y + size) < 12) {
                if (checkArea(y + size - 1, x)) {
                    for (int i = 0; i < size; i++) {
                        field[y + i][x] = true;
                    }
                    switch (size) {
                        case 2:
                            addTwoF(x, y, x, y + 1);
                            break;
                        case 3:
                            addThreeF(x, y, x, y + 1, x, y + 2);
                            break;
                        case 4:
                            addFourF(x, y, x, y + 1, x, y + 2, x, y + 3);
                            break;
                    }
                }
            } else if (dir == Direction.UPP && (y - size) >= 0) {
                if (checkArea(y - size + 1, x)) {
                    for (int i = 0; i < size; i++) {
                        field[y - i][x] = true;
                    }
                    switch (size) {
                        case 2:
                            addTwoF(x, y, x, y - 1);
                            break;
                        case 3:
                            addThreeF(x, y, x, y - 1, x, y - 2);
                            break;
                        case 4:
                            addFourF(x, y, x, y - 1, x, y - 2, x, y - 3);
                            break;
                    }
                }
            } else if (dir == Direction.RIGHT && (x + size) < 12) {
                if (checkArea(y, x + size - 1)) {
                    for (int i = 0; i < size; i++) {
                        field[y][x + i] = true;
                    }
                    switch (size) {
                        case 2:
                            addTwoF(x, y, x + 1, y);
                            break;
                        case 3:
                            addThreeF(x, y, x + 1, y, x + 2, y);
                            break;
                        case 4:
                            addFourF(x, y, x + 1, y, x + 2, y, x + 3, y);
                            break;
                    }
                }
            } else if (dir == Direction.LEFT && (x - size) >= 0) {
                if (checkArea(y, x - size + 1)) {
                    for (int i = 0; i < size; i++) {
                        field[y][x - i] = true;
                    }
                    switch (size) {
                        case 2:
                            addTwoF(x, y, x - 1, y);
                            break;
                        case 3:
                            addThreeF(x, y, x - 1, y, x - 2, y);
                            break;
                        case 4:
                            addFourF(x, y, x - 1, y, x - 2, y, x - 3, y);
                            break;
                    }
                }
            } else System.out.println("Input correct parameters");
        } else System.out.println("Input correct parameters");
    }

    private void addFourF(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        shipsXY.put(asList(x1, y1), fourFunnel);
        shipsXY.put(asList(x2, y2), fourFunnel);
        shipsXY.put(asList(x3, y3), fourFunnel);
        shipsXY.put(asList(x4, y4), fourFunnel);
        count4++;
    }

    private void addThreeF(int x1, int y1, int x2, int y2, int x3, int y3) {
        switch (count3) {
            case 0:
                shipsXY.put(asList(x1, y1), threeFunnel1);
                shipsXY.put(asList(x2, y2), threeFunnel1);
                shipsXY.put(asList(x3, y3), threeFunnel1);
                break;
            case 1:
                shipsXY.put(asList(x1, y1), threeFunnel2);
                shipsXY.put(asList(x2, y2), threeFunnel2);
                shipsXY.put(asList(x3, y3), threeFunnel2);
                break;
        }
        count3++;
    }

    public void addTwoF(int x1, int y1, int x2, int y2) {
        switch (count2) {
            case 0:
                shipsXY.put(asList(x1, y1), twoFunnel1);
                shipsXY.put(asList(x2, y2), twoFunnel1);
                break;
            case 1:
                shipsXY.put(asList(x1, y1), twoFunnel2);
                shipsXY.put(asList(x2, y2), twoFunnel2);
                break;
            case 2:
                shipsXY.put(asList(x1, y1), twoFunnel3);
                shipsXY.put(asList(x2, y2), twoFunnel3);
                break;
        }
        count2++;
    }

    private boolean validateSize(int size) {
        if (size > 1 && size < 5) {
            if (size == 2 && count2 < 3) return true;
            if (size == 3 && count3 < 2) return true;
            if (size == 4 && count4 < 1) return true;
        }
        return false;
    }

    public static int parseStr(String s) {
        s = s.toUpperCase();
        switch (s) {
            case "A":
                return 1;
            case "B":
                return 2;
            case "C":
                return 3;
            case "D":
                return 4;
            case "E":
                return 5;
            case "F":
                return 6;
            case "G":
                return 7;
            case "H":
                return 8;
            case "I":
                return 9;
            case "J":
                return 10;
            default:
                return 0;
        }
    }

    public String reParseStr(int i) {
        switch (i) {
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            case 5:
                return "E";
            case 6:
                return "F";
            case 7:
                return "G";
            case 8:
                return "H";
            case 9:
                return "I";
            case 10:
                return "J";
            default:
                throw new IllegalArgumentException();
        }
    }


    public boolean validateXY(int x, int y) {
        return (x > 0 && x < 11 && y > 0 && y < 11);
    }

    private boolean checkArea(int y, int x) {
        if (checkXY(y - 1, x - 1) &&
                checkXY(y, x - 1) &&
                checkXY(y + 1, x - 1) &&
                checkXY(y - 1, x) &&
                checkXY(y + 1, x) &&
                checkXY(y - 1, x + 1) &&
                checkXY(y, x + 1) &&
                checkXY(y + 1, x + 1))
            return true;
        return false;
    }

    private boolean checkXY(int y, int x) {
        return field[y][x] == false;
    }

    private Direction checkDir(String dir) throws ConsoleInputException {
        dir = dir.toUpperCase();
        switch (dir) {
            case "U":
                return Direction.UPP;
            case "D":
                return Direction.DOWN;
            case "R":
                return Direction.RIGHT;
            case "L":
                return Direction.LEFT;
            default:
                throw new ConsoleInputException();
        }
    }

    public int getCount1() {
        return count1;
    }

    public Map<List<Integer>, Ship> getShipsXY() {
        return shipsXY;
    }

    public void printField() {
        System.out.println("    A  B  C  D  E  F  G  H  I  J ");
        for (int i = 1; i < 11; i++) {
            if (i < 10) System.out.print(" " + i + " ");
            else System.out.print(" 10");
            for (int j = 1; j < 11; j++) {
                if (field[i][j] == true) System.out.print(" x ");
                else System.out.print(" * ");
                if (j == 10) {
                    System.out.println();
                }
            }
        }
    }

    public void printStatistic() {
        System.out.println("single-funnel: " + count1 + "\ntwo-funnel: " + count2 + "\nthree-funnel: " + count3 + "\nfour-funnel: " + count4);
    }

    public Ship getSingleFunnel1() {
        return singleFunnel1;
    }

    public Ship getSingleFunnel2() {
        return singleFunnel2;
    }

    public int getCount2() {
        return count2;
    }

    public int getCount3() {
        return count3;
    }

    public int getCount4() {
        return count4;
    }

    public Ship getThreeFunnel1() {
        return threeFunnel1;
    }

    public Ship getThreeFunnel2() {
        return threeFunnel2;
    }

    public Ship getFourFunnel() {
        return fourFunnel;
    }

    public boolean[][] getField() {
        return field;
    }

    private boolean checkAllShips() {
        return (count2 == 3 && count3 == 2 && count4 == 1);
    }

    public int random(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public Direction randomDir() {
        int num = random(1, 4);
        switch (num) {
            case 1:
                return Direction.UPP;
            case 2:
                return Direction.DOWN;
            case 3:
                return Direction.RIGHT;
            case 4:
                return Direction.LEFT;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void clearField() {
        field = new boolean[12][12];
    }


}
