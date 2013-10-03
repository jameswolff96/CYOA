package CYOA.Tavern.GamblingTable.Pig;
import java.util.Scanner;
import CYOA.Adventure.Adventurer;
import CYOA.ErrorCounter;
public class PigDice {
    private ErrorCounter errorCounts;
    private PigPlayer[] players;
    private PigDealer dealer;
    private Adventurer player;
    public PigDice(Adventurer c){
        player=c;
        players=new PigPlayer[1];
        players[0]=new PigPlayer(c.getName());
        dealer=new PigDealer();
        errorCounts=new ErrorCounter();
    }
    public void pigDice(){
        Scanner scan=new Scanner(System.in);
        System.out.println("Place your bets!!");
        int answer=scan.nextInt();
        while(answer>player.getGold()){
            try{
                System.out.println("You don't have that much gold!!");
                answer=scan.nextInt();
            }catch(Exception e){
                errorCounts.incrementErrors();
            }
        }
        while(!getWin()){
            for(int x=0; x<players.length; x++){
                System.out.println("\n\n\n"+players[x].getName()+"\n\n");
                players[x].turn();
            }
            System.out.println("\n\n\nDealer\n\n");
            dealer.turn();
        }
        System.out.println(players[getWinner()].getName()+" was the winner.");
        if(getWinner()==0){
            System.out.println(players[getWinner()].getName()+" won "+answer+" gold!!");
            player.addGold(answer);
        }else{
            System.out.println(players[getWinner()].getName()+" lost "+answer+" gold!!");
            player.addGold(-answer);
        }
    }
    
    public boolean getWin(){
        boolean winner=false;
        for(int x=0; x<players.length; x++){
            if(players[x].getTotal()>=100){
                winner=true;
                break;
            }
        }
        return winner;
    }
    public int getWinner(){
        int winner=0;
        for(int x=0; x<players.length; x++){
            if(players[x].getTotal()>=100){
                winner=x;
                break;
            }
        }
        return winner;
    }
}
