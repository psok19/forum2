package pl.pawelsokolowski.forum2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user/add")
    public ModelAndView addUser(
            @RequestParam("email") String email
            , @RequestParam("username") String username
            , @RequestParam("password") String password
    ) throws NoSuchAlgorithmException {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        user.setHashedPassword(hashedPassword);
        userRepository.save(user);
        return new ModelAndView("redirect:/");
    }


}
