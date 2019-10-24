package com.example.hw2_part2;

import java.util.ArrayList;

public class StudentDB {

    StudentDB() {

    }

    // Methods:
    static public StudentDB GetSingleton() {
        return singleton;
    }

    // Setters:
    void SetStudents(ArrayList<Student> sStudents) {
        mStudents = sStudents;
    }

    // Getters:
    ArrayList<Student> GetStudents() {
        return mStudents;
    }

    // Variables:
    // The singleton instance of the student database.
    private static final StudentDB singleton = new StudentDB();

    // The students in the student database. Starts off non-existent.
    private ArrayList<Student> mStudents = null;
}
