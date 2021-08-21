package sg.edu.rp.c346.id20022404.mynotesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Note> notes;

    public CustomAdapter(Context context, int resource, ArrayList<Note> objects) {
        super(context, resource, objects);
        this.parent_context = context;
        this.layout_id = resource;
        this.notes = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvDescription = rowView.findViewById(R.id.tvDescription);
        TextView tvPin = rowView.findViewById(R.id.tvPin);

        // Obtain the Android Version information based on the position
        Note currentNote = notes.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentNote.getTitle());
        tvDescription.setText(currentNote.getDescription());

        if (currentNote.getPin() == "yes") {
            tvPin.setText("*");
        } else if (currentNote.getPin() == "no") {
            tvPin.setText("");
        }

        return rowView;
    }
}
