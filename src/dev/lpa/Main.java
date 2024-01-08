package dev.lpa;

import java.util.LinkedList;

public class Main {

    private int distanceFromSydney;

    public static void main(String[] args) {

        var town = new LinkedList<String>();

        addNewTown(town,0);

        printItinerary(town);

    }

    public Main(int distanceFromSydney){
        this.distanceFromSydney = distanceFromSydney;
    }

    public int getDistanceFromSydney(){
        return this.distanceFromSydney;
    }

    private static void addNewTown(LinkedList<String> list, int distanceFromSydney){

        list.offer("Melbourne");
        list.offer("Sydney");
        list.offer("Adelaide");
        list.offer("Alice Springs");
        list.offer("Brisbane");
        list.offer("Darwin");
        list.offer("Perth");
        list.offer("Sydney");

    }

    private static void printItinerary(LinkedList<String> list){

        System.out.println("Trip starts at " + list.getFirst());
        for(int i = 1; i < list.size(); i++){
            System.out.println("--> From: " + list.get(i - 1) + " to " + list.get(i));
        }

        System.out.println("Tripe ends at " + list.getLast());

    }

}
