import java.util.Scanner;

public class CombatSimulator {
    private static CombatManager manager;
    private static DiceRoller diceRoller;
    
    public static void main(String[] args){
        
        manager = new CombatManager();
        manager.loadCombatants("availableCombatants.txt","defeatedCombatants.txt");

        makeChoice();

    }

    public static void makeChoice() {
        Scanner kb = new Scanner(System.in);
        int choice;
        do{
            mainMenu();
            choice = InputValidator.getValidInteger("Enter your choice (1-5): ", 1, 5);
            System.out.println("You chose option " + choice);
            menuChoice(choice);
            System.out.print("\u001B[32m" + "Press enter to continue..."+ "\u001B[0m");
            kb.nextLine();
            clearConsole();
        } while (choice != 5);
    }

    public static void clearConsole() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
    public static void menuChoice(int choice){
        switch (choice){
            case 1:
                String name = InputValidator.getNonEmptyString("Enter combatant name: ");
                int hp = InputValidator.getValidInteger("Enter HP: ", 1, 100);
                int initiative = InputValidator.getValidInteger("Enter Initiative: ", 1, 20);
                String type = InputValidator.getNonEmptyString("Enter type (Monster, NPC, etc.): ");
                manager.addCombatant(name, hp, initiative, type);
                System.out.println("Combatant added!");
                break;
            case 2:
                manager.startCombat();
                break;
            case 3:
                manager.listCombatants();
                manager.listDefeatedCombatants();
                break;
            case 4:
                manager.viewTurnOrder();
                break;
            case 5:
                manager.saveCombatants("availableCombatants.txt","defeatedCombatants.txt");
                System.out.println("Combatants have been saved for later. Goodbye!");
                return;
        }
    }
    public static void mainMenu() {
        System.out.println(ANSIColors.CYAN + "✦ . 　⁺ 　 . ✦ . 　⁺ 　 . ✦");
        System.out.println("Welcome to the Combat Simulator!");
        System.out.println("✦ . 　⁺ 　 . ✦ . 　⁺ 　 . ✦" + ANSIColors.RESET);
        System.out.println();
        System.out.println("1. Add combatant");
        System.out.println("2. Start combat");
        System.out.println("3. View combatants");
        System.out.println("4. View turn order");
        System.out.println("5. Exit");        
    }
}