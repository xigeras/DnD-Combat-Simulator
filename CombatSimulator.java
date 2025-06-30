import java.util.Scanner;

public class CombatSimulator {
    private static CombatManager manager;
    private static DiceRoller diceRoller;
    
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        manager = new CombatManager();
        manager.loadCombatants("availableCombatants.txt","defeatedCombatants.txt");

        //makeChoice();

    }

    public static void mainMenu() {
        System.out.println("Welcome to the Combat Simulator!");
        System.out.println("1. Add combatant");
        System.out.println("2. Roll dice");
        System.out.println("3. Start combat / view turn order");
        System.out.println("4. Apply damage");
        System.out.println("5. View combatants");
        System.out.println("6. Exit");        
    }
}