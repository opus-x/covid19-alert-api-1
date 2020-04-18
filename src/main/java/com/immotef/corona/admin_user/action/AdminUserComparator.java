package com.immotef.corona.admin_user.action;

import com.immotef.corona.admin_user.UserEntity;

import java.util.Comparator;

public class AdminUserComparator implements Comparator<UserEntity> {

    @Override
    public int compare(UserEntity u1, UserEntity u2) {
        int nameComparison = u1.getFirstName().compareTo(u2.getFirstName());
        if (nameComparison == 0) {
            return u1.getLastName().compareTo(u2.getLastName());
        } else
            return nameComparison;
    }
}
