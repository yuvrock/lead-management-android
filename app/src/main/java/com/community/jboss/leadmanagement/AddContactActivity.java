package com.community.jboss.leadmanagement;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.community.jboss.leadmanagement.Classes.entities.Contact;
import com.community.jboss.leadmanagement.Classes.entities.ContactNumber;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddContactActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_UUID = "INTENT_EXTRA_UUID";
    public static final String INTENT_EXTRA_NUMBER = "INTENT_EXTRA_NUMBER";

    @BindView(R.id.add_contact_toolbar)
    Toolbar toolbar;
    @BindView(R.id.contact_name_field)
    EditText contactNameField;
    @BindView(R.id.contact_number_field)
    EditText contactNumberField;

    private ContactNumber currentContactNumber;
    private UUID userUUID;

    LeadDB leadDB = Room.databaseBuilder(getApplicationContext(),
            LeadDB.class, "database-name").build();

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_save:
                createContact();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_contact, menu);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_contact);

        ButterKnife.bind(this);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_black_24dp));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Intent intent = getIntent();
        final String uuid = intent.getStringExtra(INTENT_EXTRA_UUID);
        if (uuid != null) {
            userUUID = UUID.fromString(uuid);
        }
        final String number = intent.getStringExtra(INTENT_EXTRA_NUMBER);

        if (leadDB.getContactNumberDao().getContactNumber(number) != null) {
            this.currentContactNumber = leadDB.getContactNumberDao().getContactNumber(number);
            setTitle(R.string.contact_edit);
            contactNameField.setText(currentContactNumber != null ?
                    leadDB.getContactDao().getContact(currentContactNumber.getContactId()).getName() : "");
            contactNumberField.setText(number);
        } else {
            currentContactNumber = new ContactNumber("", "");
            contactNumberField.setText(number);
            setTitle(R.string.contact_add);
        }
    }

    //TODO Add multiple numbers
    private void createContact() {
        // Check is Name or Password is empty
        if (!checkEditText(contactNameField, "Please enter name") || !checkEditText(contactNumberField, "Please enter number")) {
            return;
        }

        String _name = contactNameField.getText().toString();
        String _number = contactNumberField.getText().toString();

        Contact currentContact = leadDB.getContactDao().getContact(currentContactNumber.getContactId());

        if (currentContactNumber != null && currentContact != null) {
            if (currentContact.getName().equals(_name) && currentContactNumber.getNumber().equals(_number)) {
                this.finish();
            } else if (currentContactNumber.getNumber().length() > 0) {
                currentContact.setName(_name);
                leadDB.getContactDao().update(currentContact);
                currentContactNumber.setNumber(_number);
                leadDB.getContactNumberDao().update(currentContactNumber);
                this.finish();
            }
        } else {
            Contact contact = new Contact(_name);
            leadDB.getContactDao().update(contact);

            ContactNumber contactNumber = new ContactNumber(_number, contact.getUid());
            leadDB.getContactNumberDao().update(contactNumber);

            this.finish();
        }
    }

    private boolean checkEditText(EditText editText, String errorStr) {
        if (editText.getText().toString().length() <= 0) {
            editText.setError(errorStr);
            return false;
        }
        return true;
    }
}
