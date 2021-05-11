package com.wilies.salery2;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TempExecutor {

    private static TempExecutor sInstance;
    private final Executor diskIO;
    private final Executor newtWorkIO;

    private TempExecutor(Executor diskIO, Executor newtWorkIO){
        this.diskIO = diskIO;
        this.newtWorkIO = newtWorkIO;
    }


    public static TempExecutor getInstance(){
        if(sInstance == null){
            sInstance = new TempExecutor(Executors.newSingleThreadExecutor(),
                    Executors.newFixedThreadPool(3));
        }

        return sInstance;
    }

    public Executor getDiskIO() {
        return diskIO;
    }

    public Executor getNewtWorkIO() {
        return newtWorkIO;
    }
}
