package org.happybaras.taller2.services;

import org.happybaras.taller2.domain.dtos.LoginDTO;
import org.happybaras.taller2.domain.dtos.RegisterDTO;
import org.happybaras.taller2.domain.entities.User;
import org.happybaras.taller2.domain.enums.LoginStatus;
import org.happybaras.taller2.domain.enums.RegisterStatus;

public interface AuthService {
    RegisterStatus register(RegisterDTO data);
    LoginStatus login(LoginDTO data);
    User findEmail(String email);
    User findUserName(String username);

}
