package com.github.charlesvhe.springcloud.practice.zuul;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.ribbon.XHeaderHolder;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * Created by Charles on 2016/8/26.
 */
public class PreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        XHeaderHolder.putHeader(request);

        return null;
    }
}
