package io.github.jwolff52.cyoa.adventure;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import io.github.jwolff52.cyoa.ErrorCounter;
import io.github.jwolff52.cyoa.Quest;
import io.github.jwolff52.cyoa.Question;
import io.github.jwolff52.cyoa.RandomGenerator;
import io.github.jwolff52.cyoa.adventure.npc.Monster;
import io.github.jwolff52.cyoa.io.TypeChanger;
public class Adventure{
    private RandomGenerator generator;
    private static ErrorCounter errorCounts;
    private Adventurer	player;
    private Quest quest;
    private ArrayList<Weapon> questInventory;
    private Scanner scan;
    private int gold;
    private TypeChanger tc;
    private Monster currentMonster;
    public Adventure(RandomGenerator g, Adventurer c, Quest q){
	generator=g;
	player=c;
        quest=q;
        gold=0;
        scan=new Scanner(System.in);
        tc=new TypeChanger();
        errorCounts=new ErrorCounter();
    }
    public void beginAdventure(){
        System.out.println("Choose 4 weapons to bring with you");
        weaponSelecter();   
        System.out.println("Choose an amount of gold to bring with you");
        while(gold<=0){
            try{
                gold=scan.nextInt();
            }catch(InputMismatchException e){
                errorCounts.incrementErrors();
            }
        }
        player.addGold(-gold);
        System.out.println("This is your last chance to change difficulty do you wish to do so?");
        String answer=null;
        while(!(answer.equalsIgnoreCase("yes")||answer.equalsIgnoreCase("no"))){
            try{
               answer=scan.nextLine();
            }catch(InputMismatchException e){
                errorCounts.incrementErrors();
            }
        }
        if(answer.equalsIgnoreCase("yes")){
            while(answer.equalsIgnoreCase("yes")){
                try{
                    answer=scan.nextLine();
                }catch(InputMismatchException e){
                    errorCounts.incrementErrors();
                }
            }
            generator.changeDifficulty(answer);
        }
        quest();
    }
    private void quest(){
        displayStuff(quest.getInformation());
        int currentQuestion=0;
        while(!quest.questFinished()){
            Question q=quest.getQuestion(currentQuestion);
            ArrayList<String> temp=q.getInformation();
            if(isFight(q)){
                temp=getFightInfo(q);
            }
            displayStuff(temp);
            
            displayStuff(q.getOptions());
            int choice=scan.nextInt();
            displayStuff(q.getResult(choice));
            currentQuestion=findNextNumber(q,choice);
        }
        
    }
    private boolean isFight(Question q){
        ArrayList<String> strings=q.getInformation();
        for(String s: strings){
            if(s.equals("FIGHT")){
                return true;
            }
        }
        return false;
    }
    private ArrayList<String> getFightInfo(Question q){
        ArrayList<String> info=q.getInformation();
        ArrayList<String> temp=new ArrayList<>(1);
        for(String s:info){
            if(s.substring(0, 14).equals("--Monster is ")){
                currentMonster=generator.getMonster(s.substring(14));
                temp.add(s);
            }else{
                temp.add(s);
            }
        }
        for(int x=0; x<temp.size();x++){
            if(temp.get(x).substring(0, 14).equals("--Monster is ")){
                temp.set(x, "Monster has "+currentMonster.getHealth()+" health");
            }
        }
        return null;
    }
    private int findNextNumber(Question q, int choice){
        ArrayList<String> temp=q.getResult(choice);
        int nextQuestion = 0;
        for(String s:temp){
            if(s.substring(0, 2).equalsIgnoreCase("--")){
                nextQuestion=tc.changeTypeInt(s.substring(2));
                break;
            }
        }
        return nextQuestion;
    }
    private void weaponSelecter(){
        ArrayList<Weapon> temp=player.getInventory();
        int selected=0;
        while(selected<4){
            System.out.println(selected);
            int x=0,idx=-1;
            for(Weapon w:temp){
                System.out.println((x+1)+":  "+w.getClass()+" : "+w.getName());
                x++;
            }
            while(idx==-1){
                try{
                    idx=scan.nextInt()-1;
                }catch(InputMismatchException e){
                    errorCounts.incrementErrors();
                }
            }
            ArrayList<String> strings=temp.get(idx).toStringArray();
            for(String s:strings){
                System.out.println(s);
            }
            System.out.println("Do you Choose this or do you wish to Return?");
            String answer=null;
            while(!(answer.equalsIgnoreCase("Choose")||answer.equalsIgnoreCase("Return"))){
                try{
                    answer=scan.nextLine();
                }catch(InputMismatchException e){
                    errorCounts.incrementErrors();
                }
            }
            answer=answer.toLowerCase();
            switch(answer){
                case "choose":
                    player.removeFromInventory(temp.get(idx).getName());
                    temp.remove(idx);
                    selected++;
                    break;
                case "return":
                    break;
                default:
                    break;
            }
        }
    }
    public int getDifficulty(){
        return generator.getDifficulty();
    }
    private void displayStuff(ArrayList<String> strings){
        for(String s:strings){
            if(s.equals("PAUSE")){
                scan.nextLine();
            }else if(s.equals("FIGHT")){
            }else if(!(s.substring(0, 2).equalsIgnoreCase("--"))){
                System.out.println(s);
            }
        }
        scan.nextLine();
    }
}
