package com.community.jboss.leadmanagement.main.contacts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.community.jboss.leadmanagement.Classes.Contact;
import com.community.jboss.leadmanagement.R;
import com.community.jboss.leadmanagement.main.MainActivity;
import com.community.jboss.leadmanagement.main.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactsFragment extends MainFragment {

    @BindView(R.id.contact_recycler) RecyclerView recyclerView;

    private Unbinder mUnbinder;

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initView();
        ((MainActivity)getContext()).initFab();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUnbinder.unbind();
    }

    private void initView() {

        if (Contact.count(Contact.class) > 0) {
            ContactsAdapter adapter = new ContactsAdapter(Contact.listAll(Contact.class));
            recyclerView.setAdapter(adapter);
        } else {
            //No Contact
        }
    }

    @Override
    public int getTitle() {
        return R.string.title_contacts;
    }
}
