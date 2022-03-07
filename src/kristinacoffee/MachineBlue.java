package kristinacoffee;


import java.io.*;
import java.util.Scanner;

public class MachineBlue {

    static Scanner scanner = new Scanner(System.in);
    public static String menu = " ";

    public String name;
    public String color;
    public String shape;
    public int dimension;
    public int water;
    public int milk;
    public int beans;
    public int cups;
    public int money;
    FileReader fileReader;

    public FileReader getFileReader() {
        return fileReader;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public String toString() {
        return "MachineBlue{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", shape='" + shape + '\'' +
                ", dimension=" + dimension +
                ", water=" + water +
                ", milk=" + milk +
                ", beans=" + beans +
                ", cups=" + cups +
                ", money=" + money +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getWater() throws FileNotFoundException {
        FileReader reader = new FileReader("doc/coffee_status.csv");
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getBeans() {
        return beans;
    }

    public void setBeans(int beans) {
        this.beans = beans;
    }

    public int getCups() {
        return cups;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public static void loadStatusFromFile () throws FileNotFoundException {
        File myObj = new File("doc/coffee_remaining.csv");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println((data));
        }
    }


    public void remaining() throws IOException {
        FileWriter writer1 = new FileWriter("doc/coffee_status.csv");
        writer1.write(water+"\n");
        writer1.write(milk + "\n");
        writer1.write(beans + "\n");
        writer1.write(cups + "\n");
        writer1.write(money + "\n");
        writer1.flush();
        writer1.close();

        FileWriter writer = new FileWriter("doc/coffee_remaining.csv");
        writer.write("The coffee machine has: \n");
        writer.write(water + " ml of water\n");
        writer.write(milk + " ml of milk\n");
        writer.write(beans + " g of coffee beans\n");
        writer.write(cups + " disposable cups\n");
        writer.write("$" + money + " of money\n");
        writer.flush();
        writer.close();

        loadStatusFromFile();
    }

    public void fill() {
        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        beans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        cups += scanner.nextInt();
    }

    public void take() {
        System.out.println("I gave you " + "$" + money);
        money -= money;
    }

    public void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String coffee = scanner.next();
        switch (coffee) {
            case "1":
                if (water >= 250 && beans >= 16 && cups >= 1) {
                    System.out.println("I have enough resources, making you a coffee!");
                    water -= 250;
                    milk += 0;
                    beans -= 16;
                    cups -= 1;
                    money += 4;
                } else if (water < 250) {
                    System.out.println("Sorry, not enough water!");
                } else if (beans < 16) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough disposable cups!");
                }
                break;

            case "2":
                if (water >= 350 && milk >= 75 && beans >= 20 && cups >= 1) {
                    System.out.println("I have enough resources, making you a coffee!");
                    water -= 350;
                    milk -= 75;
                    beans -= 20;
                    cups -= 1;
                    money += 7;
                } else if (water < 350) {
                    System.out.println("Sorry, not enough water!");
                } else if (milk < 75) {
                    System.out.println("Sorry, not enough milk!");
                } else if (beans < 20) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough disposable cups!");
                }
                break;

            case "3":
                if (water >= 200 && milk >= 100 && beans >= 12 && cups >= 1) {
                    System.out.println("I have enough resources, making you a coffee!");
                    water -= 200;
                    milk -= 100;
                    beans -= 12;
                    cups -= 1;
                    money += 6;
                } else if (water < 200) {
                    System.out.println("Sorry, not enough water!");
                } else if (milk < 100) {
                    System.out.println("Sorry, not enough milk!");
                } else if (beans < 12) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough disposable cups!");
                }
                break;

            default:
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

                default:
                    break;
            }
        }
    }

}



