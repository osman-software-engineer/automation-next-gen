package org.osmanacademy.webbrowser.enums;

public enum EmulatedDevice {
    // From Chrome Emulated Devices List_1.png
    BLACKBERRY_Z30("BlackBerry Z30"),
    BLACKBERRY_PLAYBOOK("Blackberry PlayBook"),
    GALAXY_NOTE_3("Galaxy Note 3"),
    GALAXY_NOTE_II("Galaxy Note II"),
    GALAXY_S_III("Galaxy S III"),
    GALAXY_S8("Galaxy S8"),
    GALAXY_S9_PLUS("Galaxy S9+"),
    GALAXY_TAB_S4("Galaxy Tab S4"),
    KINDLE_FIRE_HDX("Kindle Fire HDX"),
    LG_OPTIMUS_L70("LG Optimus L70"),
    MICROSOFT_LUMIA_550("Microsoft Lumia 550"),
    MICROSOFT_LUMIA_950("Microsoft Lumia 950"),
    MOTO_G_POWER("Moto G Power"),
    MOTO_G4("Moto G4"),
    NEXUS_10("Nexus 10"),
    NEXUS_4("Nexus 4"),
    NEXUS_5("Nexus 5"),
    NEXUS_5X("Nexus 5X"),
    NEXUS_6("Nexus 6"),
    NEXUS_6P("Nexus 6P"),
    NEXUS_7("Nexus 7"),

    NOKIA_LUMIA_520("Nokia Lumia 520"),
    NOKIA_N9("Nokia N9"),
    PIXEL_3("Pixel 3"),
    PIXEL_4("Pixel 4"),
    JIOPHONE_2("JioPhone 2"),
    IPHONE_SE("iPhone SE"),
    IPHONE_XR("iPhone XR"),
    IPHONE_12_PRO("iPhone 12 Pro"),
    IPHONE_14_PRO_MAX("iPhone 14 Pro Max"),
    PIXEL_3_XL("Pixel 3 XL"),
    PIXEL_7("Pixel 7"),
    SAMSUNG_GALAXY_S8_PLUS("Samsung Galaxy S8+"),
    SAMSUNG_GALAXY_S20_ULTRA("Samsung Galaxy S20 Ultra"),
    IPAD_MINI("iPad Mini"),
    IPAD_AIR("iPad Air"),
    IPAD_PRO("iPad Pro"),
    SURFACE_PRO_7("Surface Pro 7"),
    SURFACE_DUO("Surface Duo"),
    GALAXY_FOLD("Galaxy Fold"),
    ASUS_ZENBOOK_FOLD("Asus Zenbook Fold"),
    SAMSUNG_GALAXY_A51_A71("Samsung Galaxy A51/A71"),
    NEST_HUB("Nest Hub"),
    NEST_HUB_MAX("Nest Hub Max"),
    GALAXY_S5("Galaxy S5"),
    PIXEL_2_XL("Pixel 2 XL"),
    IPHONE_5_SE("iPhone 5/SE"), // New naming to distinguish from existing IPHONE_SE
    IPHONE_6_7_8("iPhone 6/7/8"),
    IPHONE_6_7_8_PLUS("iPhone 6/7/8 Plus"),
    IPHONE_X("iPhone X"),

    // No new unique devices to add.

    ;

    private final String displayName;

    EmulatedDevice(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

