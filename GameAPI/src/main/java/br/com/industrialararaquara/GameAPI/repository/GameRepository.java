package br.com.industrialararaquara.GameAPI.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.industrialararaquara.GameAPI.bean.Game;

public interface GameRepository extends CrudRepository<Game, Integer> {

}
