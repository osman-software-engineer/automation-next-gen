package org.osmanacademy.testdata;

import com.github.javafaker.Faker;

import org.osmanacademy.enums.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class TestDataGeneratorImpl implements TestDataGenerator{

    private final Faker faker;

    public TestDataGeneratorImpl() {
        this.faker = new Faker(Locale.ENGLISH);
    }

    @Override
    public String getFirstName() {
        return this.faker.name().firstName();
    }

    @Override
    public String getLastName() {
        return  this.faker.name().lastName();
    }

    @Override
    public String getMiddleName() {
        return  this.faker.name().firstName();
    }

    @Override
    public String getFullName() {
        return  this.faker.name().fullName();
    }


    @Override
    public String getPrefix() {
        return  this.faker.name().prefix();
    }

    @Override
    public String getSuffix() {
        return  this.faker.name().suffix();
    }

    @Override
    public String getAddressLine1() {
        return this.faker.address().streetAddress();
    }

    /**
     * @return
     */
    @Override
    public String getAddressLine2() {
        return this.faker.address().secondaryAddress();
    }

    @Override
    public String getCityName() {
        return "Nashville";
    }

    @Override
    public String getCountryName() {
        return "USA";
    }

    @Override
    public String getZipCode() {
        return "37011";
    }

    /**
     * @param dateFormat
     * @return
     */
    @Override
    public String getCurrentDate(DateFormat dateFormat) {
        try {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat.getFormat());
            return currentDate.format(formatter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param dateFormat
     * @param minAge
     * @param maxAge
     * @return
     */
    @Override
    public String getDateOfBirth(DateFormat dateFormat, int minAge, int maxAge) {
            Date currentDate = this.faker.date().birthday(minAge,maxAge);
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat.getFormat());
            return formatter.format(currentDate);
    }


    public static void main(String[] args) throws Exception {
        TestDataGenerator testDataGenerator = new TestDataGeneratorImpl();
        System.out.println("First Name ::: " + testDataGenerator.getFirstName());
        System.out.println("Last Name ::: " + testDataGenerator.getLastName());
        System.out.println("Middle Name ::: " + testDataGenerator.getMiddleName());
        System.out.println("Full Name ::: " + testDataGenerator.getFullName());
        System.out.println("Prefix ::: " + testDataGenerator.getPrefix());
        System.out.println("Suffix ::: " + testDataGenerator.getSuffix());
        System.out.println("Address Line 1 ::: " + testDataGenerator.getAddressLine1());
        System.out.println("Address Line 2 ::: " + testDataGenerator.getAddressLine2());
        System.out.println("City Name ::: " + testDataGenerator.getCityName());
        System.out.println("Country Name ::: " + testDataGenerator.getCountryName());
        System.out.println("Zipe Code ::: " + testDataGenerator.getZipCode());
        System.out.println("Today's Date ::: " + testDataGenerator.getCurrentDate(DateFormat.YYYY_MM_DD));
        System.out.println("Date of Birth ::: " + testDataGenerator.getDateOfBirth(DateFormat.YYYY_MM_DD,34,34));

    }

}
