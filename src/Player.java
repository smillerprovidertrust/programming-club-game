import java.net.InetAddress;
import java.util.Scanner;

public class Player
{
    public int hitpoints = 10;

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
}
