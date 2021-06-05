package com.example.calculationapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.calculationapp.ActivityItem;
import com.example.calculationapp.R;
import com.example.calculationapp.data.NoteData;

import java.util.List;

public class NotesAdapter extends ArrayAdapter<NoteData> {

    public NotesAdapter(@NonNull Context context,
                        int resource, @NonNull List<NoteData> notesList) {
        super(context, resource, notesList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.note_item, parent, false);
        }

        NoteData currentNoteItem = getItem(position);

        TextView text = listItemView.findViewById(R.id.noteTitle);
        text.setText(currentNoteItem.getTitle());
        TextView date = listItemView.findViewById(R.id.noteDate);
        date.setText(currentNoteItem.getDate());
        return listItemView;
    }

}
