
/**
*	Name		:
*	Matric No.	:
*/

import java.util.*;

public class Passing {
    public Passing(){
        
        }
	public static void main(String[] args) {
        Passing passing=new Passing();
        LinkedList<Player> players=new LinkedList<Player>();
        Scanner sc=new Scanner(System.in);
        int numOfPlayers,numOfRounds,max,currIdx;
        numOfPlayers=sc.nextInt();
        numOfRounds=sc.nextInt();
        max=sc.nextInt();
        currIdx=0;
        for(int j=0;j<numOfPlayers;j++){
            players.add(new Player(sc.next()));
            
            }
        for(int i=0;i<numOfRounds;i++){
            int numOfPass=sc.nextInt();
            currIdx=passing.handlePassing(players,numOfPass,currIdx,max);
            }    
            


	}
    private int handlePassing(LinkedList<Player> players,int numOfPass,int currIdx,int max){
        int curr;
        int finalIdx=(currIdx+numOfPass) % players.size();
        System.out.println(players.get(finalIdx).getName());
        if(players.get(finalIdx).getCount()+1>=max){
            if(finalIdx==players.size()-1){
                curr=0;
                }
            else{
                curr=finalIdx;
                } 
                
            players.remove(finalIdx);    
            }
        else{
            players.get(finalIdx).incCount();
            if(currIdx==finalIdx){
                curr=finalIdx;
                }
            else{
                Player currPlayer=players.get(currIdx);
                Player finalPlayer=players.get(finalIdx);
                String tempName=currPlayer.getName();
                int tempCount=currPlayer.getCount();
                currPlayer.setName(finalPlayer.getName());
                currPlayer.setCount(finalPlayer.getCount());
                finalPlayer.setName(tempName);
                finalPlayer.setCount(tempCount);
                curr=currIdx;
                }    
            } 
            return curr;
        }
}

class Player{
    private String _name;
    private int _count;

    public Player(String name){
        _name=name;
        _count=0;
        }

    public String getName(){
        return _name;
        }    
    public int getCount(){
        return _count;
        }    
    public void setName(String newName){
        _name=newName;
        }    
    public void incCount(){
        _count++;
        }    
    public void setCount(int newCount){
        _count=newCount;
        }    
    }
