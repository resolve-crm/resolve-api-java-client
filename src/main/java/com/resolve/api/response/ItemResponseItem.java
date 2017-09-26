package com.resolve.api.response;

import org.json.JSONObject;

public class ItemResponseItem extends JSONObject
{
    public ItemResponseItem(String source)
    {
        super(source);
    }

    public int getId()
    {
        return this.getInt("id");
    }
}
