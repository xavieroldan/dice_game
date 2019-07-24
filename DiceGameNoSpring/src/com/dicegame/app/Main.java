package com.dicegame.app;

import com.dicegame.app.tools.RandomRollGen;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class Main
{
    public static void main(String[] args)
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println(RandomRollGen.getRandomRoll());

        }
    }

}
