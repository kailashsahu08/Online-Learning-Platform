package com.xyz.EHub.service;

import com.xyz.EHub.exception.UserNotFoundException;
import com.xyz.EHub.model.Users;
import com.xyz.EHub.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Service("UserServiceImpl")
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public Users registerUser(Users user) {
        String pass =user.getPassword();
        user.setPassword(passwordEncoder.encode(pass));
        return userRepository.save(user);
    }

    @Override
    public boolean existEmailCheck(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Users getUserByUserName(String username) throws UserNotFoundException {
        Optional<Users> user = userRepository.findByName(username);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserNotFoundException("User not found");
    }
    public void removeSessionMessage() {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
                .getSession();

        session.removeAttribute("msg");
    }
}
