package com.luxoft.basics.part2.xtasks;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class T5_TanksMoveToQuadrant extends JPanel
{
    int tankX = 256;
    int tankY = 256;
    int speed = 5;
    enum Direction {up, down, left, right};

    void runTheGame(){
        moveToQuadrant("a1");
        moveToQuadrant("f8");
        moveToQuadrant("b3");
        moveToQuadrant("j9");
    }

    /**
     * One method call should smoothly move the tank one quadrant according to given direction.
     *
     * @param direction can be 1 - up, 2 - right, 3 - down, 4 - right
     */
    void move(int direction)
    {
        switch (direction){
            case 1 : moveY("up");
                break;

            case 2 : moveRight();
                break;

            case 3 : moveY("down");
                break;

            case 4 : moveLeft();
                break;
        }
    }
    void randomMove(){
        move((int) (System.currentTimeMillis() % 4 + 1));
    }

    void moveUp(){
        if (tankY > 0){
            System.out.println("move up");
            System.out.println("tankY = " + tankY);
            for (int i = 0; i < 64; i++){
                tankY-=1;
                repaint();
                sleep(speed);
            }
            System.out.println("tankY = " + tankY);
        }
        else System.out.println("Step is skipped");
    }
    void moveY(String direction){
        int step = (direction.equals("up")) ? -1 : 1;
        System.out.print("direction = " + direction +" step = " + step);
        if ((tankY > 0 && step == -1) || (tankY < 512 && step == 1)){
            System.out.print("Tank moved from Y = " + tankY);
            for (int i = 0; i < 64; i++){
                tankY += step;
                repaint();
                sleep(speed);
            }
            System.out.println(" to Y = " + tankY);
        }
        else System.out.println("Step is skipped");
    }

    /*void moveDown(){
        if (tankY < 512){
            System.out.println("move down");
            System.out.println("tankY = " + tankY);
            for (int i = 0; i < 64; i++){
                tankY += 1;
                repaint();
                sleep(speed);
            }
            System.out.println("tankY = " + tankY);
        }
        else System.out.println("Step is skipped");
    }*/
    void moveLeft(){
        if (tankX > 0){
            System.out.println("move left");
            System.out.println("tankX = " + tankX);
            for (int i = 0; i < 64; i++){
                tankX -= 1;
                repaint();
                sleep(speed);
            }
            System.out.println("tankX = " + tankX);
        }
        else System.out.println("Step is skipped");
    }
    void moveRight(){
        if (tankX < 512){
            System.out.println("move right");
            System.out.println("tankX = " + tankX);
            for (int i = 0; i < 64; i++){
                tankX += 1;
                repaint();
                sleep(speed);
            }
            System.out.println("tankX = " + tankX);
        }
        else System.out.println("Step is skipped");
    }

    void moveToQuadrant(String quadrant)
    {
        int destY = 64 * (Integer.parseInt(quadrant.substring(1))-1);
        int destX = 64 * "abcdefghij".indexOf(quadrant.charAt(0));

        int stepsX = (destX - tankX) / 64;
        int stepsY = (destY - tankY) / 64;
        System.out.println("StepY = " + stepsY);

        for (int stx = 0; stx < Math.abs(stepsX); stx++){
            if (stepsX > 0) {
                moveRight();
            }
            else moveLeft();
        }

        for (int sty = 0; sty < Math.abs(stepsY); sty++){
            if (stepsY > 0) {
                moveY("down");
            }
            else moveY("up");
        }

    }


    // Magic bellow. Do not worry about this now, you will understand everything in this course.
    // Please concentrate on your tasks only.

    final int BF_WIDTH = 597;
    final int BF_HEIGHT = 597;

    public static void main(String[] args) throws Exception
    {
        T5_TanksMoveToQuadrant bf = new T5_TanksMoveToQuadrant();
        bf.runTheGame();
    }

    public T5_TanksMoveToQuadrant() throws Exception
    {
        JFrame frame = new JFrame("MOVE TANK FORWARD");
        frame.setLocation(500, 150);
        frame.setMinimumSize(new Dimension(BF_WIDTH, BF_HEIGHT + 22));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {

        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        paintBF(g);

        g.setColor(new Color(90, 13, 255));
        g.fillRect(tankX, tankY, 64, 64);
    }

    private void paintBF(Graphics g)
    {
        super.paintComponent(g);

        int i = 0;
        Color cc;
        for (int v = 0; v < 9; v++)
        {
            for (int h = 0; h < 9; h++)
            {
                if (i % 2 == 0)
                {
                    cc = new Color(42, 252, 122);
                }
                else
                {
                    cc = new Color(233, 243, 255);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h * 64, v * 64, 64, 64);
                g.drawString("A", 25, 25);
            }
        }
        g.setColor(new Color(1, 1, 1));
        g.drawString("A", 40, 15);
        g.drawString("B", 64 + 40, 15);
        g.drawString("C", 2 * 64 + 40, 15);
        g.drawString("D", 3 * 64 + 40, 15);
        g.drawString("E", 4 * 64 + 40, 15);
        g.drawString("F", 5 * 64 + 40, 15);
        g.drawString("G", 6 * 64 + 40, 15);
        g.drawString("H", 7 * 64 + 40, 15);
        g.drawString("I", 8 * 64 + 40, 15);

        g.drawString("1", 10, 50);
        g.drawString("2", 10, 64 + 50);
        g.drawString("3", 10, 2 * 64 + 50);
        g.drawString("4", 10, 3 * 64 + 50);
        g.drawString("5", 10, 4 * 64 + 50);
        g.drawString("6", 10, 5 * 64 + 50);
        g.drawString("7", 10, 6 * 64 + 50);
        g.drawString("8", 10, 7 * 64 + 50);
        g.drawString("9", 10, 8 * 64 + 50);

    }

}