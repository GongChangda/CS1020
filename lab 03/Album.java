/**
 * Name         :Gong Changda
 * Matric No.   :A0162477X
 * PLab Acct.   :plab7373
 */

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.NoSuchElementException;
// Check with a LabTA before you decide to import anything else...
// Using other Collection classes and arrays might result in 0 marks

public class Album {
	private LinkedList<Photo> _albumA;
	private LinkedList<Photo> _albumB;
	private LinkedList<String> _stackA;
	private LinkedList<String> _stackB;
	// define your own attributes, constructor, and methods here
	public Album(){
		_albumA=new LinkedList<Photo>();
		_albumB=new LinkedList<Photo>();
		_stackA=new LinkedList<String>();
		_stackB=new LinkedList<String>();
	}
	private void run() {
		Scanner sc=new Scanner(System.in);
		int numOfOp=sc.nextInt();
		// below is to read the query and process them accordingly.
		for(int i=0;i<numOfOp;i++){
			String query=sc.next();
			if(query.equals("INSERT")){
				String albumId=sc.next();
				int position=sc.nextInt();
				int photoId=sc.nextInt();
				handleInsertion(albumId,position,photoId);
			}
			else if(query.equals("DELETE")){
				String albumId=sc.next();
				int position=sc.nextInt();
				handleDeletion(albumId,position);
			}	
			else if(query.equals("PREVIEW")){
				String albumId=sc.next();
				handlePreview(albumId);
			}	
			else if (query.equals("UNDO")){
				String albumId=sc.next();
				handleUndo(albumId);
			}	
			else{
				handleCount();
			}	
		}
	}
	//precondition:all the albums have been initialized.
	//postcondition:create a new photo with photo id and insert the new photo in the album if the given position is valid.
	private void handleInsertion(String albumId,int position,int photoId){
		if(albumId.equals("A")){
			insert(_albumA,position,photoId,albumId,_stackA);// call another helper method called insert to perform insertion and thus avoid duplicate code.
		}
		else{
			insert(_albumB,position,photoId,albumId,_stackB);
		}
	}
	private void insert(LinkedList<Photo> album,int position,int photoId,String albumId,LinkedList<String> stack){
		if(position>album.size()){
			System.out.println("Invalid position,album "+albumId+" only has "+album.size()+" photos.");
		}
		else{
			Photo photo=new Photo(photoId);
			album.add(position,photo);
			System.out.println("Photo "+photoId+" inserted after position "+position+" of album "+albumId+".");	
			stack.push("INSERT"+albumId+position+photoId);	//store this change into the stack
		}	
	}
	//precondition:two albums have been initialized.
	//postcondition:delete the photo in the specified position if the given position is valid.
	private void handleDeletion(String albumId, int position){
		if (albumId.equals("A")){
			delete(_albumA,position,albumId,_stackA);// call the delete helper method perform deletion.
		}
		else{
			delete(_albumB,position,albumId,_stackB);
		}	
	}
	private void delete(LinkedList<Photo> album,int position, String albumId,LinkedList<String> stack){
		if(position>album.size()){
			System.out.println("invalid position,album "+albumId+" only has "+album.size()+" photos.");
		}
		else if(position==0){
			System.out.println("invalid position 0.");
		}	
		else{
			album.remove(position-1);
			System.out.println("Photo deleted from postion "+position+" of album "+albumId+".");
			stack.push("DELETE"+albumId+position);
		}	
	}
	//precondition: two albums have been initialized
	//postcondition:print out the photos in the specified album in the specified format.
	private void handlePreview(String albumId){
		if(albumId.equals("A")){
			preview(_albumA,albumId);// call the helper preview method to perform preview.
		}
		else{
			preview(_albumB,albumId);
		}	
	}
	private void preview(LinkedList<Photo> album,String albumId){
		String result="";
		result=result+"Album "+albumId+":"+" [";
		ListIterator<Photo> iter=album.listIterator();
		String photoIds="";
		if(album.size()==0){

		}
		else if(album.size()==1){
			photoIds+=iter.next().getId();
		}
		else{
			for(int idx=1;idx<album.size();idx++){
				photoIds=photoIds+iter.next().getId()+", ";
			}// first print all the photos'id except the lastone.
			photoIds+=iter.next().getId();	//print the last one.
		}	
		System.out.println(result+photoIds+"].");
	}	
	//precondition:Two albums and two stacks have been initialized.
	//postcondition: Undo the change if possible.
	private void handleUndo(String albumId){
		if(albumId.equals("A"))
			Undo(_albumA,_stackA,albumId);
		else
			Undo(_albumB,_stackB,albumId);
	}
	private void Undo(LinkedList<Photo> album,LinkedList<String> stack,String albumId){
		if(stack.size()==0){
			System.out.println("No changes in album "+albumId+" to undo.");
		}
		else{
			String operation=stack.pop();
			if(operation.substring(0,6).equals("INSERT")){
				String position=operation.substring(7,8);
				int posi=Integer.parseInt(position);
				handleDeletion(albumId,posi);
			}
			else{
				// to be finished
			}	
		}
	}
	//precondition: two albums have been initialized.
	//postcondition: count the total number of distinct album.
	private void handleCount(){
		ListIterator<Photo> iterA=_albumA.listIterator();
		ListIterator<Photo> iterB=_albumB.listIterator();
		LinkedList<Photo> counted=new LinkedList<Photo>();
		int count=0;
		while(iterA.hasNext()){
			Photo currPhoto=iterA.next();
			if(counted.contains(currPhoto)){

			}
			else{
				counted.add(currPhoto);
				count++;
			}	
		}
		while(iterB.hasNext()){
			Photo currPhoto=iterB.next();
			if(counted.contains(currPhoto)){ // check whether already count this photo.
			}
			else{
				counted.add(currPhoto);
				count++;
			}	   
		}		
	}	

	public static void main(String[] args) {
		Album newAlbum = new Album();
		newAlbum.run();
	}
}

class Photo{
	private int _id;

	public Photo(int id){
		_id=id;
	}

	public int getId(){
		return _id;
	}	
	//override the equals method so that we can use contains to check whether photo already counted.
	public boolean equals(Object another){
		if(another!=null && another instanceof Photo){
			return getId()==((Photo) another).getId();
		}
		return false;	
	}	
}
