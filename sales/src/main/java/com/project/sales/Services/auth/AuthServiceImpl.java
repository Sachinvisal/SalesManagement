package com.project.sales.Services.auth;

import com.project.sales.Dto.SingupRequest;
import com.project.sales.Dto.UserDto;
import com.project.sales.Entity.User;
import com.project.sales.Repo.UserRepository;
import com.project.sales.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto createUser(SingupRequest singupRequest){

        User user = new User();
        user.setName(singupRequest.getName());
        user.setEmail(singupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(singupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        return userDto;

    }
public Boolean hasUserWithEmail(String email){
        return userRepository.findFirstByEmail(email).isPresent();
}
}
