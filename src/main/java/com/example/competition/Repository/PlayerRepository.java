package com.example.competition.Repository;

import com.example.competition.Entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {
    Optional<Player> findByUserId(Integer userId);
}
