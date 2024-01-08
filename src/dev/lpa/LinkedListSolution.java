package dev.lpa;

import java.util.LinkedList;
import java.util.Scanner;

//This record has two fields, name and distance.
//By using a record, we have a constructor, accessor methods, and an overridden toString method, created for us
//implicitly by Java.
//We can use this instead of creating an entirely different class called "Place".
record Place(String name, int distance){

    //Below, we are overriding Java's toString method to make our output easier to read.
    @Override
    public String toString() {
        return String.format("%s (%d)", name, distance);
    }
}
public class LinkedListSolution {

    public static void main(String[] args) {

        //By creating a Linked list like we did below, we're saying our linked list is going to contain a list of our Place
        //records.
        LinkedList<Place> placesToVisit = new LinkedList<>();

        //Now each place we want to add, we create a new object and assign that object the values we are going to add.
        //Notice these are the parameters on the Place record.
        Place adelaide = new Place("Adelaide", 1374);
        //We then execute our addPlace method, which takes our object with it's populated values and our list as
        //arguments, and runs the method to add whatever place we enter.
        addPlace(placesToVisit, adelaide);
        addPlace(placesToVisit, new Place("Brisbane", 917));
        addPlace(placesToVisit, new Place("Perth", 3923));
        addPlace(placesToVisit, new Place("Alice Springs", 2771));
        addPlace(placesToVisit, new Place("Darwin", 3972));
        addPlace(placesToVisit, new Place("Melbourne", 877));

        placesToVisit.addFirst(new Place("Sydney", 0));

        var iterator = placesToVisit.listIterator();
        Scanner scanner = new Scanner(System.in);
        boolean quitLoop = false;
        boolean forward = true;

        printMenu();

        while(!quitLoop){
            if(!iterator.hasPrevious()){
                System.out.println("Originating : " + iterator.next());
                forward = true;
            }
            if(!iterator.hasPrevious()){
                System.out.println("Final : " + iterator.previous());
                forward = false;
            }
            System.out.print("Enter Value: ");
            String menuItem = scanner.nextLine().toUpperCase().substring(0,1);

            switch (menuItem){
                case "F":
                    System.out.println("User wants to go forward");
                    if(!forward){                   //All of this code is for when we change directions from backwards
                        forward = true;             //to forwards, using our iterator. This is necessary because an
                        if(iterator.hasNext()){     //iterator lies between to places on a list at all times.
                            iterator.next();
                        }
                    }
                    if(iterator.hasNext()){
                        System.out.println(iterator.next());
                    }
                    break;

                case "B":
                    System.out.println("User wants to go backwards");
                    if(forward){                   //All of this code is for when we change directions from forwards
                        forward = false;             //to backwards, using our iterator. This is necessary because an
                        if(iterator.hasPrevious()){     //iterator lies between to places on a list at all times.
                            iterator.previous();
                        }
                    }
                    if(iterator.hasPrevious()){
                        System.out.println(iterator.previous());
                    }
                    break;

                case "M":
                    printMenu();
                    break;
                case "L":
                    System.out.println(placesToVisit);
                    break;
                default:
                    quitLoop = true;
                    break;
            }
        }

    }


    //Using two parameters, one of type LinkedList with type of <Place>, and another of type Place.
    private static void addPlace(LinkedList<Place> list, Place place){

        //Adding whatever is passed into place to our LinkedList.
        //Checking to see if our LinkedList already contains the place argument we pass.
        if(list.contains(place)) {
            System.out.println("Found duplicate: " + place);
            //return keyword stops the method from executing (Can return nothing in a void method and break out of method).
            return;
        }

        for(Place p : list){
            if (p.name().equalsIgnoreCase(place.name())){
                System.out.println("Found duplicate: " + place);
                return;
            }
        }

        int matchedIndex = 0;
        for (var listPlace : list){
            if(place.distance() < listPlace.distance()) {
                list.add(matchedIndex, place);
                return;
            }
                matchedIndex++;

        }
        list.add(place);
    }

    private static void printMenu(){

        System.out.println("""
                Available actions (select word or letter):
                (F)orward
                (B)ackwards
                (L)ist Places
                (M)enu
                (Q)uit""");

    }

}
