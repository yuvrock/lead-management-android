package com.community.jboss.leadmanagement.Classes.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Created by carbonyl on 10/01/2018.
 */

@Entity
public class Contact {

    @PrimaryKey
    @NonNull
    private String uid;
    private String name;

    public Contact() {
        this(null);
    }

    @Ignore
    public Contact(String name) {
        this(name, UUID.randomUUID().toString());
    }

    private Contact(@NonNull String uid, String name) {
        this.name = name;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }
}
