package com.example.hw2_part2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StudentListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return StudentDB.GetSingleton().GetStudents().size();
    }

    @Override
    public Object getItem(int i) {
        return StudentDB.GetSingleton().GetStudents().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // The row view being created.
        View row_view;

        // If the view is null, create a new row view. Otherwise, just set the row view to the
        // // current view.
        if(view == null) {
            // The layout inflator to create the row view.
            LayoutInflater layoutInflator = LayoutInflater.from(viewGroup.getContext());

            // Create the row view.
            row_view = layoutInflator.inflate(R.layout.student_row, viewGroup, false);
        } else {
            row_view = view;
        }

        // The current student being extracted for information to put into the text views inside of
        // the row view.
        Student curStudent = StudentDB.GetSingleton().GetStudents().get(i);

        // The current text view being modified.
        TextView curTextView = row_view.findViewById(R.id.first_name);

        // Set the text for the text views in the row views with the current student's information.
        if(curTextView != null) {
            curTextView.setText(curStudent.GetFirstName());
        }

        curTextView = row_view.findViewById(R.id.last_name);

        if(curTextView != null) {
            curTextView.setText(curStudent.GetLastName());
        }

        curTextView = row_view.findViewById(R.id.CWID);

        if(curTextView != null) {
            curTextView.setText(Integer.toString(curStudent.GetCWID()));
        }

        row_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);

                TextView CWIDView = view.findViewById(R.id.CWID);

                String CWIDStr = CWIDView.getText().toString();

                intent.putExtra("CWID", Integer.decode(CWIDStr));

                view.getContext().startActivity(intent);
            }
        });

        return row_view;
    }
}
