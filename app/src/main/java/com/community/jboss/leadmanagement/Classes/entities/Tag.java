package com.community.jboss.leadmanagement.Classes.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Created by carbonyl on 10/01/2018.
 */

@Entity
public class Tag {

    @PrimaryKey
    @NonNull
    private String uid;
    private String key;
    private String value;

    public Tag(String key, String value) {
        this(key, value, UUID.randomUUID().toString());
    }

    private Tag(String key, String value, @NonNull String uid) {
        this.key = key;
        this.value = value;
        this.uid = uid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(@NonNull String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(@NonNull String value) {
        this.value = value;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }
}
