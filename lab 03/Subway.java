/**
 * Name			:
 * Matric. No	:
 * PLab Acct.	:
 */

import java.util.*;

public class Subway {
    private LinkedList<Station> _stations;

	public Subway() {
		_stations=new LinkedList<Station>();
	}

	public void run() {
		//implement your "main" method here
        Scanner sc=new Scanner(System.in);
        int numOfSts,numOfQs;
        numOfSts=sc.nextInt();
        for(int i=0;i<numOfSts;i++){
           String name=sc.next();
           _stations.add(new Station(name));
            }
        numOfQs=sc.nextInt();
        for(int j=0;j<numOfQs;j++){
            String query=sc.next();
            if(query.equals("TIME")){
                String start,end;
                start=sc.next();
                end=sc.next();
                handleTime(start,end);
                }
            else if(query.equals("BUILD")){
                String prevStation,newStation;
                prevStation=sc.next();
                newStation=sc.next();
                handleBuild(prevStation,newStation);

                }    
            else if(query.equals("PATH")){
                String start,end;
                start=sc.next();
                end=sc.next();
                handlePath(start,end);
                }
            else{
                String start;
                start=sc.next();
                handlePrint(start);
                }    
            }


	}
    public void handlePrint(String start){
        LinkedList<Station> temp=new LinkedList<Station>();
        temp.addAll(_stations);
        temp.addAll(_stations);
        int index=_stations.indexOf(new Station(start));
        ListIterator<Station> iter=temp.listIterator(index);
        System.out.print(iter.next());
        for(int i=1;i<_stations.size();i++){
            System.out.print(" "+iter.next());
            }
        System.out.println();    
        }
    public void handlePath(String starting,String ending){
        int start,end;
        start=_stations.indexOf(new Station(starting));
        end=_stations.indexOf(new Station(ending));
        int path1,path2;
        path1=Math.abs(start-end);
        path2=_stations.size()-path1;
        if(start<end){
            if(path1<=path2){
                ListIterator iter=_stations.listIterator(start);
                System.out.print(iter.next());
                for(int j=1;j<=path1;j++){
                    System.out.print(" "+iter.next());
                    }
                System.out.println();    
                }
            else{
                System.out.print(_stations.get(start));
                for(int i=1;i<=path2;i++){
                    System.out.print(" "+_stations.get((start-i+_stations.size())% _stations.size()));
                    }
                System.out.println();    
                } 
                
            }
         else if(start==end){
             System.out.println(_stations.get(start));
             }
         else{
             if(path1<path2){
                 System.out.print(_stations.get(start));
                 for(int inc=1;inc<=path1;inc++){
                     System.out.print(" "+_stations.get(start-inc));
                     
                     }
                 System.out.println();    
                 }
             else{
                 System.out.print(_stations.get(start));
                 for(int k=1;k<=path2;k++){
                     System.out.print(" "+_stations.get((start+k)% _stations.size()));
                     
                     }
                 System.out.println();     
                 }    
             }    
         
        }
    public void handleBuild(String prevName,String newName){
        int index=_stations.indexOf(new Station(prevName));
        _stations.add(index+1,new Station(newName));
        System.out.println("station "+newName+" has been built");

        }
    public void handleTime(String start,String end){
        int startIdx,endIdx;
        startIdx=_stations.indexOf(new Station(start));
        endIdx=_stations.indexOf(new Station(end));
        int steps1,steps2;
        steps1=Math.abs(endIdx-startIdx);
        steps2=_stations.size()-steps1;
        int minStep;
        minStep=Math.min(steps1,steps2);
        int minTime;
        if(minStep==0)
            minTime=0;
        else
            minTime=3*minStep-1;
        System.out.println(minTime);    
        }

	public static void main(String[] args) {
		Subway newSubwayNetwork = new Subway();
		newSubwayNetwork.run();
	}
}

class Station{
    private String _name;

    public Station(String name){
        _name=name;

        }

    public String getName(){
        return _name;
        }
    public boolean equals(Object another){
        if(another!=null && another instanceof Station){
            return this.getName().equals(((Station)another).getName());
            }
        return false;    
        }
    public String toString(){
        return _name;
        }    
    }

