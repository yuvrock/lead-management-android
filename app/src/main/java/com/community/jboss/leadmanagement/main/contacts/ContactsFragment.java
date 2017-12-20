package com.community.jboss.leadmanagement.main.contacts;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.community.jboss.leadmanagement.R;
import com.community.jboss.leadmanagement.data.entities.Contact;
import com.community.jboss.leadmanagement.main.MainActivity;
import com.community.jboss.leadmanagement.main.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactsFragment extends MainFragment implements ContactsAdapter.AdapterListener {

    @BindView(R.id.contact_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeToRefresh;

    private Unbinder mUnbinder;
    private ContactsFragmentViewModel mViewModel;
    private ContactsAdapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        mViewModel = ViewModelProviders.of(this).get(ContactsFragmentViewModel.class);
        mViewModel.getContacts().observe(this, contacts -> {
            mAdapter.replaceData(contacts);
        });

        final MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.initFab();
        }

        mAdapter = new ContactsAdapter(this);
        recyclerView.setAdapter(mAdapter);

        swipeToRefresh.setOnRefreshListener(() -> {
            mAdapter.replaceData(mViewModel.getContacts().getValue());
            swipeToRefresh.setRefreshing(false);
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUnbinder.unbind();
    }

    @Override
    public int getTitle() {
        return R.string.title_contacts;
    }

    @Override
    public void onContactDeleted(Contact contact) {
        mViewModel.deleteContact(contact);
    }
}
