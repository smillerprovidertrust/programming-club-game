import java.util.Scanner;

public class Player
{
    public static String ask(String question)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(question);
        String response = sc.next();
        return response;
    }

    public static void tell(String statement)
    {
        System.out.println(statement);
    }
}
