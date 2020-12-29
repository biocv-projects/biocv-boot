package com.biocv.boot.autoconfigure;

/**
 * 双重检查单例
 *
 * @author kai
 * @date 2020/12/29 18:57
 */
public class DCLSample {

    private volatile static DCLSample INSTANCE = null;

    /**
     * 构造函数私有
     */
    private DCLSample(){

    }

    public static DCLSample getInstance(){

        if (INSTANCE == null){
            synchronized (DCLSample.class){
                if (INSTANCE == null){
                    INSTANCE = new DCLSample();
                }
            }
        }

        return INSTANCE;
    }
}
