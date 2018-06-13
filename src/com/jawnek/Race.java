package com.jawnek;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Race extends Thread {
    public static void main(String[] args) {

        Race race = new Race(15);
        race.start();

    }

    private List<Biker> bikers = new ArrayList<>();
    private final int bikersLimit;
    private static int counter = 0;
    private String[] names;
    ScheduledExecutorService scheduledExecutorService;


    public Race(int limitOfBikers) {
        this.bikersLimit = limitOfBikers;
        this.names = new String[15];
        names[0] = "Grzeda";
        names[1] = "Wnek";
        names[2] = "Wolanski";
        names[3] = "Groszek";
        names[4] = "Siwiec";
        names[5] = "Barucha";
        names[6] = "Kowal";
        names[7] = "Kowalski";
        names[8] = "Shwartz";
        names[9] = "Nowak";
        names[10] = "Grzesiuk";
        names[11] = "Holbowski";
        names[12] = "Krychowicz";
        names[13] = "Kalakan";
        names[14] = "Smith";

        this.scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
    }

    public void printFirstThreeBikers(){
        for(int i = 0; i<this.bikers.size()-1; i++){
            if(i>=3)
                break;
            System.out.println(this.bikers.get(i).toString());
        }
    }

    public void printBikers(){
        for(int i = 0; i<this.bikers.size()-1; i++){
            System.out.println(this.bikers.get(i).toString());
        }
    }


    @Override
    public synchronized void run() {
        while(counter<bikersLimit) {

            Biker biker = new Biker(names[counter]);
            counter++;
            biker.start();
            scheduledExecutorService.scheduleWithFixedDelay(biker, 0, 10, TimeUnit.SECONDS);
            this.bikers.add(biker);

            System.out.println("====================================================");
            try{
                //biker.join();
                Thread.currentThread().sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            Collections.sort(this.bikers, (o1, o2) -> {
                if(o1.getTime()>o2.getTime()){
                    return 1;
                }else if(o1.getTime()==o2.getTime()){
                    return 0;
                }else{
                    return -1;
                }
            });
            if(counter==bikersLimit){
                printBikers();
            }else {
                printFirstThreeBikers();
            }
        }

        scheduledExecutorService.shutdown();

    }
}
