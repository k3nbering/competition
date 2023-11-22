package com.example.competition.Repository;

import com.example.competition.Entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    List<Role> findByName(String name);
}
