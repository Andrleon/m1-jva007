package com.luxoft.basics.part2.xtasks;

import javax.swing.*;
import java.awt.*;

public class T4_TanksMoveRandom extends JPanel
{
    int tankX = 256;
    int tankY = 256;
    int speed = 10;

    void runTheGame()
    {
        while (true)
        {
            randomMove();
        }
    }

    void move(int direction)
    {
        switch (direction){
            case 1 :
                moveUp();
                break;
            case 2 :
                moveRight();
                break;
            case 3 :
                moveDown();
                break;
            case 4 :
                moveLeft();
                break;
        }
    }
    void animateMove(char axisXY, int step){
        for (int i = 0; i < 64; i++){
            if (axisXY == 'x'){
                tankX += step;
            }
            else if (axisXY == 'y')
                tankY += step;
            else
                break;
            repaint();
            sleep(speed);
        }
    }

    void moveLeft(){
        if (tankX > 0 ){
            animateMove('x', -1);
            System.out.println("Moved left to " + tankX);
        }
        else System.out.println("Dead end. Move is skipped.");
    }
    void moveRight(){
        if (tankX < 512 ){
            animateMove('x', 1);
            System.out.println("Moved right to " + tankX);
        }
        else System.out.println("Dead end. Move is skipped.");
    }
    void moveUp(){
        if (tankY > 0 ){
            animateMove('y', -1);
            System.out.println("Moved up to " + tankY);
        }
        else System.out.println("Dead end. Move is skipped.");
    }

    void moveDown(){
        if (tankY < 512 ){
            animateMove('y', 1);
            System.out.println("Moved down to " + tankY);
        }

        else System.out.println("Dead end. Move is skipped.");
    }

    void randomMove(){
        move((int)(System.currentTimeMillis() % 4 +1));
    }



    // Magic bellow. Do not worry about this now, you will understand everything in this course.
    // Please concentrate on your tasks only.

    final int BF_WIDTH = 596;
    final int BF_HEIGHT = 598;

    public static void main(String[] args) throws Exception
    {
        T4_TanksMoveRandom bf = new T4_TanksMoveRandom();
        bf.runTheGame();
    }

    public T4_TanksMoveRandom() throws Exception
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

        g.setColor(new Color(255, 11, 227));
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
                    cc = new Color(54, 252, 173);
                }
                else
                {
                    cc = new Color(64, 54, 255);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h * 64, v * 64, 64, 64);
            }
        }
    }

}