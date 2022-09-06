import player.Player;

public interface Encounter
{
    /**
     * This processes the entire encounter
     *
     * @param world the world
     * @return true if the player is still alive, false if they are dead
     */
    EncounterResult encounter(World world, Player player);
}
