package com.resolve.api.response;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;

public class ItemsResponse extends BaseResponse
{
    public ItemsResponse(HttpResponse response)
    {
        super(response);
    }

    /**
     * Get items
     *
     * @return List Items list
     */
    public List<ItemsResponseItem> getItems()
    {
        JsonNode body = (JsonNode) this.httpResponse.getBody();
        JSONArray responseItems = body.getObject().getJSONArray("items");

        List<ItemsResponseItem> items = new ArrayList<ItemsResponseItem>();

        for (Object item: responseItems) {
            items.add(new ItemsResponseItem(item.toString()));
        }

        return items;
    }
}
