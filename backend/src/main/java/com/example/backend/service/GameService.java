package com.example.backend.service;

import com.example.backend.model.Game;
import com.example.backend.model.GameDTO;
import com.example.backend.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final IdService idService;

    public GameService(GameRepository gameRepository, IdService idService) {
        this.gameRepository = gameRepository;
        this.idService = idService;
    }

    public Game createGame(GameDTO game) {
        Game newGame = Game.builder()
                .gameId(idService.generateID())
                .currentQuestionId(game.getCurrentQuestion())
                .round(game.getRound())
                .maxRounds(game.getMaxRounds())
                .currentPlayer(game.getCurrentPlayer())
                .players(game.getPlayers())
                .build();
        return gameRepository.save(newGame);
    }

    public Game getGameById(String gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new NoSuchElementException("No game found with this id: " + gameId));
    }

    public Game updateGame(Game game) {
        if (!gameRepository.existsById(game.getGameId())) {
            throw new NoSuchElementException("There is no game with the requested Id");
        }
        return gameRepository.save(game);
    }

    public void deleteGameById(String gameId) {
        gameRepository.deleteById(gameId);
    }
}
