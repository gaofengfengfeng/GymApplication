package com.example.gymapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.SearchView;

import com.example.gymapplication.R;
import com.example.gymapplication.adapter.SportsAdapter;
import com.example.gymapplication.model.SportsModel;

public class SearchListActivity extends AppCompatActivity {
    public static final String BundleKeyTitle = "search_page_title";
    SearchView mSearchView;
    SportsAdapter mAdapter;
    List<SportsModel> allSports = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent().getExtras() != null) {
            String title = getIntent().getExtras().getString(BundleKeyTitle);
            getSupportActionBar().setTitle(title);
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new SportsAdapter(this, allSports);
        mAdapter.setOnSportItemClick(new SportsAdapter.OnSportItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(SearchListActivity.this, CheeseDetailActivity.class);
                intent.putExtra(CheeseDetailActivity.BundleKeyImage, R.drawable.banner_gym);
                intent.putExtra(CheeseDetailActivity.BundleKeyName, mAdapter.getModelByPos(position).name);
                startActivity(intent);
                SearchListActivity.this.finish();
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public List<SportsModel> getAllSports() {
        if (allSports == null || allSports.size() == 0) {
            allSports = SportsModel.getSports();
        }
        return allSports;
    }
    public List<SportsModel> filterDataset(String word) {
        List<SportsModel> ds = new ArrayList<>();
        if (TextUtils.isEmpty(word)){
            return ds;
        }
        for (SportsModel item: getAllSports()) {
            if(item.name.toLowerCase().contains(word.toLowerCase())) {
                ds.add(item);
            }
        }
        return ds;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search, menu);
//        MenuItem searchItem = menu.findItem(R.id.search_view);
//        //通过MenuItem得到SearchView
//        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);

        //获取SearchView对象
        MenuItem searchItem = menu.findItem(R.id.search);
        mSearchView = (android.support.v7.widget.SearchView) searchItem.getActionView();


        //搜索框展开时后面叉叉按钮的点击事件
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return true;
            }
        });

        //搜索框文字变化监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.e("martin", "onQueryTextSubmit : " + s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.e("martin", "onQueryTextChange --> " + s);
                List<SportsModel> ds = filterDataset(s);
                mAdapter.updateDataset(ds);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
