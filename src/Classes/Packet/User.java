package Classes.Packet;

import Classes.fileio.UserInput;

public class User {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private int balance;

    public User(UserInput input) {
        name = input.getCredentials().getName();
        password = input.getCredentials().getPassword();
        accountType = input.getCredentials().getAccountType();
        country = input.getCredentials().getCountry();
        balance = input.getCredentials().getBalance();
    }

    public User(User input) {
        this.name = input.name;
        this.password = input.password;
        this.accountType = input.accountType;
        this.country = input.country;
        this.balance = input.balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
