/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicegame.api;

import com.dicegame.app.tools.TimeStamp;
import com.dicegame.models.Players;
import com.google.gson.Gson;
import java.util.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@RestController
//Intocable
@RequestMapping("/api")
public class ApiCallsController
{
    @RequestMapping(value = "/test")
    public String test()
//    public <T> Object test()
    {
        String idPlayer = "klklashdfk";
        String name = "Lololo";
        Date date = TimeStamp.getDate();
        Players player = new Players(idPlayer, name, date);
        Gson gson = new Gson();
        String json = gson.toJson(player);
        System.out.println(json);
        System.out.println("\nTest ok");
        return json;
    }
}
