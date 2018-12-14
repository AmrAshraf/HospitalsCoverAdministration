package com.hospitalscoveradministration.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hospitalscoveradministration.Adapter.SubCategoryAdapter;
import com.hospitalscoveradministration.Interfaces.AdapterCategoryInterface;
import com.hospitalscoveradministration.Model.SubCategory;
import com.hospitalscoveradministration.Model.SubCategoryData;
import com.hospitalscoveradministration.ModelView.SubCategoryModelView;
import com.hospitalscoveradministration.R;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryActivity extends AppCompatActivity implements AdapterCategoryInterface {
    private RecyclerView recyclerSubCategory;
    private SubCategoryAdapter subCategoryAdapter;
    private ImageView imageSubCategory;
    private TextView nameSubCategory;
    private SubCategoryModelView modelView;
    private List<SubCategoryData> subCategoryDataList;
    private ProgressBar progressBar;
    private String idCategory;
    private String idSubCategory;
    private Toolbar toolbar;
    String name;
    String icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sub_category);
        modelView = ViewModelProviders.of(this).get(SubCategoryModelView.class);
        initView();
        initListener();


        name = getIntent().getStringExtra("name");
        icon = getIntent().getStringExtra("icon");
        idCategory = getIntent().getStringExtra("id");

        if (icon != null)
            Glide.with(this).load(icon).into(imageSubCategory);
        if (name != null)
            nameSubCategory.setText(name);
        else
            nameSubCategory.setText("Sub Category");


        showProgress();
        modelView.getAllSubCategory(idCategory);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        imageSubCategory = (ImageView) findViewById(R.id.image_subCategory);
        nameSubCategory = (TextView) findViewById(R.id.name_subCategory);
        recyclerSubCategory = (RecyclerView) findViewById(R.id.recycler_subCategory);
        recyclerSubCategory.setHasFixedSize(true);
        recyclerSubCategory.setLayoutManager(new LinearLayoutManager(this));
        subCategoryAdapter = new SubCategoryAdapter(this, this);
        recyclerSubCategory.setAdapter(subCategoryAdapter);

        setSupportActionBar(toolbar);
        setTitle("Hospitals Cover Admin");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initListener() {
        modelView.getSubCategory().observe(this, new Observer<SubCategory>() {
            @Override
            public void onChanged(@Nullable SubCategory subCategory) {
                hideProgress();
                if (subCategory != null) {
                    subCategoryDataList = new ArrayList<>();
                    subCategoryDataList.clear();
                    subCategoryDataList.addAll(subCategory.getData());
                    setUpAdapter();

                } else {
                    Toast.makeText(SubCategoryActivity.this, "Please Try Again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setUpAdapter() {
        subCategoryAdapter.setData(subCategoryDataList);
    }


    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCardClick(View view, int position) {

    }
}
