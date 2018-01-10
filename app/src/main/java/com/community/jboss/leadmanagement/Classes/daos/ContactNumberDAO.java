package com.community.jboss.leadmanagement.Classes.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.community.jboss.leadmanagement.Classes.entities.ContactNumber;

import java.util.List;

/**
 * Created by carbonyl on 10/01/2018.
 */

@Dao
public interface ContactNumberDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ContactNumber contactNumber);

    @Update
    void update(ContactNumber contactNumber);

    @Query("SELECT * FROM contactnumber WHERE number = :number")
    ContactNumber getContactNumber(String number);

    @Query("SELECT * FROM contactnumber WHERE contactId = :contactId")
    List<ContactNumber> getContactNumbers(String contactId);
}
