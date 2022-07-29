import java.util.ArrayList;
import java.util.List;

public class NormalDayEncounter implements Encounter
{
    private Player player;

    @Override
    public EncounterResult encounter(World world, Player playerInEncounter)
    {
        player = playerInEncounter;
        EncounterResult result = new EncounterResult();
        String walkOrRest = player.ask("Do you want to walk or rest?");

        if (walkOrRest.equalsIgnoreCase("walk"))
        {
            result.newEncountersThatResultedFromThisEncounter = getEncountersForTheDay(world);
            result.playerIsAlive = true;
        }
        else
        {
            player.tell("You rest all day and you feel great");
            world.daysRemaining++;
            result.playerIsAlive = true;
        }

        return result;
    }

    private List<Encounter> getEncountersForTheDay(World world)
    {
        List<Encounter> encountersToResolve = new ArrayList<>();
        int result = Dice.d20();

        if (result == 1)
        {
            encountersToResolve.add(new ZombieEncounter());
        }
        else if (result <=5)
        {
            encountersToResolve.add(new SurvivorsEncounter());
        }
        else if (result <=15)
        {
            encountersToResolve.add(new RandomGuyEncounter());
        }

        return encountersToResolve;
    }
}
