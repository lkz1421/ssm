package com.ssm.bean;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getSpeakintervaltime() {
        return speakintervaltime;
    }

    public void setSpeakintervaltime(String speakintervaltime) {
        this.speakintervaltime = speakintervaltime == null ? null : speakintervaltime.trim();
    }

    public String getForbidtalk() {
        return forbidtalk;
    }

    public void setForbidtalk(String forbidtalk) {
        this.forbidtalk = forbidtalk == null ? null : forbidtalk.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }
}