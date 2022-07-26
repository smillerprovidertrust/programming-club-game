public class Miscellaneous
{
    public static void pause(int seconds)
    {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            //it seems unlikely that I could do anything about this
        }
    }
}
