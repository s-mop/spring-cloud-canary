package org.springframework.cloud.netflix.ribbon;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * Created by charles on 2017/5/26.
 */
public class TagInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        String first = request.getHeaders().getFirst(TagHolder.X_TAG);
        request.getHeaders().add(TagHolder.X_TAG, first);

        return execution.execute(request, body);
    }
}
