package CYOA.Tavern.GamblingTable.Pig;

import java.util.Random;

public class PigDealer {
    private int total, turnCount;
    private String name;
    public PigDealer(){
        total=0;
        turnCount=0;
    }
    public void turn(){
        int turnScore=0, rollCount=0;
        int[] rolls=new int[50];
        int answer;
        boolean stay=false, rolled1=false;
        turnCount++;
        rolls[rollCount]=Dice.sixDie();
        turnScore=rolls[0];
        rollCount++;
        if(rolls[rollCount-1]==1){
            System.out.println("The Dealer Rolled 1");
        }else{
            System.out.println("The dealer rolled a "+rolls[rollCount-1]);
            Random dealer=new Random();
            answer=dealer.nextInt(10);
            if(answer>4){
                stay=true;
                System.out.println("The dealer stayed!!");
            }
            while(!stay){
                rolls[rollCount]=Dice.sixDie();
                turnScore+=rolls[rollCount];
                rollCount++;
                if(rolls[rollCount-1]==1){
                System.out.println("The Dealer Rolled 1");
                rolled1=true;
		break;
                }else if(total+turnScore>=100){
                    break;
                }else{
                    System.out.println("You rolled a "+rolls[rollCount-1]);
                    answer=dealer.nextInt(10);
                    if(answer>4){
                        stay=true;
                    }
                }
            }
            if(!rolled1){
                total+=turnScore;
            }
        }
    }
    public String getName(){
        return name;
    }
    public int getTotal(){
        return total;
    }
}
