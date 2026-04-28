package domain;

import java.util.ArrayList;

public class HeroCharacter extends Character {
    ArrayList<Skill> skile;

    public HeroCharacter() {

    }

    public HeroCharacter(String name, int maxHp, int attack, int defense, ArrayList<Skill> skile) {
        super(name, maxHp, attack, defense);
        this.skile = skile;
    }
}
