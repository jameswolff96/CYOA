package CYOA.Tavern.GamblingTable.BlackJack;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Decks {
        private int[] total, turnCounter;
	private String[] hand;
        private boolean isPlayer;
	private ArrayList<ArrayList<Card>> decks;
        private boolean Stay, split, splitStay1=false, splitStay2=false;
        private int turn;
        public Decks(boolean player){
            decks=new ArrayList<>();
            hand=new String[2];
            total=new int[2];
            total[0]=0;
            total[1]=0;
            isPlayer=player;
            Stay=false;
            turnCounter=new int[2];
            turnCounter[0]=1;
        }
        public void turn(){
            if(turn==0){
                firstTurn();
            }
            turn++;
            if(!split||splitStay2==true){
                if(turn>1){
                    System.out.println(hand[0]);
                }
                if(isPlayer){
                    String answer;
                    Scanner scan=new Scanner(System.in);
                    if(splitable()&&!splitStay2){
                        System.out.println("Would you like to split?");
                        answer=scan.next();
                        if(answer.equalsIgnoreCase("yes")){
                            splitDeck();
                            hand[0]=decks.get(0).get(0).getCard()+" and "+decks.get(1).get(0).getCard();
                            total[0]=decks.get(0).get(0).getValue()+decks.get(1).get(0).getValue();
                            hand[1]=decks.get(0).get(1).getCard()+" and "+decks.get(1).get(1).getCard();
                            total[1]=decks.get(0).get(1).getValue()+decks.get(1).get(1).getValue();
                            turnCounter[1]=1;
                            turn();
                        }
                    }
                    if(total[0]<21){
                        System.out.println("Hit or Stay?");
                        answer=scan.next();
                        System.out.println("\n");
                        if(answer.equalsIgnoreCase("Stay")){
                            Stay=true;
                        }else if (answer.equalsIgnoreCase("Hit")){
                            turnCounter[0]++;
                            hit(0);
                            Stay=false;
                        }
                        if(total[0]>=20){
                            Stay=true;
                        }
                    }else{
                        Stay=true;
                    }
                }else{
                    Random dealer=new Random();
                    int answer=dealer.nextInt(10);
                    if(total[0]<18){
                        answer=0;
                    }
                    if(answer>4){
                        Stay=true;
                        System.out.println("\nThe Dealer Stayed!!");
                    }else if(answer>=0&&answer<=4){
                        System.out.println("\nThe Dealer Hit!!\n");
                        turnCounter[0]++;
                        hit(0);
                    }
                    if(total[0]>=20&&!Stay){
                        System.out.println("\nThe Dealer Stayed!!");
                        Stay=true;
                    }
                }
            }else if(split){
                System.out.println(hand[0]);
                String answer;
                Scanner scan=new Scanner(System.in);
                System.out.println("Hit or Stay?");
                answer=scan.next();
                System.out.println("\n");
                if(answer.equalsIgnoreCase("Stay")||total[0]>=20){
                    splitStay1=true;
                }else if (answer.equalsIgnoreCase("Hit")){
                    turnCounter[0]++;
                    hit(0);
                    splitStay1=false;
                }
                System.out.println(hand[1]);
                System.out.println("Hit or Stay?");
                answer=scan.next();
                System.out.println("\n");
                if(answer.equalsIgnoreCase("Stay")){
                    splitStay2=true;
                }else if (answer.equalsIgnoreCase("Hit")){
                    turnCounter[1]++;
                    hit(1);
                    splitStay2=false;
                }
                if(total[1]>=20){
                    splitStay2=true;
                }
            }else if(splitStay1==true){
                System.out.println(hand[1]);
                String answer;
                Scanner scan=new Scanner(System.in);
                System.out.println("Hit or Stay?");
                answer=scan.next();
                System.out.println("\n");
                if(answer.equalsIgnoreCase("Stay")){
                   splitStay2=true;
                }else if (answer.equalsIgnoreCase("Hit")){
                    turnCounter[1]++;
                    hit(1);
                    splitStay2=false;
                }
                if(total[1]>=21){
                    splitStay2=true;
                }
            }
        }
    private void firstTurn(){
        ArrayList<Card> deck=new ArrayList<>();
        deck.add(new Card());
        deck.add(new Card());
        deck.get(0).setNumber();
        deck.get(1).setNumber();
        while(deck.get(0).getNumber()==deck.get(1).getNumber()){
            deck.get(1).setNumber();
        }
        deck.get(0).setCard(isPlayer);
        deck.get(1).setCard(isPlayer);
        hand[0]=deck.get(0).getCard()+" and "+deck.get(1).getCard();
        System.out.println(hand[0]);
        deck.get(0).setValue(isPlayer);
        deck.get(1).setValue(isPlayer);
        total[0]+=deck.get(0).getValue();
        total[0]+=deck.get(1).getValue();
        decks.add(deck);
    }
    private void hit(int deck){
        decks.get(deck).add(new Card());
	decks.get(deck).get(turnCounter[deck]).setNumber();
        for(int countx=turnCounter[deck]; countx>1;countx--){
            while(decks.get(deck).get(0).getNumber()==decks.get(deck).get(turnCounter[deck]).getNumber()||decks.get(deck).get(1).getNumber()==decks.get(deck).get(turnCounter[deck]).getNumber()){
                if(decks.get(deck).get(0).getNumber()==decks.get(deck).get(turnCounter[deck]).getNumber()){
                    decks.get(deck).get(turnCounter[deck]).setNumber();
                }else if(decks.get(deck).get(1).getNumber()==decks.get(deck).get(turnCounter[deck]).getNumber()){
                    decks.get(deck).get(turnCounter[deck]).setNumber();
                }
            }
            if(decks.get(deck).get(countx).getNumber()==decks.get(deck).get(turnCounter[deck]).getNumber()){
                decks.get(deck).get(turnCounter[deck]).setNumber();
            }
        }
        decks.get(deck).get(turnCounter[deck]).setCard(isPlayer);
        hand[deck]+=" and " + decks.get(deck).get(turnCounter[deck]).getCard();
        System.out.println(hand[deck]);
        decks.get(deck).get(turnCounter[deck]).setValue(isPlayer);
        total[deck]+=decks.get(deck).get(turnCounter[deck]).getValue();
    }
    public void splitDeck(){
        split=true;
        ArrayList<Card> deck=new ArrayList<>();
        deck.add(decks.get(0).get(1));
        deck.add(new Card());
        deck.get(1).setNumber();
        while(deck.get(0).getNumber()==deck.get(1).getNumber()||deck.get(0).getNumber()==decks.get(0).get(0).getNumber()){
            deck.set(1, new Card());
            deck.get(1).setNumber();
        }
        deck.get(1).setCard(isPlayer);
        deck.get(1).setValue(isPlayer);
        decks.add(deck);
        decks.get(0).remove(1);
        decks.get(0).add(new Card());
        decks.get(0).get(1).setNumber();
        while(decks.get(0).get(1).getNumber()==decks.get(0).get(0).getNumber()||decks.get(0).get(1).getNumber()==decks.get(1).get(0).getNumber()||decks.get(0).get(1).getNumber()==decks.get(1).get(1).getNumber()){
            decks.get(0).add(new Card());
            decks.get(0).get(1).setNumber();
        }
        decks.get(0).get(1).setCard(isPlayer);
        decks.get(0).get(1).setValue(isPlayer);
        total[0]=0;
        total[1]=1;
        for(int x=0;x<decks.size();x++){
            for(Card card:decks.get(x)){
                total[x]+=card.getValue();
            }
        }
    }
    public boolean splitable(){
        if(decks.get(0).get(0).getValue()!=decks.get(0).get(1).getValue()||decks.get(0).get(0).getValue()==1||decks.get(0).get(1).getValue()==1||decks.get(0).get(0).getValue()==11||decks.get(0).get(1).getValue()==11){
            return false;
        }
        return true;
    }
    public int getTotal(int x){
	return total[x];
    }
    public int getTurns(int deck){
        return turnCounter[deck];
    }
    public boolean getStay(){
        return Stay;
    }
    public boolean getSplit(){
        return split;
    }
}
