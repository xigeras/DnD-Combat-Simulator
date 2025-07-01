# DnD-Combat-Simulator

## A project description.

This project is a Dungeons & Dragons Combat Simulator that allows users to manage combatants, track initiative, apply damage, and persist combat data between sessions. This would be a Dungeon Master's tool, not something for players.

It demonstrates the use of Java arrays and ArrayLists, recursive input validation, exception handling, and file persistence. This program is **menu-driven** and runs in the terminal.

## Instructions for running the program.

1. **Clone the repository** and open it in GitHub Codespaces or preferred Java IDE.
2. **Compile the program** by using `javac *.java`
3. **Run the main class** by using `java CombatSimulator`
4. **Follow the on-screen menu** to add combatants, start combat, view combatants, view turn order, or exit and save.

## Examples of program usage.

```
✦ . 　⁺ 　 . ✦ . 　⁺ 　 . ✦
Welcome to the Combat Simulator!
✦ . 　⁺ 　 . ✦ . 　⁺ 　 . ✦

1. Add combatant
2. Start combat
3. View combatants
4. View turn order
5. Exit

```

- **Adding a combatant:**
  
  ```
  1. Add combatant
  Enter combatant name: Goblin
  Enter HP: 7
  Enter Initiative: 15
  Enter type (Monster, NPC, etc.): Monster
  Combatant added!
  ```

- **Starting combat:**
  ```
  2. Start combat
  Current turn: Goblin (Initiative: 15, HP: 7)
  Enter damage die (e.g., 6 for d6, 8 for d8 and so on): 6
  Rolled a d6 for 4 damage!
  Goblin now has 2 HP.
  ```

- **Viewing combatants:**
  ```
  3. View combatants
  Active Combatants:
  1. Goblin (HP: 3, Initiative: 15, Type: Monster)
  Defeated Combatants:
  (none)
  ```

- **Viewing turn order:**
  ```
  4. View turn order
  Current Turn Order:
  1. Goblin (Initiative: 15, HP: 3)
  ```

- **Exiting and saving:**
  ```
  5. Exit
  Combatants have been saved for later. Goodbye!
  ```

## Known limitations or future improvements.
 - Only the most basic combat features are implemented ; advanced D&D rules are not supported.
 - The UI is text-based; in the future perhaps do a graphical interface or make it into a game.
 - Only one combat encounter is tracked at a time.
 - More detailed combat logs and undo/redo features could be added in the future.
 - Perhaps in the future it could be used to manage D&D Sessions.
 - Maybe a saving and loading process for different D&D sessions.
