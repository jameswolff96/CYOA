package CYOA.Tavern.GamblingTable.BlackJack;
import java.util.Random;
public class Draw {
    public static int draw(){
        Random rand=new Random();
    	int value=rand.nextInt(52)+1;
    	return value;
    }
}
