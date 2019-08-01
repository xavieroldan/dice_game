/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicegame.tools;

import com.dicegame.model.Players;
import com.google.gson.Gson;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class JsonPlayerGenerator
{
    public static void main(String[] args)
    {
        getPlayerJson("Manolo");
    }

    public static String getPlayerJson(String name)
    {

        String id = UUID.randomUUID().toString().replace("-", "");
        Date date = TimeStamp.getDate();
        String output = "{\"idPlayers\": \"" + id
                + "\",\"name\": \"" + name
                + "\",\"regDate\": \"" + date
                + "\"}";
        System.out.println("Sacando json: " + output);
        return output;
    }

}
