package com.dicegame.controller;

import com.dicegame.model.Player;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.dicegame.repository.PlayerRepository;

@RestController
public class PlayerRestController
{
    @Autowired
    private PlayerRepository playerRepo;

    //POST: /players : crea un jugador
    @RequestMapping(value = "/players", headers = "content-type=application/json")
    @ResponseBody
    public Player createPlayer(@RequestBody Player player)
    {
        return playerRepo.save(player);
    }

    //PUT /players : modifica el nom del jugador
    @PutMapping("/players")
    public Player updatePlayer(@RequestBody Player player)
    {
        String idPlayer = player.getIdPlayer();
        Optional<Player> playerToUpdate = playerRepo.findById(idPlayer);
        playerToUpdate.get().setName(idPlayer);
        Player playerUpdated = playerRepo.save(playerToUpdate.get());
        return playerUpdated;
    }

    //GET /players/: retorna el llistat de tots els jugadors del sistema 
    //amb el seu percentatge mig d’èxits
    @GetMapping("/players")
    public List<Player> getAllPlayers()
    {
        return playerRepo.findAll();
    }

    //DELETE /players/{id} elimina el jugador.
    @DeleteMapping("/players/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") String idPlayer)
    {
        Optional<Player> player = playerRepo.findById(idPlayer);
        playerRepo.delete(player.get());
        return ResponseEntity.ok().build();
    }

}
