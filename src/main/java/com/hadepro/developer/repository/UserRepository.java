package com.hadepro.developer.repository;

import org.springframework.data.repository.CrudRepository;
import com.hadepro.developer.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
