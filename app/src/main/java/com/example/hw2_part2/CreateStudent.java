package com.example.hw2_part2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class CreateStudent extends AppCompatActivity {

    private class GridRows extends ArrayList<ArrayList<LinearLayout>>{}

    // The root layout of the activity.
    public LinearLayout rootLayout;

    // The grid layout for the student vehicles.
    public GridLayout gridDetails;

    // The number of vehicles being created.
    public int numOfVehiclesToCreate = 0;

    // The grid columns of the vehicle creator.
    protected int gridColumns = 3;

    // The grid rows of the grid.
    protected GridRows gridRowList = new GridRows();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the correct content view.
        setContentView(R.layout.activity_student_creation);

        // The back button.
        Button backButton;

        // The done button.
        Button doneButton;

        // The create vehicle button.
        Button createVehicleButton;

        rootLayout = findViewById(R.id.linear_layout_create_student);

        // Get the buttons and set their input methods.
        backButton = findViewById(R.id.BackButton);
        doneButton = findViewById(R.id.DoneButton);
        createVehicleButton = findViewById(R.id.CreateVehicleButton);

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

                EditText firstNameEditText = findViewById(R.id.first_name_edit_text);
                EditText lastNameEditText = findViewById(R.id.last_name_edit_text);
                EditText CWIDEditText = findViewById(R.id.CWID_edit_text);

                // The first name of the student.
                String firstName = firstNameEditText.getText().toString();

                // The last name of the student.
                String lastName = lastNameEditText.getText().toString();

                // The CWID of the student in string form.
                String CWIDStr = CWIDEditText.getText().toString();

                if (firstName.length() == 0) {
                    Toast.makeText(CreateStudent.this,
                            "Please enter your First Name.", Toast.LENGTH_SHORT).show();
                } else if (lastName.length() == 0) {
                    Toast.makeText(CreateStudent.this,
                            "Please enter your Last Name.", Toast.LENGTH_SHORT).show();
                } else if (CWIDStr.length() == 0) {
                    Toast.makeText(CreateStudent.this,
                            "Please enter your CWID.", Toast.LENGTH_SHORT).show();
                } else {

                    // Checks if a vehicle can't be created.
                    boolean vehicleIsValid = true;

                    if(numOfVehiclesToCreate != 0) {
                        vehicleIsValid = CheckCurVehicleIsValid();
                    }

                    if(vehicleIsValid) {
                        // The summary intent for switching back to the summary activity. It doesn't need
                        // the class type.
                        Intent summaryIntentResult = new Intent();

                        summaryIntentResult.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                        // The CWID of the student.
                        int CWID = Integer.decode(CWIDStr);

                        // The bundle to send back to the summary activity.
                        Bundle studentBundle = new Bundle();

                        studentBundle.putString("firstName", firstName);
                        studentBundle.putString("lastName", lastName);
                        studentBundle.putInt("CWID", CWID);

                        summaryIntentResult.putExtra("student", studentBundle);

                        Bundle vehiclesBundle = new Bundle();

                        // The vehicle info being sent over.
                        ArrayList<String> vehicleInfo = new ArrayList<>();

                        // Generate the vehicle descs info.
                        for(int i = 1; i < gridRowList.size(); i++) {

                            ArrayList<LinearLayout> curColumn = gridRowList.get(i);

                            EditText curEditText = (EditText)curColumn.get(0).getChildAt(0);
                            vehicleInfo.add(curEditText.getText().toString());

                            curEditText = (EditText)curColumn.get(1).getChildAt(0);
                            vehicleInfo.add(curEditText.getText().toString());

                            curEditText = (EditText)curColumn.get(2).getChildAt(0);
                            vehicleInfo.add(curEditText.getText().toString());
                        }

                        vehiclesBundle.putStringArrayList("vehicles", vehicleInfo);

                        summaryIntentResult.putExtra("vehicles", vehiclesBundle);

                        // Send the results to the summary activity and finish.
                        setResult(RESULT_OK, summaryIntentResult);
                        finish();
                    }
                }
            }
        });

        createVehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                // Checks if a vehicle can't be created.
                boolean canCreateVehicle = true;

                // Check if prev vehicle filled out.
                if(numOfVehiclesToCreate != 0)
                {
                    canCreateVehicle = CheckCurVehicleIsValid();
                }

                if(canCreateVehicle) {
                    numOfVehiclesToCreate += 1;

                    // The number of grid rows.
                    int gridRows = numOfVehiclesToCreate + 1;

                    // The specifications for the grid.
                    GridLayout.Spec[] specRow = new GridLayout.Spec[gridRows];
                    GridLayout.Spec[] specColumn = new GridLayout.Spec[gridColumns];

                    // Compute the grid specifications.
                    for (int i = numOfVehiclesToCreate; i < gridRows; i++) {
                        specRow[i] = GridLayout.spec(i);
                    }

                    for (int i = 0; i < gridColumns; i++) {
                        specColumn[i] = GridLayout.spec(i);
                    }

                    ArrayList<LinearLayout> columnList = new ArrayList<>();
                    gridRowList.add(columnList);

                    // The current column being modified.
                    LinearLayout curColumn;

                    for (int i = 0; i < gridColumns; i++) {
                        // The current grid layout parameters.
                        GridLayout.LayoutParams lpGridLayout =
                                new GridLayout.LayoutParams(specRow[numOfVehiclesToCreate], specColumn[i]);
                        lpGridLayout.height = GridLayout.LayoutParams.WRAP_CONTENT;

                        if (i == (gridColumns - 1)) {
                            lpGridLayout.width = GridLayout.LayoutParams.WRAP_CONTENT;
                        } else {
                            lpGridLayout.width = 300;
                        }

                        lpGridLayout.setGravity(Gravity.FILL_HORIZONTAL);

                        lpGridLayout.setMargins(4, 4, 4, 4);

                        // Create a new linear layout for a column inside of the grid cell.
                        curColumn = new LinearLayout(view.getContext());

                        columnList.add(curColumn);

                        curColumn.setLayoutParams(lpGridLayout);
                        curColumn.setOrientation(LinearLayout.VERTICAL);
                        curColumn.setBackgroundColor(Color.BLACK);

                        curColumn.setGravity(Gravity.FILL_HORIZONTAL);

                        gridDetails.addView(curColumn);
                    }

                    // The layout parameters for a left column.
                    LinearLayout.LayoutParams lpColumnLeft = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    lpColumnLeft.setMargins(4, 4, 4, 4);
                    lpColumnLeft.weight = 1;
                    lpColumnLeft.gravity = Gravity.START;

                    // Grid cell (columnIndex, 0)
                    curColumn = columnList.get(0);

                    // Create the grid titles.
                    EditText editTextTitleMake = new EditText(view.getContext());
                    editTextTitleMake.setLayoutParams(lpColumnLeft);
                    editTextTitleMake.setBackgroundColor(Color.WHITE);
                    editTextTitleMake.setText("");
                    editTextTitleMake.setPadding(4, 4, 4, 4);
                    editTextTitleMake.setTypeface(Typeface.DEFAULT_BOLD);
                    editTextTitleMake.setMaxLines(1);
                    editTextTitleMake.setInputType(InputType.TYPE_CLASS_TEXT);
                    curColumn.addView(editTextTitleMake);

                    // Grid cell (columnIndex, 0)
                    curColumn = columnList.get(1);

                    // Create the grid titles.
                    EditText editTextTitleModel = new EditText(view.getContext());
                    editTextTitleModel.setLayoutParams(lpColumnLeft);
                    editTextTitleModel.setBackgroundColor(Color.WHITE);
                    editTextTitleModel.setText("");
                    editTextTitleModel.setPadding(4, 4, 4, 4);
                    editTextTitleModel.setTypeface(Typeface.DEFAULT_BOLD);
                    editTextTitleModel.setMaxLines(1);
                    editTextTitleModel.setInputType(InputType.TYPE_CLASS_TEXT);
                    curColumn.addView(editTextTitleModel);

                    // Grid cell (columnIndex, 0)
                    curColumn = columnList.get(2);

                    // Create the grid titles.
                    EditText editTextTitleYear = new EditText(view.getContext());
                    editTextTitleYear.setLayoutParams(lpColumnLeft);
                    editTextTitleYear.setBackgroundColor(Color.WHITE);
                    editTextTitleYear.setText("");
                    editTextTitleYear.setPadding(4, 4, 4, 4);
                    editTextTitleYear.setTypeface(Typeface.DEFAULT_BOLD);
                    editTextTitleYear.setMaxLines(1);
                    editTextTitleYear.setInputType(InputType.TYPE_CLASS_NUMBER);
                    curColumn.addView(editTextTitleYear);
                }
            }
        });

        // Create the vehicle list creator.
        gridDetails = new GridLayout(this);
        gridDetails.setBackgroundColor(Color.BLACK);

        rootLayout.addView(gridDetails);

        int gridRows = 1;

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

        ArrayList<LinearLayout> columnList = new ArrayList<>();
        gridRowList.add(columnList);

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

                columnList.add(CurColumn);

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
        CurColumn = columnList.get(0);

        // Create the grid initial title cells.
        TextView textViewTitleMake = new TextView(this);
        textViewTitleMake.setLayoutParams(lpColumnLeft);
        textViewTitleMake.setBackgroundColor(Color.WHITE);
        textViewTitleMake.setText("Make");
        textViewTitleMake.setPadding(4,4,4,4);
        textViewTitleMake.setTypeface(Typeface.DEFAULT_BOLD);
        CurColumn.addView(textViewTitleMake);

        // Grid cell (columnIndex, 1)
        CurColumn = columnList.get(1);

        TextView textViewTitleModel = new TextView(this);
        textViewTitleModel.setLayoutParams(lpColumnLeft);
        textViewTitleModel.setBackgroundColor(Color.WHITE);
        textViewTitleModel.setText("Model");
        textViewTitleModel.setPadding(4,4,4,4);
        textViewTitleModel.setTypeface(Typeface.DEFAULT_BOLD);
        CurColumn.addView(textViewTitleModel);

        // Grid cell (columnIndex, 2)
        CurColumn = columnList.get(2);

        TextView textViewTitleYear = new TextView(this);
        textViewTitleYear.setLayoutParams(lpColumnLeft);
        textViewTitleYear.setBackgroundColor(Color.WHITE);
        textViewTitleYear.setText("Year");
        textViewTitleYear.setPadding(4,4,4,4);
        textViewTitleYear.setTypeface(Typeface.DEFAULT_BOLD);
        CurColumn.addView(textViewTitleYear);
    }

    public boolean CheckCurVehicleIsValid() {
        // Checks if the current vehicle is valid.
        boolean isValid = true;

        // The current grid list.
        ArrayList<LinearLayout> curGridList = gridRowList.get(gridRowList.size()-1);

        // The current column being analyzed.
        LinearLayout curColumn = curGridList.get(0);

        // Check if any box is empty.
        EditText curEditText = (EditText)curColumn.getChildAt(0);

        if(curEditText.getText().toString().isEmpty()) {
            isValid = false;
            Toast.makeText(CreateStudent.this, "Please specify a Make.", Toast.LENGTH_SHORT).show();
        }
        else {
            curColumn = curGridList.get(1);

            curEditText = (EditText)curColumn.getChildAt(0);

            if(curEditText.getText().toString().isEmpty())
            {
                isValid = false;
                Toast.makeText(CreateStudent.this, "Please specify a Model.", Toast.LENGTH_SHORT).show();
            }
            else {
                curColumn = curGridList.get(2);

                curEditText = (EditText)curColumn.getChildAt(0);

                if(curEditText.getText().toString().isEmpty())
                {
                    isValid = false;
                    Toast.makeText(CreateStudent.this, "Please specify a Year.", Toast.LENGTH_SHORT).show();
                }
            }
        }

        return isValid;
    }
}
