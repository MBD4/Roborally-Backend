package com.example.accessing_data_rest.controllers;

import com.example.accessing_data_rest.model.Player;
import com.example.accessing_data_rest.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roborally/player")
public class PlayerController {



    @Autowired
    private PlayerService playerService;

    /**
     * Creates a new player in a game.
     * @param player the player to create
     * @return the persisted player instance
     */
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    /**
     * Deletes a player from a game.
     * @param uid the id of the player to delete
     */
    @DeleteMapping(value = "/{id}")
    public void deletePlayer(@PathVariable("id") long uid) {
        playerService.deletePlayer(uid);
    }

}
