package com.community.jboss.leadmanagement.Classes.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.community.jboss.leadmanagement.Classes.entities.Tag;

import java.util.List;

/**
 * Created by carbonyl on 10/01/2018.
 */

@Dao
public interface TagDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Tag tag);

    @Query("SELECT * FROM `tag`")
    List<Tag> getTags();
}
