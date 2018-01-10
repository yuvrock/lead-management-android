package com.community.jboss.leadmanagement;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.community.jboss.leadmanagement.Classes.daos.ContactDAO;
import com.community.jboss.leadmanagement.Classes.daos.ContactNumberDAO;
import com.community.jboss.leadmanagement.Classes.daos.ContactTagDAO;
import com.community.jboss.leadmanagement.Classes.daos.TagDAO;
import com.community.jboss.leadmanagement.Classes.entities.Contact;
import com.community.jboss.leadmanagement.Classes.entities.ContactNumber;
import com.community.jboss.leadmanagement.Classes.entities.ContactTag;
import com.community.jboss.leadmanagement.Classes.entities.Tag;

/**
 * Created by carbonyl on 10/01/2018.
 */

@Database(entities = {
        Contact.class,
        ContactNumber.class,
        Tag.class,
        ContactTag.class
}, version = 1)
public abstract class LeadDB extends RoomDatabase {

    public abstract ContactDAO getContactDao();

    public abstract ContactNumberDAO getContactNumberDao();

    public abstract TagDAO getGroupDao();

    public abstract ContactTagDAO getContactGroupDao();
}
