package com.resolve.api.resource;

import com.resolve.api.ResolveClient;
import com.resolve.api.response.ItemsResponse;

public interface Resource
{
    /**
     * Method to get all items response
     *
     * @return ItemsResponse
     */
    public ItemsResponse getMany() throws Exception;

    /**
     * Set client
     *
     * @param ResolveClient Resolve API client
     *
     * @return void
     */
    public void setClient(ResolveClient resolveClient);

    /**
     * Get resource endpoint
     *
     * @return string Endpoint
     */
    public String getEndpoint();
}
