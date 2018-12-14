package com.hospitalscoveradministration.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hospitalscoveradministration.Interfaces.AdapterCategoryInterface;


import com.hospitalscoveradministration.R;
import com.hospitalscoveradministration.webServicse.ResponseDate;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {
    List<ResponseDate> reservationList;
    Context context;
    AdapterCategoryInterface anInterface;

    public ReservationAdapter(Context context, AdapterCategoryInterface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }

    public void setData(List<ResponseDate> reservations) {
        this.reservationList = new ArrayList<>();
        reservationList.addAll(reservations);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReservationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_single_reservation, viewGroup, false);
        return new ReservationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationAdapter.ViewHolder viewHolder, int position) {
        ResponseDate item = reservationList.get(position);
        viewHolder.categoryName.setText(item.category.getName());
        viewHolder.subCategory.setText(item.subCategory.getName());
        if (item.category.getIcon() != null) {
            Glide.with(context).load(item.category.getIcon()).into(viewHolder.categoryImage);
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
        TextView subCategory;
        ImageView categoryImage;
        AppCompatButton cancele;
        AppCompatImageButton call;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category);
            subCategory = itemView.findViewById(R.id.subCategory);
            categoryImage = itemView.findViewById(R.id.image_subCategory);
            call = itemView.findViewById(R.id.call);
            cancele = itemView.findViewById(R.id.cancele);
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    anInterface.onCardClick(call, getAdapterPosition());
                }
            });
            cancele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    anInterface.onCardClick(cancele, getAdapterPosition());
                }
            });
        }
    }
}
