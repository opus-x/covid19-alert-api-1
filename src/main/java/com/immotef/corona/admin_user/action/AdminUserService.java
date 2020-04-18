package com.immotef.corona.admin_user.action;

//import com.immotef.corona.storage.FileStorageService; TODO add later
//import com.immotef.corona.email.RegistrationMessageSender;
import com.immotef.corona.exception.ElementNotFoundException;
import com.immotef.corona.admin_user.Role;
import com.immotef.corona.admin_user.UserEntity;
import com.immotef.corona.admin_user.repository.AdminUserRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class AdminUserService {

    private AdminUserRepository adminUserRepository;
//    private RegistrationMessageSender registrationMessageSender; TODO add later
//    private FileStorageService fileStorageService;

//    @Value("${resources.userIcon.default.name}") TODO initialise
    private String baseFileName;

    @Autowired
    public AdminUserService(AdminUserRepository adminUserRepository//, BCryptPasswordEncoder bCryptPasswordEncoder
//            , RegistrationMessageSender registrationMessageSender, FileStorageService fileStorageService TODO add later
    ) {
        this.adminUserRepository = adminUserRepository;
        //  this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        this.registrationMessageSender = registrationMessageSender; TODO add later
//        this.fileStorageService = fileStorageService;
    }

    public void addUser(String email) {
        String token = UUID.randomUUID().toString();

        UserEntity newUser = UserEntity
                .builder()
                .email(email)
                .role(Role.CUSTOMER)
                .token(token)
                .build();

        adminUserRepository.save(newUser);
//        registrationMessageSender.send(email, token); TODO add later
    }

    public void addPassword(String token, String password) {

        UserEntity user = adminUserRepository.findByToken(token);
        //admin_user.setPassword(bCryptPasswordEncoder.encode(password));
        adminUserRepository.save(user);
    }

    public void addIconUrl(long userId, MultipartFile file) throws IOException, ElementNotFoundException {

        String name = getName(file);
//        String path = fileStorageService.storeFile(file, name); TODO add later

        UserEntity user = adminUserRepository.findById(userId).orElseThrow(ElementNotFoundException::new);
//        admin_user.setIconUrl(path); TODO add later
        adminUserRepository.save(user);
    }

    private String getName(MultipartFile file) {
        return LocalDateTime.now().toString() + "_" + baseFileName + "." + FilenameUtils.getExtension(file.getOriginalFilename());
    }

    public List<UserEntity> getUsers(String search) {

        Set<UserEntity> usersByFirstName = adminUserRepository.findByFirstNameLike(search);
        Set<UserEntity> usersByLastName = adminUserRepository.findByLastNameLike(search);
        Set<UserEntity> usersByEmail = adminUserRepository.findByEmailLike(search);

        Set<UserEntity> allUsers = new HashSet<>();
        allUsers.addAll(usersByFirstName);
        allUsers.addAll(usersByLastName);
        allUsers.addAll(usersByEmail);

        List<UserEntity> listAllUsers = new ArrayList<>(allUsers);

        return listAllUsers.stream()
                .sorted(new AdminUserComparator())
                .collect(Collectors.toList());
    }
}



