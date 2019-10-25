package com.example.hw2_part2;

public class Vehicle {

    public Vehicle() {

    }

    // Methods:

    // Initializes the vehicle.
    public void Init(VehicleDesc desc) {
        mMake = desc.mMake;
        mModel = desc.mModel;
        mYear = desc.mYear;
    }

    // Setters:
    void SetMake(String sMake) {
        mMake = sMake;
    }

    void SetModel(String sModel) {
        mModel = sModel;
    }

    void SetYear(int sYear) {
        mYear = sYear;
    }

    // Getters:
    String GetMake() {
        return mMake;
    }

    String GetModel() {
        return mModel;
    }

    int GetYear() {
        return mYear;
    }

    // Variables:
    // The make of the vehicle.
    public String mMake;

    // The model of the vehicle.
    public String mModel;

    // The year the car was created.
    public int mYear;
}
