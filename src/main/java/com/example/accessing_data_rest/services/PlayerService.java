package com.example.accessing_data_rest.services;

import com.example.accessing_data_rest.model.Game;
import com.example.accessing_data_rest.model.Player;
import com.example.accessing_data_rest.model.User;
import com.example.accessing_data_rest.repositories.GameRepository;
import com.example.accessing_data_rest.repositories.PlayerRepository;
import com.example.accessing_data_rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    // custom exception that's thrown if requirements for creating player isnt met
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class CouldNotCreatePlayerException extends RuntimeException {
        public CouldNotCreatePlayerException(String message) {
            super(message);
        }
    }

    @Transactional
    public Player createPlayer(Player player) {
        User user = userRepository.findByUid(player.getUser().getUid());
        Game game = gameRepository.findByUid(player.getGame().getUid());

        if (user == null || game == null) {
            throw new CouldNotCreatePlayerException("User og Game does not exist");
        }
        if (game.getPlayers().size() >= game.getMaxPlayers()) {
            throw new CouldNotCreatePlayerException("Max number of players reached");
        }
        for (Player currentPlayer: game.getPlayers()) {
            if (currentPlayer.getUser().getUid() == player.getUser().getUid()) {
                throw new CouldNotCreatePlayerException("This player has already joined");
            }
        }

        //make sure it is pointing to the user and game in the db
        player.setGame(game);
        player.setUser(user);

        playerRepository.save(player);
        return playerRepository.findByUid(player.getUid());

    }

    @Transactional
    public  void deletePlayer(long id) {
        Player player = playerRepository.findById(id).orElseThrow();
        if (player.getGame().getOwner().getUid() == player.getUser().getUid()) {
            throw new RuntimeException("The owner of the game cannot leave the game.");
        }
        playerRepository.deleteById(id);
    }
}
