package com.luxoft.basics.part2.xtasks;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class T5_1_MyTanksMoveToQuadrant extends JPanel
{
    int tankX = 256;
    int tankY = 256;
    int speed = 5;
    enum Direction {UP, DOWN, LEFT, RIGHT};

    void runTheGame(){
        while (true){
            Scanner sc = new Scanner(System.in);
            String quad = sc.nextLine();
            if (quad.isEmpty() || !isValidInput(quad))
                continue;

            else
                moveToQuadrant(quad);
        }
    }

    boolean isValidInput(String input){
        return (input.length() == 2 &&
                input.substring(0,1).matches("[a-jA-J]") &&
                input.substring(1).matches("[1-9]")
        );
    }

    void animateMove(int step, char axisXY){
        for (int i = 0; i < 64; i++){
            if (axisXY == 'x')
                tankX += step;
            else if (axisXY == 'y')
                tankY += step;
            else break;
            repaint();
            sleep(speed);
        }
    }
    void move(Direction direction){
        int tankPosition;
        int step;
        char axisXY;

        if (direction == Direction.UP || direction == Direction.DOWN){
            tankPosition = tankY;
            axisXY = 'y';
        }
        else {
            tankPosition = tankX;
            axisXY = 'x';
        }

        step = (direction == Direction.UP || direction == Direction.LEFT) ? -1 : 1;

        if ((tankPosition > 0 && step == -1) || (tankPosition < 512 && step == 1)){
            animateMove(step, axisXY);
        }
        else System.out.println("Dead end. Step is skipped");
    }

    void moveToQuadrant(String quadrant)
    {
        int destY = 64 * (Integer.parseInt(quadrant.substring(1))-1);
        int destX = 64 * "abcdefghij".indexOf(quadrant.charAt(0));

        int stepsX = (destX - tankX) / 64;
        int stepsY = (destY - tankY) / 64;

        for (int stx = 0; stx < Math.abs(stepsX); stx++){
            if (stepsX > 0) {
                move(Direction.RIGHT);
            }
            else move(Direction.LEFT);
        }

        for (int sty = 0; sty < Math.abs(stepsY); sty++){
            if (stepsY > 0) {
                move(Direction.DOWN);
            }
            else move(Direction.UP);
        }
    }


    // Magic bellow. Do not worry about this now, you will understand everything in this course.
    // Please concentrate on your tasks only.

    final int BF_WIDTH = 597;
    final int BF_HEIGHT = 597;

    public static void main(String[] args) throws Exception
    {
        T5_1_MyTanksMoveToQuadrant bf = new T5_1_MyTanksMoveToQuadrant();
        bf.runTheGame();
    }

    public T5_1_MyTanksMoveToQuadrant() throws Exception
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
                    cc = new Color(49, 215, 252);
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
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(new Color(100, 100, 100));
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