package com.community.jboss.leadmanagement.Classes.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Created by carbonyl on 10/01/2018.
 */

@Entity(foreignKeys = @ForeignKey(
        entity = Contact.class,
        parentColumns = "uid",
        childColumns = "contactId",
        onDelete = ForeignKey.CASCADE
))
public class ContactNumber {

    @PrimaryKey
    @NonNull
    private String uid;
    @NonNull
    private String number;
    @NonNull
    private String contactId;

    @Ignore
    public ContactNumber(String number, String contactId) {
        this(number, contactId, UUID.randomUUID().toString());
    }

    public ContactNumber(@NonNull String number,@NonNull String contactId,@NonNull String uid) {
        this.number = number;
        this.contactId = contactId;
        this.uid = uid;
    }

    @NonNull
    public String getNumber() {
        return number;
    }

    public void setNumber(@NonNull String number) {
        this.number = number;
    }

    @NonNull
    public String getContactId() {
        return contactId;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
}
