package com.dice.api;

import com.dice.model.Game;
import com.dice.model.Player;
import com.dice.repository.PlayerRepository;
import com.dice.tool.ErrorValueException;
import com.dice.tool.GameMaker;
import com.dice.tool.NotFoundException;
import com.dice.tool.RateDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /*
    POST: /players : crea un jugador
    localhost:8080/players
    {    
    "name": "Foo"
    }    
     */
    @PostMapping("/players")
    @ResponseBody
    public Player createPlayer(@RequestBody Player player)
            throws ErrorValueException, NotFoundException
    {
        if (player == null)
        {
            throw new ErrorValueException("No hay datos para crear nuevo jugador.");
        }
        try
        {
            return playerRepo.save(player);
        }
        catch (Exception e)
        {
            throw new NotFoundException("No fue posible guardar los datos.");
        }
    }

    /*
    PUT /players : modifica el nom del jugador
    localhost:8080/players
    {    
    "idPlayer" : "150db883-c08b-4a0d-aa0c-c8607f3f2c93",
    "name": "Martaaaa"
    } 
    Keep the correct format to no have format JSON problems
     */
    @PutMapping("/players")
    @ResponseBody
    public Player editName(@RequestBody Player playerToEdit)
            throws ErrorValueException, NotFoundException
    {
        if (playerToEdit == null || playerToEdit.getName().isEmpty()
                || playerToEdit.getName() == null
                || playerToEdit.getName().trim().length() < 1)
        {
            throw new ErrorValueException("No hay datos para modificar el jugador.");
        }
        try
        {
            Optional<Player> player = playerRepo.findById(playerToEdit.getIdPlayer());
            player.get().setName(playerToEdit.getName());
            return playerRepo.save(player.get());
        }
        catch (Exception e)
        {
            throw new NotFoundException(
                    "No fue posible localizar la ID del jugador indicada.");
        }
    }

    /*
    POST /players/{id}/games/ : un jugador específic realitza una tirada dels daus.
    localhost:8080/players/150db883-c08b-4a0d-aa0c-c8607f3f2c93/games/
     */
    @RequestMapping(value = "/players/{id}/games/", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Player playGame(@PathVariable UUID id)
            throws ErrorValueException, NotFoundException
    {
        if (id == null)
        {
            throw new ErrorValueException("ID de jugador nula");
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
            throw new NotFoundException(
                    "No fue posible localizar la ID del jugador indicada.");
        }
    }

    /*
    DELETE /players/{id} elimina el jugador.   
    localhost:8080/players/84cbe2c8-9ab6-466d-ab62-8d2a735d48ec
     */
    @DeleteMapping(value = "/players/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") UUID idPlayer)
            throws ErrorValueException, NotFoundException
    {
        if (idPlayer == null)
        {
            throw new ErrorValueException("ID de jugador nula");
        }
        try
        {
            Optional<Player> player = playerRepo.findById(idPlayer);
            playerRepo.delete(player.get());
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            throw new NotFoundException("ID de jugador no válida");
        }
    }

    /*
    DELETE /players/{id}/games: elimina les tirades del jugador.
     */
    @DeleteMapping(value = "/players/{id}/games", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Player deleteGames(@PathVariable(value = "id") UUID idPlayer)
            throws ErrorValueException, NotFoundException
    {
        if (idPlayer == null)
        {
            throw new ErrorValueException("ID de jugador nula");
        }
        try
        {
            Optional<Player> player = playerRepo.findById(idPlayer);
            //Delete the player's games
            player.get().getListGame().clear();
            return playerRepo.save(player.get());
        }
        catch (Exception e)
        {
            throw new NotFoundException("ID de jugador no válida");
        }
    }

    /*
    GET /players/: retorna el llistat de tots els jugadors del sistema
    amb el seu percentatge mig d’èxits
    localhost:8080/players/
     */
    @GetMapping("/players/")
    public List<RateDTO> getListRatePlayers() throws NotFoundException
    {
        List<RateDTO> outputDTO = new ArrayList<>();
        //Select the players
        List<Player> listPlayer = (List<Player>) playerRepo.findAll();
        if (listPlayer.isEmpty())
        {
            throw new NotFoundException("No hay jugadores en el sistema");
        }
        for (Player player : listPlayer)
        {
            //Count the games
            double games = player.getListGame().size();
            if (games != 0)
            {
                //Count the wins
                List<Game> listGame = player.getListGame();
                double wins = 0;
                for (Game game : listGame)
                {
                    if (game.getIsWinner())
                    {
                        wins++;
                    }
                }
                //Calculate and output the results
                double result = (wins / games) * 100;
                RateDTO resultDTO = new RateDTO(player.getIdPlayer(), result);
                outputDTO.add(resultDTO);
            }
            else
            {
                //No games rate 0%
                RateDTO resultDTO = new RateDTO(player.getIdPlayer(), 0);
                outputDTO.add(resultDTO);
            }
        }
        return outputDTO;
    }

    /*
    GET /players/{id}/games: retorna el llistat de jugades per un jugador.
    localhost:8080/players/2da626a9-cee0-47a8-9107-bd2961fc680f/games/
     */
    @GetMapping(value = "/players/{id}/games", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Game> getPlayerGames(@PathVariable(value = "id") UUID idPlayer)
            throws ErrorValueException, NotFoundException
    {
        List<Game> output = new ArrayList<>();
        if (idPlayer == null)
        {
            throw new ErrorValueException("ID de jugador nula");
        }
        try
        {
            output = playerRepo.findById(idPlayer).get().getListGame();
        }
        catch (Exception e)
        {
            throw new NotFoundException("ID de jugador no válida.");
        }
        return output;
    }

    /*
    GET /players/ranking/loser: retorna el jugador amb pitjor percentatge d’èxit.
    
     */
    @GetMapping("/players/ranking/loser")
    public RateDTO getLoser() throws NotFoundException
    {
        List<RateDTO> listRateDTO = new ArrayList<>();
        //Select the players
        List<Player> listPlayer = (List<Player>) playerRepo.findAll();
        if (listPlayer.isEmpty())
        {
            throw new NotFoundException("No hay jugadores en el sistema");
        }
        for (Player player : listPlayer)
        {
            //Count the games
            double games = player.getListGame().size();
            if (games != 0)
            {
                List<Game> listGame = player.getListGame();
                double wins = 0;
                //Count the wins
                for (Game game : listGame)
                {
                    if (game.getIsWinner())
                    {
                        wins++;
                    }
                }
                //Calculate and output the results
                double result = (wins / games) * 100;
                RateDTO resultDTO = new RateDTO(player.getIdPlayer(), result);
                listRateDTO.add(resultDTO);
            }
            else
            {
                //No games rate 0%
                RateDTO resultDTO = new RateDTO(player.getIdPlayer(), 0);
                listRateDTO.add(resultDTO);
            }
        }
        //Order and find the loser
        Collections.sort(listRateDTO);
        RateDTO output = listRateDTO.get(0);
        return output;
    }

    /*
    GET /players/ranking/winner: retorna el jugador amb mijor percentatge d’èxit.
    
     */
    @GetMapping("/players/ranking/winner")
    public RateDTO getWinner() throws NotFoundException
    {
        List<RateDTO> listRateDTO = new ArrayList<>();
        //Select the players
        List<Player> listPlayer = (List<Player>) playerRepo.findAll();
        if (listPlayer.isEmpty())
        {
            throw new NotFoundException("No hay jugadores en el sistema");
        }
        for (Player player : listPlayer)
        {
            //Count the games
            double games = player.getListGame().size();
            if (games != 0)
            {
                List<Game> listGame = player.getListGame();
                double wins = 0;
                //Count the wins
                for (Game game : listGame)
                {
                    if (game.getIsWinner())
                    {
                        wins++;
                    }
                }
                //Calculate and output the results
                double result = (wins / games) * 100;
                RateDTO resultDTO = new RateDTO(player.getIdPlayer(), result);
                listRateDTO.add(resultDTO);
            }
            else
            {
                //No games rate 0%
                RateDTO resultDTO = new RateDTO(player.getIdPlayer(), 0);
                listRateDTO.add(resultDTO);
            }
        }
        //Order and find the loser
        Collections.sort(listRateDTO);
        RateDTO output = listRateDTO.get(listRateDTO.size() - 1);
        return output;
    }

    /*
     **************************************************************************
     TODO: delete //GET: TEST List all Players /* localhost:8080/getall
     */
    @GetMapping("/getall")
    public Iterable<Player> testGetAllPlayer()
    {
        return playerRepo.findAll();
    }
}
