package com.luxoft.basics.part3.xtasks;


import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.util.Arrays;

public class T4_TanksShoot extends JPanel
{

    final boolean COLORED_MODE = false;
    final boolean IS_GRID = false;

    final int BF_WIDTH = 596;
    final int BF_HEIGHT = 596;

    // 1 - top, 2 - right, 3 - down, 4 - left
    int tankDirection = 3;

    int tankX = 256;
    int tankY = 256;

    int bulletX = tankX + 25;
    int bulletY = tankY + 25;

    int tankSpeed = 2;
    int bulletSpeed = 1;

    boolean cleanLine = false;
    boolean cleanField = false;

    final String BRICK = "B";
    final String BLANK = " ";

    String[][] battleField = {
            {"B", "B", "B", "B", "B", "B", "B", "B", "B"},
            {" ", " ", " ", " ", " ", " ", " ", " ", "B"},
            {"B", "B", "B", " ", "B", " ", "B", "B", "B"},
            {"B", "B", "B", " ", " ", " ", "B", "B", "B"},
            {"B", "B", "B", " ", "B", " ", "B", "B", "B"},
            {"B", "B", " ", "B", "B", "B", " ", "B", "B"},
            {"B", "B", " ", " ", " ", " ", " ", "B", "B"},
            {"B", " ", " ", "B", "B", "B", " ", " ", "B"},
            {"B", " ", " ", "B", "B", "B", " ", " ", "B"}
    };

    void runTheGame() throws Exception
    {
        printCurrentBattleField();
            clean();
    }

    /**
     *
     * When called tank should produce new bullet.
     * This bullet should smoothly move to the opposite side.
     *
     * Bullet should be destroyed when rich the opposite side.
     *
     * TODO When the bullet shoot something method would clean appropriate quadrant and destroy the bullet.
     * TODO Use #checkAndProcessInterception() to check if you already shoot something.
     */
    void fire(){
        int[] bulQuadrant = new int[2];
        int bulletPosition;
        char bulAxisXY;
        int step;

        // initial position of the bullet
        bulletY = tankY + 25;
        bulletX = tankX + 25;

        // bullet AxisXY
        if (tankDirection == 1 || tankDirection == 3){
            bulletPosition = bulletY;
            bulAxisXY = 'y';
        }
        else {
            bulletPosition = bulletX;
            bulAxisXY = 'x';
        }

        // bullet's flight direction:
        //  1 : right/down;
        // -1 : left/up
        step = (tankDirection == 3 || tankDirection == 2) ? 1 : -1;

        // bullet's flight animation
        while ( ((bulletPosition > 32 && step == -1) || (bulletPosition < 575 && step == 1)) && !cleanLine){
            if (bulAxisXY == 'x') {
                bulletX += step;
                bulletPosition = bulletX;

            }
            else if (bulAxisXY == 'y') {
                bulletY += step;
                bulletPosition = bulletY;
            }
            else break;

            if ((bulletPosition == 32 && step == -1) || (bulletPosition == 575 && step == 1))
                cleanLine = true;
            repaint();
            sleep(bulletSpeed);

            // destruction of the brick
            bulQuadrant = getQuadrant(bulletX, bulletY);
            if (checkAndProcessInterception(bulQuadrant[0],bulQuadrant[1])){
                battleField[bulQuadrant[1]][bulQuadrant[0]] = BLANK;
                printCurrentBattleField();
                break;
            }
        }
    }

    void clean()
    {
    //-------------------------
        turn(1);
    //-------------------------
        if (tankY == 0)
            cleanLine = true;
        while (!cleanLine){
        fire();
        }
    //-------------------------
        while (tankY > 0) {
            move(1);
        }
    //-------------------------
        turn(4);
    //-------------------------
            cleanLine = false;
    //-------------------------
        if (tankX == 0)
            cleanLine = true;
        while (!cleanLine){
            fire();
        }
    //-------------------------

        while (tankX > 0) {
            move(tankDirection);
        }
    //-------------------------
        for (int i = 0; i <9; i++) {
    //-------------------------
            turn(2);
    //-------------------------
            cleanLine = false;
    //-------------------------
            while (!cleanLine) {
                fire();
            }
    //-------------------------
            turn(3);
            cleanLine = false;
    //-------------------------
            fire();
    //-------------------------
            move(3);
    //-------------------------
            cleanLine = false;
        }
        cleanField = true;
        repaint();
    }




