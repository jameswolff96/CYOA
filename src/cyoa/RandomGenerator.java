package cyoa;
/*@author Timothy*/
import cyoa.adventure.npc.Monster;
public class RandomGenerator {
    private int difficulty;
    public RandomGenerator(String difficult){
        changeDifficulty(difficult);
    }
    public void changeDifficulty(String difficult){
        switch(difficult){
            case "easy":
                difficulty=1;
                break;
            case "medium":
                difficulty=2;
                break;
            case "hard":
                difficulty=3;
                break;
            case "insanity":
                difficulty=4;
                break;
            default:
                difficulty=1;
                break;
        }
    }
    public int difficultyGenerator(int charlvl1){
        
        return 4;
    }
    public int getDifficulty(){
        return difficulty;
    }
    public Monster getMonster(String monsterType){
        Monster m;
        return null;
    }
    
    
    
}
