package com.ssm.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String id;
    private String username;
    private String password;
    private String nick;
    private String email;
    private String mobile;
    private String level;
    private String speakintervaltime;
    private String forbidtalk;
    private String createtime;
}