public class Main {

    public static void main(String[] args)
    {
        boolean playerIsAlive = true;
        World world = new World();

        for (world.daysRemaining = 16; world.daysRemaining >=0 && playerIsAlive; world.daysRemaining--)
        {
            Player.tell("Days remaining " + world.daysRemaining);
            playerIsAlive = simulateDay(world);
        }

        if (playerIsAlive)
        {
            Player.tell("You win!");
        }
        else
        {
            Player.tell("You lost!");
        }
    }

    private static boolean simulateDay(World world)
    {
        int result = Dice.d20();

        if (result == 1)
        {
            Player.tell("You encountered a zombie.");
            return encounterZombie();

        }
        else if (result <=5)
        {
            Player.tell ("You come across other survivors");
            return encounterSurvivors(world);
        }

        return true;
    }

    private static boolean encounterSurvivors(World world) {
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

    private static boolean processHelp(World world) {
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

    private static boolean processMedicine(World world) {
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


    private static boolean processAttack2(World world) {
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
            return encounterSurvivors(world);
        }
    }
    private static boolean processCaptives()
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

    private static boolean encounterZombie()
    {
        String attackOrRun = Player.ask("Do you want to attack or run?");

        if (attackOrRun.equalsIgnoreCase("attack"))
        {
            return processAttack();
        }
        else if (attackOrRun.equalsIgnoreCase("run"))
        {
            return processRun();
        }
        else
        {
            return processDoNothing();
        }

    }

    private static boolean processDoNothing() {
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

    private static boolean processRun() {
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
            return encounterZombie();
        }
    }

    private static boolean processAttack()
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
            return encounterZombie();
        }
    }

}