package com.hospitalscoveradministration.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hospitalscoveradministration.Adapter.CategoryAdapter;
import com.hospitalscoveradministration.Interfaces.AdapterCategoryInterface;
import com.hospitalscoveradministration.Model.Category;
import com.hospitalscoveradministration.Model.CategoryData;
import com.hospitalscoveradministration.ModelView.CategoryModelView;
import com.hospitalscoveradministration.ModelView.MyMACAdress;
import com.hospitalscoveradministration.R;

import java.util.ArrayList;
import java.util.List;


public class ResourcesFragment extends Fragment implements AdapterCategoryInterface {

    private RecyclerView recyclerCategory;
    private CategoryAdapter categoryAdapter;
    private ProgressBar progressBar;
    private CategoryModelView modelView;
    private List<CategoryData> categoryList;
    public final String resourcesTag = "resources";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modelView = ViewModelProviders.of(this).get(CategoryModelView.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_resources, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initListener();

        showProgress();
        modelView.getAllCategory(MyMACAdress.getMacAddr());

    }

    private void initListener() {

        modelView.getCategory().observe(this, new Observer<Category>() {
            @Override
            public void onChanged(@Nullable Category category) {
                hideProgress();
                if (category != null) {
                    categoryList = new ArrayList<>();
                    categoryList.clear();
                    categoryList.addAll(category.getData());
                    setUpAdapter();

                } else {
                    Toast.makeText(getContext(), "Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setUpAdapter() {
        categoryAdapter = new CategoryAdapter(getContext(), this);
        categoryAdapter.setData(categoryList);
        recyclerCategory.setAdapter(categoryAdapter);
    }

    private void initView(View view) {
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerCategory = view.findViewById(R.id.recycler_category);
        recyclerCategory.setHasFixedSize(true);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCardClick(View view, int position) {
        Intent intent = new Intent(getContext(), SubCategoryActivity.class);
        intent.putExtra("name", categoryList.get(position).getName());
        intent.putExtra("id", categoryList.get(position).getId());
        intent.putExtra("icon", categoryList.get(position).getIcon());
        startActivity(intent);

    }
}
