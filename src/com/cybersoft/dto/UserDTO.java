package com.cybersoft.dto;

public class UserDTO {
    private int id;
    private String fullname;
    private String email;
    private String password;
    private String avatar;
    private int roleId;
    private String roleName;

    public UserDTO() {
    }

    public UserDTO(int id, String fullname, String email, String password, String avatar, int roleId) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.roleId = roleId;
    }

    public UserDTO(String fullname, String email, String password, String avatar, int roleId) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
