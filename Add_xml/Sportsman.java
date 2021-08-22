package com.company;

import java.util.ArrayList;

public class Sportsman {
    private String name;
    private String birthday;
    private String gender;
    private ArrayList<Integer> year;
    private ArrayList<String> place;
    private ArrayList<Integer> result;
    private ArrayList<String> award;

    Sportsman(String name, String birthday, String gender) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        year = new ArrayList<> ();
        place = new ArrayList<> ();
        result = new ArrayList<> ();
        award = new ArrayList<> ();
    }

    public String getName() {

        return name;
    }

    public String getBirthday() {

        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public void addYear(int year) {
        this.year.add (year);
    }

    public void addPlace(String place) {
        this.place.add (place);
    }

    public void addResult(int result) {
        this.result.add (result);
    }

    public void addAward(String award) {
        this.award.add (award);
    }

    public int getYear(int i) {
        if (i < year.size ()) return year.get (i);
        else return -1;
    }

    public String getPlace(int i) {
        if (i < place.size ()) return place.get (i);
        else return String.valueOf (-1);
    }

    public int getResult(int i) {
        if (i < result.size ()) return result.get (i);
        else return -1;
    }

    public String getAward(int i) {
        if (i < award.size ()) return award.get (i);
        else return String.valueOf (-1);
    }
}