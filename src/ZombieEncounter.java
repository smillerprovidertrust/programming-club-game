public class ZombieEncounter implements Encounter
{

    @Override
    public void beginEncounter()
    {
        Player.tell("You encountered a zombie.");
    }

    @Override
    public boolean encounter(World world)
    {
        String attackOrRun = Player.ask("Do you want to attack or run?");

        if (attackOrRun.equalsIgnoreCase("attack"))
        {
            return playerAttacks(world);
        }
        else if (attackOrRun.equalsIgnoreCase("run"))
        {
            return playerRuns(world);
        }
        else
        {
            return playerFreezes();
        }

    }

    private boolean playerFreezes() {
        int roll = Dice.d20();
        if (roll >= 20)
        {
            Player.tell("The zombie didn't notice you");
            return true;
        }
        else
        {
            Player.tell("The Zombie Killed you");
            return false;
        }
    }

    private boolean playerRuns(World world) {
        int roll = Dice.d20();
        if (roll <= 2)
        {
            Player.tell("The zombie killed you");
            return false;
        }
        else if (roll < 12)
        {
            Player.tell("You escaped the zombie");
            return true;
        }
        else
        {
            Player.tell("Its right behind you!");
            return encounter(world);
        }
    }

    private boolean playerAttacks(World world)
    {
        int roll = Dice.d20();
        if (roll <= 2)
        {
            Player.tell("The zombie killed you");
            return false;
        }
        else if (roll < 12)
        {
            Player.tell("You killed the zombie");
            return true;
        }
        else
        {
            Player.tell("You both miss. The fight continue!");
            return encounter(world);
        }
    }
}
