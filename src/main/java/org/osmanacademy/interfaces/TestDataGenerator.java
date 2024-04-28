package org.osmanacademy.interfaces;

import org.osmanacademy.common.DateFormat;


public interface TestDataGenerator {

    public String getFirstName();

    public String getLastName();

    public String getMiddleName();

    public String getFullName();

    public String getPrefix();

    public String getSuffix();

    public String getAddressLine1();

    public String getAddressLine2();

    public String getCityName();

   public String getCountryName();

    public String getZipCode();

   public String getCurrentDate(DateFormat dateFormat);

   public String getDateOfBirth(DateFormat dateFormat, int minAge, int maxAge) throws Exception;


}
