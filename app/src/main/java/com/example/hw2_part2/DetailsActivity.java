package com.example.hw2_part2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    public LinearLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the correct content view.
        setContentView(R.layout.activity_details);

        // The intent that contains the student's CWID.
        Intent intent = getIntent();

        int CWID = intent.getIntExtra("CWID", 0);

        // The current student being displayed.
        Student curStudent = StudentDB.GetSingleton().GetStudentByCWID(CWID);

        rootLayout = findViewById(R.id.details_layout);

        Button button = new Button(this);
        button.setText("Back");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If clicked, exit this view back to the summary activity.
                finish();
            }
        });
        rootLayout.addView(button);

        TextView firstNameTextView = new TextView(this);
        firstNameTextView.setText("First Name: " + curStudent.GetFirstName());
        firstNameTextView.setPadding(6,2,6,2);
        rootLayout.addView(firstNameTextView);

        TextView lastNameTextView = new TextView(this);
        lastNameTextView.setText("Last Name: " + curStudent.GetLastName());
        lastNameTextView.setPadding(6,2,6,2);
        rootLayout.addView(lastNameTextView);

        TextView CWIDTextView = new TextView(this);
        CWIDTextView.setText("CWID: " + String.valueOf(CWID));
        CWIDTextView.setPadding(6,2,6,2);
        rootLayout.addView(CWIDTextView);

        // The layout parameters for the course enrollments title.
        LinearLayout.LayoutParams lpCenterTitle = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lpCenterTitle.setMargins(2, 2,2,2);
        lpCenterTitle.gravity = Gravity.CENTER_HORIZONTAL;

        TextView courseEnrollmentsTitle = new TextView(this);
        courseEnrollmentsTitle.setText("Vehicles");
        courseEnrollmentsTitle.setLayoutParams(lpCenterTitle);
        courseEnrollmentsTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        rootLayout.addView(courseEnrollmentsTitle);

        // The list of student vehicles.
        ArrayList<Vehicle> vehicles = curStudent.GetVehicles();

        // The grid layout for the student vehicles.
        GridLayout gridDetails = new GridLayout(this);
        gridDetails.setBackgroundColor(Color.BLACK);

        rootLayout.addView(gridDetails);

        int gridRows = vehicles.size() + 1;
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

                if(j == (gridColumns - 1))
                {
                    lpGridLayout.width = GridLayout.LayoutParams.WRAP_CONTENT;
                }
                else
                {
                    lpGridLayout.width = 300;
                }

                lpGridLayout.height = GridLayout.LayoutParams.WRAP_CONTENT;

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

        for(int i = 0; i < vehicles.size(); i++) {

            // The current column index.
            int columnIndex = i+1;

            // Grid cell (columnIndex, 0)
            CurColumn = columns[columnIndex][0];

            Vehicle curVehicle = vehicles.get(i);

            TextView textViewMake = new TextView(this);
            textViewMake.setLayoutParams(lpColumnLeft);
            textViewMake.setBackgroundColor(Color.WHITE);
            textViewMake.setText(curVehicle.GetMake());
            textViewMake.setPadding(4,4,4,4);
            CurColumn.addView(textViewMake);


            // Grid cell (columnIndex, 1)
            CurColumn = columns[columnIndex][1];

            TextView textViewModel = new TextView(this);
            textViewModel.setLayoutParams(lpColumnLeft);
            textViewModel.setBackgroundColor(Color.WHITE);
            textViewModel.setText(curVehicle.GetModel());
            textViewModel.setPadding(4,4,4,4);
            CurColumn.addView(textViewModel);

            // Grid cell (columnIndex, 2)
            CurColumn = columns[columnIndex][2];

            TextView textViewYear = new TextView(this);
            textViewYear.setLayoutParams(lpColumnLeft);
            textViewYear.setBackgroundColor(Color.WHITE);
            textViewYear.setText(String.valueOf(curVehicle.GetYear()));
            textViewYear.setPadding(4,4,4,4);
            CurColumn.addView(textViewYear);
        }
    }
}
