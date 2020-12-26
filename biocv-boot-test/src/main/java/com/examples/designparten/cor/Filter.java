package com.examples.designparten.cor;

import com.alibaba.fastjson.JSONObject;

/**
 * @author kai
 * @date 2020/12/24 22:59
 */
public interface Filter {

    void doFilter(JSONObject jsonObject);

}
