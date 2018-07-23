package com.luxoft.basics.part2.xtasks;

import javax.swing.*;
import java.awt.*;

public class T3_TanksMove extends JPanel
{
    int tankX = 256;
    int tankY = 256;
    int speed = 10;

    void runTheGame()
    {
        int i = 1;
      while (true) {
          System.out.println("Step " + i++);
          randomMove();
      }
    }

    /**
     * One method call should smoothly move the tank one quadrant according to given direction.
     *
     * !!! Method should ignore wrong commands.
     * For expl: it should do nothing when tank at A0 and direction is 1-UP or 4 - LEFT
     *
     * @param direction can be 1 - up, 2 - right, 3 - down, 4 - left
     */
    void move(int direction)
    {
        switch (direction){
            case 1 : moveUp();
            break;

            case 2 : moveRight();
            break;

            case 3 : moveDown();
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
    void moveDown(){
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
    }
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


    // Magic bellow. Do not worry about this now, you will understand everything in this course.
    // Please concentrate on your tasks only.

    final int BF_WIDTH = 576;
    final int BF_HEIGHT = 576;

    public static void main(String[] args) throws Exception
    {
        T3_TanksMove bf = new T3_TanksMove();
        bf.runTheGame();
    }

    public T3_TanksMove() throws Exception
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