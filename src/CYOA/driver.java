package CYOA;

import InputAndOutput.FileWriter;

/*@author Timothy*/
public class driver {
    public static void main(String[] args){
        String[] strings={"This","is","a","test"};
        FileWriter tester=new FileWriter("test.txt");
        tester.write(strings);       
    }
}
