/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicegame.tools;

import java.text.ParseException;
import java.util.UUID;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class JsonPlayerGenerator
{
    public static void main(String[] args) throws ParseException
    {
        getPlayerJson("Manolo");
    }

    public static String getPlayerJson(String name) throws ParseException
    {

        String id = UUID.randomUUID().toString();
//        String id = UUID.randomUUID().toString().replace("-", "");
        String date = TimeStamp.getJsonDate();
        String output = "{\"idPlayers\": \"" + id
                + "\",\"name\": \"" + name
                + "\",\"regDate\": \"" + date
                + "\"}";
        System.out.println(output);
        return output;
    }

}
