package com.example.productlist.service;

import com.example.productlist.model.StoreUserDetails;
import com.example.productlist.model.User;
import com.example.productlist.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUserName(username);
    if (user.isEmpty()) throw new UsernameNotFoundException("Not found : " + username);
    return new StoreUserDetails(user.get());
  }
}
