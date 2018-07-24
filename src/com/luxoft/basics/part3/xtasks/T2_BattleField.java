package com.luxoft.basics.part3.xtasks;

public class T2_BattleField
{

    /**
     * TODO PUT YOUR DESIGN HERE
     */
    String[][] battleField = {
            {" ", "B", " ", "B", "B", " ", " ", " ", "B"},
            {" ", " ", " ", "B", " ", " ", " ", " ", "B"},
            {" ", " ", " ", " ", " ", "B", " ", " ", "B"},
            {" ", " ", " ", " ", " ", " ", " ", "B", "B"},
            {"B", "B", " ", " ", " ", "B", " ", " ", "B"},
            {"B", " ", " ", " ", " ", " ", " ", " ", " "},
            {"B", " ", "B", " ", " ", " ", "B", " ", " "},
            {"B", " ", " ", " ", " ", " ", " ", "B", " "},
            {"B", " ", "B", "T", "E", " ", " ", " ", " "}
    };

    void runTheGame() throws Exception
    {
        printCurrentBattleField();
    }

    /**
     * Should print battle field to console output.
     */
    private void printCurrentBattleField()
    {
        for (String line[] : battleField){
            for (String el : line) {
                if (el.equals(" ")  )
                    System.out.print("-");
                else
                    System.out.print(el);
            }
            System.out.println();
        }

    }


    // Magic bellow. Do not worry about this now, you will understand everything in this course.
    // Please concentrate on your tasks only.


    public static void main(String[] args) throws Exception
    {
        T2_BattleField bf = new T2_BattleField();
        bf.runTheGame();
    }

    public T2_BattleField() throws Exception
    {
    }
}