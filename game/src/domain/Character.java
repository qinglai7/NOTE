package domain;

public class Character {
    private String name;
    private int Hp;
    private int maxHp;
    private int attack;
    private int defense;
    public Character() {

    }

    public Character(String name,  int maxHp, int attack, int defense) {
        this.name = name;
        Hp = maxHp;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return Hp;
    }

    public void setHp(int hp) {
        Hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
