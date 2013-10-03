package CYOA.Tavern.GamblingTable;
import CYOA.Tavern.GamblingTable.BlackJack.BlackJack;
import CYOA.Adventure.Adventurer;
import CYOA.Tavern.GamblingTable.Pig.PigDice;
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
