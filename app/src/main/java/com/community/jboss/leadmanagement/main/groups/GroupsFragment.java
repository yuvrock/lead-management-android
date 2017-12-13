package com.community.jboss.leadmanagement.main.groups;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.community.jboss.leadmanagement.R;
import com.community.jboss.leadmanagement.main.MainFragment;

public class GroupsFragment extends MainFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_groups, container, false);
    }

    @Override
    public int getTitle() {
        return R.string.title_groups;
    }
}
