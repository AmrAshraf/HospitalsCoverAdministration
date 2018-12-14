package com.hospitalscoveradministration.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hospitalscoveradministration.ModelView.UpdateResourseActivityModelView;
import com.hospitalscoveradministration.R;
import com.hospitalscoveradministration.webServicse.ResourseResponse;
import com.hospitalscoveradministration.webServicse.UpdateRequest;

import static com.hospitalscoveradministration.View.HomeScreenAdmin.currentUser;

public class UpdateResourseActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView categoryImage;
    private TextView categryName;
    private TextView subCategryName;

    private TextView avilable;
    private TextView pending;
    private TextView update_value;
    private AppCompatEditText maxTime;
    private AppCompatButton upgrade;
    private AppCompatImageButton add;
    private AppCompatImageButton remove;
    private UpdateResourseActivityModelView modelView;
    private ProgressBar progressBar;

    private ResourseResponse resourseResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_update_resourse);
        modelView = ViewModelProviders.of(this).get(UpdateResourseActivityModelView.class);
        setTitle("Upgrade Recourse");
        resourseResponse = (ResourseResponse) getIntent().getSerializableExtra("data");
        initView();
        initListerner();
    }

    private void initListerner() {
        modelView.getUpdate().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                hideProgress();
                if (aBoolean) {
                    gotToHomeScreen();
                } else {
                    Toast.makeText(UpdateResourseActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void gotToHomeScreen() {
        Intent intent = new Intent(this, HomeScreenAdmin.class);
        startActivity(intent);
        finish();
    }

    private void initView() {
        progressBar = findViewById(R.id.progress_bar);
        categoryImage = findViewById(R.id.image_subCategory);
        categryName = findViewById(R.id.name_Category);
        subCategryName = findViewById(R.id.name_subCategory);
        avilable = findViewById(R.id.avilable_value);
        pending = findViewById(R.id.binding_set_value);
        update_value = findViewById(R.id.update_value);
        maxTime = findViewById(R.id.max_time);
        upgrade = findViewById(R.id.update);
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);

        String name = getIntent().getStringExtra("name");
        String icon = getIntent().getStringExtra("icon");
        String subCategory = getIntent().getStringExtra("nameSub");
        Glide.with(this).load(icon).into(categoryImage);
        categryName.setText(name);
        subCategryName.setText(subCategory);
        avilable.setText(String.valueOf(resourseResponse.data.get(0).available));
        pending.setText(String.valueOf(resourseResponse.data.get(0).pending));
        update_value.setText(String.valueOf(resourseResponse.data.get(0).available));


        add.setOnClickListener(this);
        remove.setOnClickListener(this);
        upgrade.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                int result = Integer.valueOf(update_value.getText().toString());
                result++;
                update_value.setText(String.valueOf(result));
                break;
            case R.id.remove:
                int resultRemov = Integer.valueOf(update_value.getText().toString());
                if (resultRemov > 0) {
                    resultRemov--;
                } else {
                    resultRemov = 0;
                }
                update_value.setText(String.valueOf(resultRemov));
                break;

            case R.id.update:
                upgradeData();
                break;

            default:
                break;
        }
    }

    private void upgradeData() {
        showProgress();
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.hospitalResourceId = resourseResponse.data.get(0).id;
        if (maxTime.getText().toString() != null)
            updateRequest.availabilityTime = Integer.valueOf(maxTime.getText().toString());
        else
            updateRequest.availabilityTime = resourseResponse.data.get(0).availabilityTime;
        updateRequest.available = Integer.valueOf(update_value.getText().toString());
        modelView.updateResouse(updateRequest);

    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
