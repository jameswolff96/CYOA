package InputAndOutput;

import CYOA.Adventure.Blade;
import CYOA.Adventure.Bow;
import CYOA.Adventure.Adventurer;
import CYOA.Adventure.Weapon;
import java.util.ArrayList;

/* @author Timothy*/
public class TypeChanger {
    public int changeTypeInt(String s){
        int sum=0;
        for(int x=0;x<s.length();x++){
            sum+=(changeTypeInt(s.charAt(x))*Math.pow(10, (s.length()-(x+1))));
        }
        return sum;
    }
    public int changeTypeInt(char c){
        switch(c){
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                return -1;
        }
    }
	public String changeTypeString(int x){
		return x+"";
	}
        public ArrayList<String> changeTypeString(Adventurer c){
            ArrayList<String> strings=new ArrayList<>(1);
            strings.add(c.getName());
            return null;
        }
	public char changeTypeChar(int x){
		String temp="012346789";
		return temp.charAt(x);
	}
	public Weapon weaponMaker(String[] strings){
                Weapon weapon=null;
		if(strings[1].equalsIgnoreCase("Strength")){
			weapon=new Blade(strings[0],changeTypeInt(strings[2]),changeTypeInt(strings[3]),changeTypeInt(strings[4]),changeTypeInt(strings[5]));
		}else{
			weapon=new Bow(strings[0],changeTypeInt(strings[2]),changeTypeInt(strings[3]),changeTypeInt(strings[4]),changeTypeInt(strings[5]));
		}
		return weapon;
	}
	public ArrayList<String> commentRemover(ArrayList<String> strings){
		for(int x=0;x<strings.size();x++){
			if(strings.get(x).charAt(0)=='#'){
				strings.remove(x);
				x--;
			}
		}
		return strings;
	}
}
