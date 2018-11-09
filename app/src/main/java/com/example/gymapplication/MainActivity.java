package com.example.gymapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.view.GravityCompat.START;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private SearchView mSearchView;
    private ViewPager mVpContent;
    private SportFragment sportFragment;
    private ShowFragment showFragment;
    private DemoFragment demoFragment;
    private CoachFragment coachFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViewPager();

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new DashboardFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);

        }
    }

    private void initViewPager() {
        mVpContent = findViewById(R.id.content_frame);

        List<Fragment> list = new ArrayList<>();
        DashboardFragment defaultFragment = new DashboardFragment();
        list.add(defaultFragment);
        sportFragment = SportFragment.newInstance();
        list.add(sportFragment);
        showFragment = ShowFragment.newInstance();
        list.add(showFragment);
        demoFragment = new DemoFragment();
        list.add(demoFragment);
        coachFragment = CoachFragment.newInstance();
        list.add(coachFragment);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        //MainVPAdapter adapter = new MainVPAdapter(getSupportFragmentManager(), list);

        mVpContent.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                mVpContent.setCurrentItem(0);
                break;
            case R.id.nav_info:
                mVpContent.setCurrentItem(2);
                break;
            case R.id.nav_schedule:
                mVpContent.setCurrentItem(1);
                break;
            case R.id.nav_coachlist:
                mVpContent.setCurrentItem(4);
                break;
            case R.id.nav_member:
                Intent intent = new Intent(this, MemberActivity.class);
                intent.putExtra(MemberActivity.EXTRA_NAME, "Member Register");
                startActivity(intent);
                break;
            case R.id.nav_share:
                Intent shareIntent = new Intent(this, CheeseDetailActivity.class);
                shareIntent.putExtra(CheeseDetailActivity.BundleKeyName, "CheeseCake");
                startActivity(shareIntent);
//                Toast.makeText(this, "Share menu clicked ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(START)) {
            drawer.closeDrawer(START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);

        //获取SearchView对象
        MenuItem searchItem = menu.findItem(R.id.search);
        mSearchView = (SearchView) searchItem.getActionView();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //文字提交的时候哦回调，query是最后提交搜索的文字
                System.out.println("onQueryTextSubmit=" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //在文字改变的时候回调，newText是改变之后的文字
                sportFragment.searchSport(newText);
                return false;
            }
        });

        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始搜索的时候，设置显示搜索页面
                mVpContent.setCurrentItem(1);
            }
        });


        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //关闭搜索按钮的时候，设置显示默认页面
                mVpContent.setCurrentItem(0);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
