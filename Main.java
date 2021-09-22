package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashSet;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {

    public static HashSet readBondNumberFile(String bondFilePath) {
        HashSet numSet = new HashSet();
        try {
            File myObj = new File(bondFilePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] strArr = data.split(" ");
                for (int i = 0; i < strArr.length; i++) {
                    if (isNumber(strArr[i])) {
                        numSet.add(strArr[i]);
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return numSet;
    }

    public static HashMap readDrawFiles(String[] pathArr) {
        HashMap numMap = new HashMap();
        for (int j = 0; j < pathArr.length; j++) {
            try {
                File myObj = new File(pathArr[j]);
                Scanner myReader = new Scanner(myObj);
                String drawName = pathArr[j].substring(72, 80);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] strArr = data.split(" ");
                    for (int i = 0; i < strArr.length; i++) {
                        if (isNumber(strArr[i])) {
                            numMap.put(strArr[i], drawName);
                        }
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return numMap;
    }

    public static boolean isNumber(String str) {
        Pattern strPattern = Pattern.compile("[0-9]{6}");
        Matcher matcher = strPattern.matcher(str);
        return matcher.find();
    }

    public static void compareAndTell(HashMap prizeList, HashSet myBondsList) {
        for (Object bondNumber : myBondsList) {
            if (prizeList.containsKey(bondNumber)) {
                System.out.println("Bond number *** " + bondNumber + " *** won in *** " + prizeList.get(bondNumber) + " *** draw");
            }
        }
    }

    public static void main(String[] args) {
        String[] prizeListPath = {
                "C:\\Users\\tzia\\Desktop\\Tajammul\\Personal\\Sheets\\Bonds List and WithDraws\\Mar-2020.txt",
                "C:\\Users\\tzia\\Desktop\\Tajammul\\Personal\\Sheets\\Bonds List and WithDraws\\Jun-2020.txt",
                "C:\\Users\\tzia\\Desktop\\Tajammul\\Personal\\Sheets\\Bonds List and WithDraws\\Sep-2020.txt",
                "C:\\Users\\tzia\\Desktop\\Tajammul\\Personal\\Sheets\\Bonds List and WithDraws\\Dec-2020.txt",
                "C:\\Users\\tzia\\Desktop\\Tajammul\\Personal\\Sheets\\Bonds List and WithDraws\\Mar-2021.txt",
                "C:\\Users\\tzia\\Desktop\\Tajammul\\Personal\\Sheets\\Bonds List and WithDraws\\Jun-2021.txt",
                "C:\\Users\\tzia\\Desktop\\Tajammul\\Personal\\Sheets\\Bonds List and WithDraws\\Sep-2021.txt",
        };
        String myBondsListPath = "C:\\Users\\tzia\\Desktop\\Tajammul\\Personal\\Sheets\\Bonds List and WithDraws\\BondNumbers.txt";

        HashMap prizeList = readDrawFiles(prizeListPath);
        HashSet myBondsList = readBondNumberFile(myBondsListPath);
        compareAndTell(prizeList, myBondsList);
    }


}