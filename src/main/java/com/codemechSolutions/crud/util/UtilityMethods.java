package com.codemechSolutions.crud.util;

import com.codemechSolutions.crud.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UtilityMethods {
    public static User getLoggedInPersonInfo() {

        System.out.println(SecurityContextHolder.getContext());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal == null) {
            return null;
        }
        if (principal instanceof UserDetails) {
            return (User) principal;
        } else {
            return null;
        }
    }

    public static UserDetails getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return (UserDetails) principal;
        } else {
            throw new UsernameNotFoundException("You are not Logged in");
        }

    }
}
