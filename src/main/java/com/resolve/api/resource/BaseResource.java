package com.resolve.api.resource;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.resolve.api.ResolveClient;
import com.resolve.api.response.ItemResponse;
import com.resolve.api.response.ItemsResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

abstract public class BaseResource implements Resource
{
    /**
     * Client instance
     */
    protected ResolveClient client;

    /**
     * Method to get all items response
     *
     * @return ItemsResponse
     *
     * @throws Exception Exception if not request failed
     */
    public ItemsResponse getMany() throws Exception
    {
        return this.getManyResponse("");
    }

    /**
     * Method to get all items response by status
     *
     * @param status Desired items status
     *
     * @return ItemsResponse
     *
     * @throws Exception Exception if not request failed
     */
    public ItemsResponse getMany(String status) throws Exception
    {
        return this.getManyResponse("/"+status);
    }

    /**
     * Method to get all items response by last modified date
     *
     * @param lastModified Last modified datetime
     *
     * @return ItemsResponse
     *
     * @throws Exception Exception if not request failed
     */
    public ItemsResponse getMany(Date lastModified) throws Exception
    {
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(lastModified);
        return this.getManyResponse("/"+formattedDate);
    }

    /**
     * Make request to many endpoint based on uri
     *
     * @param uri API endpoint URI
     *
     * @return ItemsResponse
     *
     * @throws Exception
     */
    private ItemsResponse getManyResponse(String uri) throws Exception
    {
        HttpResponse httpResponse = null;
        try {
            httpResponse = Unirest.get(this.client.baseUrl + this.getEndpoint() + uri)
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
        return this.getClass().getSimpleName().toLowerCase();
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
