package com.dicegame.controller;

import com.dicegame.model.DiceGames;
import com.dicegame.model.DiceResults;
import com.dicegame.repository.DiceResultsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@RestController
public class DiceResultsController
{
    @Autowired
    private DiceResultsRepository repository;

    @GetMapping("/dicegames")
    public List<DiceResults> getAllDiceResults()
    {
        return repository.findAll();
    }

    @RequestMapping(value = "/saveresult", headers = "content-type=application/json")
    @ResponseBody
    public DiceResults saveDiceResult(@RequestBody DiceResults diceResult)
    {
        DiceGames diceGame = new DiceGames();
        diceResult.setDiceGames(diceGame);
//Fallo al guardar por la id del game
        //java.sql.SQLException: Field 'dice_games_id' doesn't have a default value
        System.out.println("Voy a guardar el diceResult " + diceResult.toString());
        return repository.save(diceResult);
    }
}
