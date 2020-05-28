package com.example.coffeeclicker;

public class Coffee
{
    static long coffee;

    long coffeePerTap = 1;
    long coffeePerSecond = 0;

    public Coffee()
    {
        coffee = 1;
    }

    public void Set(long amount)
    {
        coffee = amount;
    }

    public void Add(long amount)
    {
        coffee += amount;
    }

    public void Subtract(long amount)
    {
        coffee -= amount;
    }

    public void AddPerTap(long amount)
    {
        coffeePerTap += amount;
    }

    public void AddPerSecond(long amount)
    {
        coffeePerSecond += amount;
    }

    public void MultiplyPerTap(long amount)
    {
        coffeePerTap *= amount;
    }

    public void MultiplyPerSecond(long amount)
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

    public long GetCoffee()
    {
        return coffee;
    }
}
