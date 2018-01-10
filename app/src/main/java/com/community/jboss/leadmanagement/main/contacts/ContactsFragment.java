package com.community.jboss.leadmanagement.main.contacts;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.community.jboss.leadmanagement.LeadDB;
import com.community.jboss.leadmanagement.R;
import com.community.jboss.leadmanagement.main.MainActivity;
import com.community.jboss.leadmanagement.main.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactsFragment extends MainFragment {

    @BindView(R.id.contact_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeToRefresh;

    private Unbinder mUnbinder;

    LeadDB leadDB = Room.databaseBuilder(getContext(),
            LeadDB.class, "database-name").build();

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
        ((MainActivity) getContext()).initFab();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUnbinder.unbind();
    }

    private void initView() {
        final ContactsAdapter adapter = new ContactsAdapter(getContext(), leadDB.getContactDao().getContacts());
        if (leadDB.getContactDao().getContacts().size() > 0) {
            recyclerView.setAdapter(adapter);

            swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    initView();

                    swipeToRefresh.setRefreshing(false);
                }
            });
        }

        //No Contact
    }

    @Override
    public int getTitle() {
        return R.string.title_contacts;
    }
}
