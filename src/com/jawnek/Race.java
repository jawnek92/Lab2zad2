package com.jawnek;

import java.io.*;
import java.util.*;


public class Race implements Runnable {

    private List<Biker> bikers = new ArrayList<>();
    private final int bikersLimit;
    private static int counter = 0;
    private String[] names;


    public Race(int limitOfBikers) {
        this.bikersLimit = limitOfBikers;
        this.names = new String[15];
        try(BufferedReader reader = new BufferedReader(new FileReader("nazwiska.txt"))){
            String line = reader.readLine();
            this.names = line.split(",");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void printFirstThreeBikers(){
        for(int i = 0; i<this.bikers.size(); i++){
            if(i>=3)
                break;
            System.out.println((i+1)+". "+this.bikers.get(i).toString());
        }
    }

    public void printBikers(){
        for(int i = 0; i<this.bikers.size(); i++){
            System.out.println((i+1)+". "+this.bikers.get(i).toString());
        }
    }


    @Override
    public void run() {

        Biker biker = new Biker(names[counter]);
        counter++;
        biker.start();
        this.bikers.add(biker);


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
        }else
            printFirstThreeBikers();
        System.out.println("====================================================");
    }
}
