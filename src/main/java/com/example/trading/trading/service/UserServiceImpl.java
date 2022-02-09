package com.example.trading.trading.service;
import java.util.ArrayList;
import java.util.List;

import com.example.trading.trading.model.User;
import com.example.trading.trading.repository.UserRepository;
import com.example.trading.trading.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), "USER", 0L, 0L);

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        String ROLE = "USER";
        list.add(new SimpleGrantedAuthority(ROLE));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), list);
    }
    @Override
    public User getAUserByEmail(String email) {

        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public void modifyWallet(Long id, Long prev) {
        userRepository.modifyWallet(id, prev);
    }

    @Override
    public User updateNewUser(User user) {
        return userRepository.save(user);
    }


}
