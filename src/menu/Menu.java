package menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu implements Runnable{

    private final String title;         //название меню
    private ArrayList<MenuItem> items;  //пункты меню
    public Menu(String title) {
        this.title = title;
        items = new ArrayList<>();
    }

    @Override
    public void run() {
        int choice = -1;
        while(choice != 0) {
            System.out.println(title + ":");
            for (MenuItem item : items) {
                System.out.println("[" + item.getId() + "]: " + item.getText());
            }
            System.out.println("[0]: Выход");
            choice = getNumber("---> ", 0, items.size());
            System.out.println();
            execute(choice);
            System.out.println();
        }
    }

    public void addMenuItem(MenuItem menuItem) {
        items.add(menuItem);
    }

    public int getNumber(String message, int min, int max){
        System.out.print(message);
        Scanner input = new Scanner(System.in);
        int number;
        do {
            number = input.nextInt();
            if(number < min || number > max)
                System.out.print("Число вне диапазона. Повторите ввод.\n---> ");
        } while (number < min || number > max);
        return number;
    }

    public void execute(int choice) {
        if(choice == 0)
            return;
        int choicePos = choice - 1;
        items.get(choicePos).getMethod().run();
    }
}

