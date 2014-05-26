package cyoa;

import cyoa.io.TypeChanger;
import cyoa.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class QuestMaker{
    private FileReader fr;
    private TypeChanger tc;
    private static ErrorCounter errorCounts;
    public QuestMaker(String questLoc){
    	fr=new FileReader(questLoc);
    	tc=new TypeChanger();
        errorCounts=new ErrorCounter();
    }
    public void newFileReader(String questLoc){
    	fr=new FileReader(questLoc);
    }
    public Quest createQuest(){
        ArrayList<String> buffer=new ArrayList<>(1);
        try{
            buffer = fr.readFile();
        }catch(FileNotFoundException e){
            errorCounts.incrementErrors();
        }
	buffer=tc.commentRemover(buffer);
        int question=0;
        ArrayList<String> temp=new ArrayList<>(1);
        ArrayList<Question> tempQuestions=new ArrayList<>(1);
        ArrayList<String> info=new ArrayList<>(1);
        for(int x=0;x<buffer.size();x++){
            if(question==0){
                if(!buffer.get(x).equals("******")){
                    info.add(buffer.get(x));
                }else{
                    question++;
                }
            }else{
                if(buffer.get(x).equals("******")){
                   tempQuestions.add(new Question(temp, question));
                   temp=new ArrayList<>(1);
                   question++;
                }else{
                    temp.add(buffer.get(x));
                }
            }
        }
        Quest newQuest=new Quest(tempQuestions, info);
        return newQuest;
    }
}
