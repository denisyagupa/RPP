package com.example.lab_rpp_1;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class My_Adapter extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] names;

    public My_Adapter(Activity context, String[] names) {
        super(context, R.layout.activity_home, names);
        this.context = context;
        this.names = names;
    }

    static class ViewHolder {
        public ImageView imageView;
        public TextView textView;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.activity_home, null, true);
            holder = new ViewHolder();
            holder.textView = (TextView) rowView.findViewById(R.id.label);
            holder.imageView = (ImageView) rowView.findViewById(R.id.icon);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        holder.textView.setText(Words.inwords(position + 1));
        Random random = new Random();
        holder.imageView.setImageResource(getImageId(context, "m" + String.valueOf(random.nextInt(97) + 1)));
        if (position % 2 == 1){
            rowView.setBackgroundColor(Color.parseColor("#CCCCCC"));
        }
        else if (position % 2 == 0){
            rowView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }


        return rowView;
    }
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
