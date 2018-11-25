package com.example.gymapplication.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import com.example.gymapplication.MyApplication;
import com.example.gymapplication.R;
import com.example.gymapplication.adapter.ViewPagerAdapter;
import com.example.gymapplication.fragment.CoachFragment;
import com.example.gymapplication.fragment.DashboardFragment;
import com.example.gymapplication.fragment.DemoFragment;
import com.example.gymapplication.fragment.MyCoachFragment;
import com.example.gymapplication.fragment.ShowFragment;
import com.example.gymapplication.fragment.SportFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.support.v4.view.GravityCompat.START;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private SearchView mSearchView;
    private ViewPager mVpContent;
    private SportFragment sportFragment;
    private ShowFragment showFragment;
    private DemoFragment demoFragment;
    private CoachFragment coachFragment;
    private MyCoachFragment myCoachFragment;
    private CircleImageView header;
    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViewPager();

        myApplication = MyApplication.getInstance();
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

        View headLayout = navigationView.getHeaderView(0);
        header = headLayout.findViewById(R.id.profile_image);

        //点击头像
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
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
        myCoachFragment = myCoachFragment.newInstance();
        list.add(myCoachFragment);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        //MainVPAdapter adapter = new MainVPAdapter(getSupportFragmentManager(), list);

        mVpContent.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                mVpContent.setCurrentItem(0);
                myApplication.setActiveFragment(0);
                break;
            case R.id.nav_info:
                mVpContent.setCurrentItem(2);
                myApplication.setActiveFragment(2);
                break;
            case R.id.nav_schedule:
                mVpContent.setCurrentItem(1);
                myApplication.setActiveFragment(1);
                break;
            case R.id.nav_coachlist:
                mVpContent.setCurrentItem(4);
                myApplication.setActiveFragment(4);
                supportInvalidateOptionsMenu();
                break;
            case R.id.nav_my_coach:
                mVpContent.setCurrentItem(5);
                myApplication.setActiveFragment(5);
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
                if (myApplication.getActiveFragment().equals(1)){
                    sportFragment.searchSport(newText);
                } else if (myApplication.getActiveFragment().equals(4)){
                    coachFragment.searchCoach(newText);
                } else {
                    sportFragment.searchSport(newText);
                }

                return false;
            }
        });

        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始搜索的时候，设置显示搜索页面
                if (myApplication.getActiveFragment().equals(1)) {
                    mVpContent.setCurrentItem(1);
                } else if (myApplication.getActiveFragment().equals(4)) {
                    mVpContent.setCurrentItem(4);
                } else if (myApplication.getActiveFragment().equals(5)) {
                    mVpContent.setCurrentItem(5);
                } else {
                    mVpContent.setCurrentItem(1);
                }
            }
        });


        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //关闭搜索按钮的时候，设置显示默认页面
                mVpContent.setCurrentItem(myApplication.getActiveFragment());
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
