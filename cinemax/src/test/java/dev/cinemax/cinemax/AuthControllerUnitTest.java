package dev.cinemax.cinemax;

import dev.cinemax.cinemax.dto.ReqRes;
import dev.cinemax.cinemax.entity.Role;
import dev.cinemax.cinemax.exception.IncorrectEmailException;
import dev.cinemax.cinemax.exception.UserAlreadyExistsException;
import dev.cinemax.cinemax.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthControllerUnitTest {
    @Autowired
    private UserService userService;
    @Test
    public void testSignup_Success() {
        String email = "test@example.com";
        String password = "password123";

        ReqRes reqRes = new ReqRes();
        reqRes.setEmail(email);
        reqRes.setPassword(password);
        reqRes.setRoles(Role.USER);

        ReqRes response = userService.signUp(reqRes);
        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getUser());
        assertEquals(email, response.getUser().getEmail());
    }

    @Test
    public void testSignup_InvalidEmailFormat() {
        String email = "invalid_email";
        String password = "password123";

        ReqRes reqRes = new ReqRes();
        reqRes.setEmail(email);
        reqRes.setPassword(password);
        reqRes.setRoles(Role.USER);

        assertThrows(IncorrectEmailException.class, () -> userService.signUp(reqRes));
    }

    @Test
    public void testSignup_ExistingEmail() {
        String email = "existing@example.com";
        String password = "password123";
        String roles = "ROLE_USER";


        ReqRes reqRes = new ReqRes();
        reqRes.setEmail(email);
        reqRes.setPassword(password);
        reqRes.setRoles(Role.USER);

        assertThrows(UserAlreadyExistsException.class, () -> userService.signUp(reqRes));
    }
    @Test
    public void testSignin_Success() {
        String email = "existing@example.com";
        String password = "password123";


        ReqRes reqRes = new ReqRes();
        reqRes.setEmail(email);
        reqRes.setPassword(password);

        ReqRes response = userService.signIn(reqRes);

        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getToken());
        assertNotNull(response.getRefreshToken());
    }

    @Test
    public void testSignin_InvalidCredentials() {
        String email = "invalid_email";
        String password = "wrong_password";

        ReqRes reqRes = new ReqRes();
        reqRes.setEmail(email);
        reqRes.setPassword(password);

        assertThrows(Exception.class, () -> userService.signIn(reqRes));
    }



}
