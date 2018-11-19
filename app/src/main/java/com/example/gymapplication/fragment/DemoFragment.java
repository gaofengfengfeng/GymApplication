package com.example.gymapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gymapplication.R;

public class DemoFragment extends Fragment {
    public static final String BundleKeyArg1 = "bundle_key_arg1";
    private View mRootView;

    public static DemoFragment newInstance(String arg1) {

        Bundle args = new Bundle();

        DemoFragment fragment = new DemoFragment();
        args.putString(BundleKeyArg1, arg1);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_demo, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txtView = mRootView.findViewById(R.id.txtContent);
        Bundle args = getArguments();
        if (args != null) {
            String arg1 = args.getString(BundleKeyArg1);
            txtView.setText(arg1);
        }
    }

}
