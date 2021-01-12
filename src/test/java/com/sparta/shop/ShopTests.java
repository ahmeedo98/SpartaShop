package com.sparta.shop;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ShopTests {
    HashMap<String, Integer> hashMap = new HashMap<>();
    Shoppable shoppable;

    @BeforeEach
    void setUp(){
        shoppable = Mockito.mock(Shoppable.class);
        hashMap.put("Carrots", 2);
        hashMap.put("Bananas", 4);
        hashMap.put("Cake", 1);
        hashMap.put("Strawberrys", 2);
    }

    @ParameterizedTest(name = "Number {index} with argument {arguments}")
    @ValueSource (strings = {"Carrots", "Bananas", "Cake"})
    @NullAndEmptySource
    void doesContain(String string){
        Mockito.when(shoppable.hasItem(string)).thenReturn(true);
        Assertions.assertTrue(shoppable.hasItem(string));
    }

    @ParameterizedTest(name = "Number {index} with argument {arguments}")
    @ValueSource (strings = {"Carrots", "Bananas", "Cake", "Strawberrys"})
    void doesReturnPrice(String string){
        Assumptions.assumeTrue(string != null || !string.equals(""));
        Mockito.when(shoppable.getPrice(string)).thenReturn(hashMap.get(string));
        Assertions.assertEquals(hashMap.get(string), shoppable.getPrice(string));
    }

    @Test
    @CsvFileSource (resources = "src/test/resources/shoppingList.csv")
    void doesReadFile(){
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("Bread", "Carrot", "Cookie"
                , "Cake", "Cake", "Cabbage", "Cake"));
        Mockito.when(shoppable.readCSV("src/test/resources/shoppingList.csv")).thenReturn(arrayList);
        Assertions.assertEquals(7, shoppable.readCSV("src/test/resources/shoppingList.csv").size());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/shoppingList.csv")
    @DisplayName("Getting the sum of trolley")
    void gettingTrolleySum(String item) {
        Mockito.when(shoppable.readCSV(item)).thenReturn(new ArrayList<>(Arrays.asList("Bread", "Carrot", "Cookie"
                , "Cake", "Cake", "Cabbage", "Cake")));
        Mockito.when(shoppable.countTotalInMapFromCSV(shoppable.readCSV(item))).thenReturn(22);
        Assertions.assertEquals(22, shoppable.countTotalInMapFromCSV(shoppable.readCSV(item)));
    }
}