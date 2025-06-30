import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.Scanner;

public class CombatManager {
    private ArrayList<Combatant> activeCombatants = new ArrayList<>();
    private Combatant[] defeatedCombatants = new Combatant[0];
    private DiceRoller diceRoller = new DiceRoller();
    

    public void addCombatant(String name, int hp, int initiative, String type) {
        Combatant combatant = new Combatant(name, hp, initiative, type);
        activeCombatants.add(combatant);
    }

    public void addDefeatedCombatant(Combatant combatant) {
        defeatedCombatants = Arrays.copyOf(defeatedCombatants, defeatedCombatants.length + 1);
        defeatedCombatants[defeatedCombatants.length - 1] = combatant;
    }
    public Combatant getCombatantByName(String name) {
        for (Combatant combatant : activeCombatants) {
            if (combatant.getName().equals(name)) {
                return combatant;
            }
        }
        return null; // Return null if no combatant found
    }

    public void listCombatants() {
        System.out.println("Active Combatants:");
        if (activeCombatants.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (int i = 0; i < activeCombatants.size(); i++) {
                Combatant c = activeCombatants.get(i);
                System.out.println((i + 1) + ". " + c.getName() + " (HP: " + c.getHp() + ", Initiative: " + c.getInitiative() + ", Type: " + c.getType() + ")");
            }
        }
    }

    public void listDefeatedCombatants() {
        System.out.println("Defeated Combatants:");
        if (defeatedCombatants.length == 0) {
            System.out.println("  (none)");
        } else {
            for (int i = 0; i < defeatedCombatants.length; i++) {
                Combatant c = defeatedCombatants[i];
                System.out.println((i + 1) + ". " + c.getName() + " (HP: " + c.getHp() + ", Initiative: " + c.getInitiative() + ", Type: " + c.getType() + ")");
            }
        }
    }
    public void saveCombatants(String availableFile, String defeatedFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(availableFile))) {
            for (Combatant combatant : activeCombatants) {
                writer.write(combatant.getName() + "," + combatant.getHp() + "," + combatant.getInitiative());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving combatants: " + e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(defeatedFile))) {
            for (Combatant combatant : defeatedCombatants) {
                writer.write(combatant.getName() + "," + combatant.getHp() + "," + combatant.getInitiative());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving defeated combatants: " + e);
        }
    }

    public void loadCombatants(String availableFile, String defeatedFile) {
        activeCombatants.clear();
        defeatedCombatants = new Combatant[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(availableFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    Combatant combatant = new Combatant(
                        parts[0],
                        Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]),
                        parts[3]
                    );
                    activeCombatants.add(combatant);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading combatants: " + e);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(defeatedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    Combatant combatant = new Combatant (
                        parts[0],
                        Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]),
                        parts[3]
                    );
                    addDefeatedCombatant(combatant);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading defeated combatants: " + e);
        }
    }

    // COMBAT FLOW!
    public void startCombat() {
        Scanner scanner = new Scanner(System.in);
        if (activeCombatants.isEmpty()) {
            System.out.println("No combatants available to start combat.");
            return;
        }

        //sort by initiative
        activeCombatants.sort((a, b) -> b.getInitiative() - a.getInitiative());

        boolean continueCombat = true;
        int turnIndex = 0;

        while (continueCombat && !activeCombatants.isEmpty()) {
            Combatant currentCombatant = activeCombatants.get(turnIndex);
            System.out.println("Current turn: " + currentCombatant.getName());

            System.out.println("Enter target name: ");
            String targetName = scanner.nextLine();
            Combatant target = getCombatantByName(targetName);

            if (target != null) {
                System.out.print("Enter damage die (e.g., 6 for d6, 8 for d8 and so on): ");
                int sides = InputValidator.getValidDiceSides("Enter damage die: ");

                if (!DiceRoller.isValidDie(sides)) {
                    System.out.println("Invalid die type. Try again.");
                    continue;
                }

                int damage = diceRoller.rollDie(sides);
                System.out.println("Rolled a d" + sides + " for " + damage + " damage!");
                System.out.println(ANSIColors.RED + "YEEEEOUCH!" + ANSIColors.RESET);
                target.setHp(target.getHp() - damage);
                System.out.println(target.getName() + " now has " + target.getHp() + " HP.");

                if (target.getHp() <= 0) {
                    System.out.println(target.getName() + " has been defeated!");
                    moveToDefeated(target);
                }
            } else {
                System.out.println("Target not found.");
            }
            System.out.println("Continue combat? (y/n): ");
            String response = scanner.nextLine();
            continueCombat = response.equalsIgnoreCase("y");

            if (!activeCombatants.isEmpty()) {
                turnIndex = (turnIndex + 1) % activeCombatants.size();
            }
        }
        System.out.println("Combat has ended.");
    }

    public void viewTurnOrder() {
    if (activeCombatants.isEmpty()) {
        System.out.println("No combatants in the turn order.");
        return;
    }
    // Sort by initiative (highest first)
    ArrayList<Combatant> sorted = new ArrayList<>(activeCombatants);
    sorted.sort((a, b) -> b.getInitiative() - a.getInitiative());
    System.out.println("Current Turn Order:");
    for (int i = 0; i < sorted.size(); i++) {
        Combatant c = sorted.get(i);
        System.out.println((i + 1) + ". " + c.getName() + " (Initiative: " + c.getInitiative() + ", HP: " + c.getHp() + ")");
    }
}

    public void moveToDefeated(Combatant combatant) {
        activeCombatants.remove(combatant);
        
        Combatant[] newDefeated = new Combatant[defeatedCombatants.length + 1];
        for (int i = 0; i < defeatedCombatants.length; i++) {
            newDefeated[i] = defeatedCombatants[i];
        }
        newDefeated[defeatedCombatants.length] = combatant;
        defeatedCombatants = newDefeated;
    }
}