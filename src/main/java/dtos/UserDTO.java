package dtos;

import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {

    private String userName;
    private String userPass;
    private String id;
    private String address;
    private String phone;
    private String email;
    private int birthYear;
    private int accountBalance;
    private List<RoleDTO> roleList = new ArrayList<>();

//    public UserDTO(User user){
//        this.userName = user.getUserName();
//        this.userPass = user.getUserPass();
//        this.roleList = user.getRoleList().stream().map(r -> new RoleDTO(r)).collect(Collectors.toList());
//
//    }

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.birthYear = user.getBirthYear();
        this.accountBalance = user.getAccountBalance();
        this.roleList = user.getRoleList().stream().map(r -> new RoleDTO(r)).collect(Collectors.toList());
    }

    public UserDTO(String userName, String userPass, String id, String address, String phone, String email, int birthYear, int accountBalance) {
        this.userName = userName;
        this.userPass = userPass;
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthYear = birthYear;
        this.accountBalance = accountBalance;
    }

    public UserDTO(String userName, String userPass, String id, String address, String phone, String email, int birthYear, int accountBalance, List<RoleDTO> roleList) {
        this.userName = userName;
        this.userPass = userPass;
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthYear = birthYear;
        this.accountBalance = accountBalance;
        this.roleList = roleList;
    }


    public static List<UserDTO> getDtos(List<User> persons) {
        return persons.stream().map(p -> new UserDTO(p)).collect(Collectors.toList());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public List<RoleDTO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleDTO> roleList) {
        this.roleList = roleList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthYear=" + birthYear +
                ", accountBalance=" + accountBalance +
                ", roleList=" + roleList +
                '}';
    }
}
