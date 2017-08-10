package sg.edu.rp.c346.p12_mydatabook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15017420 on 10/8/2017.
 */

public class CustomAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> items;
    int resource;
    TextView tvNote;
    ImageView iv;

    public CustomAdapter(Context context, int resource, ArrayList<String> items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables
        tvNote = (TextView) rowView.findViewById(R.id.textViewNote);
        iv = (ImageView)rowView.findViewById(R.id.imageView2);

        String item = items.get(position);

        tvNote.setText(item);

        //Check if the property for stars >= 5, if so, "light" up the stars
        if (item.equals("Bio")) {
            iv.setImageResource(android.R.drawable.ic_menu_info_details);
        } else if (item.equals("Vaccination")) {
            iv.setImageResource(android.R.drawable.ic_menu_edit);
        } else if (item.equals("Anniversary")) {
            iv.setImageResource(android.R.drawable.ic_menu_my_calendar);
        } else if (item.equals("About Us")) {
            iv.setImageResource(android.R.drawable.btn_star_big_on);
        }

        return rowView;
    }
}
