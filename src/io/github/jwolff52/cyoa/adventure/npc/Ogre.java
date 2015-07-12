package io.github.jwolff52.cyoa.adventure.npc;
/*@author Timothy*/
public class Ogre extends Monster{
    private static final int MULTIPLIER=20;
    private static final int MAX=200;
    private static String[] attackName;
    public Ogre(String n, int h, int dmg, int d){
        super(n,h,dmg,d);
        int gold=Math.abs((int) ((MAX-(MULTIPLIER*h*d)/MAX)-(MAX-(Math.random()*(dmg%20)*d*MULTIPLIER)/MAX)));
        attackName=new String[]{"Smash", "Kick", "Throw"};
        super.setGold(gold);
    }
    public String getAttack(){
        return attackName[(int)(Math.random()*(attackName.length+1))];
    }
}
