package com.dice.api;

import com.dice.model.Game;
import com.dice.repository.GameRepository;
import com.dice.tool.ErrorTransactionException;
import com.dice.tool.ErrorValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@RestController
public class GameRestController
{
    @Autowired
    GameRepository gameRepo;

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ErrorValueException.class)
    public String return401(ErrorValueException ex)
    {
        return ex.getMessage();
    }

    /*
    GET /players/ranking: retorna el ranking mig de tots els jugadors del sistema. 
    És a dir, el percentatge mig d’èxits.
     */
    @GetMapping("/players/ranking")
    public double getAvgRate() throws ErrorValueException
    {
        double countGames = gameRepo.count();
        if (countGames != 0)
        {
            return (gameRepo.countByIsWinner(true) / countGames) * 100;
        }
        else
        {
            throw new ErrorValueException("No hay jugadas en el sistema");
        }
    }

    /*
    *******************************************************************************
    TODO: DELETE
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
