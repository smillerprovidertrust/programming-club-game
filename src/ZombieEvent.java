public class ZombieEvent implements Event
{
    @Override
    public boolean simulate() {
        Player.tell("You almost stumble right into a zombie. As you realize he is there, he slowly turns and realizes you are there too.");
        return nextActionSequence();
    }

    public boolean nextActionSequence()
    {
        String action = Player.ask("What are you going to do!? (attack|run)");

        if (action.equalsIgnoreCase("attack"))
        {
            return processAttack();
        }
        else if (action.equalsIgnoreCase("run"))
        {
            return processRun();
        }
        else
        {
            return processFreeze(action);
        }
    }

    private boolean processFreeze(String action)
    {
        int roll = Dice.d20();

        switch (roll) {
            case 1:
            case 2:
            case 3:
            case 4:
                Player.tell("The zombie lunged for you while you were " + action + "ing, and with you pinned down it feasted on your brains");
                return false;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                Player.tell("You manage to escape!");
                return true;
            default:
                Player.tell("The zombie lunges for you and trips you up. You both get to your feet and you stare into each other's vacant eyes.");
                return nextActionSequence();
        }
    }

    private boolean processRun()
    {
        int roll = Dice.d20();

        if (roll < 5)
        {
            Player.tell("The zombie lunged for you and, before you could react, it was eating your brains");
            return false;
        }
        else if (roll < 16)
        {
            Player.tell("You manage to escape!");
            return true;
        }
        else
        {
            Player.tell("The zombie lunges for you and trips you up. You both get to your feet and you stare into each other's vacant eyes.");
            return nextActionSequence();
        }
    }

    private boolean processAttack()
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
            Player.tell("You both miss. The fight continues!");
            return nextActionSequence();
        }
    }
}
