package com.community.jboss.leadmanagement.main.contacts;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.jboss.leadmanagement.AddContactActivity;
import com.community.jboss.leadmanagement.Classes.Contact;
import com.community.jboss.leadmanagement.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by carbonyl on 10/12/2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<Contact> contacts;

    public ContactsAdapter(List<Contact> myDataset) {
        contacts = myDataset;
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_cell, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Contact contact = contacts.get(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static final class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.contact_name)
        TextView name;

        @BindView(R.id.contact_number)
        TextView number;

        @BindView(R.id.contact_avatar)
        ImageView avatar;

        private Contact mContact;

        ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }

        void bind(Contact contact) {
            mContact = contact;

            name.setText(contact.getName());
            number.setText(contact.getNumbers().get(0).getNumber());

            // TODO: Bind the avatar to the ImageView
            // holder.avatar.setImageResource(contact.getAvatar);
        }

        @Override
        public void onClick(View view) {
            final Context context = view.getContext();

            final Intent intent = new Intent(context, AddContactActivity.class);
            intent.putExtra(AddContactActivity.INTENT_EXTRA_NAME, mContact.getName());
            // TODO: Support multiple numbers
            intent.putExtra(AddContactActivity.INTENT_EXTRA_NUMBER,
                    mContact.getNumbers().get(0).getNumber());

            context.startActivity(intent);
        }
    }
}
