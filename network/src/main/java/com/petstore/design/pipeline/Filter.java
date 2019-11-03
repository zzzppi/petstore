package com.petstore.design.pipeline;

public interface Filter {
    void doFilter(Request request, Response response, FilterChain chain);
}
