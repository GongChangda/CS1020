/**
 * Name :Gong Changda
 * Matric No. :A0162477X
 * PLab Acct. :
 */

import java.util.*;

public class IVLE {
    // define your own attributes, constructor, and methods here
    private int _numOfModule,_numOfQuery;
    private Module[] _modules ;
    private ArrayList<Student> _students;
    // we use java default constructor;
    private void run() {
        Scanner sc=new Scanner(System.in);
        _numOfModule=sc.nextInt();
        _numOfQuery=sc.nextInt();
        _modules=new Module[_numOfModule];
        _students=new ArrayList<Student>();
        for(int i=0;i<_numOfModule;i++){
            String code=sc.next();
            int mc=sc.nextInt();
            int max=sc.nextInt();
            _modules[i]=new Module(code,mc,max);
        }
        for(int j=0;j<_numOfQuery;j++){
            String query=sc.next();
            if(query.equals("register")){
                String name=sc.next();
                String code=sc.next();
                Student stu=new Student("");
                int counter=0;
                for(int i=0;i<_students.size();i++){
                    if(_students.get(i).getName().equals(name)){

                        stu=_students.get(i);
                        counter=1;
                    }
                }
                if(counter==0){

                    stu=new Student(name);
                    _students.add(stu);
                }    
                for(int k=0;k<_numOfModule;k++){
                    if(_modules[k].getCode().equals(code)){
                        _modules[k].register(stu);
                    }
                }
            }
            else if(query.equals("remove")){
                Student stu=new Student("");
                String name=sc.next();
                String code=sc.next();
                int counter=0;
                for(int a=0;a<_students.size();a++){
                    if(_students.get(a).getName().equals(name)){
                        stu=_students.get(a);
                        counter=1;
                    }
                }
                if(counter==0){
                    System.out.println(name+" is not registered into "+code);
                }
                else{    
                    for(int i=0;i<_numOfModule;i++){
                        if(_modules[i].getCode().equals(code)){
                            _modules[i].remove(stu);

                        }
                    }
                }   
            }
            else if(query.equals("details")){
                Student stu=new Student("");
                String name=sc.next();
                int counter=0;
                for(int i=0;i<_students.size();i++){
                    if(_students.get(i).getName().equals(name)){
                        stu=_students.get(i);
                        counter=1;
                        stu.printDetail();
                        break;
                    }
                }
                if(counter==0){
                    Student stu1=new Student(name);
                    stu1.printDetail();
                }

            } 
            else if(query.equals("total")){
                int sum=0;
                for(int b=0;b<_students.size();b++){
                    if(_students.get(b).getModules().size()>0){
                        sum++;
                    }
                }
                if(sum==0){
                    System.out.println("No students registered for modules");
                }
                else{
                    System.out.println(sum);
                }    
            }
            else{
                ArrayList higheststu=new ArrayList();
                int highest=0;

                for(int i=0;i<_students.size();i++){
                    if(_students.get(i).getTotalMcs()>highest){
                        highest=_students.get(i).getTotalMcs();
                    }
                }
                for(int c=0; c<_students.size();c++){
                    if(_students.get(c).getTotalMcs()==highest){
                        higheststu.add(_students.get(c).getName());
                    }
                }
                if(highest==0){
                    System.out.println("No students registered for modules");
                }
                else{    
                    Collections.sort(higheststu);
                    System.out.print(highest);
                    for(int k=0;k<higheststu.size();k++){
                        System.out.print(" "+higheststu.get(k));
                    }
                    System.out.println();}
            }    
        }
    }

    public static void main(String[] args) {
        IVLE ivle = new IVLE();
        ivle.run();
    }
}

class Module {
    // define your own attributes, constructor, and methods here
    private int _mc;
    private ArrayList<Student> _students;
    private int _max;
    private String _moduleCode;

    public Module(String code,int mc,int max){
        _students=new ArrayList<Student>();
        _mc=mc;
        _max=max;
        _moduleCode=code;
    }

    public int getMc(){
        return _mc;
    }
    public ArrayList<Student> getStudents(){
        return _students;
    }
    public int getMax(){
        return _max;
    }
    public String getCode(){
        return _moduleCode;
    }
    public void register(Student student){
        // ArrayList<Student> students;
        // students=getStudents();

        for(int i=0;i<getStudents().size();i++){
            if(getStudents().get(i)!=null && getStudents().get(i).getName()==student.getName()){
                System.out.println(student.getName()+" is already registered into "+getCode());
                return ;
            }
        }    
        if(getStudents().size()==getMax()){
            System.out.println(getCode()+" is full");
        }
        else{
            getStudents().add(student);
            student.getModules().add(this);
            System.out.println(student.getName()+" successfully registered into "+ getCode());
        }    


    }

    public void remove(Student student){
        ArrayList<Student> students=getStudents();
        for(int i=0;i<students.size();i++){
            if(students.get(i).getName()==student.getName()){
                students.remove(i);
                ArrayList<Module> modules=student.getModules();
                for(int j=0;j<modules.size();j++){
                    if(modules.get(j).getCode().equals(this.getCode())){
                        modules.remove(j);
                        break;
                    }
                }                   


                System.out.println(student.getName()+" has been removed from "+getCode());
                return;
            }


        }   


        System.out.println(student.getName()+" is not registered into "+getCode());

    }
}



class Student {

    // define your own attributes, constructor, and methods here
    private String _name;
    private ArrayList<Module> _modules;

    public Student(String name){
        _modules=new ArrayList<Module>();
        _name=name;
    }

    public String getName(){
        return _name;
    }
    public ArrayList getModules(){
        return _modules;
    }
    public int getTotalMcs(){
        int sum=0;
        ArrayList<Module> modules=getModules();
        for(int i=0;i<modules.size();i++){
            sum=sum+modules.get(i).getMc();
        }
        return sum;
    }
    public void printDetail(){
        if(getModules().size()==0){
            System.out.println(getName()+" has no modules");
        }
        else{
            System.out.print(getTotalMcs());
            ArrayList<String> moduleCodes=new ArrayList<String>();
            for(int j=0;j<_modules.size();j++){
                moduleCodes.add(_modules.get(j).getCode());
            }
            Collections.sort(moduleCodes);    
            for(int i=0;i<_modules.size();i++){
                System.out.print(" "+moduleCodes.get(i));

            }
            System.out.println();    
        }    
    }

}
