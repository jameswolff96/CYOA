package io.github.jwolff52.cyoa.adventure;

import java.util.ArrayList;
import java.util.Scanner;

import io.github.jwolff52.cyoa.ErrorCounter;

public class Character {
    private String myName;
    private int lvl, xp, skillGroup,maxHealth, health, strength, dexterity, blade, archery, gold;
    private ArrayList<Weapon> inventory;
    private Weapon[] equipped;
    private static ErrorCounter errorCounts;
    public Character(String name){
        myName=name;
        lvl=0;
        skillGroup=0;
        health=89;
        levelUp();
	equipped=new Weapon[2];
        equipped[0]=new Blade("Small Dagger", 7, 0, 5);
        equipped[1]=new Bow("Hunters Bow", 5, 0, 5);
        errorCounts=new ErrorCounter();
        gold=100;
    }
    public Character (String name, int gold, int maxHP, int hitPoints, int str, int dex, int bl, int arch, int level, int exp){
        myName=name;
        this.gold=gold;
        lvl=level;
        xp=exp;
        health=hitPoints;
        strength=str;
        dexterity=dex;
        blade=bl;
        archery=arch;
	equipped=new Weapon[2];
        maxHealth=maxHP;
    }
    private void levelUp(){
        Scanner scan=new Scanner(System.in);
        int skillPoints=getSkillPoints();
        int[] skillBuffer=new int[5];
        skillBuffer[0]=1;
        boolean lvled=false;
        String answer;
        System.out.println("Apply level up to "+(lvl+1));
        for(int x=skillPoints; x>0;x--){
            System.out.println("Skill Points remaining: "+x);
            System.out.println("\nApply next skill Point\n");
            System.out.println("Health   : "+(maxHealth+skillBuffer[0]));
            System.out.println("Strength : "+(strength+skillBuffer[1]));
            System.out.println("Dexterity: "+(dexterity+skillBuffer[2]));
            System.out.println("Blade    : "+(blade+skillBuffer[3]));
            System.out.println("Archery  : "+(archery+skillBuffer[4]));
            answer=scan.next().toLowerCase();
            switch(answer){
                case "health":
                    skillBuffer[0]++;
                    break;
                case "strength":
                    skillBuffer[1]++;
                    break;
                case "dexterity":
                    skillBuffer[2]++;
                    break;
                case "blade":
                    skillBuffer[3]++;
                    break;
                case "archery":
                    skillBuffer[4]++;
                    break;
                default:
                    x++;
                    break;
            }
        }
        System.out.println("\nApply next skill Point\n");
        System.out.println("Health   : "+(maxHealth+skillBuffer[0]));
        System.out.println("Strength : "+(strength+skillBuffer[1]));
        System.out.println("Dexterity: "+(dexterity+skillBuffer[2]));
        System.out.println("Blade    : "+(blade+skillBuffer[3]));
        System.out.println("Archery  : "+(archery+skillBuffer[4]));
        System.out.println("\n\n\nAre you absolutely sure thats where you want to spend your points?");
        answer=scan.next().toLowerCase();
        switch(answer){
            case "y":
            case "yes":
                maxHealth=skillBuffer[0];
                strength=skillBuffer[1];
                dexterity=skillBuffer[2];
                blade=skillBuffer[3];
                archery=skillBuffer[4];
                lvled=true;
                break;
            default:
                skillBuffer=new int[5];
                levelUp();
                break;
        }
        if(lvled){
            lvl++;
            skillGroup=lvl/10;
        }
    }
    private int getSkillPoints(){
        int skillPoints;
        switch(skillGroup){
            case 0:
                skillPoints=10;
                break;
            case 1:
                skillPoints=9;
                break;
            case 2:
                skillPoints=8;
                break;
            case 3:
                skillPoints=7;
                break;
            case 4:
                skillPoints=6;
                break;
            default:
                if(skillGroup<10){
                    skillPoints=5;
                }else if(skillGroup<200){
                    skillPoints=(5-skillGroup%10);
                }else{
                    skillPoints=0;
                }
        }
        return skillPoints;
    }
	public void addToInventory(Weapon weapon){
            inventory.add(weapon);
	}
	public void addToInventory(ArrayList<Weapon> weaponsToAdd){
		for(Weapon wep:weaponsToAdd){
			addToInventory(wep);
		}
	}
	public void addMainWeapon(Weapon weapon){
		if(weapon instanceof Blade){
                    if(equipped == null){
                        inventory.add(equipped[0]);
                        equipped[0]=weapon;
                    }else{
                        
                    }
                    equipped[0]=weapon;
		}else{
                    equipped[1]=weapon;
		}
	}
    public String getName(){
        return myName;
    }
    public ArrayList<String> toStringArray(){
        ArrayList<String> strings=new ArrayList<>(1);
        ArrayList<String> temp=null;
        strings.add(myName);
        strings.add(gold+"");
        strings.add(maxHealth+"");
        strings.add(health+"");
        strings.add(strength+"");
        strings.add(dexterity+"");
        strings.add(blade+"");
        strings.add(archery+"");
        strings.add(lvl+"");
        strings.add(xp+"");
        for(Weapon w:equipped){
            try{
                temp=w.toStringArray();
            }catch(NullPointerException e){
            }
            try{
            for(String s:temp){
                    try{
                        strings.add(s);
                    }catch(NullPointerException e){
                        errorCounts.incrementErrors();
                    }
                }
            }catch(NullPointerException e){
                errorCounts.incrementErrors();
            }
        }
        try{
            for(Weapon w:inventory){
                try{
                    temp=w.toStringArray();
                }catch(NullPointerException e){
                }
                try{
                    for(String s:temp){
                        try{
                            strings.add(s);
                        }catch(NullPointerException e){
                            errorCounts.incrementErrors();
                        }
                    }
                }catch(NullPointerException e){
                    errorCounts.incrementErrors();
                }
            }
        }catch(NullPointerException e){
        }
        return strings;
    }
	public ArrayList<Weapon> getInventory(){
		return inventory;
	}
	public ArrayList<Weapon> getMeleeInventory(){
		ArrayList<Weapon> meleeInventory=new ArrayList<>(1);
		for(Weapon w:inventory){
			if(w instanceof Blade){
				meleeInventory.add(w);
			}
		}
                return meleeInventory;
	}
	public ArrayList<Weapon> getRangedInventory(){
		ArrayList<Weapon> rangedInventory=new ArrayList<>(1);
		for(Weapon w:inventory){
			if(w instanceof Bow){
				rangedInventory.add(w);
			}
		}
                return rangedInventory;
	}
        public void removeFromInventory(String name){
            for(int x=0;x<inventory.size();x++){
                if(inventory.get(x).getName().equalsIgnoreCase(name)){
                    inventory.remove(x);
                    break;
                }
            }
        }
        public void addGold(int g){
            gold+=g;
        }
        public void addHealth(int h){
            health+=h;
            if(health>maxHealth){
                health=maxHealth;
            }
        }
        public int getHealthRegenRate(String location){
            int regenRate;
            if(location.equalsIgnoreCase("tavern")){
                regenRate=(health/10)*4;
            }else{
                regenRate=(health/10)*2;
            }
            return regenRate;
        }
        public int getHealth(){
            return health;
        }
        public int getMaxHealth(){
            return maxHealth;
        }
        public int getGold(){
            return gold;
        }
        public int getLevel(){
            return lvl;
        }
}
