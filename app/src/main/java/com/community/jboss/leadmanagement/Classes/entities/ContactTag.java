package com.community.jboss.leadmanagement.Classes.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

/**
 * Created by carbonyl on 10/01/2018.
 */

@Entity(primaryKeys = {"contactId", "tagId"},
        foreignKeys = {
                @ForeignKey(entity = Contact.class, parentColumns = "uid", childColumns = "contactId"),
                @ForeignKey(entity = Tag.class, parentColumns = "uid", childColumns = "tagId"),
        })
public class ContactTag {

    @NonNull
    private String contactId;
    @NonNull
    private String tagId;

    public ContactTag(@NonNull String contactId,@NonNull String tagId) {
        this.contactId = contactId;
        this.tagId = tagId;
    }

    @NonNull
    public String getTagId() {
        return tagId;
    }

    @NonNull
    public String getContactId() {
        return contactId;
    }

    @NonNull
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @NonNull
    public void setTagID(String tagId) {
        this.tagId = tagId;
    }
}
