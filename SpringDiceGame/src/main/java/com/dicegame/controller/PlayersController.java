package com.dicegame.controller;

import com.dicegame.model.Players;
import com.dicegame.repository.PlayersRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

@RestController
public class PlayersController
{
    @Autowired
    private PlayersRepository playerRepo;

    //POST: /players : crea un jugador
    @RequestMapping(value = "/players", headers = "content-type=application/json")
    @ResponseBody
    public Players createPlayers(@RequestBody Players player)
    {
        return playerRepo.save(player);
    }

    //PUT /players : modifica el nom del jugador
    @PutMapping("/players")
    public Players updatePlayers(@RequestBody Players player)
    {
        UUID idPlayer = player.getIdPlayers();
        Optional<Players> playerToUpdate = playerRepo.findById(idPlayer);
        playerToUpdate.get().setName(player.getName());
        Players playerUpdated = playerRepo.save(playerToUpdate.get());
        return playerUpdated;
    }

    //GET /players/: retorna el llistat de tots els jugadors del sistema 
    //amb el seu percentatge mig d’èxits
    @GetMapping("/players")
    public List<Players> getAllPlayers()
    {
        return playerRepo.findAll();
    }

    //DELETE /players/{id} elimina el jugador.
    @DeleteMapping("/players/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") UUID idPlayer)
    {
        Optional<Players> player = playerRepo.findById(idPlayer);
        playerRepo.delete(player.get());
        return ResponseEntity.ok().build();
    }

}
