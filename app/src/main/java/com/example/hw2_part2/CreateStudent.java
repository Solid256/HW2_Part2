package com.example.hw2_part2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // The back button.
        Button backButton;

        // The done button.
        Button doneButton;

        // Set the correct content view.
        setContentView(R.layout.activity_student_creation);

        // Get the buttons and set their input methods.
        backButton = findViewById(R.id.BackButton);
        doneButton = findViewById(R.id.DoneButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                // Exit the activity to the summary activity.
                finish();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // The summary intent for switching back to the summary activity. It doesn't need
                // the class type.
                Intent summaryIntentResult = new Intent();

                summaryIntentResult.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                // The bundle to send back to the summary activity.
                Bundle studentBundle = new Bundle();

                EditText firstNameEditText = findViewById(R.id.first_name_edit_text);
                EditText lastNameEditText = findViewById(R.id.last_name_edit_text);
                EditText CWIDEditText = findViewById(R.id.CWID_edit_text);

                // The first name of the student.
                String firstName = firstNameEditText.getText().toString();

                // The last name of the student.
                String lastName = lastNameEditText.getText().toString();

                // The CWID of the student in string form.
                String CWIDStr = CWIDEditText.getText().toString();

                if(firstName.length() == 0)
                {
                    Toast.makeText(CreateStudent.this,
                            "Please enter your First Name.", Toast.LENGTH_SHORT).show();
                }
                else if(lastName.length() == 0) {
                    Toast.makeText(CreateStudent.this,
                            "Please enter your Last Name.", Toast.LENGTH_SHORT).show();
                }
                else if(CWIDStr.length() == 0) {
                    Toast.makeText(CreateStudent.this,
                            "Please enter your CWID.", Toast.LENGTH_SHORT).show();
                }
                else {
                    // The CWID of the student.
                    int CWID = Integer.decode(CWIDStr);

                    studentBundle.putString("firstName", firstName);
                    studentBundle.putString("lastName", lastName);
                    studentBundle.putInt("CWID", CWID);

                    summaryIntentResult.putExtra("student", studentBundle);

                    // Send the results to the summary activity and finish.
                    setResult(RESULT_OK, summaryIntentResult);
                    finish();
                }
            }
        });
    }
}
