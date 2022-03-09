package kristinacoffee;

import java.io.IOException;

public class CoffeeMachine {

    public static void main(String[] args) throws IOException {

        MachineBlue machine1;

        machine1 = new MachineBlue();
        machine1.setName("Kristina");
        machine1.setColor("blue");
        machine1.setShape("circle with base");
        machine1.setDimension(170);
        machine1.setWater(400);
        machine1.setMilk(540);
        machine1.setBeans(50);
        machine1.setCups(9);
        machine1.setMoney(550);

        machine1.menu();

    }
}
