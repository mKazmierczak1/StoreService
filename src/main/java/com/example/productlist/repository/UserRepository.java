package com.example.productlist.repository;

import com.example.productlist.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  public Optional<User> findByUserName(String name);
}
