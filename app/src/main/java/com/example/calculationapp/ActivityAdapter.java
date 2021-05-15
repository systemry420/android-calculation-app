package com.example.calculationapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ActivityAdapter extends ArrayAdapter<ActivityItem> {
    public ActivityAdapter(Activity context, ActivityItem[] activityItems) {
        super(context, 0, activityItems);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.row, parent, false);
        }

        ActivityItem currentActivityItem = getItem(position);

        TextView text = listItemView.findViewById(R.id.itemTitle);
        text.setText(currentActivityItem.getText());
        ImageView image = listItemView.findViewById(R.id.itemIcon);
        image.setImageResource(currentActivityItem.getIcon());

        return listItemView;
    }

}
