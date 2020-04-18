package com.immotef.corona.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserService {

    private final UsersRepository usersRepository;

    private Integer pageSize = 20;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public PagedUsersResult userList(Optional<String> page, Optional<String> filter) {
        List<User> users = usersRepository.findAll();
        if(filter.get() != null){
            users = filterUserList(users, filter);
        }
        int numberOfPages = (int)Math.ceil((double)users.size() / pageSize);

        Integer pageNumber = 0;
        if(page.get() != null){
            try {
                pageNumber = Integer.parseInt(page.get());
            }catch (Exception e){}
        }

        users = getPageList(users, pageNumber);
        List<UserDTO> result = new ArrayList<>(users.size());
        for (User user : users) {
            result.add(UserDTO.withUser(user));
        }
        PagedUsersResult usersResult = PagedUsersResult.builder().users(result).numberOfPages(numberOfPages).currentPage(pageNumber).build();
        return usersResult;
    }

    private List<User> getPageList(List<User> users, Integer pageNumber) {
        if(pageNumber == null){
            pageNumber = 0;
        }
        List<User> pagedUsers = users.stream().
                skip(pageNumber*pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        return pagedUsers;
    }

    private List<User> filterUserList(List<User> users, Optional<String> filter) {
        return users;
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = usersRepository.findById(userDTO.getId()).orElseThrow(() -> new UsernameNotFoundException("No user found with id "));
        usersRepository.save(user);
        return UserDTO.withUser(user);
    }
}
