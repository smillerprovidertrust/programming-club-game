import player.ItemType;
import player.Player;

public class SurvivorsEncounter implements Encounter
{
    private Player player;

    public EncounterResult encounter(World world, Player playerInEncounter)
    {
        player = playerInEncounter;
        player.tell("You come across other survivors");

        EncounterResult result = new EncounterResult();
        result.playerIsAlive = nextTurn(world);

        return result;
    }

    public boolean nextTurn(World world)
    {
        String attackOrHelp = player.ask("Do you want to attack or help?");

        if (attackOrHelp.equalsIgnoreCase("attack"))
        {
            return processAttack2(world);
        }
        else if (attackOrHelp.equalsIgnoreCase("help"))
        {
            return processHelp(world);
        }
        else
        {
            player.tell("You " + attackOrHelp + " and leave the survivors behind.");
            return true;
        }

    }

    private boolean processHelp(World world) {
        int roll = Dice.d20();
        if (roll <= 2)
        {
            player.tell("The Survivors tricked and killed you");
            return false;
        }
        else if (roll <= 15)
        {
            player.tell("The Survivors need medicine");
            return processMedicine(world);
        }
        else
        {
            player.tell("The survivors have a a car");
            world.daysRemaining--;
            return true;
        }

    }

    private boolean processMedicine(World world) {

        if (player.backpack.contains(ItemType.MEDICINE)) {
            String attackOrHelp = player.ask("Do you want to give them medicine?");

            if (attackOrHelp.equalsIgnoreCase("yes")
                    || attackOrHelp.equalsIgnoreCase("sure")) {
                return processGivingMedicine(world);
            }
        }

        //No medicine
        player.tell("You don't have any medicine to give them. A survivor child dies in your arms.");
        player.increaseTrauma();
        return true;
    }

    private boolean processGivingMedicine(World world)
    {
        int roll = Dice.d20();
        if (roll <= 2)
        {
            player.tell("The Survivors disease infects you");
            return false;
        }
        else if (roll < 8)
        {
            player.tell("The Survivors recover but are weak");
            int daysRemaining = world.lastDay + world.daysRemaining;

            world.lastDay+= daysRemaining;
            return true;
        }
        else
        {
            player.tell("The survivors recover completely");
            return true;
        }

    }


    private boolean processAttack2(World world) {
        int roll = Dice.d20();
        if (roll <= 2)
        {
            player.tell("The Survivors killed you");
            return false;
        }
        else if (roll < 8)
        {
            player.tell("The Survivors Surrender");
            return processCaptives();
        }
        else if (roll < 15)
        {
            player.tell("You kill the Survivors");
            return true;
        }
        else
        {
            player.tell("You both miss. The fight continue!");
            return nextTurn(world);
        }
    }
    private boolean processCaptives()
    {
        String roborJoin = player.ask("Do you want to rob or join?");

        if (roborJoin.equalsIgnoreCase("rob"))
        {
            player.tell ("You gain money and lose your integrity");
            return true;
        }
        else if (roborJoin.equalsIgnoreCase("join"))
        {
            player.tell ("Yay new friends");
            return true;
        }
        else
        {
            player.tell ("You must choose rob or join");
            return processCaptives();
        }

    }
}
