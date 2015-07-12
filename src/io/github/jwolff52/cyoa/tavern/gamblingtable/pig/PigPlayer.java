package io.github.jwolff52.cyoa.tavern.gamblingtable.pig;
import java.util.Scanner;
public class PigPlayer {
    private int total, turnCount;
    private String name;
    public PigPlayer(String n){
        name=n;
        total=0;
        turnCount=0;
    }
    public void turn(){
        Scanner scan=new Scanner(System.in);
        int turnScore=0, rollCount=0;
        int[] rolls=new int[50];
        String answer="";
        boolean stay=false, rolled1=false;
        turnCount++;
        rolls[rollCount]=Dice.sixDie();
        turnScore=rolls[0];
        rollCount++;
        if(rolls[rollCount-1]==1){
            System.out.println("Sorry You Rolled 1");
        }else{
            System.out.println("You rolled a "+rolls[rollCount-1]+"\nYour score for this turn is "+turnScore+"\nYour total score would be "+(total+turnScore));
            System.out.println("Would you like to stay?");
            answer=scan.next();
            if(answer.equalsIgnoreCase("yes")){
                stay=true;
            }
            while(!stay){
                rolls[rollCount]=Dice.sixDie();
                turnScore+=rolls[rollCount];
                rollCount++;
                if(rolls[rollCount-1]==1){
                System.out.println("Sorry You Rolled 1");
                rolled1=true;
		break;
                }else if(total+turnScore>=100){
                    break;
                }else{
                    System.out.println("You rolled a "+rolls[rollCount-1]+"\nYour score for this turn is "+turnScore+"\nYour total score would be "+(total+turnScore));
                    System.out.println("Would you like to stay?");
                    answer=scan.next();
                    if(answer.equalsIgnoreCase("yes")){
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