package com.community.jboss.leadmanagement.Classes;

import com.orm.SugarRecord;

/**
 * Created by carbonyl on 09/12/2017.
 */

public class Number extends SugarRecord {

    private int number;
    private Contact contact;

    public Number(int number, Contact contact) {
        this.number = number;
        this.contact = contact;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public int getNumber() {
        return number;
    }

    public Contact getContact() {
        return contact;
    }
}
