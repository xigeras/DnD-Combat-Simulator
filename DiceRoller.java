import java.util.Random;

public class DiceRoller {
    Random rand = new Random();
    //rolls a single die with the specified number of sides
    public int rollDie(int sides)
        rand.nextInt(sides) + 1;

    // rolls multiple die and returns the total
    public int rollMultipleDie(int sides, int count) {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += rollDie(sides);
        }
        return total;
    }
}