package com.petstore.design.pipeline;

public interface FilterChain {
    void doFilter(Request request, Response response);
}

