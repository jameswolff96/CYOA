package cyoa.tavern.gamblingtable;
import java.util.Random;
public class Dice {
    private static Random die=new Random();
    public static int sixDie(){
        return die.nextInt(6)+1;
    }
    public static int twelveDie(){
        return die.nextInt(12)+1;
    }
    public static int thirteenDie(){
        return die.nextInt(13)+1;
    }
    public static int randomDie(int x){
        return die.nextInt(x)+1;
    }
}
