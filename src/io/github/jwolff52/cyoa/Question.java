package io.github.jwolff52.cyoa;

import java.util.ArrayList;

public class Question{
    private ArrayList<String> information;
    private ArrayList<String> options;
    private ArrayList<String> results;
    private final int number;
    public Question(ArrayList<String> strings, int num){
    	information=new ArrayList<>(1);
	options=new ArrayList<>(1);
	results=new ArrayList<>(1);
        number=num;
	for(String s:strings){
            if(s.charAt(0)=='@'){
                options.add(s.substring(1));
            }else if(s.charAt(1)=='$'){
            	results.add(s.substring(1));
            }else{
		information.add(s);
            }
	}
    }
    public ArrayList<String> getInformation(){
        return information;
    }
    public ArrayList<String> getOptions(){
        int currentOption=1;
        for(int x=0; x<options.size();x++){
            if(options.get(x).charAt(0)!=currentOption){
                options.add(x,"\n");
                currentOption++;
            }else{
                options.set(x, options.get(x).substring(1));
            }
        }
        return options;
    }
    public ArrayList<String> getResult(int choice){
        ArrayList<String> selectedResult=new ArrayList<>(1);
        for(String s:results){
            if(s.charAt(0)==choice){
                selectedResult.add(s.substring(choice));
            }
        }
        return selectedResult;
    }
    public int getNumber(){
        return number;
    }
}
