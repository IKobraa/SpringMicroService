package de.thbingen.UserService.repo;

import de.thbingen.UserService.entity.Messages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends CrudRepository<Messages,Integer> {}
