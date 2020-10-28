package application.DTO;

public class RegistrationDTO {

    private String userName;
    private String password;
    private String matchingPassword;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String userName, String password, String matchingPassword) {
        this.userName = userName;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
