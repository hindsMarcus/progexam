package dtos;

public class MemberDTO {

    private String id;
    private String address;
    private String phone;
    private String email;
    private int birthYear;
    private double accountBalance;

    public MemberDTO() {
    }

    public MemberDTO(String id, String address, String phone, String email, int birthYear, double accountBalance) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthYear = birthYear;
        this.accountBalance = accountBalance;
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

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthYear=" + birthYear +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
