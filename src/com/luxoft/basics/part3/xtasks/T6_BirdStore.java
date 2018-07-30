package com.luxoft.basics.part3.xtasks;

public class T6_BirdStore
{
    double ourMoney = 1000.0;
    double margin = 0.25;
    String[] birds = new String[50];
    int[] birdsForSale = new int[50];
    double[] price = new double[50];
    //  int[] sickBirds = new int[50];
    //  int[] deadBirds = new int[50];
    int[] soldBirds = new int[50];

    void runDemo(){

        birds[0] = "chick";
        birds[1] = "duck";
        birds[2] = "goose";
        birds[3] = "turkey";
        birds[4] = "parrot";


        birdsForSale[0] = 1;
        birdsForSale[1] = 5;
        birdsForSale[2] = 3;
        birdsForSale[3] = 8;
        birdsForSale[4] = 2;

        price[0] = 10.0;
        price[1] = 20.1;
        price[2] = 30.5;
        price[3] = 40.0;
        price[4] = 50.0;

        showPriceList();
        System.out.println("----------------------------------------------------");

        showBirdPrice("goose");
        showBirdPrice("penguin");
        showMoney();
        sellBird("chick");
        sellBird("chick");
        showMoney();
        addBirds("penguin", 505.0, 2);
        showMoney();
        showPriceList();

    }
    // Technical method - return the index of exact bird if exist
    int getBirdIndex(String bird){

        for (int i = 0; i < birds.length; i++){
            if (birds[i]== null){
                return -1;
            }
            else if (birds[i].equals(bird)){
                return i;
            }
        }
        return -1;
    }

    // Technical method - checks if requested bird is registered in our store
    boolean isRegisteredBird(String bird){
        int index = getBirdIndex(bird);
        if (index == -1){
            System.out.println("We don't have such bird: \"" + bird + "\"" );
            return false;
        }
        else{
            return true;
        }
    }

    // Show our money
    void showMoney(){
        System.out.println("We have " + ourMoney + "$");
    }

    // Selling of 1 bird
    void sellBird(String bird){
        if (isRegisteredBird(bird)){
            int index = getBirdIndex(bird);
            if (birdsForSale[index] == 0){
                System.out.println("All " + bird + "s" + " are sold. Sorry." );
            }
            else {
                birdsForSale[index] -= 1;
                soldBirds[index] += 1;
                ourMoney += price[index];
                System.out.println("You've bought 1 " + bird + "Thank you!");
            }
        }
    }
    // Sow general price list
    void showPriceList() {
        int p = 0;
        // print Birds
        System.out.print("Bird \t\t||");
        for (String s : birds) {
            if (s == null) {
                System.out.println("|");
                break;
            }
            System.out.print(s + "\t|");
            p++;
        }

        // print Prices
        System.out.print("Price \t\t||");
        for (int i = 0; i < p; i++) {
            System.out.print(Double.toString(price[i]) + "\t|");
        }
        System.out.println("|");

        // print count of available items
        System.out.print("Available \t||");
        for (int i = 0; i < p; i++) {
            System.out.print(Integer.toString(birdsForSale[i]) + "\t\t|");
        }
        System.out.println("|");
    }

    // Sow price of exact bird
    void showBirdPrice(String bird){
        if (isRegisteredBird(bird)){
            int index = getBirdIndex(bird);
            System.out.println("The price of " + bird + " is " + price[index]);
        }
    }


    // Add new item: Bird + price with margin + count for sale
    void addBirds (String bird, double birdPrice, int count){
        int i = 0;
        for(String s: birds){
            if (s == null){
                birds[i] = bird;
                break;
            }
            i++;
        }
        if (i < 50){
            price[i] =  birdPrice + birdPrice * margin;
            birdsForSale[i] = count;
            ourMoney -= (birdPrice * count);
        }
        else System.out.println("Our store is full. Sorry.");
    }

    public static void main(String args[]) {
        T6_BirdStore birdStore = new T6_BirdStore();
        birdStore.runDemo();
    }

}
