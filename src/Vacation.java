import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Vacation {
    private String city; // [Sharm El-Shiekh, Alexandria, Dahab, Hurghada]
    private String hotel; // [Tulib, Semiramis, Hilton, Regency]
    private String temp; // [yes, no]
    private int bedNum; // [1 bed, 2 beds, 4 beds]
    private int tempInt;
    private boolean wifi;
    private boolean breakfast;
    private boolean parking;
    private boolean airportDelivery;
    private double latitude;
    private double longitude;
    private final String[] eReceipt = new String[9]; // 0.city, 1.hotel, 2.period, 3.bedNum, 4.wifi, 5.breakfast, 6.parking, 7.airportDelivery, 8.mapURL
    private final Scanner scan = new Scanner(System.in);

    public Vacation() {
        book();
        printEReceipt();
    }

    private void setCity() {
        tempInt = 0;
        System.out.print("""
                \nSelect a city to visit:
                1. Sharm El-Shiekh
                2. Alexandria
                3. Dahab
                4. Hurghada
                """);
        do {
            try {
                tempInt = scan.nextInt();
                if (tempInt > 4 || tempInt < 1) {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scan.next();
            }
        } while (!(tempInt >= 1 && tempInt <= 4));
        switch (tempInt) {
            case 1 -> city = "Sharm El-Shiekh";
            case 2 -> city = "Alexandria";
            case 3 -> city = "Dahab";
            case 4 -> city = "Hurghada";
        }
        eReceipt[0] = city;
    }

    private void setHotel() {
        tempInt = 0;
        System.out.print("""
                \nSelect a hotel:
                1. Tulib
                2. Semiramis
                3. Hilton
                4. Regency
                """);
        do {
            try {
                tempInt = scan.nextInt();
                if (tempInt > 4 || tempInt < 1) {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scan.next();
            }
        } while (!(tempInt >= 1 && tempInt <= 4));
        switch (tempInt) {
            case 1 -> hotel = "Tulib";
            case 2 -> hotel = "Semiramis";
            case 3 -> hotel = "Hilton";
            case 4 -> hotel = "Regency";
        }
        eReceipt[1] = hotel;
    }

    private void setPeriod() {
        int period = 0;
        int day = 0;
        int month = 0;
        String startDate = "";
        String endDate;
        SimpleDateFormat dateFormat;
        Calendar cal = Calendar.getInstance();

        // Get the number of days the user will spend in the hotel
        System.out.println("\nEnter number of days you will spend in the hotel: ");
        do {
            try {
                period = scan.nextInt();
                if (period < 1) {
                    System.out.println("Invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scan.next();
            }
        } while (period < 1);
        // Get the day and month the user will arrive
        System.out.println("\nEnter the day you will arrive: ");
        do {
            try {
                day = scan.nextInt();
                if (day < 1 || day > 31) {
                    System.out.println("Invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scan.next();
            }
        } while (day < 1 || day > 31);

        System.out.println("\nEnter the month you will arrive: ");
        do {
            try {
                month = scan.nextInt();
                if (month < 1 || month > 12) {
                    System.out.println("Invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scan.next();
            }
        } while (month < 1 || month > 12);

        if (day < 10)
            startDate = "0";
        startDate += day + "/";
        if (month < 10)
            startDate += "0";
        startDate += month + "/" + cal.get(Calendar.YEAR);

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        cal.setTime(dateFormat.parse(startDate, new java.text.ParsePosition(0)));
        cal.add(Calendar.DATE, period);
        endDate = dateFormat.format(cal.getTime());
        eReceipt[2] = period + " days          from : " + startDate + " to " + endDate;
    }

    private void setBedNum() {
        System.out.print("""
                \nSelect the number of beds:
                1 bed
                2 beds
                4 beds
                """);
        do {
            try {
                bedNum = scan.nextInt();
                if (bedNum != 1 && bedNum != 2 && bedNum != 4) {
                    System.out.println("Invalid input. Please enter 1, 2, or 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scan.next();
            }
        } while (bedNum != 1 && bedNum != 2 && bedNum != 4);
        eReceipt[3] = bedNum + " beds";
    }

    private void setWifi() {
        temp = "";
        System.out.print("\nDo you want Wi-Fi? (yes/no): ");
        do {
            temp = scan.next();
            switch (temp.toLowerCase().charAt(0)) {
                case 'y' -> wifi = true;
                case 'n' -> wifi = false;
                default -> System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        } while (!(temp.toLowerCase().charAt(0) == 'y' || temp.toLowerCase().charAt(0) == 'n'));
        eReceipt[4] = wifi ? "Yes" : "No";
    }

    private void setBreakfast() {
        temp = "";
        System.out.print("\nDo you want breakfast? (yes/no): ");
        do {
            temp = scan.next();
            switch (temp.toLowerCase().charAt(0)) {
                case 'y' -> breakfast = true;
                case 'n' -> breakfast = false;
                default -> System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        } while (!(temp.toLowerCase().charAt(0) == 'y' || temp.toLowerCase().charAt(0) == 'n'));
        eReceipt[5] = breakfast ? "Yes" : "No";
    }

    private void setParking() {
        temp = "";
        System.out.print("\nDo you want parking? (yes/no): ");
        do {
            temp = scan.next();
            switch (temp.toLowerCase().charAt(0)) {
                case 'y' -> parking = true;
                case 'n' -> parking = false;
                default -> System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        } while (!(temp.toLowerCase().charAt(0) == 'y' || temp.toLowerCase().charAt(0) == 'n'));
        eReceipt[6] = parking ? "Yes" : "No";
    }

    private void setAirportDelivery() {
        temp = "";
        System.out.print("\nDo you want airport delivery? (yes/no): ");
        do {
            temp = scan.next();
            switch (temp.toLowerCase().charAt(0)) {
                case 'y' -> airportDelivery = true;
                case 'n' -> airportDelivery = false;
                default -> System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        } while (!(temp.toLowerCase().charAt(0) == 'y' || temp.toLowerCase().charAt(0) == 'n'));
        eReceipt[7] = airportDelivery ? "Yes" : "No";
    }

    private void setMapURL() {
        String mapURL;
        switch (city) {
            case "Sharm El-Shiekh" -> {
                switch (hotel) {
                    case "Tulib" -> {
                        latitude = 27.893151606163737;
                        longitude = 34.294083131120715;
                    }
                    case "Semiramis" -> {
                        latitude = 27.920385346364913;
                        longitude = 34.33678535951663;
                    }
                    case "Hilton" -> {
                        latitude = 27.968178243742763;
                        longitude = 34.399279004133035;
                    }
                    case "Regency" -> {
                        latitude = 27.91524246095953;
                        longitude = 34.350048048314186;
                    }
                }
            }
            case "Alexandria" -> {
                switch (hotel) {
                    case "Tulib" -> {
                        latitude = 31.2317362622057;
                        longitude = 29.94610810400287;
                    }
                    case "Semiramis" -> {
                        latitude = 31.202066811784366;
                        longitude = 29.900523773317204;
                    }
                    case "Hilton" -> {
                        latitude = 31.263831933314407;
                        longitude = 29.982214149145413;
                    }
                    case "Regency" -> {
                        latitude = 31.273053034108393;
                        longitude = 30.000536688657807;
                    }
                }
            }
            case "Dahab" -> {
                switch (hotel) {
                    case "Tulib" -> {
                        latitude = 28.496819303355455;
                        longitude = 34.51181196063251;
                    }
                    case "Semiramis" -> {
                        latitude = 28.484565009845845;
                        longitude = 34.49597711700262;
                    }
                    case "Hilton" -> {
                        latitude = 28.4829240074401;
                        longitude = 34.490812252816916;
                    }
                    case "Regency" -> {
                        latitude = 28.481704505910685;
                        longitude = 34.50008202396293;
                    }
                }
            }
            case "Hurghada" -> {
                switch (hotel) {
                    case "Tulib" -> {
                        latitude = 27.28821970005394;
                        longitude = 33.76450508631322;
                    }
                    case "Semiramis" -> {
                        latitude = 27.185235766058224;
                        longitude = 33.820122448383195;
                    }
                    case "Hilton" -> {
                        latitude = 27.256944627077182;
                        longitude = 33.830516575323784;
                    }
                    case "Regency" -> {
                        latitude = 28.096158991701916;
                        longitude = 34.41503633697847;
                    }
                }
            }
        }
        mapURL = "https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude;
        eReceipt[8] = mapURL;
    }

    private void book() {
        System.out.println(" \n----- Welcome to the vacation planner -----\n");
        setCity();
        setHotel();
        setPeriod();
        setBedNum();
        setWifi();
        setBreakfast();
        setParking();
        setAirportDelivery();
        setMapURL();
    }

    public void printEReceipt() {
        System.out.printf("""
                \n
                ______________________________________________________________________________________________________
                |                           E-Receipt for your vacation booking                                      |
                |____________________________________________________________________________________________________|
                | City:             %-80s |
                | Hotel:            %-80s |
                | Period:           %-80s |
                | Number of beds:   %-80s |
                | Wi-Fi:            %-80s |
                | Breakfast:        %-80s |
                | Parking:          %-80s |
                | Airport delivery: %-80s |
                |____________________________________________________________________________________________________|
                | Location: %-88s |
                |____________________________________________________________________________________________________|
                    """, eReceipt[0], eReceipt[1], eReceipt[2], eReceipt[3], eReceipt[4], eReceipt[5], eReceipt[6],
                eReceipt[7], eReceipt[8]);
    }

}
