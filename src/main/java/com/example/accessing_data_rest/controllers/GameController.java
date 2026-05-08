package com.example.accessing_data_rest.controllers;

import com.example.accessing_data_rest.model.Game;

import com.example.accessing_data_rest.model.GameState;
import com.example.accessing_data_rest.services.GameService;
import com.example.accessing_data_rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roborally/game")
public class GameController {

    @Autowired
    private GameService gameService;

    // DONE Assignment 7b: add a method with @GetMapping, which obtains
    //      a list of all games from the games repository  (via the service
    //      getGames) and returns this list (in JSON representation).
    //      See class UserController for inspiration and class GameService

    /**
     * Retrieves all games.
     * @return a list of all games in JSON format
     */
    @GetMapping(value = "", produces="application/json")
    public List<Game> allGames() {
        return gameService.getGames();
    }

    // DONE Assignment 7b: Create a post method in this controller for creating a new game
    //      this method should call the corresponding service for creating a game

    /**
     * Creates a new game.
     * @param game the game to create
     * @return the persisted game instance
     */
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public Game postGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }

    // DONE Assignment 7d: Create a method and @RequestMpping for deleting a game.

    /**
     * Deletes a game by its id.
     * @param gameUid the id of the game to delete
     */
    @DeleteMapping(value = "/{id}")
    public void deleteGame(@PathVariable("id") long gameUid) {
        gameService.deleteGame(gameUid);
    }

    /**
     * Updates the state of an existing game.
     * @param gameUid the id of the game to update
     * @param gameStub the game data containing the new state
     * @return the updated game
     */
    @PatchMapping(value = "/{id}")
    public Game updateGameState(@PathVariable("id") long gameUid, @RequestBody Game gameStub) {
        return gameService.updateGameState(gameUid, gameStub);
    }

    // DONE Assignment 7c-7e: At some point you might want to implement an
    //      endpoint for obtaining open games (open for joining) only or
    //      games that have started.

    /**
     * Retrieves all open games available for joining.
     * @return a list of games in SIGNUP state
     */
    @GetMapping(value = "/open", produces="application/json")
    public List<Game> openGames() {
        return gameService.getGamesByState(GameState.SIGNUP);
    }

    /**
     * Retrieves all started games.
     * @return a list of games in ACTIVE state
     */
    @GetMapping(value = "/started", produces="application/json")
    public List<Game> startedGames() {
        return gameService.getGamesByState(GameState.ACTIVE);
    }

}
