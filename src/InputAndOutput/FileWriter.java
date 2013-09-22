package InputAndOutput;

import CYOA.ErrorCounter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class FileWriter {
    private static String fileName="SavedGame2.txt";
    private static ErrorCounter errorCounts;
    public FileWriter(String name){
        fileName=name;
        errorCounts=new ErrorCounter();
    }
    public void write(String s){
        Writer out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(fileName));
        } catch (FileNotFoundException ex) {
            errorCounts.incrementErrors();
        }
       BufferedWriter writer=new BufferedWriter(out);
        try {
            try {
                writer.write(s);
                writer.newLine();
            } catch (IOException ex) {
                errorCounts.incrementErrors();
            }
        }
        finally {
            try {
                writer.close();
            } catch (IOException ex) {
                errorCounts.incrementErrors();
            }
        }
    }
    public void write(ArrayList<String> strings){
        Writer out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(fileName));
        } catch (FileNotFoundException ex) {
            errorCounts.incrementErrors();
        }
        BufferedWriter writer=new BufferedWriter(out);
        try{
            for(String s:strings){
                try {
                    writer.write(s);
                    writer.newLine();
                } catch (IOException ex) {
                    errorCounts.incrementErrors();
                }
            }
        }finally {
            try {
                writer.close();
            } catch (IOException ex) {
                errorCounts.incrementErrors();
            }
        }
    }
    public void write(String[] strings){
        Writer out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(fileName));
        } catch (FileNotFoundException ex) {
            errorCounts.incrementErrors();
        }
        BufferedWriter writer=new BufferedWriter(out);
        try{
            for(String s:strings){
                try {
                    writer.write(s);
                    writer.newLine();
                } catch (IOException ex) {
                    errorCounts.incrementErrors();
                }
            }
        }finally {
            try {
                writer.close();
            } catch (IOException ex) {
                errorCounts.incrementErrors();
            }
        }
    }
}
