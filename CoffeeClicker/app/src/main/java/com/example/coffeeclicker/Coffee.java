package com.example.coffeeclicker;

public class Coffee
{
    static int coffee;

    int coffeePerTap = 1;
    int coffeePerSecond = 0;

    public Coffee()
    {
        coffee = 1;
    }

    public void Set(int amount)
    {
        coffee = amount;
    }

    public void Add(int amount)
    {
        coffee += amount;
    }

    public void Subtract(int amount)
    {
        coffee -= amount;
    }

    public void AddPerTap(int amount)
    {
        coffeePerTap += amount;
    }

    public void AddPerSecond(int amount)
    {
        coffeePerSecond += amount;
    }

    public void MultiplyPerTap(int amount)
    {
        coffeePerTap *= amount;
    }

    public void MultiplyPerSecond(int amount)
    {
        coffeePerSecond *= amount;
    }

    public void Tap()
    {
        coffee += coffeePerTap;
    }

    public void OverTime()
    {
        coffee += (coffeePerSecond);
    }

    public int GetCoffee()
    {
        return coffee;
    }
}
