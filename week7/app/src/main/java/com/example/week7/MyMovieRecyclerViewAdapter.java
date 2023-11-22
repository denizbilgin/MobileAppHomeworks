package com.example.week7;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder> {

    private final List<Movie> mValues;
    private MovieFragment.OnMovieSelected mListener;
    int selectedIndex;

    public MyMovieRecyclerViewAdapter(List<Movie> items, MovieFragment.OnMovieSelected listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movie, parent, false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(Integer.toString(position));
        holder.mContentView.setText(mValues.get(position).getName());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.movieSelected(holder.mItem);
                    notifyItemChanged(selectedIndex);
                    // Rowlara tıkladığında seçilmiş item'ı değiştiriyor, detay sayfasına giderken lazım olcak
                    selectedIndex = holder.getLayoutPosition();
                    notifyItemChanged(selectedIndex);
                }
            }
        });
        holder.itemView.setBackgroundColor(selectedIndex == position ? Color.GREEN : Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final View mView;
        public final TextView mContentView;
        public Movie mItem;

    public ViewHolder(View view) {
      super(view);
      mView = view; // mView represents each rows
      mIdView = (TextView) view.findViewById(R.id.item_number);
      mContentView = (TextView) view.findViewById(R.id.content);
    }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}