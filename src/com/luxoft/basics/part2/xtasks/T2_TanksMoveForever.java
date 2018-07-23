package com.luxoft.basics.part2.xtasks;

import javax.swing.*;
import java.awt.*;

public class T2_TanksMoveForever extends JPanel
{
    int sleep = 5;
    int tankX = 0;
    int tankY = 0;
    boolean horizontal = false;
    boolean moveRight = true;
    boolean moveDown = true;

     void moveX(){
        if (moveRight) {
            if (tankX > 512) {
                tankX = 512;
                moveRight = false;
                horizontal = false;
            } else
                tankX += 64;
        }
        else {
            if (tankX < 0) {
                tankX = 0;
                moveRight = true;;
                horizontal = false;
            } else
                tankX -= 64;
        }
        repaint();
        sleep(sleep);
        System.out.println(tankX);
    }
    void moveY(){
        if (moveDown) {
            if (tankY > 512) {
                tankY = 512;
                moveDown = false;
                horizontal = true;
            } else
                tankY += 10;
        }
        else {
            if (tankY < 0) {
                tankY = 0;
                moveDown = true;
                horizontal = true;

            } else
                tankY -= 1;
        }
        repaint();
        sleep(sleep);
        System.out.println(tankY);
    }



    void runTheGame()
    {

        repaint();
        sleep(sleep);
        while (true){
            if (horizontal)
                moveX();
            else
                moveY();
        }
    }

    // Magic bellow. Do not worry about this now, you will understand everything in this course.
    // Please concentrate on your tasks only.

    final int BF_WIDTH = 597;
    final int BF_HEIGHT = 597;

    public static void main(String[] args) throws Exception
    {
        T2_TanksMoveForever bf = new T2_TanksMoveForever();
        bf.runTheGame();
    }

    public T2_TanksMoveForever() throws Exception
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

        g.setColor(new Color(255, 0, 0));
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
                    cc = new Color(252, 241, 177);
                }
                else
                {
                    cc = new Color(233, 243, 255);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h * 64, v * 64, 64, 64);
            }
        }
    }

}