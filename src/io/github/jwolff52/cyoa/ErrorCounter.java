package io.github.jwolff52.cyoa;
/*@author timothy.chandler*/
public class ErrorCounter {
    private static int numErrors=0;
    public int getNumErrors(){
        return numErrors;
    }
    public void incrementErrors(){
        numErrors++;
    }
}
