package CYOA.Adventure;

import java.util.ArrayList;

public class Bow extends Weapon{
    private String requiredSkill;
    private int requiredLvl, skillLvl;
    public Bow(String name, int dmg, int lvl, int maxLvl){
        super(name,"Bow", dmg, lvl, maxLvl);
        requiredSkill="Dexterity";
        requiredLvl=maxLvl/2;
        skillLvl=(maxLvl*2)+(dmg/2)-4;
    }
    public Bow(String name, int dmg, int lvl, int exp, int maxLvl){
        super(name,"Bow", dmg, lvl, exp, maxLvl);
        requiredSkill="Dexterity";
    }
    public String getSkill(){
        return requiredSkill;
    }
    public int getRequiredLvl(){
        return requiredLvl;
    }
    public int getSkillLvl(){
        return skillLvl;
    }
    public ArrayList<String> toStringArray(){
        ArrayList<String> strings=super.toStringArray();
        strings.add(1, requiredSkill);
        return strings;
    }
}
