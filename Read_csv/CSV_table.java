package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class CSV_table {

    private static String fname="table.csv";
    private static final String separate=",";
    private static String[][] d = new String[4][2];

    public static void main(String[] args) {

        ReadCSV();
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(d[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void ReadCSV() {
        File file = new File(fname);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Windows-1251"))) {
            String line = "";
            line = br.readLine();
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] elements = line.split(separate);
                d[i][0] = elements[0];
                d[i][1] = elements[4];
                i++;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}







