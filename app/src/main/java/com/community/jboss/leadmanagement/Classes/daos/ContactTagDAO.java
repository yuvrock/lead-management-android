package com.community.jboss.leadmanagement.Classes.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.community.jboss.leadmanagement.Classes.entities.Contact;
import com.community.jboss.leadmanagement.Classes.entities.ContactTag;
import com.community.jboss.leadmanagement.Classes.entities.Tag;

import java.util.List;

/**
 * Created by carbonyl on 10/01/2018.
 */

@Dao
public interface ContactTagDAO {

    @Insert
    void insert(ContactTag contactTag);

    @Query("SELECT * FROM contact INNER JOIN contacttag ON contact.uid = contacttag.contactId WHERE contacttag.tagId = :tagId")
    List<Contact> getContactList(String tagId);

    @Query("SELECT * FROM `tag` INNER JOIN contacttag ON `tag`.uid = contacttag.tagId WHERE contacttag.contactId = :contactId")
    List<Tag> getTagList(String contactId);
}
