package com.dice.api;

import com.dice.model.Game;
import com.dice.model.Player;
import com.dice.repository.PlayerRepository;
import com.dice.tool.ErrorValueException;
import com.dice.tool.GameMaker;
import com.dice.tool.GameMakerSixDice;
import com.dice.tool.ErrorTransactionException;
import com.dice.tool.RateDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@RestController
public class PlayerRestController
{
    @Autowired
    PlayerRepository playerRepo;

    //Error sending to the client
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ErrorTransactionException.class)
    public String return409(ErrorTransactionException ex)
    {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ErrorValueException.class)
    public String return401(ErrorValueException ex)
    {
        return ex.getMessage();
    }

    /*
    POST: /players : crea un jugador
    localhost:8080/players
    {    
    "name": "Foo"
    }    
     */
    @PostMapping("/players")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Player createPlayer(@RequestBody Player player)
            throws ErrorValueException, ErrorTransactionException
    {
        if (player == null || player.getName().trim().isEmpty()
                || player.getName() == null)
        {
            throw new ErrorValueException("No hay datos para crear nuevo jugador.");
        }

        try
        {
            //verify no duplicate name
            if (playerRepo.findByName(player.getName().trim()) == null)
            {
                //no duplicate: save it
                return playerRepo.save(player);
            }
            else
            {
                throw new ErrorValueException("El nombre ya existe.");
            }
        }
        catch (Exception e)
        {
            throw new ErrorTransactionException("No fue posible crear el jugador." + e);
        }
    }

    /*
    PUT /players : modifica el nom del jugador
    localhost:8080/players
    {    
    "idPlayer" : "150db883-c08b-4a0d-aa0c-c8607f3f2c93",
    "name": "Martaaaa"
    } 
    Keep the correct format to don't have JSON format problems
     */
    @PutMapping("/players")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Player editName(@RequestBody Player playerToEdit)
            throws ErrorValueException, ErrorTransactionException
    {
        if (playerToEdit == null || playerToEdit.getName().trim().isEmpty()
                || playerToEdit.getName() == null)
        {
            throw new ErrorValueException("No hay datos para modificar el jugador.");
        }
        try
        {
            //Verify if already exists the new name
            String nameToCheck = playerToEdit.getName().trim(); //The name to find
            if (playerRepo.findByName(nameToCheck) == null)
            {
                //Correct name: change it
                Optional<Player> player = playerRepo.findById(playerToEdit.getIdPlayer());
                player.get().setName(playerToEdit.getName());
                return playerRepo.save(player.get());
            }
            else
            {
                throw new ErrorValueException("El nombre ya existe.");
            }
        }
        catch (Exception e)
        {
            throw new ErrorTransactionException(
                    "No fue posible modificar el jugador indicado." + e);
        }
    }

    /*
    POST /players/{id}/games/ : un jugador específic realitza una tirada dels daus.
    localhost:8080/players/150db883-c08b-4a0d-aa0c-c8607f3f2c93/games/
     */
    @RequestMapping(value = "/players/{id}/games/", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Player playGame(@PathVariable UUID id)
            throws ErrorValueException, ErrorTransactionException
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
            throw new ErrorTransactionException(
                    "No fue posible localizar la ID del jugador indicada.");
        }
    }

    /*
    DELETE /players/{id} elimina el jugador.   
    localhost:8080/players/84cbe2c8-9ab6-466d-ab62-8d2a735d48ec
     */
    @DeleteMapping(value = "/players/{id}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") UUID idPlayer)
            throws ErrorValueException, ErrorTransactionException
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
            throw new ErrorTransactionException("ID de jugador no válida");
        }
    }

    /*
    DELETE /players/{id}/games: elimina les tirades del jugador.
     */
    @DeleteMapping(value = "/players/{id}/games", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Player deleteGames(@PathVariable(value = "id") UUID idPlayer)
            throws ErrorValueException, ErrorTransactionException
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
            throw new ErrorTransactionException("No es posible borar esta ID de jugador.");
        }
    }

    /*
    GET /players/: retorna el llistat de tots els jugadors del sistema
    amb el seu percentatge mig d’èxits
    localhost:8080/players/
     */
    @GetMapping("/players/")
    @ResponseStatus(HttpStatus.OK)
    public List<RateDTO> getListRatePlayers() throws ErrorTransactionException
    {
        List<RateDTO> outputDTO = new ArrayList<>();
        //Select the players
        List<Player> listPlayer = (List<Player>) playerRepo.findAll();
        if (listPlayer.isEmpty() || listPlayer == null)
        {
            throw new ErrorTransactionException("No hay jugadores en el sistema.");
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
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Game> getPlayerGames(@PathVariable(value = "id") UUID idPlayer)
            throws ErrorValueException, ErrorTransactionException
    {
        List<Game> output = new ArrayList<>();
        if (idPlayer == null)
        {
            throw new ErrorValueException("ID de jugador nula.");
        }
        try
        {
            return playerRepo.findById(idPlayer).get().getListGame();
        }
        catch (Exception e)
        {
            throw new ErrorTransactionException("ID de jugador sin juegos.");
        }
    }

    /*
    GET /players/ranking/loser: retorna el jugador amb pitjor percentatge d’èxit.
    
     */
    @GetMapping("/players/ranking/loser")
    @ResponseStatus(HttpStatus.OK)
    public RateDTO getLoser() throws ErrorTransactionException
    {
        List<RateDTO> listRateDTO = new ArrayList<>();
        //Select the players
        List<Player> listPlayer = (List<Player>) playerRepo.findAll();
        if (listPlayer.isEmpty() || listPlayer == null)
        {
            throw new ErrorTransactionException("No hay jugadores en el sistema.");
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
    @ResponseStatus(HttpStatus.OK)
    public RateDTO getWinner() throws ErrorTransactionException
    {
        List<RateDTO> listRateDTO = new ArrayList<>();
        //Select the players
        List<Player> listPlayer = (List<Player>) playerRepo.findAll();
        if (listPlayer.isEmpty() || listPlayer == null)
        {
            throw new ErrorTransactionException("No hay jugadores en el sistema.");
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
    POST /players/{id}/games/six : un jugador específic realitza una tirada dels daus
    amb el joc del sis daus.
    Per poder jugar al joc de daus ara es necessiten sis daus.
    En cas que el resultat de tots els daus sigui sis o cinc, la partida és guanyada,
    sinó es perduda.
    localhost:8080/players/662213d6-b576-4ec6-8b74-79f614606b05/games/six
     */
    @RequestMapping(value = "/players/{id}/games/six", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Player playGameSixDice(@PathVariable UUID id)
            throws ErrorValueException, ErrorTransactionException
    {
        if (id == null)
        {
            throw new ErrorValueException("ID de jugador nula.");
        }
        try
        {
            //find the player 
            Optional<Player> playerToPlay = playerRepo.findById(id);
            //play and save the game
            GameMakerSixDice game = new GameMakerSixDice();
            return playerRepo.save(game.playGame(playerToPlay.get()));
        }
        catch (Exception e)
        {
            throw new ErrorTransactionException(
                    "No fue posible localizar la ID del jugador indicada.");
        }
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
