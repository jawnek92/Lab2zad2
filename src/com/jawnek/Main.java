package com.jawnek;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.*;

public class Main {

    private  final static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    public static void main(String[] args) throws Exception {
        startRace();
    }

    private static void startRace(){
        Race race = new Race(15);
        final ScheduledFuture<?> raceHandle = executor.scheduleAtFixedRate(race, 1, 15, TimeUnit.SECONDS);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                if(!raceHandle.isCancelled()) {
                    raceHandle.cancel(true);
                }
                if(!executor.isShutdown()){
                    executor.shutdown();
                }
            }
        }, 15, TimeUnit.SECONDS);
    }
}


