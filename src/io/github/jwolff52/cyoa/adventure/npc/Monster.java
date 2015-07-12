package io.github.jwolff52.cyoa.adventure.npc;
/*@author Timothy*/
public class Monster {
    private int health, damage, difficulty, gold;
    private String name;
    public Monster(String n, int h, int dmg, int d){
        name=n;
        health=h;
        damage=dmg;
        difficulty=d;
    }
    protected void setGold(int g){
        gold=g;
    }
    public int getHealth(){
        return health;
    }
    public int attack(String atkName){
        System.out.println(name+" attacked you with "+atkName+"!!");
        int dmg;
        int multi=(int)(Math.random()*difficulty);
        if(multi>8){
            dmg=multi*damage;
        }else if(multi<2){
            dmg=0;
        }else{
            dmg=damage;
        }
        System.out.println("It dealt "+dmg+" to you!!");
        return dmg;
    }
    public String onDeath(){
        return "killed "+name+" and gained "+gold+" gold!";
    }
    public int getGold(){
        return gold;
    }
}
