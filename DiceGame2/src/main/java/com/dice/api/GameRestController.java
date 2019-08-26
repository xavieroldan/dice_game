package com.dice.api;

import com.dice.model.Game;
import com.dice.repository.GameRepository;
import com.dice.repository.PlayerRepository;
import com.dice.tool.ErrorValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@RestController
public class GameRestController
{
    @Autowired
    GameRepository gameRepo;
    @Autowired
    PlayerRepository playerRepo;

    /*
    GET /players/ranking: retorna el ranking mig de tots els jugadors del sistema. 
    És a dir, el percentatge mig d’èxits.
     */
    @GetMapping("/players/ranking")
    public double getAvgRate() throws ErrorValueException
    {
        double output = 0;
        //Query count games
        double countGames = gameRepo.countAllGames();
        if (countGames != 0)
        {
            //Query count winner games
            double countWinGames = gameRepo.countAllWinGames();
            output = (countWinGames / countGames) * 100;
        }
        else
        {
            throw new ErrorValueException("No hay jugadores en el sistema");
        }
        return output;
    }

    /*
    *******************************************************************************
    POST: create a game
    localhost:8080/newgame    
    {
        "isAnonim": "false",
        "isWinner": "false",
        "isDeleted": "false"
    }
     */
    @PostMapping("/newgame")
    @ResponseBody
    public Game createPlayer(@RequestBody Game game)
    {
        return gameRepo.save(game);
    }

    //GET: List all Games
    /*
    localhost:8080/getallgame
     */
    @GetMapping("/getallgame")
    public Iterable<Game> getAllGame()
    {
        return gameRepo.findAll();
    }
}
