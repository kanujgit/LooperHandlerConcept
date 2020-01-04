package com.test.pilot;
//
// Created by anuj on 4/1/20.
// Copyright (c) 2020 algoworks. All rights reserved.
//

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleWorker extends Thread {

    private static final String TAG = "SimpleWorker";

    private AtomicBoolean alive = new AtomicBoolean(true);
    private ConcurrentLinkedDeque<Runnable> taskDeque = new ConcurrentLinkedDeque<>();

    public SimpleWorker() {
        super(TAG);
        start();
    }

    @Override
    public void run() {
        while (alive.get()) {
            Runnable task = taskDeque.pollFirst();
            if (task != null) {
                task.run();
            }
        }
    }
    public SimpleWorker execute(Runnable task){
        taskDeque.add(task);
        return this;
    }
    public void quit(){
        alive.set(false);
    }

}
