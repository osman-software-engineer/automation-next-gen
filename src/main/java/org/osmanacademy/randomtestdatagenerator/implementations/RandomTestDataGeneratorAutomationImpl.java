package org.osmanacademy.randomtestdatagenerator.implementations;

import com.devskiller.jfairy.Fairy;
import com.github.javafaker.Faker;
import org.osmanacademy.enums.DateFormat;
import org.osmanacademy.randomtestdatagenerator.RandomTestDataGeneratorAutomation;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class RandomTestDataGeneratorAutomationImpl implements RandomTestDataGeneratorAutomation {

    private Faker faker;

    private Fairy fairy;

    public RandomTestDataGeneratorAutomationImpl() {
        setFaker(new Faker(Locale.ENGLISH));
        setFairy(Fairy.create(Locale.ENGLISH));
    }
    @Override
    public String getFirstName() {
        return getFairy().person().getFirstName();
    }

    @Override
    public String getLastName() {
        return getFairy().person().getLastName();
    }

    @Override
    public String getMiddleName() {
        return getFairy().person().getMiddleName();
    }

    @Override
    public String getFullName() {
        return String.format("%s %s %s", getFirstName(), getMiddleName(), getLastName());
    }

    @Override
    public String getPrefix() {
        return getFaker().name().prefix();
    }

    @Override
    public String getSuffix() {
        return getFaker().name().suffix();
    }

    @Override
    public String getAddressLine1() {
        return getFairy().person().getAddress().getAddressLine1();
    }

    /**
     * @return
     */
    @Override
    public String getAddressLine2() {
        return getFairy().person().getAddress().getAddressLine2();
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
        Date currentDate = this.faker.date().birthday(minAge, maxAge);
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat.getFormat());
        return formatter.format(currentDate);
    }

    public Faker getFaker() {
        return faker;
    }

    public void setFaker(Faker faker) {
        this.faker = faker;
    }

    public Fairy getFairy() {
        return fairy;
    }

    public void setFairy(Fairy fairy) {
        this.fairy = fairy;
    }
    public static void main(String[] args) throws Exception {
        RandomTestDataGeneratorAutomation randomTestDataGeneratorAutomation = new RandomTestDataGeneratorAutomationImpl();
        System.out.println("First Name ::: " + randomTestDataGeneratorAutomation.getFirstName());
        System.out.println("Last Name ::: " + randomTestDataGeneratorAutomation.getLastName());
        System.out.println("Middle Name ::: " + randomTestDataGeneratorAutomation.getMiddleName());
        System.out.println("Full Name ::: " + randomTestDataGeneratorAutomation.getFullName());
        System.out.println("Prefix ::: " + randomTestDataGeneratorAutomation.getPrefix());
        System.out.println("Suffix ::: " + randomTestDataGeneratorAutomation.getSuffix());
        System.out.println("Address Line 1 ::: " + randomTestDataGeneratorAutomation.getAddressLine1());
        System.out.println("Address Line 2 ::: " + randomTestDataGeneratorAutomation.getAddressLine2());
        System.out.println("City Name ::: " + randomTestDataGeneratorAutomation.getCityName());
        System.out.println("Country Name ::: " + randomTestDataGeneratorAutomation.getCountryName());
        System.out.println("Zipe Code ::: " + randomTestDataGeneratorAutomation.getZipCode());
        System.out.println("Today's Date ::: " + randomTestDataGeneratorAutomation.getCurrentDate(DateFormat.YYYY_MM_DD));
        System.out.println("Date of Birth ::: " + randomTestDataGeneratorAutomation.getDateOfBirth(DateFormat.YYYY_MM_DD, 34, 34));

    }
}
