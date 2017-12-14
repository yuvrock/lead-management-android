package com.community.jboss.leadmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.community.jboss.leadmanagement.Classes.Contact;
import com.community.jboss.leadmanagement.Classes.Number;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by carbonyl on 10/12/2017.
 */

public class AddContactActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_NAME = "INTENT_EXTRA_NAME";
    public static final String INTENT_EXTRA_NUMBER = "INTENT_EXTRA_NUMBER";

    @BindView(R.id.add_contact_toolbar) Toolbar toolbar;
    @BindView(R.id.contact_name_field) EditText contactNameField;
    @BindView(R.id.contact_number_field) EditText contactNumberField;

    Number currentUser;
    boolean creatingNew = true;

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
        final String name = intent.getStringExtra(INTENT_EXTRA_NAME);
        final String number = intent.getStringExtra(INTENT_EXTRA_NUMBER);

        if(findUserByNumber(number)!=null){
            this.currentUser = findUserByNumber(number);
            setTitle(R.string.contact_edit);
            creatingNew = false;
            contactNameField.setText(currentUser != null ? currentUser.getContact().getName() : "");
            contactNumberField.setText(number);
        }else{
            currentUser = new Number();
            contactNumberField.setText(number);
            setTitle(R.string.contact_add);
        }
    }

    //TODO Add multiple numbers
    private void createContact() {
        // Check is Name or Password is empty
        if(!checkEditText(contactNameField,"Please enter name") || !checkEditText(contactNumberField, "Please enter number")){
            return;
        }

        String _name = contactNameField.getText().toString();
        String _number = contactNumberField.getText().toString();

        if(currentUser!=null && currentUser.getContact()!=null){
            if(currentUser.getContact().getName().equals(_name) && currentUser.getNumber().equals(_number)){
                this.finish();
            }else if (currentUser.getNumber().length()>0) {
                currentUser.getContact().setName(_name);
                currentUser.getContact().save();
                currentUser.setNumber(_number);
                currentUser.save();
                this.finish();
            }
        }else {
            Contact contact = new Contact(_name);
            contact.save();

            Number number = new Number(_number, contact);
            number.save();

            this.finish();
        }
    }

    @Nullable
    private Number findUserByNumber(String number){
        if(number!=null) {
            if (number.length() > 0) {
                List<Number> data = Number.find(Number.class, "number = ?", number);
                if (data.size() > 0) {
                    return data.get(0);
                }
            }
        }
        return null;
    }

    private boolean checkEditText(EditText editText,String errorStr){
        if(editText.getText().toString().length()<=0){
            editText.setError(errorStr);
            return false;
        }
        return true;
    }
}
