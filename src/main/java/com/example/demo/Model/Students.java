package com.example.demo.Model;

public class Students {
    private String studName;
    private int studId;
    private double studMarks;

    public Students(String studName, int studId, double studMarks) {
        this.studName = studName;
        this.studId = studId;
        this.studMarks = studMarks;
    }

    public String getStudName() {
        return studName;
    }
    public void setStudName(String studName) {
        this.studName = studName;
    }

    public int getStudId() {
        return studId;
    }

    public void setStudId(int studId) {
        this.studId = studId;
    }

    public double getStudMarks() {
        return studMarks;
    }

    public void setStudMarks(double studMarks) {
        this.studMarks = studMarks;
    }

    @Override
    public String toString() {
        return "Students{" +
                "studName='" + studName + '\'' +
                ", studId='" + studId + '\'' +
                ", studMarks=" + studMarks +
                '}';
    }
}