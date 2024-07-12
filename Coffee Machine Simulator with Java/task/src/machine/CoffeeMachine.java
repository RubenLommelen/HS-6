package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private int water = 400;
    private int milk = 540;
    private int coffeeBeans = 120;
    private int disposableCups = 9;
    private int money = 550;

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            boolean isMachineOn = true;

            CoffeeMachine coffeeMachine = new CoffeeMachine();

            while (isMachineOn) {
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                String action = sc.next();

                switch (action) {
                    case "remaining":
                        coffeeMachine.writeCurrentInventory();
                        break;
                    case "buy":
                        coffeeMachine.buy(sc);
                        break;
                    case "fill":
                        coffeeMachine.fill(sc);
                        break;
                    case "take":
                        coffeeMachine.take();
                        break;
                    case "exit":
                        isMachineOn = false;
                        break;
                    default:
                        break;
                }
            }

        }
    }

    public boolean enoughResourcesLeft(int water, int milk, int coffeeBeans, int disposableCups) {
        if (water > this.water) {
            System.out.println("Sorry, not enough water!");
            return false;
        }

        if (milk > this.milk) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }

        if (coffeeBeans > this.coffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        }

        if (disposableCups > this.disposableCups) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }

        return true;
    }

    public void espresso() {
        water -= Espresso.water;
        coffeeBeans -= Espresso.coffeeBeans;
        disposableCups -= Espresso.disposableCups;
        money += Espresso.money;
    }

    public void latte() {
        water -= Latte.water;
        milk -= Latte.milk;
        coffeeBeans -= Latte.coffeeBeans;
        disposableCups -= Latte.disposableCups;
        money += Latte.money;
    }

    public void cappuccino() {
        water -= Cappuccino.water;
        milk -= Cappuccino.milk;
        coffeeBeans -= Cappuccino.coffeeBeans;
        disposableCups -= Cappuccino.disposableCups;
        money += Cappuccino.money;
    }

    public void buy(Scanner sc) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");

        switch (sc.next()) {
            case "1":
                if (enoughResourcesLeft(Espresso.water, Espresso.milk, Espresso.coffeeBeans, Espresso.disposableCups)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    espresso();
                }
                break;
            case "2":
                if (enoughResourcesLeft(Latte.water, Latte.milk, Latte.coffeeBeans, Latte.disposableCups)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    latte();
                }
                break;
            case "3":
                if (enoughResourcesLeft(Cappuccino.water, Cappuccino.milk, Cappuccino.coffeeBeans, Cappuccino.disposableCups)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    cappuccino();
                }
                break;
            case "back":
            default:
                break;
        }

    }

    public void fill(Scanner sc) {
        System.out.println("Write how many ml of water the coffee machine has:");
        int waterAmount = sc.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        int milkAmount = sc.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int coffeeBeansAmount = sc.nextInt();
        System.out.println("Write how many disposable cups you want to add: ");
        int disposableCupsAmount = sc.nextInt();

        water += waterAmount;
        milk += milkAmount;
        coffeeBeans += coffeeBeansAmount;
        disposableCups += disposableCupsAmount;
    }

    public void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    public void writeCurrentInventory() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffeeBeans + " g of coffee beans");
        System.out.println(disposableCups + " disposable cups");
        System.out.println("$" + money + " of money");
    }
}
