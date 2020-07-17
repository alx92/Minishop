package userapp.ui;

import userapp.persistence.model.AddressModel;
import userapp.persistence.model.DepartmentModel;
import userapp.persistence.model.UserModel;
import userapp.service.DepartmentService;
import userapp.service.IllegalPostalCodException;
import userapp.service.UserService;

import java.util.List;
import java.util.Scanner;

// here we implement user interaction
// presentation layer
public class UserManagementUI
{
    private DepartmentService departmentService = new DepartmentService();
    private Scanner scanner = new Scanner(System.in);
    private UserService userService = new UserService();

    public void startProgram() {
        int option = -1;
        while (option != 0) {
            displayMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                viewUsers();
            }
            if (option == 2) {
                addUser();
            }
            if (option == 4) {
                addDepartment();
            }
            if (option == 5) {
                viewDepartments();
            }
        }
    }

    private void displayMenu(){
        System.out.println("0.Exit");
        System.out.println("1.View users");
        System.out.println("2.Add user");
        System.out.println("3.Change name");
        System.out.println("4.Add department");
        System.out.println("5.View departments");
    }
    private void viewUsers() {
        List<UserModel> userModelList = userService.getSortedUsers();

        userModelList.forEach(userModel -> {
                    System.out.print(userModel.getId() +
                            "." + userModel.getLastName() + " " + userModel.getName());
                    AddressModel addressModel = userModel.getAddress();
                    System.out.println(" (" + addressModel.getStreet()
                            + " " + addressModel.getApNumber() + "(" + addressModel.getPostalCode() + "))");
                }
        );
    }

    private void addUser() {
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter street:");
        String street = scanner.nextLine();
        System.out.println("Enter postal code:");
        long codPostal = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter apartment number:");
        int apartmentNumber = scanner.nextInt();
        scanner.nextLine();

        AddressModel addressModel = new AddressModel();
        addressModel.setApNumber(apartmentNumber);
        addressModel.setPostalCode(codPostal);
        addressModel.setStreet(street);

        UserModel newUserModel = new UserModel();
        newUserModel.setLastName(lastName);
        newUserModel.setName(name);
        newUserModel.setAddress(addressModel);

        try {
            userService.addUser(newUserModel);
        } catch (IllegalPostalCodException e) {
            System.out.println("Invalid postal code. User can not be added!");
        }
    }

    private void addDepartment() {
        System.out.println("Enter department name:");
        String name = scanner.nextLine();
        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setName(name);
        departmentService.add(departmentModel);
    }

    private void viewDepartments() {
        System.out.println("Departments:");
        List<DepartmentModel> departmentModels = departmentService.getDepartments();
        departmentModels.forEach(departmentModel -> System.out.println(departmentModel.getId()
                + " " + departmentModel.getName()));
    }

}
