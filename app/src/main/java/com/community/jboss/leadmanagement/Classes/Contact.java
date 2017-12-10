package com.community.jboss.leadmanagement.Classes;

import com.orm.SugarRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by carbonyl on 09/12/2017.
 */

public class Contact extends SugarRecord {

    private UUID uuid;
    private String name;

    public Contact(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public List<Number> getNumbers() {
        return Number.find(Number.class, "contact = ?", this.getId().toString());
    }

    public Map<String, String> getTags() {
        List<Tag> tagList = Tag.find(Tag.class, "contact = ?", this.getId().toString());
        Map<String, String> tags = new HashMap<>();
        for (Tag tag: tagList) {
            tags.put(tag.getKey(), tag.getValue());
        }
        return tags;
    }
}
