package org.osmanacademy.implementations;

import org.osmanacademy.dataobjects.UserCredential;
import org.osmanacademy.interfaces.Credentials;

import org.osmanacademy.common.PropertiesFileLoader;

public class CredentialsImpl implements Credentials {

    private PropertiesFileLoader propertiesFileLoader;

    public CredentialsImpl() {
        setPropertiesFileLoader(new PropertiesFileLoader("user-credentials.properties"));
    }

    /**
     * @return
     */
    @Override
    public UserCredential getStandardUser() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUserName(getPropertiesFileLoader().getProperty("username.standard.user"));
        userCredential.setPassword(getPropertiesFileLoader().getProperty("password.standard.user"));
        return userCredential;
    }

    /**
     * @return
     */
    @Override
    public UserCredential getLockedOutUser() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUserName(getPropertiesFileLoader().getProperty("username.locked.out.user"));
        userCredential.setPassword(getPropertiesFileLoader().getProperty("password.locked.out.user"));
        return userCredential;
    }

    /**
     * @return
     */
    @Override
    public UserCredential getProblemUser() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUserName(getPropertiesFileLoader().getProperty("username.problem.user"));
        userCredential.setPassword(getPropertiesFileLoader().getProperty("password.problem.user"));
        return userCredential;
    }

    /**
     * @return
     */
    @Override
    public UserCredential getPerformanceGlitchUser() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUserName(getPropertiesFileLoader().getProperty("username.performance.glitch.user"));
        userCredential.setPassword(getPropertiesFileLoader().getProperty("password.performance.glitch.user"));
        return userCredential;
    }

    /**
     * @return
     */
    @Override
    public UserCredential getErrorUser() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUserName(getPropertiesFileLoader().getProperty("username.standard.user"));
        userCredential.setPassword(getPropertiesFileLoader().getProperty("password.error.user"));
        return userCredential;
    }

    public PropertiesFileLoader getPropertiesFileLoader() {
        return propertiesFileLoader;
    }

    public void setPropertiesFileLoader(PropertiesFileLoader propertiesFileLoader) {
        this.propertiesFileLoader = propertiesFileLoader;
    }
        public static void main(String[] args) {
            CredentialsImpl credentialsAutomation = new CredentialsImpl();
            System.out.println("Standard User: " + credentialsAutomation.getStandardUser().toString());
            System.out.println("Locked Out User: " + credentialsAutomation.getLockedOutUser().toString());
            System.out.println("Problem User: " + credentialsAutomation.getProblemUser().toString());
            System.out.println("Performance Glitch User: " + credentialsAutomation.getPerformanceGlitchUser().toString());
            System.out.println("Error User: " + credentialsAutomation.getErrorUser().toString());
        }
}
