package com.hospitalscoveradministration.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hospitalscoveradministration.Adapter.ReservationAdapter;
import com.hospitalscoveradministration.Interfaces.AdapterCategoryInterface;
import com.hospitalscoveradministration.Model.ReservationData;
import com.hospitalscoveradministration.ModelView.ReservationModelView;
import com.hospitalscoveradministration.R;
import com.hospitalscoveradministration.webServicse.CansleRequest;
import com.hospitalscoveradministration.webServicse.ResponseDate;
import com.hospitalscoveradministration.webServicse.RserveationResponse;

import java.util.ArrayList;
import java.util.List;

import static com.hospitalscoveradministration.View.HomeScreenAdmin.currentUser;


public class ReservationsFragment extends Fragment implements AdapterCategoryInterface {

    public final String reservationsTag = "reservations";
    private ReservationModelView reservationModelView;
    private List<ResponseDate> rserveationList;
    private RecyclerView recyclerReservation;
    private ReservationAdapter adapter;
    private ProgressBar progressBar;
    private TextView noData;
    private int cardClickPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reservationModelView = ViewModelProviders.of(this).get(ReservationModelView.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reservations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initListener();

        showProgress();
        reservationModelView.getAllReservation(currentUser.getData().id);
    }

    private void initListener() {
        reservationModelView.getReservation().observe(this, new Observer<RserveationResponse>() {
            @Override
            public void onChanged(@Nullable RserveationResponse rserveationResponse) {
                hideProgress();
                if (rserveationResponse != null) {
                    rserveationList = new ArrayList<>();
                    rserveationList.clear();
                    rserveationList.addAll(rserveationResponse.data);
                    setUpAdapter();

                } else {
                    Toast.makeText(getContext(), "Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        reservationModelView.getCancele().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                hideProgress();
                if (aBoolean) {
                    rserveationList.remove(cardClickPosition);
                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(getContext(), "Failed To Cancel", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setUpAdapter() {
        if (rserveationList.size() > 0) {
            noData.setVisibility(View.GONE);
            adapter = new ReservationAdapter(getContext(), this);
            adapter.setData(rserveationList);
            recyclerReservation.setAdapter(adapter);

        } else {
            noData.setVisibility(View.VISIBLE);
        }
    }

    private void initView(View view) {
        progressBar = view.findViewById(R.id.progress_bar);
        noData = view.findViewById(R.id.noData);
        recyclerReservation = view.findViewById(R.id.recycler_reservation);
        recyclerReservation.setHasFixedSize(true);
        recyclerReservation.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCardClick(View view, int position) {
        switch (view.getId()) {
            case R.id.call:
                callPhone(position);
                break;
            case R.id.cancele:
                canceleBooking(position);
                break;

            default:

                break;

        }
    }

    private void callPhone(int position) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + rserveationList.get(position).reservation.phone));
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.CALL_PHONE},
                    10);
            callPhone(position);
        } else {
            try {
                startActivity(callIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getContext(), "Call failed", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private void canceleBooking(int position) {
        showProgress();
        cardClickPosition = position;
        CansleRequest reservationData = new CansleRequest();
        reservationData.reservationData = rserveationList.get(position).reservation;
        reservationModelView.canceleBooking(reservationData);


    }
}
