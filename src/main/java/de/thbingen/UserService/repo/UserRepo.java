package de.thbingen.UserService.repo;


import de.thbingen.UserService.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User,Integer> {
    Optional<User> findOneByEmailAndPassword(String email, String password);
    Optional<User> findByChatId(String chatId);
    User findByEmail(String email);
}
