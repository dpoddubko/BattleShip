package com.dmipoddubko.battleShip;

import com.dmipoddubko.battleShip.field.Direction;
import com.dmipoddubko.battleShip.field.FieldImpl;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class FieldTest {

    @Test
    public void validateXYTest() {
        FieldImpl field = new FieldImpl();
        for (int i = 1; i < 11; i++) {
            assertTrue(field.validateXY(i, i));
        }
        assertFalse(field.validateXY(11, 12));
        assertFalse(field.validateXY(0, 1));
        assertFalse(field.validateXY(0, 0));
        assertFalse(field.validateXY(1, 0));
    }

    @Test
    public void parseStrTest() {
        FieldImpl field = new FieldImpl();
        assertEquals(1, field.parseStr("a"));
        assertEquals(1, field.parseStr("A"));
        assertEquals(2, field.parseStr("b"));
        assertEquals(2, field.parseStr("B"));
        assertEquals(3, field.parseStr("c"));
        assertEquals(3, field.parseStr("C"));
        assertEquals(4, field.parseStr("d"));
        assertEquals(4, field.parseStr("D"));
        assertEquals(5, field.parseStr("e"));
        assertEquals(5, field.parseStr("E"));
        assertEquals(6, field.parseStr("f"));
        assertEquals(6, field.parseStr("F"));
        assertEquals(7, field.parseStr("g"));
        assertEquals(7, field.parseStr("G"));
        assertEquals(8, field.parseStr("h"));
        assertEquals(8, field.parseStr("H"));
        assertEquals(9, field.parseStr("i"));
        assertEquals(9, field.parseStr("I"));
        assertEquals(10, field.parseStr("j"));
        assertEquals(10, field.parseStr("J"));
        assertEquals(0, field.parseStr("cb"));
        assertEquals(0, field.parseStr("sdC"));
        assertEquals(0, field.parseStr("any str"));

    }

    @Test
    public void addSingleFunnel() {
        FieldImpl field = new FieldImpl();
        FieldImpl field1 = new FieldImpl();
        field.addShip("a", 2);
        assertEquals(1, field.getCount1());
        assertEquals(field.getSingleFunnel1(), field.getShipsXY().get(asList(1, 2)));
        assertEquals(1, field.getShipsXY().size());
        field.addShip("f", 7);
        assertEquals(2, field.getCount1());
        assertEquals(field.getSingleFunnel2(), field.getShipsXY().get(asList(6, 7)));
        assertEquals(2, field.getShipsXY().size());
        field.addShip("f", 2);
        assertEquals(3, field.getCount1());
        field.addShip("g", 9);
        assertEquals(4, field.getCount1());
        assertEquals(4, field.getShipsXY().size());
        field.addShip("h", 9);
        assertEquals(4, field.getCount1());
        assertEquals(4, field.getShipsXY().size());
        field1.addShip("a", 1);
        assertEquals(1, field1.getCount1());
        field1.addShip("a", 0);
        assertEquals(1, field1.getCount1());
        field1.addShip("a", 11);
        assertEquals(1, field1.getCount1());
        field1.addShip("j", 0);
        assertEquals(1, field1.getCount1());
        field1.addShip("j", 11);
        assertEquals(1, field1.getCount1());
        field1.addShip("w", 0);
        assertEquals(1, field1.getCount1());
        field1.addShip("f", 2);
        assertEquals(2, field1.getCount1());
        field1.addShip("f", 2);
        assertEquals(2, field1.getCount1());
        field1.addShip("g", 3);
        assertEquals(2, field1.getCount1());
        field1.addShip("f", 1);
        assertEquals(2, field1.getCount1());
    }

    @Test
    public void addDownTest() {
        FieldImpl field = new FieldImpl();
        field.addShip("c", 7, 3, Direction.DOWN);
        assertEquals(1, field.getCount3());
        assertEquals(field.getThreeFunnel1(), field.getShipsXY().get(asList(3, 7)));
        assertEquals(field.getThreeFunnel1(), field.getShipsXY().get(asList(3, 8)));
        assertEquals(field.getThreeFunnel1(), field.getShipsXY().get(asList(3, 9)));
        assertEquals(3, field.getShipsXY().size());
        field.addShip("c", 8, 3, Direction.DOWN);
        assertEquals(1, field.getCount3());
        field.addShip("a", 10, 3, Direction.DOWN);
        assertEquals(1, field.getCount3());
        field.addShip("j", 8, 3, Direction.DOWN);
        assertEquals(2, field.getCount3());
        assertEquals(field.getThreeFunnel2(), field.getShipsXY().get(asList(10, 8)));
        assertEquals(field.getThreeFunnel2(), field.getShipsXY().get(asList(10, 9)));
        assertEquals(field.getThreeFunnel2(), field.getShipsXY().get(asList(10, 10)));
        assertEquals(6, field.getShipsXY().size());
        field.addShip("a", 10, 2, Direction.DOWN);
        assertEquals(0, field.getCount2());
        field.addShip("j", 7, 2, Direction.DOWN);
        assertEquals(0, field.getCount2());
        field.addShip("f", 7, 2, Direction.DOWN);
        assertEquals(1, field.getCount2());
        field.addShip("g", 3);
        assertEquals(1, field.getCount1());
        field.addShip("h", 4, 2, Direction.DOWN);
        assertEquals(1, field.getCount2());
        field.addShip("d", 6, 2, Direction.DOWN);
        assertEquals(1, field.getCount2());
    }

    @Test
    public void addUppTest() {
        FieldImpl field = new FieldImpl();
        field.addShip("c", 7, 3, Direction.UPP);
        assertEquals(1, field.getCount3());
        assertEquals(field.getThreeFunnel1(), field.getShipsXY().get(asList(3, 7)));
        assertEquals(field.getThreeFunnel1(), field.getShipsXY().get(asList(3, 6)));
        assertEquals(field.getThreeFunnel1(), field.getShipsXY().get(asList(3, 5)));
        assertEquals(3, field.getShipsXY().size());
        field.addShip("c", 8, 3, Direction.UPP);
        assertEquals(1, field.getCount3());
        field.addShip("a", 10, 3, Direction.UPP);
        assertEquals(2, field.getCount3());
        assertEquals(field.getThreeFunnel2(), field.getShipsXY().get(asList(1, 10)));
        assertEquals(field.getThreeFunnel2(), field.getShipsXY().get(asList(1, 9)));
        assertEquals(field.getThreeFunnel2(), field.getShipsXY().get(asList(1, 8)));
        assertEquals(6, field.getShipsXY().size());
        field.addShip("i", 8, 3, Direction.UPP);
        assertEquals(2, field.getCount3());
        field.addShip("a", 2, 2, Direction.UPP);
        assertEquals(1, field.getCount2());
        field.addShip("e", 3, 4, Direction.UPP);
        assertEquals(0, field.getCount4());
        field.addShip("e", 4, 4, Direction.UPP);
        assertEquals(1, field.getCount4());
        field.addShip("f", 7, 2, Direction.UPP);
    }

    @Test
    public void addRightTest() {
        FieldImpl field = new FieldImpl();
        field.addShip("a", 1);
        field.addShip("j", 10);
        field.addShip("c", 7, 3, Direction.RIGHT);
        assertEquals(1, field.getCount3());
        field.addShip("c", 8, 3, Direction.RIGHT);
        assertEquals(1, field.getCount3());
        field.addShip("a", 10, 4, Direction.RIGHT);
        assertEquals(1, field.getCount4());
        assertEquals(field.getFourFunnel(), field.getShipsXY().get(asList(1, 10)));
        assertEquals(field.getFourFunnel(), field.getShipsXY().get(asList(2, 10)));
        assertEquals(field.getFourFunnel(), field.getShipsXY().get(asList(3, 10)));
        assertEquals(field.getFourFunnel(), field.getShipsXY().get(asList(4, 10)));
        assertEquals(9, field.getShipsXY().size());
        field.addShip("j", 8, 2, Direction.RIGHT);
        assertEquals(0, field.getCount2());
        field.addShip("i", 8, 2, Direction.RIGHT);
        assertEquals(1, field.getCount2());
            }

    @Test
    public void addLeftTest() {
        FieldImpl field = new FieldImpl();
        field.addShip("c", 7, 3, Direction.LEFT);
        assertEquals(1, field.getCount3());
        field.addShip("c", 8, 3, Direction.LEFT);
        assertEquals(1, field.getCount3());
        field.addShip("b", 5, 3, Direction.LEFT);
        assertEquals(1, field.getCount3());
        field.addShip("j", 8, 3, Direction.LEFT);
        assertEquals(2, field.getCount3());
        field.addShip("a", 10, 2, Direction.LEFT);
        assertEquals(0, field.getCount2());
        field.addShip("b", 10, 2, Direction.LEFT);
        assertEquals(1, field.getCount2());
        field.addShip("j", 12, 2, Direction.LEFT);
        assertEquals(1, field.getCount2());
    }
}
