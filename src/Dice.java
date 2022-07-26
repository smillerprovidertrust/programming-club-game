import java.util.Random;

/**
 * FIXME: Add Doc
 *
 * @author Stephen Miller (smiller@providertrust.com)
 * @since 7/22/22
 */
public class Dice
{
    public static int d20()
    {
        return new Random().nextInt(20) + 1;
    }

    public static boolean didTheySucceed(int percentageChance)
    {
        int roll = new Random().nextInt(100) + 1;

        if ( roll <= percentageChance )
            return false;
        else
            return true;
    }
}
