package com.project.sales.Services.auth;

import com.project.sales.Dto.SingupRequest;
import com.project.sales.Dto.UserDto;

public interface AuthService {
    public UserDto createUser(SingupRequest singupRequest);
    Boolean hasUserWithEmail(String email);
}
