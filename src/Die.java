import java.util.Random;

public class Die
{
    private Random random;
    public int numberOfSides;

    public Die(int numberOfSides)
    {
        this.numberOfSides = numberOfSides;
        random = new Random();
    }

    public int roll()
    {
        return random.nextInt(numberOfSides) + 1;
    }
}
