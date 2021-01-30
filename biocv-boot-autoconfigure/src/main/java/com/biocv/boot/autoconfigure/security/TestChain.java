package com.biocv.boot.autoconfigure.security;

import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TestChain implements SecurityFilterChain {

    @Override
    public boolean matches(HttpServletRequest request) {
        return true;
    }

    @Override
    public List<Filter> getFilters() {
        return null;
    }
}
