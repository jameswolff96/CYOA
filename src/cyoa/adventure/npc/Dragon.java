package cyoa.adventure.npc;
/*@author Timothy*/
public class Dragon extends Monster{
    private static final int MULTIPLIER=75;
    private static final int MAX=500;
    private static String[] attackName;
    public Dragon(String n, int h, int dmg, int d){
        super(n,h,dmg,d);
        int gold=Math.abs((int) ((MAX-(MULTIPLIER*h*d)/MAX)-(MAX-(Math.random()*(dmg%20)*d*MULTIPLIER)/MAX)));
        attackName=new String[]{"Fire Breath", "Claw", "Tail Whip"};
        super.setGold(gold);
    }
    public String getAttack(){
        return attackName[(int)(Math.random()*(attackName.length+1))];
    }
}
