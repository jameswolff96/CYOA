package io.github.jwolff52.cyoa.tavern.gamblingtable;
import io.github.jwolff52.cyoa.adventure.Adventurer;
import io.github.jwolff52.cyoa.tavern.gamblingtable.blackjack.BlackJack;
import io.github.jwolff52.cyoa.tavern.gamblingtable.pig.PigDice;
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
