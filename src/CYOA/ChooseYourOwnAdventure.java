package CYOA;
import CYOA.Adventure.Weapon;
import CYOA.Adventure.Character;
import InputAndOutput.TypeChanger;
import InputAndOutput.FileWriter;
import InputAndOutput.FileReader;
import CYOA.Tavern.Tavern;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*@author Timothy*/
public class ChooseYourOwnAdventure {
    private static ErrorCounter errorCounts;
    private static Character[] savedGames;
    private static final int MAXCHARS=5;
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scan=new Scanner(System.in);	
        String[] savedGameNames;
        errorCounts=new ErrorCounter();
        savedGameNames = retrieveSaves();
	String[] mainMenu={"1: New Game","2: Load Game","3: Help", "4: Exit"};
	for(String menuOption: mainMenu){
            System.out.println(menuOption);
	}
        int selectedCharacter=-1;
	String answer="default";
        try{
            answer=scan.nextLine();
        }catch(InputMismatchException e){
            errorCounts.incrementErrors();
        }
        switch(answer){
            case "1":
            case "New Game":
                int idxToSave=-1;
                for(int x=0;x<savedGames.length;x++){
                    if(savedGameNames[x].equalsIgnoreCase("empty")){
                        idxToSave=x;
                        break;
                    }
                }
                if(idxToSave==-1){
                    System.out.println("Which Save would you like to delete?");
                    for(int x=0;x<savedGameNames.length;x++){
                        System.out.println((x+1)+":"+savedGameNames[x]);
                    }
                }
                System.out.println("Name your Character");
                savedGames[idxToSave]=new Character(scan.nextLine());
                selectedCharacter=idxToSave;
                FileWriter fw=new FileWriter("SavedGame"+idxToSave+".txt");
                ArrayList<String> strings=savedGames[idxToSave].toStringArray();
                fw.write(strings);
                break;
            case "2":
            case "Load Game":
                System.out.println("\n\n");
                for(int x=0;x<savedGameNames.length;x++){
                    System.out.println((x+1)+":"+savedGameNames[x]);
                }
                try{
                    selectedCharacter=(scan.nextInt()-1);
                }catch(InputMismatchException e){
                    errorCounts.incrementErrors();
                }
                break;
            case "3":
            case "Help":
		FileReader help=new FileReader("Help.txt");
		TypeChanger tc=new TypeChanger();
		ArrayList<String>buffer=new ArrayList<>(1);
		buffer.add("empty");
		try{
                    buffer = help.readFile();
		}catch(FileNotFoundException e){
                    errorCounts.incrementErrors();
		}
		buffer=tc.commentRemover(buffer);
		for(String str:buffer){
                    if(!str.substring(0, 5).equals("PAUSE")){
                        System.out.println(str);
                    }else{
                        try {
                            scan.nextLine();
                        } catch (InputMismatchException ex) {
                            errorCounts.incrementErrors();
                        }
                        System.out.println("\n\n\n\n");
                    }
		}
                break;
            case "4":
            case "Exit":
                System.exit(1);
        }
        if(selectedCharacter==-1){
            System.exit(2);
        }
        String difficulty="easy";
        boolean play=true;
        while(play){
            Tavern myTavern=new Tavern(difficulty, savedGames[selectedCharacter]);
            myTavern.mainRoom();
            FileWriter fw=new FileWriter("SavedGame"+selectedCharacter+".txt");
            ArrayList<String> strings=savedGames[selectedCharacter].toStringArray();
            fw.write(strings);
            System.out.println("Would you like to proceed to the tavern?");
            answer="empty";
            while(!(answer.equalsIgnoreCase("y")||answer.equalsIgnoreCase("yes")||answer.equalsIgnoreCase("n")||answer.equalsIgnoreCase("n"))){
                try{
                    answer=scan.nextLine();
                }catch(Exception e){
                    errorCounts.incrementErrors();
                }
            }
            if(answer.charAt(0)=='y'){
                play=true;
            }else{
                play=false;
            }
        }
        System.out.println("This game encountered "+errorCounts.getNumErrors()+" errors during this session");
    }
    private static String[] retrieveSaves() throws FileNotFoundException{
        Character[] characters = new Character[5];
        for(int x=0;x<MAXCHARS;x++){
            FileReader fr=new FileReader("SavedGame"+x+".txt");
            ArrayList<String> buffer = new ArrayList<>(1);
            buffer.add("empty");
            try{
                buffer = fr.readFile();
            }catch(FileNotFoundException e){
                errorCounts.incrementErrors();
            }
            characters[x]=toCharacter(buffer);
        }
        String[] characterNames=new String[MAXCHARS];
        for(int x=0;x<characterNames.length;x++){
            characterNames[x]="Empty";
        }
        for(int x=0;x<characters.length;x++){
            if(characters[x]!=null){
                characterNames[x]=characters[x].getName();
            }
        }
        savedGames=characters;
        return characterNames;
    }
    private static Character toCharacter(ArrayList<String> strings){
        if(strings.get(0).equalsIgnoreCase("empty")){
            return null;
        }
	TypeChanger tc=new TypeChanger();
        int y=-1;
        for(int z=0;z<strings.size();z++){
            try{
                if(strings.get(z).charAt(0)=='#'||strings.get(z).equalsIgnoreCase("++++++")||strings.get(z).equalsIgnoreCase("------")){
                    strings.remove(z);
                    z--;
                }
            }catch(StringIndexOutOfBoundsException e){
                errorCounts.incrementErrors();
            }
        }
	Character temp=new Character(strings.get(0),tc.changeTypeInt(strings.get(1)),tc.changeTypeInt(strings.get(2)),tc.changeTypeInt(strings.get(3)),tc.changeTypeInt(strings.get(4)),tc.changeTypeInt(strings.get(5)),tc.changeTypeInt(strings.get(6)),tc.changeTypeInt(strings.get(7)),tc.changeTypeInt(strings.get(8)),tc.changeTypeInt(strings.get(9)));
        for(int x=0;x<10;x++){
            strings.remove(0);
	}
	ArrayList<Weapon> weapons=new ArrayList<>(1);
	for(int x=0;x<strings.size();x++){
            String[] weaponStrings=new String[6];
            for(int z=0;z<6;z++){
                weaponStrings[z]=strings.get(0);
		strings.remove(0);
            }
            weapons.add(tc.weaponMaker(weaponStrings));
	}
	for(int x=0;x<2;x++){
            temp.addMainWeapon(weapons.get(0));
            weapons.remove(0);
	}
        temp.addToInventory(weapons);
	return temp;
    }
    private int selectQuest(){
        Scanner scan=new Scanner(System.in);
        FileReader help=new FileReader("QuestList.txt");
	TypeChanger tc=new TypeChanger();
	ArrayList<String>buffer=new ArrayList<>(1);
	buffer.add("empty");
	try{
            buffer = help.readFile();
	}catch(FileNotFoundException e){
            errorCounts.incrementErrors();
        }
	buffer=tc.commentRemover(buffer);
        for(String s:buffer){
            System.out.println(s);
        }
        int selectedQuest=-1;
        while(selectedQuest==-1){
            try{
                selectedQuest=scan.nextInt();
            }catch(InputMismatchException e){
                errorCounts.incrementErrors();
            }
        }
        return selectedQuest;
    }
}
