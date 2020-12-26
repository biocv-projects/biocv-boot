package com.examples.designparten.cor;

import java.util.List;

/**
 * @author kai
 * @date 2020/12/24 22:58
 */
public class DefaultFilterChain implements FilterChain {

    private final List<Filter> filters;

    public DefaultFilterChain(List<Filter> filters){
        this.filters = filters;
    }

    @Override
    public boolean matches() {
        return true;
    }

    @Override
    public List<Filter> getFilters() {
        return filters;
    }
}
