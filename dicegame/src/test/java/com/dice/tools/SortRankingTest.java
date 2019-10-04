/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dice.tools;

import com.dice.models.Game;
import com.dice.models.Player;
import com.dice.models.RateDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class SortRankingTest
{

    public SortRankingTest()
    {
    }

    @Test
    public void testOrderListRateDTO() throws InterruptedException
    {
        System.out.println("orderListRateDTO");
        List<RateDTO> listDTO = new ArrayList<>();
        List<Player> listPlayer = new ArrayList<Player>();
        //Generate the players
        for (int i = 0; i < 6; i++)
        {
            Player player = new Player();
            player.setIdPlayer(UUID.randomUUID());
            player.setName("Player" + (i + 1));
            listPlayer.add(player);
            System.out.println("Player " + (i + 1) + "/" + listPlayer.get(i).hashCode());
        }

        //Generate the rates
        double rateMax = 99;
        double rateMin = 0;

        //Generate the list of games
        List<Game> gamesMax = new ArrayList<>();
        for (int i = 0; i < 3; i++)
        {
            Game game = new Game();
            gamesMax.add(game);
        }
        System.out.println("GamesMax have " + gamesMax.size());

        List<Game> gamesMin = new ArrayList<>();
        Game game = new Game();
        gamesMin.add(game);
        System.out.println("GamesMin have " + gamesMin.size());

        // Generate the reg dates
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date dateOld = new Date(stamp.getTime());
        System.out.println("RegDate Oldest:\t" + dateOld.toString());

        Thread.sleep(1000);
        Timestamp stamp3 = new Timestamp(System.currentTimeMillis());
        Date dateYoung = new Date(stamp3.getTime());
        System.out.println("RegDate Young:\t" + dateYoung.toString());

        //Put the data into the players
        RateDTO DTO1 = new RateDTO();
        //#1 - Winner 
        listPlayer.get(0).setRegDate(dateYoung);
        listPlayer.get(0).setListGame(gamesMin);
        DTO1.setPlayer(listPlayer.get(0));
        DTO1.setRate(rateMax);
        listDTO.add(0, DTO1);
        //#2
        RateDTO DTO2 = new RateDTO();
        listPlayer.get(1).setRegDate(dateOld);
        listPlayer.get(1).setListGame(gamesMin);
        DTO2.setPlayer(listPlayer.get(1));
        DTO2.setRate(rateMax);
        listDTO.add(1, DTO2);
        //#3
        RateDTO DTO3 = new RateDTO();
        listPlayer.get(2).setRegDate(dateOld);
        listPlayer.get(2).setListGame(gamesMax);
        DTO3.setPlayer(listPlayer.get(2));
        DTO3.setRate(rateMax);
        listDTO.add(2, DTO3);
        //#4
        RateDTO DTO4 = new RateDTO();
        listPlayer.get(3).setRegDate(dateYoung);
        listPlayer.get(3).setListGame(gamesMin);
        DTO4.setPlayer(listPlayer.get(3));
        DTO4.setRate(rateMin);
        listDTO.add(3, DTO4);
        //#5
        RateDTO DTO5 = new RateDTO();
        listPlayer.get(4).setRegDate(dateOld);
        listPlayer.get(4).setListGame(gamesMin);
        DTO5.setPlayer(listPlayer.get(4));
        DTO5.setRate(rateMin);
        listDTO.add(4, DTO5);
        //#6
        RateDTO DTO6 = new RateDTO();
        listPlayer.get(5).setRegDate(dateOld);
        listPlayer.get(5).setListGame(gamesMax);
        DTO6.setPlayer(listPlayer.get(5));
        DTO6.setRate(rateMin);
        listDTO.add(5, DTO6);

        List<RateDTO> expResult = listDTO;
        System.out.println("Ordered List----------------");

        for (int i = 0; i < expResult.size(); i++)
        {

            System.out.println(expResult.get(i).getPlayer().getName()
                    + "/" + expResult.get(i).getRate()
                    + "/" + expResult.get(i).getPlayer().getListGame().size()
                    + "/" + expResult.get(i).getPlayer().getRegDate()
                    + "/" + expResult.get(i).getPlayer().getIdPlayer()
                    + "/" + expResult.get(i).getPlayer().hashCode()
            );
        }

        //Shuffle the list of DTO
        Collections.shuffle(listDTO);
        System.out.println("Shuffled List----------------");
        for (int i = 0; i < listDTO.size(); i++)
        {

            System.out.println(listDTO.get(i).getPlayer().getName()
                    + "/" + listDTO.get(i).getRate()
                    + "/" + listDTO.get(i).getPlayer().getListGame().size()
                    + "/" + listDTO.get(i).getPlayer().getRegDate()
                    + "/" + listDTO.get(i).getPlayer().getIdPlayer()
                    + "/" + listDTO.get(i).getPlayer().hashCode()
            );
        }

        SortRanking instance = new SortRanking();
        List<RateDTO> result = instance.orderListRateDTO(listDTO);
//        List<RateDTO> result = new ArrayList<RateDTO>(listDTO);
//        Collections.sort(result);

        System.out.println("Result List----------------");
        for (int i = 0; i < result.size(); i++)
        {

            System.out.println(result.get(i).getPlayer().getName()
                    + "/" + result.get(i).getRate()
                    + "/" + result.get(i).getPlayer().getListGame().size()
                    + "/" + result.get(i).getPlayer().getRegDate()
                    + "/" + result.get(i).getPlayer().getIdPlayer()
                    + "/" + result.get(i).getPlayer().hashCode()
            );
        }
        if (result.equals(expResult))
        {
            System.out.println("Equals");
        }
        else
        {
            System.out.println("Not equals");
        }
        assertTrue(result.equals(expResult));
    }
}
