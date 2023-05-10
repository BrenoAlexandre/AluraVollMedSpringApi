package com.example.demo.domain.responses;

import com.example.demo.domain.user.User;

public record UserDetailsResponse(Long id, String login) {
   public UserDetailsResponse(User user) {
       this(user.getId(), user.getLogin());
   }
}
