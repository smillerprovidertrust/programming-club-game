public class Main {

    public static void main(String[] args)
    {
        boolean playerSurvived = true;
        World world = new World();

        for (world.daysRemaining = 16; world.daysRemaining >=0 && playerSurvived; world.daysRemaining--)
        {
            Player.tell("Days remaining " + world.daysRemaining);
            playerSurvived = tryToSurviveDay(world);
        }

        if (playerSurvived)
        {
            Player.tell("You win!");
        }
        else
        {
            Player.tell("You lost!");
        }
    }

    private static boolean tryToSurviveDay(World world)
    {
        Encounter encounter = getEncounterForTheDay(world);

        encounter.beginEncounter();
        return encounter.encounter(world);
    }

    private static Encounter getEncounterForTheDay(World world)
    {
        int result = Dice.d20();

        if (result == 1)
        {
            return new ZombieEncounter();
        }
        else if (result <=5)
        {
            return new SurvivorsEncounter();
        }
        else
        {
            return new NormalDayEncounter();
        }
    }


}