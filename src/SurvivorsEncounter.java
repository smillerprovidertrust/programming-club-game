public class SurvivorsEncounter implements Encounter
{
    public void beginEncounter()
    {
        Player.tell ("You come across other survivors");
    }

    public boolean encounter(World world) {
        String attackOrHelp = Player.ask("Do you want to attack or help?");

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
            return true;
        }

    }

    private boolean processHelp(World world) {
        int roll = Dice.d20();
        if (roll <= 2)
        {
            Player.tell("The Survivors tricked and killed you");
            return false;
        }
        else if (roll <= 15)
        {
            Player.tell("The Survivors need medicine");
            return processMedicine(world);
        }
        else
        {
            Player.tell("The survivors have a a car");
            world.daysRemaining--;
            return true;
        }

    }

    private boolean processMedicine(World world) {
        int roll = Dice.d20();
        if (roll <= 2)
        {
            Player.tell("The Survivors disease infects you");
            return false;
        }
        else if (roll < 8)
        {
            Player.tell("The Survivors recover but are weak");
            int daysRemaining = world.lastDay + world.daysRemaining;

            world.lastDay+= daysRemaining;
            return true;
        }
        else
        {
            Player.tell("The survivors recover completely");
            return true;
        }

    }


    private boolean processAttack2(World world) {
        int roll = Dice.d20();
        if (roll <= 2)
        {
            Player.tell("The Survivors killed you");
            return false;
        }
        else if (roll < 8)
        {
            Player.tell("The Survivors Surrender");
            return processCaptives();
        }
        else if (roll < 15)
        {
            Player.tell("You kill the Survivors");
            return true;
        }
        else
        {
            Player.tell("You both miss. The fight continue!");
            return encounter(world);
        }
    }
    private boolean processCaptives()
    {
        String roborJoin = Player.ask("Do you want to rob or join?");

        if (roborJoin.equalsIgnoreCase("rob"))
        {
            Player.tell ("You gain money and lose your integrity");
            return true;
        }
        else if (roborJoin.equalsIgnoreCase("join"))
        {
            Player.tell ("Yay new friends");
            return true;
        }
        else
        {
            Player.tell ("You must choose rob or join");
            return processCaptives();
        }

    }
}
