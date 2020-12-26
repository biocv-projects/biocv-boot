package com.examples.designparten.cor;

import java.util.List;

/**
 * 定义filter chain
 * @author kai
 * @date 2020/12/24 22:32
 */
public interface FilterChain {

    boolean matches();

    List<Filter> getFilters();

}
