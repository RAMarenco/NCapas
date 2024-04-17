package org.happybaras.taller2.services;

import org.happybaras.taller2.domain.dtos.LoginDTO;
import org.happybaras.taller2.domain.dtos.SaveUserDTO;
import org.happybaras.taller2.domain.enums.LoginStatus;

public interface AuthService {
    void register(SaveUserDTO data);
    LoginStatus login(LoginDTO data);

}
