package com.resolve.api.resource;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.resolve.api.ResolveClient;
import com.resolve.api.response.ItemResponse;
import com.resolve.api.response.ItemsResponse;

abstract public class BaseResource implements Resource
{
    /**
     * Endpoint URI
     */
    protected String endpoint;

    /**
     * Client instance
     */
    protected ResolveClient client;

    /**
     * Method to get all items response
     *
     * @throws Exception Exception if not request failed
     *
     * @return ItemsResponse
     */
    public ItemsResponse getMany() throws Exception
    {
        HttpResponse httpResponse = null;
        try {
            httpResponse = Unirest.get(this.client.baseUrl + this.getEndpoint())
                    .header("Accept", "application/json; version=" + this.client.version)
                    .queryString("apiKey", this.client.apiKey)
                    .asJson();
        } catch (UnirestException e) {
            throw new Exception(e.getMessage());
        }
        ItemsResponse response = new ItemsResponse(httpResponse);

        return response;
    }

    /**
     * Method to get one item response
     *
     * @param id Item ID
     *
     * @throws Exception Exception if not request failed
     *
     * @return ItemResponse
     */
    public ItemResponse getOne(Integer id) throws Exception
    {
        HttpResponse httpResponse = null;
        try {
            httpResponse = Unirest.get(this.client.baseUrl + this.getEndpoint() + "/" + id)
                    .header("Accept", "application/json; version=" + this.client.version)
                    .queryString("apiKey", this.client.apiKey)
                    .asJson();
        } catch (UnirestException e) {
            throw new Exception(e.getMessage());
        }
        ItemResponse response = new ItemResponse(httpResponse);

        return response;
    }

    /**
     * Get resource endpoint
     *
     * @return string Endpoint
     */
    public String getEndpoint()
    {
        return "";
    }

    /**
     * Set client
     *
     * @param client Resolve API client
     *
     * @return this
     */
    public Resource setClient(ResolveClient client)
    {
        this.client = client;
        return this;
    }
}
