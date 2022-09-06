package player;

import java.util.Scanner;

public class Player
{
    public int hitpoints = 10;
    public final Backpack backpack;
    private int trauma;

    public Player()
    {
        backpack = new Backpack();
        trauma = 0;
    }

    public String ask(String question)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(question);
        String response = sc.next();
        return response;
    }

    public void tell(String statement)
    {
        System.out.println(statement);
    }

    public boolean isAlive()
    {
        return hitpoints > 0;
    }

    public void increaseTrauma()
    {
        trauma++;
    }
}
