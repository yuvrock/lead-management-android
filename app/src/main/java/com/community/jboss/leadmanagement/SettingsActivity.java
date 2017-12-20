package com.community.jboss.leadmanagement;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends FragmentActivity {
    @BindView(R.id.settings_bar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (savedInstanceState != null) {
            // During initial setup, plug in the details fragment.
            SettingsFragment details = new SettingsFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.settings_fragment_id, details).commit();
        }
        ButterKnife.bind(this);
        toolbar.setTitle("Settings");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
