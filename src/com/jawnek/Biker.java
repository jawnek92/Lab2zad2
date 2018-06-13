package com.jawnek;

import java.util.Random;

public class Biker extends Thread implements Comparable {
    private String name;
    Random random = new Random();
    private int time;

    public Biker(String name) {
        this.name = name;
        /*Genereting gaussian random time for biker*/
        this.time = (int)Math.round((random.nextGaussian()*100+300));
//        if(this.time > 370){
//            this.time = 370;
//        }else if(this.time<250){
//            this.time = 250;
//        }
    }

    @Override
    public String toString() {
        return "Biker: " +
                "name: " + name + '\'' +
                ", time: " + time;
    }

    @Override
    public void run() {
        toString();
    }

    public int getTime() {
        return time;
    }

    @Override
    public int compareTo(Object o) {
        if(this.time>((Biker)o).getTime()){
            return -1;
        }else if(this.time==((Biker)o).getTime()){
            return 0;
        }else{
            return 1;
        }
    }
}
