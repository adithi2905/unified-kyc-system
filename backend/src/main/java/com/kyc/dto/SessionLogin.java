package com.kyc.dto;

import com.kyc.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionLogin {
    private String message;
    private User data;

    private String accessToken;
}
