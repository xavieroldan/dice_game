package com.dice.api;

import com.dice.model.Player;
import com.dice.repository.PlayerRepository;
import com.dice.tool.ErrorValueException;
import com.dice.tool.GameMaker;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import static sun.audio.AudioPlayer.player;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@RestController
public class PlayerRestController
{
    @Autowired
    PlayerRepository playerRepo;

    /*
    POST: /players : crea un jugador
    {    
    "name": "Foo"
    }    
     */
    @PostMapping("/players")
    @ResponseBody
    public Player createPlayer(@RequestBody Player player) throws ErrorValueException
    {
        if (player == null)
        {
            throw new ErrorValueException();
        }
        try
        {
            return playerRepo.save(player);
        }
        catch (Exception e)
        {
            throw new ErrorValueException();
        }
    }

    /*
    PUT /players : modifica el nom del jugador
    {    
    "idPlayer" : "150db883-c08b-4a0d-aa0c-c8607f3f2c93",
    "name": "Martaaaa"
    } 
    Keep the correct format to no have format JSON problems
     */
    @PutMapping("/players")
    @ResponseBody
    public Player editName(@RequestBody Player playerToEdit) throws ErrorValueException
    {
        if (playerToEdit == null)
        {
            throw new ErrorValueException();
        }
        try
        {
            Optional<Player> player = playerRepo.findById(playerToEdit.getIdPlayer());
            player.get().setName(playerToEdit.getName());
            return playerRepo.save(player.get());
        }
        catch (Exception e)
        {
            throw new ErrorValueException();
        }
    }

    /*
    POST /players/{id}/games/ : un jugador específic realitza una tirada dels daus.
    localhost:8080/players/150db883-c08b-4a0d-aa0c-c8607f3f2c93/games/
     */
    @RequestMapping(value = "/players/{id}/games/", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Player playGame(@PathVariable UUID id) throws ErrorValueException
    {
        if (id == null)
        {
            throw new ErrorValueException();
        }
        try
        {
            //Verify if player exists
            Optional<Player> playerToPlay = playerRepo.findById(id);
            //play and save the game
            GameMaker game = new GameMaker();
            return playerRepo.save(game.playGame(playerToPlay.get()));
        }
        catch (Exception e)
        {
            throw new ErrorValueException();
        }
    }

    /*
    TODO: delete
    *******************************************************************************************
    
    POST: create a player    
    (Only needs the name)
    localhost:8080/new    
    {    
    "name": "Foo"
    }
     */
    @PostMapping("/new")
    @ResponseBody
    public Player testCreatePlayer(@RequestBody Player player)
    {
        return playerRepo.save(player);
    }

    //TODO: delete
    //GET: TEST List all Players
    /*
    localhost:8080/getall
     */
    @GetMapping("/getall")
    public Iterable<Player> testGetAllPlayer()
    {
        return playerRepo.findAll();
    }

}
