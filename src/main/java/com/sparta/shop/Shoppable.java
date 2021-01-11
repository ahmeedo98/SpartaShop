package com.sparta.shop;

import java.util.ArrayList;

public interface Shoppable {

    ArrayList<String> readCSV(String path);

    boolean hasItem(String item);

    int getPrice(String item);

    int countTotalInMapFromCSV(ArrayList<String> list);
}
