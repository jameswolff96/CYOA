package cyoa.tavern.gamblingtable;
import cyoa.tavern.gamblingtable.blackjack.BlackJack;
import cyoa.adventure.Adventurer;
import cyoa.tavern.gamblingtable.pig.PigDice;
/*@author Timothy*/
public class GamblingTable {
    private BlackJack blackJack;
    private PigDice pigDice;
    private Adventurer character;
    public GamblingTable(Adventurer c){
        character=c;
    }
    public void blackJack(){
        blackJack=new BlackJack(character);
    }
    public void pigDice(){
        pigDice=new PigDice(character);
    }
}
