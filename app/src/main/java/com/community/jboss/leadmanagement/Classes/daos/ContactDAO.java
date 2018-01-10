package com.community.jboss.leadmanagement.Classes.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.community.jboss.leadmanagement.Classes.entities.Contact;

import java.util.List;

/**
 * Created by carbonyl on 10/01/2018.
 */

@Dao
public interface ContactDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("SELECT * FROM contact")
    List<Contact> getContacts();

    @Query("SELECT * FROM contact WHERE uid = :uid")
    Contact getContact(String uid);
}
