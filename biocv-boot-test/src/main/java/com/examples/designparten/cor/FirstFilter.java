package com.examples.designparten.cor;

import com.alibaba.fastjson.JSONObject;

/**
 * @author kai
 * @date 2020/12/24 23:16
 */
public class FirstFilter implements Filter{

    @Override
    public void doFilter(JSONObject jsonObject) {
        System.out.println("first");
    }
}
