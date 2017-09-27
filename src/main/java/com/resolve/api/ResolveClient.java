package com.resolve.api;

import com.resolve.api.resource.Resource;

/**
 * Resolve API client
 *
 * @author Rene Korss (rene.korss@gmail.com)
 */
public class ResolveClient
{
    /**
     *  Base API URL
     */
    public String baseUrl = "https://resolve.ee/api/";

    /**
     *  API version used
     */
    public String version = "2.0.0";

    /**
     * API key
     */
    public String apiKey;

    /**
     * Constructor
     *
     * @param apiKey API key for authentication
     */
    public ResolveClient(String apiKey)
    {
        this.apiKey = apiKey;
    }

    /**
     * Get resource
     *
     * @param resourceName Desired resource name
     *
     * @return Resource Resource object
     * @throws ClassNotFoundException If resource is not found
     */
    public Resource resource(String resourceName) throws Exception
    {
        Resource resource;

        // Sanitize resource name
        resourceName = this.sanitizeResourceName(resourceName);

        // Init new resource instance
        resource = (Resource) Class.forName(resourceName).newInstance();

        // Set client
        resource.setClient(this);

        return resource;
    }

    /**
     * Sanitize resource name
     *
     * @param resourceName Resource name
     *
     * @return string Sanitized resource name
     */
    protected String sanitizeResourceName(String resourceName)
    {
        if (!resourceName.contains("com.resolve.api.resource.")) {
            // Uppercase resource name
            return "com.resolve.api.resource." + resourceName.substring(0, 1).toUpperCase() + resourceName.substring(1);
        }

        return resourceName;
    }

    /**
     * Detect if resource exists
     *
     * @param resourceName Desired resource name
     *
     * @return boolean True if exists
     */
    public boolean hasResource(String resourceName)
    {
        resourceName = this.sanitizeResourceName(resourceName);

        try  {
            Class.forName(resourceName);
            return true;
        }  catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Set API version
     *
     * @param version API version
     *
     * @return this
     */
    public ResolveClient setVersion(String version)
    {
        this.version = version;
        return this;
    }
}
