package hotelBooking;

class PageObjects {
    static String LANDING_PAGE_HEADER = "jumbotron";
    static String BOOKING_FORM = "bookings";
    static String FIRST_NAME_LABEL = "#bookings > div:nth-child(1) > div:nth-child(1) > h3";
    static String SURNAME_LABEL = "#bookings > div:nth-child(1) > div:nth-child(2) > h3";
    static String PRICE_LABEL = "#bookings > div:nth-child(1) > div:nth-child(3) > h3";
    static String DEPOSIT_LABEL = "#bookings > div:nth-child(1) > div:nth-child(4) > h3";
    static String CHECK_IN_DATE_LABEL = "#bookings > div:nth-child(1) > div:nth-child(5) > h3";
    static String CHECK_OUT_DATE_LABEL = "#bookings > div:nth-child(1) > div:nth-child(6) > h3";
    static String FIRST_NAME_TEXTFIELD = "firstname";
    static String SURNAME_TEXT_FIELD = "lastname";
    static String PRICE_TEXTFIELD = "totalprice";
    static String DEPOSIT_TEXTFIELD = "depositpaid";
    static String DEPOSIT_FALSE = "#depositpaid > option:nth-child(2)";

    static String CHECKIN_TEXTFIELD = "checkin";
    static String CHECKOUT_TEXTFIELLD = "checkout";
    static String SAVE_BUTTON = "#form > div > div:nth-child(7) > input[type=button]";
    static String DELETE_BUTTON = "Delete";
    static String CHECK_IN_PREVIOUS_MONTH = "#ui-datepicker-div > div > a.ui-datepicker-prev.ui-corner-all > span";
    static String CHECK_IN_NEXT_MONTH = "#ui-datepicker-div > div > a.ui-datepicker-next.ui-corner-all > span";
    static String CHECK_IN_DAY = "#ui-datepicker-div > table > tbody > tr:nth-child(5) > td:nth-child(6) > a";
    static String CHECK_OUT_PREVIOUS_MONTH = "#ui-datepicker-div > div > a.ui-datepicker-prev.ui-corner-all > span";
    static String CHECK_OUT_NEXT_MONTH = "#ui-datepicker-div > div > a.ui-datepicker-next.ui-corner-all > span";
    static String CHECK_OUT_DAY ="#ui-datepicker-div > table > tbody > tr:nth-child(5) > td:nth-child(7) > a";

    static String NAME_ENTRY = "div:nth-child(1) > p";
    static String NEW_ENTRY_DELETE = "div:nth-child(7) > input:nth-child(1)";
    static String RECORDS = "row";
    static String BUTTONS = "input[type='button']";


}