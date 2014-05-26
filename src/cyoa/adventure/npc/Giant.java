package cyoa.adventure.npc;
/*@author Timothy*/
public class Giant extends Monster{
    private static final int MULTIPLIER=65;
    private static final int MAX=300;
    private static String[] attackName;
    public Giant(String n, int h, int dmg, int d){
        super(n,h,dmg,d);
        int gold=Math.abs((int) ((MAX-(MULTIPLIER*h*d)/MAX)-(MAX-(Math.random()*(dmg%20)*d*MULTIPLIER)/MAX)));
        attackName=new String[]{"Smash", "Stomp", "Wooden Club"};
        super.setGold(gold);
    }
    public String getAttack(){
        return attackName[(int)(Math.random()*(attackName.length+1))];
    }
}
