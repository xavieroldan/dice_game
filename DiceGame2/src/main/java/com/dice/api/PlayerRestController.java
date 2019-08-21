package com.dice.api;

import com.dice.model.Player;
import com.dice.repository.PlayerRepository;
import com.dice.tool.InvalidParamException;
import com.dice.tool.NotFoundException;
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
public class PlayerRestController
{
    @Autowired
    PlayerRepository playerRepo;

    //TODO: delete
    //POST: create a player
    /*(Only needs the name)
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

    /*
    POST: /players : crea un jugador
    {    
    "name": "Foo"
    }    
     */
    @PostMapping("/players")
    @ResponseBody
    public Player createPlayer(@RequestBody Player player) throws InvalidParamException
    {
        if (player == null)
        {
            throw new InvalidParamException();
        }
        try
        {
            return playerRepo.save(player);
        }
        catch (Exception e)
        {
            throw new InvalidParamException();
        }
    }
}

/*
    PUT /players : modifica el nom del jugador
 */
