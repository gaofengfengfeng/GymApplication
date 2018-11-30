/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.gymapplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymapplication.R;

public class CheeseMyCoachActivity extends AppCompatActivity {

    public static final String BundleKeyName = "obj_name";
    public static final String BundleKeyImage = "image_res";
    public static final String BundleKeyDesc = "desc";
    public static final String BundleKeyPhone = "phone";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coach);

        Intent intent = getIntent();
        final String cheeseName = intent.getStringExtra(BundleKeyName);
        final int imageRes = intent.getIntExtra(BundleKeyImage, 0);
        ImageView imageView = findViewById(R.id.backdrop);
        imageView.setImageResource(imageRes);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(cheeseName);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(cheeseName);

        TextView introView = findViewById(R.id.introduction);
        introView.setText(intent.getStringExtra(BundleKeyDesc));
        TextView phoneView = findViewById(R.id.phone);
        phoneView.setText(intent.getStringExtra(BundleKeyPhone));
        phoneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView phoneView = (TextView) v;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phoneView.getText());
                intent.setData(data);
                startActivity(intent);
            }
        });

        Button bookingBtn = findViewById(R.id.booking);
        bookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(CheeseMyCoachActivity.this,"booking succeed",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
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

    private void callPhone (String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
}
