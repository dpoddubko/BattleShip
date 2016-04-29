package com.dmipoddubko.battleShip.field;

import java.io.IOException;

public interface ReadString {
    String readStr() throws IOException;

    int readInt() throws IOException;

    void close();
}
