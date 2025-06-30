import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
public class CombatManager {
    private ArrayList<Combatant> activeCombatants = new ArrayList<>();
    private Combatant[] defeatedCombatants = new Combatant[0];
    public String name;
    public int HP;
    public int initiative;

    public void addCombatant(Combatant combatant) {
        activeCombatants.add(combatant);
    }

    public void removeCombatant() throws IndexOutOfBoundsException {
        if(availableCombatants.isEmpty()) {
            System.out.println("No combatants to remove.");
            return;
        }
    }

    public getCombatantByName(String name) {
        for (Combatant combatant : activeCombatants) {
            if (combatant.name.equals(name)) {
                return combatant;
            }
        }
        return null; // Return null if no combatant found
    }

    public void listCombatants() {
        for (Combatant combatant : activeCombatants) {
            System.out.println("Name: " + combatant.name + ", HP: " + combatant.HP + ", Initiative: " + combatant.initiative);
        }
    }

    public void listDefeatedCombatants() {
        for (Combatant combatant : defeatedCombatants) {
            System.out.println("Name: " + combatant.name + ", HP: " + combatant.HP + ", Initiative: " + combatant.initiative);
        }
    }

}