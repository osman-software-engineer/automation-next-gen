package org.osmanacademy.dataobjects;

import java.util.Optional;

public class LoginPageData {

    private Optional<String> username;
    private Optional<String> password;
    public Optional<String> getUsername() {
        return username;
    }

    public void setUsername(Optional<String> username) {
        this.username = username;
    }

    public Optional<String> getPassword() {
        return password;
    }

    public void setPassword(Optional<String> password) {
        this.password = password;
    }
}
