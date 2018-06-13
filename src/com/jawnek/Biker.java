package com.jawnek;

import java.util.Random;

public class Biker extends Thread {
    private String name;
    Random random = new Random();
    private int time;

    public Biker(String name) {
        this.name = name;
        /*Genereting gaussian random time for biker*/
        this.time = (int)Math.round((random.nextGaussian()*100+300));
        if(this.time > 370){
            this.time = 370;
        }else if(this.time<250){
            this.time = 250;
        }
    }

    @Override
    public String toString() {
        return  "name: " + name +
                ", time: " + time;
    }

    @Override
    public void run() {
        toString();
    }

    public int getTime() {
        return time;
    }
}
