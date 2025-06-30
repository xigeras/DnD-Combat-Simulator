public class Combatant {
    public String name;
    public int hp;
    public int initiative;
    public String type;

    public Combatant(String name, int hp, int initiative, String type) {
        this.name = name;
        this.hp = hp;
        this.initiative = initiative;
        this.type = type;
    }

    // getter methods
    public String getName() {
        return name;
    }
    public int getHp() {
        return hp;
    }
    public int getInitiative() {
        return initiative;
    }
    public String getType() {
        return type;
    }
    // setter methods
    public void setName(String name) {
        this.name = name;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }
    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return name + " - HP: " + hp + ", Initiative: " + initiative + ", Type: " + type;
    }
    
    public boolean isDefeated() {
        return hp <= 0;
    }
}