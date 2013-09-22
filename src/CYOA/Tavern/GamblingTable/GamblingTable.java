package CYOA.Tavern.GamblingTable;
import CYOA.Tavern.GamblingTable.BlackJack.BlackJack;
import CYOA.Adventure.Character;
import CYOA.Tavern.GamblingTable.Pig.PigDice;
/*@author Timothy*/
public class GamblingTable {
    private BlackJack blackJack;
    private PigDice pigDice;
    private Character character;
    public GamblingTable(Character c){
        character=c;
    }
    public void blackJack(){
        blackJack=new BlackJack(character);
    }
    public void pigDice(){
        pigDice=new PigDice(character);
    }
}
