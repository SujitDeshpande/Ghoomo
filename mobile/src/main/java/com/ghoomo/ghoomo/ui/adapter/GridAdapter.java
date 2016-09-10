package com.ghoomo.ghoomo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ghoomo.ghoomo.R;
import com.ghoomo.ghoomo.ui.model.GridItemObject;

import java.util.ArrayList;

/**
 * Created by Agoel on 10-09-2016.
 */
public class GridAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private ArrayList<GridItemObject> itemList;
    private Context context;

    public GridAdapter(Context context, ArrayList<GridItemObject> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_card_view_list, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.sourceText.setText(itemList.get(position).getSource());
        holder.destinationTxt.setText(itemList.get(position).getDestination());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}

class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView sourceText;
    public TextView destinationTxt;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        sourceText = (TextView) itemView.findViewById(R.id.country_name);
        destinationTxt = (TextView) itemView.findViewById(R.id.country_photo);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}
