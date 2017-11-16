package com.github.charlesvhe.springcloud.practice.core;

import java.io.IOException;

import org.springframework.cloud.netflix.ribbon.XHeaderHolder;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

/**
 * Created by charles on 2017/5/26.
 */
public class CoreHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);

        String first = request.getHeaders().getFirst(XHeaderHolder.X_HEADER);
        requestWrapper.getHeaders().add(XHeaderHolder.X_HEADER, first);

        return execution.execute(requestWrapper, body);
    }
}
