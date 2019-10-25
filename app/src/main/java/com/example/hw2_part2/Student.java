package com.example.hw2_part2;

import java.util.ArrayList;

public class Student {

    public Student() {

    }

    // Methods:

    // Initializes the student.
    public void Init(StudentDesc desc) {
        mFirstName = desc.mFirstName;
        mLastName = desc.mLastName;
        mCWID = desc.mCWID;
        mVehicles = desc.mVehicles;
    }

    // Setters:
    void SetCWID(int sCWID) {
        mCWID = sCWID;
    }

    void SetFirstName(String sFirstName) {
        mFirstName = sFirstName;
    }

    void SetLastName(String sLastName) {
        mLastName = sLastName;
    }

    void SetVehicles(ArrayList<Vehicle> sVehicles) {
        mVehicles = sVehicles;
    }

    // Getters:
    int GetCWID() {
        return mCWID;
    }

    String GetFirstName() {
        return mFirstName;
    }

    String GetLastName() {
        return mLastName;
    }

    ArrayList<Vehicle> GetVehicles() {
        return mVehicles;
    }

    // Variables:
    // The student's CWID.
    protected int mCWID;

    // The first name of the student.
    protected String mFirstName;

    // The last name of the student.
    protected String mLastName;

    // The vehicles for this student.
    protected ArrayList<Vehicle> mVehicles = new ArrayList<>();
}
