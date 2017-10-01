package com.resolve.api.response;

import com.mashape.unirest.http.HttpResponse;

abstract class BaseResponse
{
    protected HttpResponse httpResponse;

    public BaseResponse(HttpResponse response)
    {
        this.httpResponse = response;
    }

    /**
     * Get original httpResponse
     *
     * @return HttpResponse HTTP reponse
     */
    public HttpResponse getHttpResponse()
    {
        return this.httpResponse;
    }
}
