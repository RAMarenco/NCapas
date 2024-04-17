package org.happybaras.taller2.services.impls;

import org.happybaras.taller2.domain.dtos.LoginDTO;
import org.happybaras.taller2.domain.dtos.SaveUserDTO;
import org.happybaras.taller2.domain.entities.User;
import org.happybaras.taller2.domain.enums.LoginStatus;
import org.happybaras.taller2.services.AuthService;
import org.happybaras.taller2.utils.ValidationTools;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void register(SaveUserDTO data) {

    }

    public LoginStatus login(LoginDTO data) {
        String identifier = data.getIdentifier();
        String password = data.getPassword();

        if (!validationTools.isValidIdentifier(identifier)) {
            return LoginStatus.NOT_VALID_IDENTIFIER;
        }

        User user = users.stream()
                .filter(b -> b.getEmail().equals(identifier) || b.getUsername().equals(identifier))
                .findFirst()
                .orElse(null);

        if (user == null) {
            return LoginStatus.LOGIN_ERROR;
        }

        if (!user.getPassword().equals(password)) {
            return LoginStatus.WRONG_PASSWORD;
        }

        return LoginStatus.LOGIN_SUCCESSFUL;
    }
}
