package Classes.fileio;

public class CredentialsInput {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private int balance;

    public CredentialsInput() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(final int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CredentialsInput{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", accountType='" + accountType + '\'' +
                ", country='" + country + '\'' +
                ", balance=" + balance +
                '}';
    }
}
