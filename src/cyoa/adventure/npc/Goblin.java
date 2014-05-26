package cyoa.adventure.npc;
/*@author Timothy*/
public class Goblin extends Monster{
    private static final int MULTIPLIER=20;
    private static final int MAX=100;
    public Goblin(String n, int h, int dmg, int d){
        super(n,h,dmg,d);
        int gold=Math.abs((int) ((MAX-(MULTIPLIER*h*d)/MAX)-(MAX-(Math.random()*(dmg%20)*d*MULTIPLIER)/MAX)));
        super.setGold(gold);
    }
}
