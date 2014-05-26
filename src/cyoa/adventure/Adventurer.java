package cyoa.adventure;

import cyoa.ErrorCounter;
import cyoa.gui.AttributesGUI;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Adventurer {
    private String myName;
    private int lvl, xp, skillGroup,maxHealth, health, strength, dexterity, blade, archery, gold;
    private int[] skillBuffer;
    private ArrayList<Weapon> inventory;
    private Weapon[] equipped;
    private static ErrorCounter errorCounts;
    public Adventurer(String name){
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
        skillBuffer=new int[5];
    }
    public Adventurer (String name, int gold, int maxHP, int hitPoints, int str, int dex, int bl, int arch, int level, int exp){
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
        skillBuffer=new int[5];
    }
    @SuppressWarnings("empty-statement")
    private void levelUp(){
        final AttributesGUI a=new AttributesGUI();
        new Thread(new Runnable(){
            @Override
            public void run(){
                a.setVisible(true);
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Adventurer.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(a.isVisible());
        maxHealth+=skillBuffer[0];
        strength+=skillBuffer[1];
        dexterity+=skillBuffer[2];
        blade+=skillBuffer[3];
        archery+=skillBuffer[4];
        lvl++;
        skillGroup=lvl/10;
    }
    public int getSkillPoints(){
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
        public int getStrength(){
            return strength;
        }
        public int getDexterity(){
            return dexterity;
        }
        public int getBlade(){
            return blade;
        }
        public int getArchery(){
            return archery;
        }
        public int getSkillBuffer(int i){
            return skillBuffer[i];
        }
        public int getTotalInSkillBuffer(){
            int tot=0;
            for(int x=0;x<skillBuffer.length;x++){
                tot+=skillBuffer[x];
            }
            return tot;
        }
        public void setMaxHealth(int mh){
            maxHealth=mh;
        }
        public void setStrength(int s){
            strength=s;
        }
        public void setDexterity(int d){
            dexterity=d;
        }
        public void setBlade(int b){
            blade=b;
        }
        public void setArchery(int a){
            archery=a;
        }
        public void setSkillBuffer(int i, int v){
            skillBuffer[i]=v;
        }
}
