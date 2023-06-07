package com.example.yj.login;

import com.example.yj.entity.User;
import com.example.yj.repository.UserRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogInService {

    private final UserRepository userRepository;

    //로그인(아이디/ 비밀번호)
    public boolean userLoginConfirm(String userId, String userPw){
        Optional<User> findUser = userRepository.findById(userId);
        if(findUser.isPresent()){
            if(findUser.get().getUserPw().equals(userPw)) {
                return true;
            } else return false;
        }else return false;
    }
}
