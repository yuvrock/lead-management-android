package com.community.jboss.leadmanagement.main.contacts;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.community.jboss.leadmanagement.AddContactActivity;
import com.community.jboss.leadmanagement.Classes.daos.ContactNumberDAO;
import com.community.jboss.leadmanagement.Classes.entities.Contact;
import com.community.jboss.leadmanagement.LeadDB;
import com.community.jboss.leadmanagement.R;

import java.util.List;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<Contact> contacts;
    private ContactsAdapter adapter;

    private LeadDB leadDB;

    ContactsAdapter(Context context, List<Contact> myDataset) {
        contacts = myDataset;
        adapter = this;
        leadDB = Room.databaseBuilder(context, LeadDB.class, "database-name").build();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView name;
        TextView number;
        ImageView avatar;
        ImageButton deleteButton;
        RelativeLayout layout;

        ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.contact_name);
            number = v.findViewById(R.id.contact_number);
            avatar = v.findViewById(R.id.contact_avatar);
            deleteButton = v.findViewById(R.id.contact_delete);
            layout = v.findViewById(R.id.contact_cell_layout);

        }
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_cell, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Contact contact = contacts.get(position);
        // TODO add contact avatar
        holder.name.setText(contact.getName());
        holder.number.setText(leadDB.getContactNumberDao().getContactNumbers(contact.getUid()).get(0).getNumber());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context = view.getContext();
                final Intent intent = new Intent(context, AddContactActivity.class);
                intent.putExtra(AddContactActivity.INTENT_EXTRA_UUID, contact.getUid());
                intent.putExtra(AddContactActivity.INTENT_EXTRA_NUMBER,
                        leadDB.getContactNumberDao().getContactNumbers(contact.getUid()).get(0).getNumber());
                context.startActivity(intent);
            }
        });
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (holder.deleteButton.getVisibility() == View.VISIBLE) {
                    holder.deleteButton.setVisibility(View.GONE);
                } else {
                    holder.deleteButton.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Notify that data was changed
                leadDB.getContactDao().delete(contact);
                contacts.clear();
                contacts.addAll(leadDB.getContactDao().getContacts());
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
