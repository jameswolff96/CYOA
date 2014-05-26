package cyoa.adventure;

import java.util.ArrayList;

/**
 *
 * @author Timothy
 */
public class Blade extends Weapon{
    private String requiredSkill;
    private int requiredLvl, skillLvl;
    public Blade(String name, int dmg, int lvl, int maxLvl){
        super(name,"Blade", dmg, lvl, maxLvl);
        requiredSkill="Strength";
        requiredLvl=maxLvl/2;
        skillLvl=(maxLvl*2)+(dmg/2)-4;
    }
    public Blade(String name, int dmg, int lvl, int exp, int maxLvl){
        super(name,"Blade", dmg, lvl, exp, maxLvl);
        requiredSkill="Strength";
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
    @Override
    public ArrayList<String> toStringArray(){
        ArrayList<String> strings=super.toStringArray();
        strings.add(1, requiredSkill);
        return strings;
    }
}
