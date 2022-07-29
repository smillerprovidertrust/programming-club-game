import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<Encounter> encountersToResolve = new ArrayList<>();

    public static void main(String[] args)
    {
        boolean playerSurvived = true;
        Player player = new Player();
        World world = new World();

        for (world.daysRemaining = 16; world.daysRemaining >=0 && player.isAlive(); world.daysRemaining--)
        {
            player.tell("Days remaining " + world.daysRemaining);
            playerSurvived = tryToSurviveDay(world, player);
        }

        if (playerSurvived)
        {
            player.tell("You win!");
        }
        else
        {
            player.tell("You lost!");
        }
    }

    private static boolean tryToSurviveDay(World world, Player player)
    {
        getEncounterForTheDay(world);

        while (!encountersToResolve.isEmpty())
        {
            Encounter encounter = encountersToResolve.remove(0);
            EncounterResult result = encounter.encounter(world, player);

            if (!result.playerIsAlive)
            {
                return false;
            }

            encountersToResolve.addAll(result.newEncountersThatResultedFromThisEncounter);
        }

        return true;
    }

    private static void getEncounterForTheDay(World world)
    {
        encountersToResolve.add(new NormalDayEncounter());
    }


}