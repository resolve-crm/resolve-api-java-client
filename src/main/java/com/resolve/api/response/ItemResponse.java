package com.resolve.api.response;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONObject;

public class ItemResponse extends BaseResponse
{
    public ItemResponse(HttpResponse response)
    {
        super(response);
    }

    /**
     * Get item
     *
     * @return List Item
     */
    public ItemResponseItem getItem()
    {
        JsonNode body = (JsonNode) this.httpResponse.getBody();
        JSONObject responseItem = body.getObject().getJSONObject("item");

        return new ItemResponseItem(responseItem.toString());
    }
}
