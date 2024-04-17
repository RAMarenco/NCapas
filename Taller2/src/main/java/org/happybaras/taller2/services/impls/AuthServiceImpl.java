package org.happybaras.taller2.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.happybaras.taller2.domain.dtos.LoginDTO;
import org.happybaras.taller2.domain.dtos.RegisterDTO;
import org.happybaras.taller2.domain.entities.User;
import org.happybaras.taller2.domain.enums.LoginStatus;
import org.happybaras.taller2.domain.enums.RegisterStatus;
import org.happybaras.taller2.services.AuthService;
import org.happybaras.taller2.utils.ValidationTools;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    public static List<User> users = new ArrayList<>();
    static {
        users.add(new User("abcd@example.com", "abcdefg", "AabD#fGa"));
        users.add(new User("hijk@example.com", "hijklmno","H$zX&wPa"));
        users.add(new User("qrst@example.com", "qrstuvwx","R#gM!eNa"));
        users.add(new User("uvwx@example.com", "uvwxyzab","LatR%iOa"));
        users.add(new User("efgh@example.com", "efghijkl","Q#pK&sEa"));
        users.add(new User("mnop@example.com", "mnopqrst","S$jH!bFa"));
        users.add(new User("yzab@example.com", "yzabcdef","ValT#wYa"));
        users.add(new User("cdef@example.com", "cdefghij","E$mU#sNa"));
        users.add(new User("ijkl@example.com", "ijklmnop","W!uJ#pXa"));
        users.add(new User("pqrs@example.com", "pqrstuvw","T#nI&rSa"));
    }

    private final ValidationTools validationTools;

    private AuthServiceImpl(ValidationTools validationTools) {
        this.validationTools = validationTools;
    }

    public RegisterStatus register(RegisterDTO data) {
        String username = data.getUsername();
        String email = data.getEmail();
        String password = data.getPassword();

        if (findUserName(username) != null) {
            return RegisterStatus.USERNAME_EXISTS;
        }

        if (findEmail(email) != null) {
            return RegisterStatus.EMAIL_EXISTS;
        }

        User user = new User(email, username, password);

        users.add(user);

        return RegisterStatus.REGISTER_SUCCESSFUL;
    }

    public LoginStatus login(LoginDTO data) {
        String identifier = data.getIdentifier();
        String password = data.getPassword();

        if (!validationTools.isValidIdentifier(identifier)) {
            return LoginStatus.NOT_VALID_IDENTIFIER;
        }

        User user = findEmail(identifier) != null ? findEmail(identifier) : findUserName(identifier);

        if (user == null) {
            return LoginStatus.NOT_FOUND;
        }

        if (!user.getPassword().equals(password)) {
            return LoginStatus.WRONG_PASSWORD;
        }

        return LoginStatus.LOGIN_SUCCESSFUL;
    }

    public User findEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public User findUserName(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
