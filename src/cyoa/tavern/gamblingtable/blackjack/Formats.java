package cyoa.tavern.gamblingtable.blackjack;
import java.util.Scanner;
import java.util.Random;
public class Formats{
    public static String Card(int value){
    	String card;
        if(value==0){
            value=Draw.draw();
        }
        switch(value%13){
            case 0:
                card="King";
                break;
            case 1:
                card="Ace";
                break;
            case 11:
                card="Queen";
                break;
            case 12:
                card="Jack";
                break;
            default:
                card=""+(value%13);
        }
    	card+=" of ";
    	if(value%13==0){
            switch(value/13){
                case 1: card+="Spades";
                    break;
                case 2: card+="Clubs";
                    break;
                case 3: card+="Hearts";
                    break;
                case 4: card+="Diamonds";
                    break;
                default: System.exit(3);
            }
    	}else{
            switch(value/13){
                case 0: card+="Spades";
                    break;
                case 1: card+="Clubs";
                    break;
                case 2: card+="Hearts";
                    break;
                case 3: card+="Diamonds";
                    break;
                default: System.exit(3);
            }
    	}
    	return card;
    }
    public static int playerFormat(int card){
    	Scanner scan=new Scanner(System.in);
    	card%=13;
    	if(card==0||card==11||card==12){
            card=10;
    	}else if(card==1){
            System.out.println("You have an Ace! Use as 1 or 11?");
            int aceValue=scan.nextInt();
            while(aceValue!=1&&aceValue!=11){
                System.out.println("That is an unacceptable value, Please input 1 or 11.");
                aceValue=scan.nextInt();
            }
            card=aceValue;
        }
        return card;
    }
    public static int dealerFormat(int card){
        Random dealer=new Random();
    	int decision=dealer.nextInt(2);
    	card%=13;
    	if(card==0||card==11||card==12){
            card=10;
    	}else if(card==1){
            if(decision==1){
                card=11;
            }else{
                card=1;
            }
    	}
    	return card;
    }
}