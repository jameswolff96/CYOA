package CYOA.Adventure.NPC;
/*@author Timothy*/
public class Bandit extends Monster{
    private static final int MULTIPLIER=40;
    private static final int MAX=200;
    private static boolean rangedAttacks;
    private static String[][] attackNames;
    public Bandit(String n, int h, int dmg, int d){
        super(n,h,dmg,d);
        int gold=Math.abs((int) ((MAX-(MULTIPLIER*h*d)/MAX)-(MAX-(Math.random()*(dmg%20)*d*MULTIPLIER)/MAX)));
        attackNames=new String[][]{{"Slice", "Stab"},{"Stone Arrow", "Iron Arrow", "Lead Arrow"}};
        rangedAttacks=true;
        super.setGold(gold);
    }
    public String getAttack(){
        if(rangedAttacks){
            int x=(int)(Math.random()*10)+1;
            if(x<5){
                rangedAttacks=false;
            }
            return attackNames[1][(int)(Math.random()*(attackNames[1].length+1))];
        }
        return attackNames[0][(int)(Math.random()*(attackNames[0].length+1))];
    }
}
