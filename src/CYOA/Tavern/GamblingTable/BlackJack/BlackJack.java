package CYOA.Tavern.GamblingTable.BlackJack;
import CYOA.Adventure.Adventurer;
import java.util.ArrayList;
import java.util.Scanner;
public class BlackJack {
    private ArrayList<Decks> players;
    private Decks dealer;
    private String result;
    private String playerName;
    private static int bet, playerGold;
    private int turnCount;
    public BlackJack(Adventurer character) {
        players=new ArrayList<>();
        players.add(new Decks(true));
        playerName=character.getName();
        Scanner scan=new Scanner(System.in);
        turnCount=0;
        int winner;
        System.out.println("Welcome to BlackJack!!\n\n");
   	do{
            dealer=new Decks(false);
            playerGold=character.getGold();
            System.out.println("Gold available to you:"+playerGold);
            System.out.println("Please place your bet!");
            bet=scan.nextInt();
            while(bet>playerGold){
                System.out.println("You don't have that much gold!!");
                bet=scan.nextInt();
            }
            play();
            winner=playerWins(0);
            if(winner==0){
                System.out.println(playerName+" won "+bet+" gold!!");
                character.addGold(bet);
            }else if(winner==1){
                System.out.println("Sorry "+playerName+", but you lost "+bet+" gold.");
                character.addGold(-bet);
            }else{
                System.out.println("It was a tie!!");
            }
            if(players.get(0).getSplit()){
                winner=playerWins(1);
                if(winner==0){
                    System.out.println(playerName+" won "+bet+" gold!!");
                    character.addGold(bet);
                }else if(winner==1){
                    System.out.println("Sorry "+playerName+", but you lost "+bet+" gold.");
                    character.addGold(-bet);
                }else{
                    System.out.println("It was a tie!!");
                }
            }
            bet=0;
            players.set(0, new Decks(true));
            dealer=new Decks(false);
        }while(scan.nextLine().equalsIgnoreCase("yes"));
    }
    private void play(){
        while(!allStay()){
            for(int x=0;x<players.size();x++){
                if(!players.get(x).getStay()){
                    System.out.println("\n\n"+playerName+"'s turn\n");
                    players.get(x).turn();
                }
            }
            if(!dealer.getStay()){
                System.out.println("\n\nDealers turn\n");
                dealer.turn();
            }
            turnCount++;
        }
        gameover(players.size());
    }
    public void gameover(int play){
        for(int x=0;x<play;x++){
            getWinner(players.get(x).getTotal(0), dealer.getTotal(0), 0, x);
            if(players.get(x).getTotal(1)!=0){
	        getWinner(players.get(x).getTotal(1), dealer.getTotal(0), 1, x);
	    }
	}
    }
    private void getWinner(int player, int deal, int deck,int x){
        System.out.print(playerName+" ");
	if(player>21){
	    result="Busted!! With a score of: "+player+" on deck "+(deck+1);
            System.out.println(result);
	}else{
	    result="scored: "+player+" on deck "+(deck+1);
            System.out.println(result);
	}
        System.out.print("Dealer ");
        if(deal>21){
	    result="Busted!! With a score of: "+deal;
            System.out.println(result);
	}else{
	    result="scored: "+deal;
            System.out.println(result);
	}
    }
    private int playerWins(int deck){
        if(players.get(0).getTotal(deck)<=21){
            if(dealer.getTotal(0)<=21){
                if(players.get(0).getTotal(deck)<dealer.getTotal(0)){
                    return 1;
                }else if(players.get(0).getTotal(deck)==dealer.getTotal(0)){
                    return 2;
                }
                return 0;
            }
            return 0;
        }
        if(dealer.getTotal(0)>21){
            return 2;
        }else{
            return 1;
        }
    }
    private boolean allStay(){
        int x=0;
        for(Decks player:players){
            if(player.getStay()){
                x++;
            }
        }
        return x==players.size();
    }
}
