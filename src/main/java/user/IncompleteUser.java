package user;

public class IncompleteUser {
    private String email;
    private String password;

    public IncompleteUser(String email, String password) {
        this.email = email;
        this.password = password;
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

    public String invalidLogin(){
        return "1222344@mail.ru";
    }



    public String invalidPass() {
        return "0000000";

    }



}
