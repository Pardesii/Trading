package com.example.trading.trading.service;
import com.example.trading.trading.model.User;
import com.example.trading.trading.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);

    User getAUserByEmail(String email);

    @Transactional
    void modifyWallet(Long id, Long prev);

    User updateNewUser(User user);
}
