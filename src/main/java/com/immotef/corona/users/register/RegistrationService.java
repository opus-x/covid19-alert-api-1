package com.immotef.corona.users.register;

import com.immotef.corona.users.User;
import com.immotef.corona.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    private final UsersRepository usersRepository;

    @Autowired
    public RegistrationService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public RegistrationResponse register(RegistrationRequest request) {
        User user = usersRepository.save(new User());
        user.setMajor(user.getId());
        user.setMinor(1L);

        user.setFirebaseToken(request.getFirebaseToken());

        user = usersRepository.save(user);
        return RegistrationResponse.fromUser(user);
    }

    private String prepareTokenForUser(User user) {
        return "";
    }

    public void updateFirebaseToken(FirebaseTokenUpdateRequest request, long userId) throws Exception {
        User user = usersRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
        user.setFirebaseToken(request.getFirebaseToken());
        usersRepository.save(user);
    }

    public void reportRecovered(long userId) throws Exception {
        User user = usersRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
        user.setDateTimeRecovered(LocalDateTime.now());
        usersRepository.save(user);
    }

    public RegistrationResponse loginExistingUserWithId(RegistrationRequest request) throws Exception {
        User user = usersRepository.findById(Long.parseLong(request.existingUserId)).orElseThrow(() -> new Exception("User not found"));
        return RegistrationResponse.fromUser(user);
    }
}
