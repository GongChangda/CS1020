/**
 * Name :Gong Changda
 * Matric. No :A0162477X
 * PLab Acct. :
 */

import java.util.*;

public class ClassPhoto {
    private LinkedList<Student> _stu;

    public ClassPhoto() {
        //constructor
        _stu=new LinkedList<Student>();
    }

    private void run() {
        //implement your "main" method here
        Scanner sc =new Scanner(System.in);
        int numOfQueries=sc.nextInt();
        for(int i=0;i<numOfQueries;i++){
            String query=sc.next();
            if(query.equals("arrive")){
                String name=sc.next();
                double height=sc.nextDouble();
                handleArrive(name,height);
            }
            else if(query.equals("leave")){
                String name=sc.next();

                handleLeave(name);
            }
            else if(query.equals("shortest")){
                int position=sc.nextInt();
                handleShortest(position);
            }
            else{
                int low=sc.nextInt();
                int high=sc.nextInt();
                handleCount(low,high);
            }    
        }
    }
    private void handleCount(int low, int high){
        int count=0;
        for(int index=0;index<_stu.size();index++){
            if(_stu.get(index).getHeight()>=low && _stu.get(index).getHeight()<=high){
                count++;
            }
        }
        System.out.println(count);    
    }
    private void handleArrive(String name,double height){
        boolean isLast=true;
        for(int index=0;index<_stu.size();index++){
            if(height>=_stu.get(index).getHeight()){
                Student student=new Student(name,height);
                _stu.add(index,student);
                int position=index+1;
                System.out.println(name+" added at "+position);
                isLast=false;
                break;

            }
        }
        if(isLast==true){

            Student student=new Student(name,height);
            _stu.addLast(student);
            int position=_stu.size();
            System.out.println(name+" added at "+position);
        }    
    }
    private void handleLeave(String name){
        boolean isFound=false;
        for(int i=0;i<_stu.size();i++){
            if(_stu.get(i).getName().equals(name)){
                _stu.remove(i);
                int position=i+1;
                System.out.println(name+" has left position "+position);

                isFound=true;
                break;
            }
        }
        if(isFound==false){
            System.out.println("No student with name "+name);
        }    

    }
    private void handleShortest(int position){
        // if no student, definitely cause error.
        if(_stu.size()==0){
            System.out.println("No such student");
            return ;
            }
        int count=1,endIndex=_stu.size()-1;double prev=_stu.getLast().getHeight();
        //the following is to decide whether there exists position number of different heights.
        for(int i=_stu.size()-1;i>=0;i--){
            if(_stu.get(i).getHeight()!=prev){
                count++;
                prev=_stu.get(i).getHeight();
                if(count==position)
                    endIndex=i;
                }
            }
        //if there does not exist position number of different heights   
        if(count<position){
            System.out.println("No such student");

            }
        // there exists enough different heights    
        else{
            int startingIndex=0;
            double height=_stu.get(endIndex).getHeight();
            // search for the leftmost occurence of student with the prescribed height.
            for(int k=0;k<=endIndex;k++){
                if(_stu.get(k).getHeight()==height){
                    
                    startingIndex=k+1;
                    break;
                    }
                }
            String name=_stu.get(startingIndex-1).getName();
            //print out the leftmost name first because there is no space in front of it
            System.out.print(name);

            //print out the remaining names with specified format if exists.
            for(int j=startingIndex;j<=endIndex;j++){
                if(_stu.get(j).getHeight()==height)
                    System.out.print(" "+_stu.get(j).getName());
                }
            System.out.println();    
            }    

    
    }
    public static void main(String[] args) {
        ClassPhoto newClassPhoto = new ClassPhoto();
        newClassPhoto.run();
    }
}

class Student{
    private double _height;
    private String _name;

    public Student(String name,double height){
        _name=name;
        _height=height;

    }
    public double getHeight(){
        return _height;
    }    
    public String getName(){
        return _name;
    }    
}
