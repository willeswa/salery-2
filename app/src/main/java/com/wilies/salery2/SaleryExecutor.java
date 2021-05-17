package com.wilies.salery2;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SaleryExecutor {

    private static SaleryExecutor sInstance;
    private final Executor diskIO;

    private SaleryExecutor(Executor diskIO){
        this.diskIO = diskIO;
    }


    public static SaleryExecutor getInstance(){
        if(sInstance == null){
            sInstance = new SaleryExecutor(Executors.newSingleThreadExecutor());
        }

        return sInstance;
    }

    public Executor getDiskIO() {
        return diskIO;
    }

}