    /**
     *
     * Should return true if bullet located in non-empty quadrant.
     *
     */
    boolean checkAndProcessInterception(int x, int y)
    {
        return (battleField[y][x].equals(BRICK));
    }

    private void printCurrentBattleField()
    {
        for (String[] row : battleField)
        {
            System.out.println(Arrays.toString(row));
        }
    }

    int[] getQuadrant(int x, int y)
    {
        return new int[] {x / 64, y / 64};
    }

    void move(int direction){
        int tankPosition;
        int step;
        char tankAxisXY;
        bulletX = -100;
        bulletY = -100;
        turn(direction);


        if (direction == 1 || direction == 3){
            tankPosition = tankY;
            tankAxisXY = 'y';
        }
        else {
            tankPosition = tankX;
            tankAxisXY = 'x';
        }
        step = (direction == 3 || direction == 2) ? 1 : -1;

        if ((tankPosition > 0 && step == -1) || (tankPosition < 512 && step == 1)){
            for (int i = 0; i < 64; i++){
                if (tankAxisXY == 'x')
                    tankX += step;
                else if (tankAxisXY == 'y')
                    tankY += step;
                else break;
                repaint();
                sleep(tankSpeed);
            }
        }
    }

    void turn(int direction)
    {
        tankDirection = direction;
        repaint();
    }

    // Magic bellow. Do not worry about this now, you will understand everything in this course.
    // Please concentrate on your tasks only.


    public static void main(String[] args) throws Exception
    {
        T4_TanksShoot bf = new T4_TanksShoot();
        bf.runTheGame();
    }

    public T4_TanksShoot() throws Exception
    {
        JFrame frame = new JFrame("YOUR TANK SHOULD FIRE!!!");
        frame.setLocation(750, 150);
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
        catch (Exception ignore)
        {
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        paintBF(g);

        paintBorders(g);

        g.setColor(new Color(255, 0, 0));
        g.fillRect(tankX, tankY, 64, 64);

        if (cleanField) {
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.setColor(new Color(0, 0, 0));
            g.drawString("G", 40, 255);
            g.drawString("O", 64 + 40, 255);
            g.drawString("O", 2 * 64 + 40, 255);
            g.drawString("D", 3 * 64 + 40, 255);
            g.drawString(" ", 4 * 64 + 40, 255);
            g.drawString("J", 5 * 64 + 40, 255);
            g.drawString("O", 6 * 64 + 40, 255);
            g.drawString("B", 7 * 64 + 40, 255);
            g.drawString("!", 8 * 64 + 40, 255);

        }

        g.setColor(new Color(0, 255, 0));
        if (tankDirection == 1)
        {
            g.fillRect(tankX + 20, tankY, 24, 34);
        }
        else if (tankDirection == 3)
        {
            g.fillRect(tankX + 20, tankY + 30, 24, 34);
        }
        else if (tankDirection == 4)
        {
            g.fillRect(tankX, tankY + 20, 34, 24);
        }
        else
        {
            g.fillRect(tankX + 30, tankY + 20, 34, 24);
        }

        g.setColor(new Color(255, 255, 0));
        g.fillRect(bulletX, bulletY, 14, 14);
    }

    private void paintBorders(Graphics g)
    {
        for (int j = 0; j < battleField.length; j++)
        {
            for (int k = 0; k < battleField.length; k++)
            {
                if (battleField[j][k].equals("B"))
                {
                    String coordinates = getQuadrantXY(j + 1, k + 1);
                    int separator = coordinates.indexOf("_");
                    int y = Integer.parseInt(coordinates.substring(0, separator));
                    int x = Integer.parseInt(coordinates.substring(separator + 1));
                    g.setColor(new Color(0, 0, 255));
                    g.fillRect(x, y, 64, 64);

                    if (IS_GRID)
                    {
                        g.setColor(new Color(0, 0, 0));
                        g.drawRect(x, y, 64, 64);
                    }
                }
            }
        }
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
                if (COLORED_MODE)
                {
                    if (i % 2 == 0)
                    {
                        cc = new Color(252, 241, 177);
                    }
                    else
                    {
                        cc = new Color(233, 243, 255);
                    }
                }
                else
                {
                    cc = new Color(180, 180, 180);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h * 64, v * 64, 64, 64);
            }
        }
    }

    private String getQuadrantXY(int v, int h)
    {

        return (v - 1) * 64 + "_" + (h - 1) * 64;
    }

}