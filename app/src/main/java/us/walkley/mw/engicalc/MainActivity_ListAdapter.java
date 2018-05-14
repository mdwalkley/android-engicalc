package us.walkley.mw.engicalc;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by michael_walkley on 5/4/2018.
 */

public class MainActivity_ListAdapter extends ArrayAdapter<MainActivity_EquationSetListItem> {

    MainActivity_ListAdapter(Activity context, ArrayList<MainActivity_EquationSetListItem> list){
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MainActivity_EquationSetListItem currentItem = getItem(position);
        View listItemView = convertView;
        if(null == listItemView){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main_listitem, parent, false);
        }

        TextView categoryTextView = listItemView.findViewById(R.id.textView_Category);
        categoryTextView.setText(currentItem.getCategory());

        TextView titleTextView = listItemView.findViewById(R.id.textView_Title);
        titleTextView.setText(currentItem.getTitle());

        return listItemView;
    }
}
