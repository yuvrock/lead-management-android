package com.community.jboss.leadmanagement.Classes;

import com.orm.SugarRecord;

/**
 * Created by carbonyl on 09/12/2017.
 */

public class Tag extends SugarRecord {

    private String key;
    private String value;
    private Contact contact;

    public Tag(String key, String value, Contact contact) {
        this.key = key;
        this.value = value;
        this.contact = contact;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Contact getContact() {
        return contact;
    }
}
