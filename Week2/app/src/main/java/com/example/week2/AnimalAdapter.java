package com.example.week2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AnimalAdapter extends BaseAdapter {
    private List<Animal> animalList;
    private LayoutInflater layoutInflater;

    public AnimalAdapter(Activity activity, List<Animal> animalList) {
        this.animalList = animalList;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return animalList.size();
    }

    @Override
    public Object getItem(int position) {
        return animalList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rowView = layoutInflater.inflate(R.layout.listview_row, null);
        TextView textView = rowView.findViewById(R.id.label);
        ImageView imageView = rowView.findViewById(R.id.pic);

        Animal animal = animalList.get(position);
        textView.setText(animal.getType());
        imageView.setImageResource(animal.getPicId());

        return rowView;
    }
}
