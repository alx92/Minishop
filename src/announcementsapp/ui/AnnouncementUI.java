package announcementsapp.ui;

import announcementsapp.persistance.model.AnnouncementModel;
import announcementsapp.persistance.model.RealEstateAnnouncementModel;
import announcementsapp.persistance.model.RealEstateType;
import announcementsapp.persistance.model.UserModel;
import announcementsapp.service.AnnouncementService;
import announcementsapp.service.UserService;
import announcementsapp.service.UsernameAlreadyTakenException;

import java.util.List;
import java.util.Scanner;

public class AnnouncementUI
{
    private Scanner scanner = new Scanner(System.in);
    private AnnouncementService announcementService = new AnnouncementService();
    private UserService userService = new UserService();

    public void startApp()
    {
        int option = -1;

        startClock();

        while (option != 0)
        {
            printMenu();

            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1)
            {
                addNewAdd();
            }

            if (option == 2)
            {
                viewAdd();
            }

            if (option == 3)
            {
                changePrice();
            }
            if (option == 4)
            {
                register();
            }
            if (option == 5)
            {
                login();
            }
        }
    }

    private void startClock()
    {
        ClockUI clockUI = new ClockUI();
        clockUI.start();
    }

    private void printMenu()
    {
        System.out.println("Choose an option: ");
        System.out.println("--------------------");
        System.out.println("0.Exit");
        System.out.println("1.Add announcement");
        System.out.println("2.View announcements");
        System.out.println("3.Change price");
        System.out.println("4.Register");
        System.out.println("5.Login");
    }

    private void addNewAdd()
    {
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        System.out.println("Enter description: ");
        String description = scanner.nextLine();
        System.out.println("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        AnnouncementModel newAnnouncementModel = new AnnouncementModel();
        newAnnouncementModel.setTitle(title);
        newAnnouncementModel.setDescription(description);
        newAnnouncementModel.setPrice(price);
        announcementService.addAnnouncement(newAnnouncementModel);
    }

    private void viewAdd()
    {
        List<AnnouncementModel> announcementModelList = announcementService.getAnnouncement();
        // lambda forEach
        announcementModelList.forEach(announcementModel ->
        {
            if (announcementModel instanceof RealEstateAnnouncementModel)
            {
                RealEstateAnnouncementModel estateAnnouncementModel = (RealEstateAnnouncementModel) announcementModel;
                System.out.println("id: " +
                        announcementModel.getId() + "Announcement title: " +
                        announcementModel.getTitle() + " ---description--- " +
                        "Area" +
                        estateAnnouncementModel.getArea());
            }
            else
            {
                System.out.println("id: " +
                        announcementModel.getId() + "Announcement title: " +
                        announcementModel.getTitle() + " ---description--- " +
                        announcementModel.getDescription() + " Price: " +
                        announcementModel.getPrice() + " RON");
            }
        });

    }

    private void changePrice()
    {
        System.out.println("Enter announcement code: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter new price: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();
        announcementService.changePrice(newPrice, id);
    }

    private void register()
    {
        System.out.println("Register new user: ");
        System.out.println("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        UserModel userModel = new UserModel();
        userModel.setName(firstName);
        userModel.setLastName(lastName);
        userModel.setUsername(username);
        userModel.setPassword(password);

        try {
            userService.register(userModel);
        }
        catch (UsernameAlreadyTakenException e)
        {
            System.out.println("Username already in use.");
            register();
        }
    }

    private void login()
    {
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        boolean loggedIn =  userService.login(username, password);

        if (loggedIn)
        {
            int option = -1;

            while (option != 0)
            {
                System.out.println("0.Log out");
                System.out.println("1.View my announcements");
                System.out.println("2.Add new add");

                option = scanner.nextInt();
                scanner.nextLine();

                if (option == 1)
                {
                    List<AnnouncementModel> announcementModelList = userService.viewMyAnnouncements(username);

                    announcementModelList.forEach(announcementModel ->
                            System.out.println(announcementModel.getId() + " " + announcementModel.getTitle()));
                }

                if (option == 2)
                {
                    System.out.println("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.println("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.println("Enter price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Is this an real estate? Y/N");
                    String estateOption = scanner.nextLine();

                    AnnouncementModel newAnnouncementModel = null;

                    if (estateOption.equals("Y"))
                    {
                        System.out.println("Enter estate type: 1 = HOUSE, 2 = APARTMENT");

                        int estateTypeInt = scanner.nextInt();
                        scanner.nextLine();

                        RealEstateType estateType;

                        if (estateTypeInt == 1)
                        {
                            estateType = RealEstateType.HOUSE;
                        }
                        else
                        {
                            estateType = RealEstateType.APARTMENT;
                        }

                        System.out.println("Enter estate area: ");
                        double area = scanner.nextDouble();
                        scanner.nextLine();

                        RealEstateAnnouncementModel estateAnnouncementModel = new RealEstateAnnouncementModel();
                        estateAnnouncementModel.setArea(area);
                        estateAnnouncementModel.setEstateType(estateType);
                        newAnnouncementModel = estateAnnouncementModel;
                    }
                    else
                    {
                        newAnnouncementModel = new AnnouncementModel();
                    }

                    newAnnouncementModel.setTitle(title);
                    newAnnouncementModel.setDescription(description);
                    newAnnouncementModel.setPrice(price);
                    userService.addAnnouncement(username, newAnnouncementModel);
                }
            }
        }
        else
        {
            System.out.println("Login failed");
            login();
        }
    }
}