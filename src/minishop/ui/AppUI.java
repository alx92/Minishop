package minishop.ui;

import java.util.Scanner;

public class AppUI
{
    private Scanner scanner = new Scanner(System.in);
    private CategoryUI categoryUI = new CategoryUI();
    private ProductUI productUI = new ProductUI();

    public void startApp()
    {
        int option = -1;

        while (option != 5)
        {
            System.out.println("-----Small Shop Management------\n" +
                    "1. Category Management.\n" +
                    "2. Product Management.\n" +

                    "5. Exit");

            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1)
            {
                categoryUI.startCategoryManagement();
            }

            if (option == 2)
            {
                productUI.startProductManagement();
            }
        }
    }
}
