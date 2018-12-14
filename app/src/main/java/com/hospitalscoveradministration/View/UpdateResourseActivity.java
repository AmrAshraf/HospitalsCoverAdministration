package com.hospitalscoveradministration.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hospitalscoveradministration.R;

public class UpdateResourseActivity extends AppCompatActivity {
    private ImageView categoryImage;
    private TextView categryName;
    private TextView subCategryName;

    private TextView avilable;
    private TextView pending;
    private TextView update_value;
    private AppCompatEditText maxTime;
    private AppCompatButton upgrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_update_resourse);

        initView();
    }

    private void initView() {
        categoryImage = findViewById(R.id.image_subCategory);
        categryName = findViewById(R.id.name_Category);
        subCategryName = findViewById(R.id.name_subCategory);
        avilable = findViewById(R.id.avilable_value);
        pending = findViewById(R.id.binding_set_value);
        update_value = findViewById(R.id.update_value);
        maxTime = findViewById(R.id.max_time);
        upgrade = findViewById(R.id.update);
    }
}
