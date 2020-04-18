package com.immotef.corona.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagedUsersResult {

    int numberOfPages;
    int currentPage;
    List<UserDTO> users;
}
