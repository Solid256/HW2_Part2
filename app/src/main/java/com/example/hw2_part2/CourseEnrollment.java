package com.example.hw2_part2;

public class CourseEnrollment {

    public CourseEnrollment() {

    }

    // Methods:

    // Initializes the course enrollment.
    public void Init(CourseEnrollmentDesc desc) {
        mCourseID = desc.mCourseID;
        mGrade = desc.mGrade;
    }

    // Setters:
    void SetCourseID(String sCourseID) {
        mCourseID = sCourseID;
    }

    void SetGrade(String sGrade) {
        mGrade = sGrade;
    }

    // Getters:
    String GetCourseID() {
        return mCourseID;
    }

    String GetGrade() {
        return mGrade;
    }

    // Variables:
    // The course ID.
    public String mCourseID;

    // The grade the student got in the course.
    public String mGrade;
}
