package com.example.yj.information;

import com.example.yj.DataNotFoundException;
import com.example.yj.entity.User;
import com.example.yj.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InformationService {

    private final UserRepository userRepository;

    public User userInformation(String id){ //유저 정보 불러오기
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        }else {
            throw new DataNotFoundException("user not found");
        }
    }

    public void updateUser(String userId, String userPw){ //유저 정보 업데이트
            Optional<User> result = userRepository.findById(userId);

            if(result.isPresent()){
                User user = result.get();
                user.setUserPw(userPw);
                userRepository.save(user);
            }
    }

}
