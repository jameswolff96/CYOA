package cyoa;
import java.util.ArrayList;
/*@author timothy*/
public class Quest {
    private ArrayList<Question> questions;
    private ArrayList<String> information;
    private boolean questOver;
    public Quest(ArrayList<Question> q, ArrayList<String> i){
        questions=q;
        information=i;
    }
    public Question getQuestion(int q){
        return questions.get(q);
    }
    public ArrayList<String> getInformation(){
        return information;
    }
    public ArrayList<String> getResults(int question, int choice){
        return questions.get(question).getResult(choice);
    }
    public boolean questFinished(){
        return questOver;
    }
}
