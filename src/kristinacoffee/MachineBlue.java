package kristinacoffee;


import java.io.*;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class MachineBlue {

    static Scanner scanner = new Scanner(System.in);
    public static String menu = " ";
    public int hasWater;
    public int hasMilk;
    public int hasBeans;
    public int hasCups;
    public int hasMoney;

    @Override
    public String toString() {
        return "MachineBlue{" +
                ", water=" + hasWater +
                ", milk=" + hasMilk +
                ", beans=" + hasBeans +
                ", cups=" + hasCups +
                ", money=" + hasMoney +
                '}';
    }

    public int getWater() {return hasWater; }

    public void setWater(int hasWater) {
        this.hasWater = hasWater;
    }

    public int getMilk() {return hasMilk; }

    public void setMilk(int hasMilk) {
        this.hasMilk = hasMilk;
    }

    public int getBeans() {
        return hasBeans;
    }

    public void setBeans(int hasBeans) {
        this.hasBeans = hasBeans;
    }

    public int getCups() {
        return hasCups;
    }

    public void setCups(int hasCups) {
        this.hasCups = hasCups;
    }

    public int getMoney() {
        return hasMoney;
    }

    public void setMoney(int hasMoney) {
        this.hasMoney = hasMoney;
    }

    public void loadStatusFromFile() throws FileNotFoundException {
        FileReader reader = new FileReader("doc/coffee_remaining.csv");
        Scanner fileScanner = new Scanner(reader);

        fileScanner.nextLine();
        fileScanner.useDelimiter("\t");

        hasWater = fileScanner.nextInt();
        hasMilk = fileScanner.nextInt();
        hasBeans = fileScanner.nextInt();
        hasCups = fileScanner.nextInt();
        hasMoney = fileScanner.nextInt();

        }


    public void remaining() {
        System.out.println("The coffee machine has: \n" +
                hasWater + " ml of water\n" +
                hasMilk + " ml of milk\n" +
                hasBeans + " g of coffee beans\n" +
                hasCups + " disposable cups\n" +
                "$" + hasMoney + " of money\n");
    }

    public void writeCoffeeStatus() throws IOException {
        FileWriter writer = new FileWriter("doc/coffee_remaining.csv");
        writer.write("Water, Milk, Coffee beans, Disposable cups, Money +\n");
        writer.write(hasWater + "\t" + hasMilk + "\t" + hasBeans + "\t" + hasCups + "\t" + hasMoney);
        writer.close();
    }

    public void fill() {
        System.out.println("Write how many ml of water you want to add:");
        hasWater += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        hasMilk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        hasBeans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        hasCups += scanner.nextInt();
    }

    public void take() {
        System.out.println("I gave you " + "$" + hasMoney);
        hasMoney -= hasMoney;
    }

    public boolean canMakeCoffee(CoffeeType c) {
        if (hasWater >= c.getWaterNeeded() && hasMilk >= c.getMilkNeeded() && hasBeans >= c.getBeansNeeded()
                && hasCups >= c.getCupsNeeded()) {
            return true;
        } else
            return false;
    }

    public String ingredientLow(CoffeeType c) {
        String ingredient = " ";
        if (hasWater < c.getWaterNeeded()) ingredient = "water";
        else if (hasMilk < c.getMilkNeeded()) ingredient = "milk";
        else if (hasBeans < c.getBeansNeeded()) ingredient = "coffee beans";
        else if (hasCups < c.getCupsNeeded()) ingredient = "disposable cups";

        return ingredient;
    }

    public void makeCoffee(CoffeeType c) {
        hasWater -= c.getWaterNeeded();
        hasMilk -= c.getMilkNeeded();
        hasBeans -= c.getBeansNeeded();
        hasCups -= c.getCupsNeeded();
        hasMoney += c.getPrice();
    }

    public void buy() {
        CoffeeType c;
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String coffee = scanner.next();

        switch (coffee) {
            case "1":
                c = new CoffeeType("espresso", 250, 0, 16, 1, 4);
                if (!canMakeCoffee(c)) {
                    System.out.println("Sorry, not enough " + ingredientLow(c) + "!");
                } else {
                    System.out.println("I have enough resources, making you a coffee!");
                    makeCoffee(c);
                }
                break;

            case "2":
                c = new CoffeeType("latte", 350, 75, 20, 1, 7);
                if (!canMakeCoffee(c)) {
                    System.out.println("Sorry, not enough " + ingredientLow(c) + "!");
                } else {
                    System.out.println("I have enough resources, making you a coffee!");
                    makeCoffee(c);
                }
                break;

            case "3":
                c = new CoffeeType("cappuccino", 200, 100, 12, 1, 6);
                if (!canMakeCoffee(c)) {
                    System.out.println("Sorry, not enough " + ingredientLow(c) + "!");
                } else {
                    System.out.println("I have enough resources, making you a coffee!");
                    makeCoffee(c);
                }
                break;

            case "back":
                return;

            default:
                System.out.println("Wrong input!");
                break;
        }
    }

    public void menu() throws IOException {

        while (!menu.equals("exit")) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            menu = scanner.next();

            switch (menu) {
                case "buy":
                    buy();
                    break;

                case "fill":
                    fill();
                    break;

                case "take":
                    take();
                    break;

                case "remaining":
                    remaining();
                    break;

                case "exit":
                    writeCoffeeStatus();
                    break;

                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }

}



