package com.somecompany.traineetask;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder> {

    List<Integer> listOfElements;

    public CustomListAdapter(List<Integer> fibonacciNumbs) {
        this.listOfElements = fibonacciNumbs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_febonacci_num, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String text = listOfElements.get(i).toString();
        viewHolder.fibonacciNumText.setText(text);
    }

    @Override
    public int getItemCount() {
        return listOfElements.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView fibonacciNumText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            fibonacciNumText = itemView.findViewById(R.id.text_num);
        }
    }
}
