package org.osmanacademy.interfaces;

import org.osmanacademy.dataobjects.UserCredential;

public interface Credentials {

    UserCredential getStandardUser();

    UserCredential getLockedOutUser();

    UserCredential getProblemUser();

    UserCredential getPerformanceGlitchUser();

    UserCredential getErrorUser();

}
