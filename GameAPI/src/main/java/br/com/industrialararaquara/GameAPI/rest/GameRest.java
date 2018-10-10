package br.com.industrialararaquara.GameAPI.rest;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.industrialararaquara.GameAPI.bean.Game;
import br.com.industrialararaquara.GameAPI.repository.GameRepository;

@RestController
@RequestMapping(value = "/games")
public class GameRest {
	@Autowired
	private GameRepository gameRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Iterable<Game>>  recuperarTodosOsAlunos(){
		Iterable<Game> games = gameRepository.findAll();
		return new ResponseEntity<>(games, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}" )
	public ResponseEntity<Game> recuperarGamePorId(@PathVariable("id") Integer id) {
		try {
		Game games = gameRepository.findById(id).get();
		return new ResponseEntity<Game>(games, HttpStatus.OK);
		
		}
		catch(NoSuchElementException ex) {
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);	
		}
		
		
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}" )
	public ResponseEntity<?> excluirGamePorId(@PathVariable("id") Integer id) {
		
		try {
			Game games = gameRepository.findById(id).get();
			gameRepository.delete(games);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(NoSuchElementException ex) {
		
		
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Game> inserirGame (@RequestBody Game game) {
		game = gameRepository.save(game);
		return new ResponseEntity<Game>(game, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Game> atualizarGame (@RequestBody Game game) {
		game = gameRepository.save(game);
		return new ResponseEntity<Game>(game, HttpStatus.OK);
	}

}
