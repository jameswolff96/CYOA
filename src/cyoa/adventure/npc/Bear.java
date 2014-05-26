package cyoa.adventure.npc;
/*@author Timothy*/
public class Bear extends Monster {
    private static final int MULTIPLIER=10;
    private static final int MAX=100;
    private static String[] attackName;
    public Bear(String n, int h, int dmg, int d){
        super(n,h,dmg,d);
        int gold=Math.abs((int) ((MAX-(MULTIPLIER*h*d)/MAX)-(MAX-(Math.random()*(dmg%20)*d*MULTIPLIER)/MAX)));
        attackName=new String[]{"Claw Swipe", "Bite", "Tackle"};
        super.setGold(gold);
    }
    public String getAttack(){
        return attackName[(int)(Math.random()*(attackName.length+1))];
    }
}
