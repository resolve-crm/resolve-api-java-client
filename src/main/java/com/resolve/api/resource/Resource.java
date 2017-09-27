package com.resolve.api.resource;

import com.resolve.api.ResolveClient;
import com.resolve.api.response.ItemResponse;
import com.resolve.api.response.ItemsResponse;

public interface Resource
{
    /**
     * Method to get all items response
     *
     * @throws Exception Exception if not request failed
     *
     * @return ItemsResponse
     */
    ItemsResponse getMany() throws Exception;

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
