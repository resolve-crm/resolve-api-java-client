package com.resolve.api.resource;

import com.resolve.api.ResolveClient;
import com.resolve.api.response.ItemResponse;
import com.resolve.api.response.ItemsResponse;
import java.util.Date;

public interface Resource
{
    /**
     * Method to get all items response
     *
     * @return ItemsResponse
     *
     * @throws Exception Exception if not request failed
     */
    public ItemsResponse getMany() throws Exception;

    /**
     * Method to get all items response by status
     *
     * @param status Desired items status
     *               
     * @return ItemsResponse
     *
     * @throws Exception Exception if not request failed
     */
    public ItemsResponse getMany(String status) throws Exception;

    /**
     * Method to get all items response by last modified date
     *
     * @param lastModified Last modified datetime
     *
     * @return ItemsResponse
     *
     * @throws Exception Exception if not request failed
     */
    public ItemsResponse getMany(Date lastModified) throws Exception;

    /**
     * Method to get one item response
     *
     * @param id Item ID
     *
     * @throws Exception Exception if not request failed
     *
     * @return ItemResponse
     */
    public ItemResponse getOne(Integer id) throws Exception;

    /**
     * Set client
     *
     * @param client Resolve API client
     *
     * @return this
     */
    Resource setClient(ResolveClient client);

    /**
     * Get resource endpoint
     *
     * @return string Endpoint
     */
    String getEndpoint();
}
