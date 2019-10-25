package com.example.hw2_part2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateStudent extends AppCompatActivity {

    // The root layout of the activity.
    public LinearLayout rootLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the correct content view.
        setContentView(R.layout.activity_student_creation);

        // The back button.
        Button backButton;

        // The done button.
        Button doneButton;

        rootLayout = findViewById(R.id.linear_layout_create_student);

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

        // Create the vehicle list creator.
        // The grid layout for the student vehicles.
        GridLayout gridDetails = new GridLayout(this);
        gridDetails.setBackgroundColor(Color.BLACK);

        rootLayout.addView(gridDetails);

        int gridRows = 1;
        int gridColumns = 3;

        // The specifications for the grid.
        GridLayout.Spec[] specRow = new GridLayout.Spec[gridRows];
        GridLayout.Spec[] specColumn = new GridLayout.Spec[gridColumns];

        // Compute the grid specifications.
        for(int i = 0; i < gridRows; i++)
        {
            specRow[i] = GridLayout.spec(i);
        }

        for(int i = 0; i < gridColumns; i++)
        {
            specColumn[i] = GridLayout.spec(i);
        }

        // The columns that belong to each grid cell.
        LinearLayout[][] columns = new LinearLayout[gridRows][gridColumns];

        // The current column being modified.
        LinearLayout CurColumn;

        // Create all of the linear layouts for the grid cells.
        for(int i = 0; i < gridRows; i++)
        {
            for(int j = 0; j < gridColumns; j++)
            {
                // The current grid layout parameters.
                GridLayout.LayoutParams lpGridLayout =
                        new GridLayout.LayoutParams(specRow[i], specColumn[j]);
                lpGridLayout.height = GridLayout.LayoutParams.WRAP_CONTENT;

                if(j == (gridColumns - 1))
                {
                    lpGridLayout.width = GridLayout.LayoutParams.WRAP_CONTENT;
                }
                else
                {
                    lpGridLayout.width = 300;
                }

                lpGridLayout.setGravity(Gravity.FILL_HORIZONTAL);

                lpGridLayout.setMargins(4,4,4,4);

                // Create a new linear layout for a column inside of the grid cell.
                CurColumn = new LinearLayout(this);

                columns[i][j] = CurColumn;

                CurColumn.setLayoutParams(lpGridLayout);
                CurColumn.setOrientation(LinearLayout.VERTICAL);
                CurColumn.setBackgroundColor(Color.BLACK);

                CurColumn.setGravity(Gravity.FILL_HORIZONTAL);

                gridDetails.addView(CurColumn);
            }
        }

        // The layout parameters for a left column.
        LinearLayout.LayoutParams lpColumnLeft = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lpColumnLeft.setMargins(4, 4,4,4);
        lpColumnLeft.weight = 1;
        lpColumnLeft.gravity = Gravity.START;

        // Grid cell (columnIndex, 0)
        CurColumn = columns[0][0];

        // Create the grid titles.
        TextView textViewTitleMake = new TextView(this);
        textViewTitleMake.setLayoutParams(lpColumnLeft);
        textViewTitleMake.setBackgroundColor(Color.WHITE);
        textViewTitleMake.setText("Make");
        textViewTitleMake.setPadding(4,4,4,4);
        textViewTitleMake.setTypeface(Typeface.DEFAULT_BOLD);
        CurColumn.addView(textViewTitleMake);

        // Grid cell (columnIndex, 0)
        CurColumn = columns[0][1];

        TextView textViewTitleModel = new TextView(this);
        textViewTitleModel.setLayoutParams(lpColumnLeft);
        textViewTitleModel.setBackgroundColor(Color.WHITE);
        textViewTitleModel.setText("Model");
        textViewTitleModel.setPadding(4,4,4,4);
        textViewTitleModel.setTypeface(Typeface.DEFAULT_BOLD);
        CurColumn.addView(textViewTitleModel);

        // Grid cell (columnIndex, 0)
        CurColumn = columns[0][2];

        TextView textViewTitleYear = new TextView(this);
        textViewTitleYear.setLayoutParams(lpColumnLeft);
        textViewTitleYear.setBackgroundColor(Color.WHITE);
        textViewTitleYear.setText("Year");
        textViewTitleYear.setPadding(4,4,4,4);
        textViewTitleYear.setTypeface(Typeface.DEFAULT_BOLD);
        CurColumn.addView(textViewTitleYear);
    }
}
