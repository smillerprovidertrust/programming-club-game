public class RandomGuyEncounter implements Encounter
{
    @Override
    public EncounterResult encounter(World world, Player player) {
        EncounterResult result = new EncounterResult();
        player.tell("Hello there, stranger");
        result.playerIsAlive = true;
        return result;
    }
}
