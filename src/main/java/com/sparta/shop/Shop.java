package com.sparta.shop;

import com.sparta.shop.Shoppable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Shop implements Shoppable {
    HashMap<String,Integer> itemsInShop = new HashMap<>();
    ArrayList<String> shoppingList = new ArrayList<>();

    @Override
    public ArrayList<String> readCSV(String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                shoppingList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return shoppingList;
    }

    @Override
    public boolean hasItem(String item) {
        if(item == null){
            return false;
        }
        return itemsInShop.containsKey(item);
    }

    @Override
    public int getPrice(String item) {
        if(item == null){
            return 0;
        }else if(hasItem(item)==false){

            System.out.println(item + " not found");
            return 0;
        }
        return itemsInShop.get(item);
    }

    @Override
    public int countTotalInMapFromCSV(ArrayList<String> list) {
        int total = 0;
        for(String item: list){
            if(hasItem(item)){
                if(getPrice(item)>0){
                    total += getPrice(item);
                }
            }
        }
        return total;
    }

    public void addToMap(){
        itemsInShop.put("ps5", 499);
        itemsInShop.put("notebook", 5);
        itemsInShop.put("macbook", 2000);
        itemsInShop.put("television",600);
        itemsInShop.put("iphone12",800);
        itemsInShop.put("alex",20);
        itemsInShop.put("burger", 5);
        itemsInShop.put("samsung", 250);
        itemsInShop.put("pencil", 2);
        itemsInShop.put("tshirt", 8);
        itemsInShop.put("tardis", 1000);
        itemsInShop.put("covidvaccine", 250);

    }



}