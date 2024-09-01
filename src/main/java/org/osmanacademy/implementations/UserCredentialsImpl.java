package org.osmanacademy.implementations;

import org.osmanacademy.common.PropertiesFileLoader;
import org.osmanacademy.dataobjects.UserCredential;
import org.osmanacademy.interfaces.UserCredentials;

public class UserCredentialsImpl implements UserCredentials {

    private PropertiesFileLoader propertiesFileLoader;

    public UserCredentialsImpl() {
        setPropertiesFileLoader(new PropertiesFileLoader("user-credentials.properties"));
    }


    @Override
    public UserCredential getStandardUser() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUserName(getPropertiesFileLoader().getProperty("username.standard.user"));
        userCredential.setPassword(getPropertiesFileLoader().getProperty("password.standard.user"));
        return userCredential;
    }

    @Override
    public UserCredential getLockedOutUser() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUserName(getPropertiesFileLoader().getProperty("username.locked.out.user"));
        userCredential.setPassword(getPropertiesFileLoader().getProperty("password.locked.out.user"));
        return userCredential;
    }


    @Override
    public UserCredential getProblemUser() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUserName(getPropertiesFileLoader().getProperty("username.problem.user"));
        userCredential.setPassword(getPropertiesFileLoader().getProperty("password.problem.user"));
        return userCredential;
    }


    @Override
    public UserCredential getPerformanceGlitchUser() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUserName(getPropertiesFileLoader().getProperty("username.performance.glitch.user"));
        userCredential.setPassword(getPropertiesFileLoader().getProperty("password.performance.glitch.user"));
        return userCredential;
    }


    @Override
    public UserCredential getErrorUser() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUserName(getPropertiesFileLoader().getProperty("username.error.user"));
        userCredential.setPassword(getPropertiesFileLoader().getProperty("password.error.user"));
        return userCredential;
    }
    @Override
    public UserCredential getVisualUser() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUserName(getPropertiesFileLoader().getProperty("username.visual.user"));
        userCredential.setPassword(getPropertiesFileLoader().getProperty("password.visual.user"));
        return userCredential;
    }

    public PropertiesFileLoader getPropertiesFileLoader() {
        return propertiesFileLoader;
    }

    public void setPropertiesFileLoader(PropertiesFileLoader propertiesFileLoader) {
        this.propertiesFileLoader = propertiesFileLoader;
    }
        public static void main(String[] args) {
            UserCredentialsImpl credentialsAutomation = new UserCredentialsImpl();
            System.out.println("Standard User: " + credentialsAutomation.getStandardUser().toString());
            System.out.println("Locked Out User: " + credentialsAutomation.getLockedOutUser().toString());
            System.out.println("Problem User: " + credentialsAutomation.getProblemUser().toString());
            System.out.println("Performance Glitch User: " + credentialsAutomation.getPerformanceGlitchUser().toString());
            System.out.println("Error User: " + credentialsAutomation.getErrorUser().toString());
            System.out.println("Visual User: " + credentialsAutomation.getVisualUser());
        }
}
