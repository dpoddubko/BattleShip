package com.dmipoddubko.battleShip.field;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadStringImpl implements ReadString {
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String readStr() throws IOException {
        return bufferedReader.readLine();
    }

    @Override
    public int readInt() throws IOException {
        return Integer.parseInt(bufferedReader.readLine());
    }

    @Override
    public void close() {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Exception: can't close BufferedReader");
        }
    }
}
