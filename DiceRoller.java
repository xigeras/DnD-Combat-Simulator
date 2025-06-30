import java.util.Random;

public class DiceRoller {
    Random rand = new Random();
    private static final int[] VALID_SIDES = {4, 6, 8, 10, 12, 20, 100};
    //rolls a single die with the specified number of sides
    public int rollDie(int sides)
        if (!isValidDie(sides)) {
            throw new IllegalArgumentException("Invalid die sides: " + sides);
        }
        return (int) (Math.random() * sides) + 1;

    // rolls multiple die and returns the total
    public int rollMultipleDie(int sides, int count) {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += rollDie(sides);
        }
        return total;
    }
    public static boolean isValidDie(int sides) {
        for (int valid : VALID_SIDES) {
            if (sides == valid) {
                return true;
            }
        }
        return false;
    }
    public static String getValidDiceList() {
        return "Valid dice: d4, d6, d8, d10, d12, d20, d100";
    }
}