public class NormalDayEncounter implements Encounter
{
    @Override
    public boolean encounter(World world) {
        return true;
    }

    @Override
    public void beginEncounter() {
        Player.tell("You walk a long way and make great progress");
    }
}
