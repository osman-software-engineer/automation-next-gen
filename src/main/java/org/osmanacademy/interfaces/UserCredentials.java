package org.osmanacademy.interfaces;

import org.osmanacademy.dataobjects.UserCredential;

public interface UserCredentials {
    UserCredential getStandardUser();

    UserCredential getLockedOutUser();

    UserCredential getProblemUser();

    UserCredential getPerformanceGlitchUser();

    UserCredential getErrorUser();

    UserCredential getVisualUser();
}
