package com.example.demo;

abstract class Pizza {
    protected double price;

    public double getPrice() {
        return price;
    }
}

abstract class PizzaDecorator extends Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public double getPrice() {
        return pizza.getPrice();
    }
}

class PepperoniTopping extends PizzaDecorator {
    public PepperoniTopping(Pizza pizza) {
        super(pizza);
        this.price = 2.50;
    }

    @Override
    public double getPrice() {
        return super.getPrice() + price;
    }
}

class MushroomTopping extends PizzaDecorator {
    public MushroomTopping(Pizza pizza) {
        super(pizza);
        this.price = 1.75;
    }

    @Override
    public double getPrice() {
        return super.getPrice() + price;
    }
}

public class pizza{
    public static void main(String[] args) {
        Pizza basicPizza = new Pizza() {
            {
                price = 8.0;
            }
        };
        Pizza pepperoniMushroomPizza = new MushroomTopping(new PepperoniTopping(basicPizza));
        System.out.println("Price of Pepperoni & Mushroom Pizza: $" + pepperoniMushroomPizza.getPrice());
    }
}
