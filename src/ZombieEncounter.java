public class ZombieEncounter implements Encounter
{
    private Player player;

    @Override
    public EncounterResult encounter(World world, Player playerInEncounter)
    {
        player = playerInEncounter;
        player.tell("You encountered a zombie.");
        EncounterResult result = new EncounterResult();
        result.playerIsAlive = nextTurn(world);
        return result;
    }

    public boolean nextTurn(World world)
    {
        String attackOrRun = player.ask("Do you want to attack or run?");

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
            player.tell("The zombie didn't notice you");
            return true;
        }
        else
        {
            player.tell("The Zombie Killed you");
            return false;
        }
    }

    private boolean playerRuns(World world) {
        int roll = Dice.d20();
        if (roll <= 2)
        {
            player.tell("The zombie killed you");
            return false;
        }
        else if (roll < 12)
        {
            player.tell("You escaped the zombie");
            return true;
        }
        else
        {
            player.tell("Its right behind you!");
            return nextTurn(world);
        }
    }

    private boolean playerAttacks(World world)
    {
        int roll = Dice.d20();
        if (roll <= 2)
        {
            player.tell("The zombie bit you");
            player.hitpoints--;
            if (player.hitpoints <= 0)
                return false;
            else
                return nextTurn(world);
        }
        else if (roll < 12)
        {
            player.tell("You killed the zombie");
            return true;
        }
        else
        {
            player.tell("You both miss. The fight continue!");
            return nextTurn(world);
        }
    }
}
