package com.jawnek;

import java.util.concurrent.*;

public class Main {

    private  final static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    public static void main(String[] args) throws Exception {
        startRace();
    }

    private static void startRace(){
        Race race = new Race(15);
        final ScheduledFuture<?> raceHandle = executor.scheduleAtFixedRate(race, 0, 2, TimeUnit.SECONDS);
        executor.schedule(() -> {
            if(!raceHandle.isCancelled()) {
                raceHandle.cancel(true);
            }
            if(!executor.isShutdown()){
                executor.shutdown();
            }
        }, 30, TimeUnit.SECONDS);
    }
}


