/**
 * Name         :GONG CHANGDA
 * Matric No.   :A0162477X
 * Plab Acct.   :
 */
import java.util.*;
public class Storage {
    private  ArrayList<Box> _boxes;
    private  ArrayList<Item> _items;
    public Storage(){
        _boxes=new ArrayList<Box>();
        _items=new ArrayList<Item>();
        }
    public void run() {
        // treat this as your "main" method
        Scanner sc=new Scanner(System.in);
        int numOfBox=sc.nextInt();
        int handCap=sc.nextInt();
        int boxCap=sc.nextInt();
        int numOfQuery=sc.nextInt();
        _boxes.add(new Box(0,handCap));
        for(int i=1;i<=numOfBox;i++){
            _boxes.add(new Box(i,boxCap));
            }
        for(int j=0;j<numOfQuery;j++){
            String query=sc.next();
            if(query.equals("purchase")){
                String name=sc.next();
                int value=sc.nextInt();
                int boxNum=0;
                Item item=new Item(name,value);
                _items.add(item);
                for(int k=0;k<_boxes.size();k++){
                    if(_boxes.get(k).getCapacity()>_boxes.get(k).getSize()){
                        boxNum=k;
                        _boxes.get(k).deposit(item);
                        item.setLocation(_boxes.get(k));
                        break;

                        }
                    }
                if(boxNum==0){
                    System.out.println("item "+name+" is now being held");
                    }
                else{
                    System.out.println("item "+name+" has been deposited to box "+boxNum);
                    }    
                }
            if(query.equals("deposit")){
                boolean hasfound=false;
                String name=sc.next();
                for(int i=0;i<_items.size();i++){
                    if(_items.get(i).getName().equals(name)){
                        hasfound=true;
                        Item item=_items.get(i);
                        if(item.getLocation().getNumber()!=0){
                            System.out.println("item "+name+" is already in storage");
                            }
                        else{
                            for(int a=1;a<_boxes.size();a++){
                                if(_boxes.get(a).getCapacity()>_boxes.get(a).getSize()){
                                int boxNum=a;
                                _boxes.get(a).deposit(item);
                                _boxes.get(0).withdraw(item);
                                item.setLocation(_boxes.get(a));
                                System.out.println("item "+name+" has been deposited to box "+boxNum);
                                break;
                                    }
                                }
                            }
                        break;    
                        }
                    }
                if(hasfound==false){
                    System.out.println("item "+name+" does not exist");
                    }    
                }
            if(query.equals("withdraw")){
                boolean hasfound=false;
                String name=sc.next();
                for(int i=0;i<_items.size();i++){
                    if(_items.get(i).getName().equals(name)){
                        hasfound=true;
                        Item item=_items.get(i);
                        if(item.getLocation().getNumber()==0){
                            System.out.println("item "+name+" is already being held");
                            }
                        else{
                            if(_boxes.get(0).isFull()){
                                System.out.println("cannot hold any more items");
                                }
                            else{
                                Box location=item.getLocation();
                                location.withdraw(item);
                                _boxes.get(0).deposit(item);
                                item.setLocation(_boxes.get(0));
                                System.out.println("item "+name+" has been withdrawn");
                                }    
                            }
                        break;    
                  
                    }
                } 
                if(hasfound==false){
                    System.out.println("item "+name+" does not exist");
                    }
                }
                if(query.equals("location")){
                    String name=sc.next();
                    boolean hasfound=false;
                    for(int i=0;i<_items.size();i++){
                        if(_items.get(i).getName().equals(name)){
                            hasfound=true;
                            Item item=_items.get(i);
                            if(item.getLocation().getNumber()==0){
                                System.out.println("item "+name+" is being held");
                                }
                            else{
                                int num=item.getLocation().getNumber();
                                System.out.println("item "+name+" is in box "+num);
                                } 
                            break;    
                            }
                            
                        }
                    if(hasfound==false){
                        System.out.println("item "+name+" does not exist");
                        }    
                    }
                if(query.equals("valuable")){
                    ArrayList<String> itemName=new ArrayList<String>();
                    int highest=0;
                    for(int i=0;i<_items.size();i++){
                        if(_items.get(i).getValue()>highest){
                            highest=_items.get(i).getValue();
                            }
                            
                        }
                    for(int b=0;b<_items.size();b++){
                        if(_items.get(b).getValue()==highest){
                            itemName.add(_items.get(b).getName());
                            }
                        }
                    Collections.sort(itemName);
                    System.out.println(itemName.get(0));
                    }     

            }    
    }
    
    public static void main(String[] args) {
        Storage myStorageSystem = new Storage();
        myStorageSystem.run();
    }

}

class Box {
   
   // define appropriate attributes, constructor, and methods
   private ArrayList<Item> _items;
   private int _capacity;
   private int _number;

   public Box(int number, int capacity){
       _items=new ArrayList<Item>();
       _number=number;
       _capacity=capacity;
       }
   public boolean isFull(){
       return getSize()==getCapacity();
       }    
   public int getSize(){
       return this.getItems().size();
       }
   public ArrayList<Item> getItems(){
       return _items;
       }
   public int getCapacity(){
       return _capacity;
       }    
   public int getNumber(){
       return _number;
       }    
   public void deposit(Item item){
       _items.add(item);
       }
   public void withdraw(Item item){
       _items.remove(item);
       }    
}

class Item {
    // define appropriate attributes, constructor, and methods
    private String _name;
    private int _value;
    private Box _location;

    public Item(String name, int value){
        _name=name;
        _value=value;
        }
    public void setLocation(Box box){
        _location=box;
        }

    public String getName(){
        return _name;
        }
    public int getValue(){
        return _value;
        }    
    public Box getLocation(){
        return _location;
        }    
} 
