package com.example.erp.common;

import com.example.erp.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUser {

    private User user;
    private List<String> roles;
    private List<String> permissions;
}
