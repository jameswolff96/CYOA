package io.github.jwolff52.cyoa.tavern;
import java.util.Scanner;

import io.github.jwolff52.cyoa.ErrorCounter;
import io.github.jwolff52.cyoa.adventure.Adventurer;
import io.github.jwolff52.cyoa.tavern.gamblingtable.GamblingTable;
/*@author Timothy*/
public class Tavern {
    private static ErrorCounter errorCounts;
    private String tavernName;
    private GamblingTable gamblingTable;
    private Adventurer character;
    private TavernKeep tavernKeep;
    private Shop shop;
    private final int ROOMCOST=20;
    private Scanner scan;
    private String difficulty;
    public Tavern(String diff, Adventurer c){
        errorCounts=new ErrorCounter();
        tavernName="The Restfull Giant";
        tavernKeep=new TavernKeep();
        gamblingTable=new GamblingTable(c);
        shop=new Shop(diff, c.getLevel());
        scan=new Scanner(System.in);
        difficulty=diff;
        character=c;
    }
    public int getRoomCost(){
        return ROOMCOST;
    }
    public void mainRoom(){
        System.out.println("\n\n\nHello "+character.getName());
        System.out.println("Welcome to "+tavernName);
        System.out.println("This is where you can:\n");
        System.out.println("1) Plan your Adventures");
        System.out.println("2) Rest after a long journey");
        System.out.println("3) Try your luck at the Gambling table");
        System.out.println("4) Visit the shop");
        System.out.println("\nSo "+character.getName()+" what is your choice?");
        String answer="empty";
        while(answer.equalsIgnoreCase("empty")){
            try{
                answer=scan.nextLine();
            }catch(Exception e){
                errorCounts.incrementErrors();
            }
        }
        
        switch(answer.toLowerCase()){
            case "1":
            case "plan your adventures":
                break;
            case "2":
            case "rest after a long journer":
                rest();
                break;
            case "3":
            case "try your luck at the gambling table":
            case "gambling table":
                gamble();
                break;
            case "4":
            case "visit the shop":
            case "shop":
                shop();
                break;
            default:
                break;
            
        }
    }
            
    public void gamble(){
        System.out.println("Choose your game");
        System.out.println("1) BlackJack");
        System.out.println("2) Pig Dice");
        System.out.println("3) Zombie Dice");
        String answer="empty";
        while(answer.equalsIgnoreCase("empty")){
            try{
                answer=scan.nextLine();
            }catch(Exception e){
            }
        }
        answer=answer.toLowerCase();
        switch(answer){
            case "1":
            case "blackjack":
                gamblingTable.blackJack();
                break;
            case "2":
            case "pig dice":
                gamblingTable.pigDice();
                break;
            default:
                break;
        }
    }
    private void rest(){
        int health, maxHealth;
        System.out.println("The cost of the room is "+getRoomCost());
        System.out.println("Do you still wish to purchase a room?");
        String answer="empty";
        while(answer.equalsIgnoreCase("empty")){
            try{
                answer=scan.nextLine();
            }catch(Exception e){
            }
        }
        if(answer.equalsIgnoreCase("yes")||answer.equalsIgnoreCase("y")){
            character.addGold(-getRoomCost());
            health=character.getHealth();
            maxHealth=character.getMaxHealth();
            int regenRate=character.getHealthRegenRate("tavern");
            double hours=(maxHealth-health)/regenRate;
            System.out.println("You must rest for "+hours+" hours to fully heal");
            System.out.println("How many hours do you want to rest");
            int restHours=-1;
            while(restHours==-1){
                try{
                    restHours=scan.nextInt();
                }catch(Exception e){
                    errorCounts.incrementErrors();
                    System.out.println("Invalid entry");
                }
            }
            character.addHealth(regenRate*restHours);
        }
    }
    private void shop(){
        
    }
}
