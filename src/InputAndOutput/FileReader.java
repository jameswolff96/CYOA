package InputAndOutput;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class FileReader{
    String file;
    public FileReader(String fileLocation){
        file=fileLocation;
    }
    public ArrayList<String> readFile() throws FileNotFoundException{
        FileInputStream fis=new FileInputStream(file);
        ArrayList<String> buffer = new ArrayList(1);
        Object o=new Object();
        Scanner scanner;
        scanner = new Scanner(fis);
        try {
            while (scanner.hasNextLine()){
                buffer.add(scanner.nextLine());
            }
        }finally{
            scanner.close();
        }
        return buffer;
    }
}