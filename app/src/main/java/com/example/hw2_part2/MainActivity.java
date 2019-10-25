package com.example.hw2_part2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the correct content view.
        setContentView(R.layout.student_list_lv);

        // Create all the students and their course enrollments.
        CreateStudentsAndCourseEnrollments();

        mStudentListView = findViewById(R.id.student_list_id);

        // The adapter for the student list view.
        StudentListAdapter adapter = new StudentListAdapter();

        mStudentListView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1:
                // If there was an intent, read it and construct a new student.
                if (data != null) {
                    // The bundle being sent from the previous activity.
                    Bundle studentBundle = data.getBundleExtra("student");

                    // The first name of the student.
                    String firstName = studentBundle.getString("firstName");

                    // The last name of the student.
                    String lastName = studentBundle.getString("lastName");

                    // The CWID of the student.
                    int CWID = studentBundle.getInt("CWID");

                    // Create a new student.
                    Student student = new Student();

                    // The descriptor for the student.
                    StudentDesc desc = new StudentDesc();

                    desc.mFirstName = firstName;
                    desc.mLastName = lastName;
                    desc.mCWID = CWID;

                    // The vehicle info being sent over.
                    Bundle vehicleInfoBundle = data.getBundleExtra("vehicles");

                    ArrayList<String> vehicleInfo = vehicleInfoBundle.getStringArrayList("vehicles");

                    // If there are no vehicles, skip.
                    if(vehicleInfo != null) {

                        // The vehicles for the student desc.
                        ArrayList<Vehicle> vehicles = new ArrayList<>();

                        // Extract the vehicle info
                        for (int i = 0; i < vehicleInfo.size(); i += 3) {

                            VehicleDesc vehicleDesc = new VehicleDesc();

                            vehicleDesc.mMake = vehicleInfo.get(i);
                            vehicleDesc.mModel = vehicleInfo.get(i+1);
                            vehicleDesc.mYear = Integer.decode(vehicleInfo.get(i+2));

                            Vehicle vehicle = new Vehicle();
                            vehicle.Init(vehicleDesc);

                            vehicles.add(vehicle);
                        }

                        desc.mVehicles = vehicles;
                    }

                    student.Init(desc);

                    // The array of students.
                    ArrayList<Student> students = StudentDB.GetSingleton().GetStudents();

                    students.add(student);

                    // Update the data in the adapter.
                    mStudentDB.SetStudents(students);

                    // Update the adapter data view.
                    ((BaseAdapter)mStudentListView.getAdapter()).notifyDataSetChanged();
                }
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Create the menu.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.summary_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The intent for creating the create student page.
        Intent intentStudentCreation = new Intent(this, CreateStudent.class);

        // Start the create student activity. Set it for result to retrieve student data.
        startActivityForResult(intentStudentCreation, 1);
        return true;
    }

    // Creates all of the students and their course enrollments for the list view.
    protected void CreateStudentsAndCourseEnrollments() {
        // The students used in this first iteration of the program.
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        Student student4 = new Student();

        // The object descriptors for the students and course enrollments.
        StudentDesc studentDesc = new StudentDesc();
        VehicleDesc vehicleDesc = new VehicleDesc();

        // The students being sent to the student database.
        ArrayList<Student> students = new ArrayList<>();

        // Student 1.
        // Vehicles.
        Vehicle vehicle1 = new Vehicle();
        vehicleDesc.mMake = "Toyota";
        vehicleDesc.mModel = "Camry";
        vehicleDesc.mYear = 2010;
        vehicle1.Init(vehicleDesc);

        Vehicle vehicle2 = new Vehicle();
        vehicleDesc.mMake = "AMC";
        vehicleDesc.mModel = "Hornet";
        vehicleDesc.mYear = 1975;
        vehicle2.Init(vehicleDesc);

        studentDesc.mFirstName = "Adam";
        studentDesc.mLastName = "Jensen";
        studentDesc.mCWID = 00000001;
        studentDesc.mVehicles.add(vehicle1);
        studentDesc.mVehicles.add(vehicle2);

        student1.Init(studentDesc);


        // Student 2.
        studentDesc.mVehicles = new ArrayList<>();

        // Vehicles.
        Vehicle vehicle3 = new Vehicle();
        vehicleDesc.mMake = "Toyota";
        vehicleDesc.mModel = "Camry";
        vehicleDesc.mYear = 2010;
        vehicle3.Init(vehicleDesc);

        // Course enrollment for sneaking.
        studentDesc.mFirstName = "Luigi";
        studentDesc.mLastName = "Mario";
        studentDesc.mCWID = 00000002;
        studentDesc.mVehicles.add(vehicle3);

        student2.Init(studentDesc);

        // Student 3.
        studentDesc.mVehicles = new ArrayList<>();

        // Vehicles.
        Vehicle vehicle4 = new Vehicle();
        vehicleDesc.mMake = "Toyota";
        vehicleDesc.mModel = "Camry";
        vehicleDesc.mYear = 2010;
        vehicle4.Init(vehicleDesc);

        studentDesc.mFirstName = "Sonic";
        studentDesc.mLastName = "Hedgehog";
        studentDesc.mCWID = 00000003;
        studentDesc.mVehicles.add(vehicle4);

        student3.Init(studentDesc);

        // Student 4.
        studentDesc.mVehicles = new ArrayList<>();

        // Vehicles.
        Vehicle vehicle5 = new Vehicle();
        vehicleDesc.mMake = "Toyota";
        vehicleDesc.mModel = "Camry";
        vehicleDesc.mYear = 2010;
        vehicle5.Init(vehicleDesc);

        // Course enrollment for running.
        Vehicle vehicle6 = new Vehicle();
        vehicleDesc.mMake = "Toyota";
        vehicleDesc.mModel = "Camry";
        vehicleDesc.mYear = 2010;
        vehicle6.Init(vehicleDesc);

        // Course enrollment for running.
        Vehicle vehicle7 = new Vehicle();
        vehicleDesc.mMake = "Toyota";
        vehicleDesc.mModel = "Camry";
        vehicleDesc.mYear = 2010;
        vehicle7.Init(vehicleDesc);


        studentDesc.mFirstName = "Razputin";
        studentDesc.mLastName = "Aquato";
        studentDesc.mCWID = 00000004;
        studentDesc.mVehicles.add(vehicle5);
        studentDesc.mVehicles.add(vehicle6);
        studentDesc.mVehicles.add(vehicle7);

        student4.Init(studentDesc);

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        mStudentDB.SetStudents(students);
    }

    // Variables:

    // A quick reference to the student database singleton.
    protected StudentDB mStudentDB = StudentDB.GetSingleton();

    // The student list view.
    protected ListView mStudentListView;
}
