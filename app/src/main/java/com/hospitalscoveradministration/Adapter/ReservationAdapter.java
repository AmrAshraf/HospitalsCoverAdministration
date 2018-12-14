package com.hospitalscoveradministration.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hospitalscoveradministration.Interfaces.AdapterCategoryInterface;
import com.hospitalscoveradministration.Model.CategoryData;
import com.hospitalscoveradministration.Model.ReservationData;
import com.hospitalscoveradministration.R;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter  extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {
    List<ReservationData> reservationList;
    Context context;
    AdapterCategoryInterface anInterface;

    public ReservationAdapter(Context context ,AdapterCategoryInterface  anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }

    public void setData(List<ReservationData> reservations) {
        this.reservationList = new ArrayList<>();
        reservationList.addAll(reservations);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReservationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout., viewGroup, false);
        return new ReservationAdapter.ViewHolder(view);
    }
//
    @Override
    public void onBindViewHolder(@NonNull ReservationAdapter.ViewHolder viewHolder, int position) {
        ReservationData item = reservationList.get(position);

        if (item.getName() != null) {
            viewHolder.categoryName.setText(item.getName());
        }

        if (item.getIcon() != null) {
            Glide.with(context).load(item.getIcon()).into(viewHolder.categoryImage);
        }

    }

    @Override
    public int getItemCount() {
        if (reservationList != null) {
            return reservationList.size();
        } else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.name_category);
            categoryImage = itemView.findViewById(R.id.image_category);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    anInterface.onCardClick(itemView,getAdapterPosition());
                }
            });
        }
    }
}
