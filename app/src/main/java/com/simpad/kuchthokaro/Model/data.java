package com.simpad.kuchthokaro.Model;

import java.util.ArrayList;

public class data {


    private String categoryName;
    private String date;
    private ArrayList<String> tasks;
    private ArrayList<String> dates;


    public data() {
    }

    public data(String categoryName, String date) {
        this.categoryName = categoryName;
        this.date = date;
    }

    public data(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<String> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<String> getDates() {
        return dates;
    }

    public void setDates(ArrayList<String> dates) {
        this.dates = dates;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
