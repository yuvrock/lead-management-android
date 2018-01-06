package com.community.jboss.leadmanagement;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.community.jboss.leadmanagement.data.entities.Contact;
import com.community.jboss.leadmanagement.main.contacts.ContactsAdapter;
import com.community.jboss.leadmanagement.utils.DbUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CustomDialogBox{

    @BindView(R.id.dialog_yes)
    Button yes;
    @BindView(R.id.dialog_no)
    Button no;


    private Contact contact;
    private ContactsAdapter adapter;
    private Dialog dialog;

    public void showAlert(Activity activity, final Contact contact, ContactsAdapter adapter){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(dialog.getContext()).inflate(R.layout.custom_dialog_box,null,false);
        this.contact = contact;
        this.adapter = adapter;
        this.dialog = dialog;
        dialog.setContentView(view);
        ButterKnife.bind(this,view);
        dialog.show();
    }

    @OnClick(R.id.dialog_yes)
    void onYesClicked(){
        adapter.mListener.onContactDeleted(contact);
        dialog.dismiss();
    }

    @OnClick(R.id.dialog_no)
    void onNoClicked(){
        dialog.dismiss();
    }

}
