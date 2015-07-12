package io.github.jwolff52.cyoa.adventure;

import java.util.ArrayList;

public class Weapon {
    private int damage, xp, level;
    private int[] xpLvls;
    private String myName, type;
    public Weapon(String name, String t, int dmg, int lvl, int maxLvl){
        myName=name;
        type=t;
        damage=dmg;
        level=lvl;
        xp=0;
        xpLvls=new int[maxLvl];
        xpLvls[0]=100;
        for(int x=1;x<xpLvls.length;x++){
            xpLvls[x]=(int) (xpLvls[x-1]*(.20));
        }
    }
    public Weapon(String name, String t, int dmg, int lvl, int exp, int maxLvl){
        myName=name;
        type=t;
        damage=dmg;
        level=lvl;
        xp=exp;
        xpLvls=new int[maxLvl+1];
        xpLvls[0]=100;
        for(int x=1;x<xpLvls.length;x++){
            xpLvls[x]=(int) (xpLvls[x-1]*(.20));
        }
    }
    public void addXP(int gained){
        xp+=gained;
        if(xp>=xpLvls[level]){
            lvlUp();
        }
    }
    private void lvlUp(){
        level++;
        damage+=damage/2;
        xp=0;
    }
    public String getName(){
        return myName;
    }
    public int getDamage(){
        return damage;
    }
    public int getLvl(){
        return level;
    }
    public int getXP(){
        return xp;
    }
    public ArrayList<String> toStringArray(){
        ArrayList<String> strings=new ArrayList<>(1);
        strings.add(myName);
        strings.add(damage+"");
        strings.add(level+"");
        strings.add(xp+"");
        strings.add((xpLvls.length-1)+"");
        return strings;
    }
    public String getType(){
        return type;
    }
    public int getPrice(){
        return (2*(level+xpLvls.length)+3*((xpLvls[level]-xp)+xpLvls[level])*damage);
    }
}
