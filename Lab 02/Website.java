/**
 * Name         : GONG CHANGDA
 * Matric No.   : A0162477X
 * PLab Acct.   : plab7213
 */

import java.util.*;

public class Website {

	// define your own attributes, constructor, and methods here
	private HashMap<String,User> _users;
	private HashMap<Integer,Photo> _photos;

	public Website(){
		_users=new HashMap<String,User>();
		_photos=new HashMap<Integer,Photo>();
	}
	private void run() {
		Scanner sc=new Scanner(System.in);
		int numOfOps;
		numOfOps=sc.nextInt();
		// below code is to read in and handle the query accordingly
		for(int i=0;i<numOfOps;i++){
			String query=sc.next();
			if(query.equals("UPLOAD")){
				String userName=sc.next();
				int userId=sc.nextInt();
				handleUpload(userName,userId);
			}
			else if(query.equals("TAG")){
				int id=sc.nextInt();
				String name=sc.next();
				handleTag(id,name);
			}
			else if(query.equals("TAGGED_PHOTOS")){
				String name=sc.next();
				handleTaggedQuery(name);
			}
			else if(query.equals("SELF_TAG")){
				handleSelfTag();
			}
			else{
				int numOfUsers=_users.size();
				int numOfPhotos=_photos.size();
				System.out.println("Total: "+numOfUsers+" user(s), "+numOfPhotos+" photo(s).");
			}	
		}
	}
	//compute number of selftagged user and print accordingly.
	private void handleSelfTag(){
		int count=0;
		for(User user:_users.values()){
			if(isSelfTagged(user)){
				count++;
			}
		}
		System.out.println("There are "+count+" user(s) that have tagged themselves.");	

	}
	// this is a helper function to determine whether a particular user is self tagged.
	// precondition:a user object which has already initialized two arraylist attributes is passed in.
	// postcondition: nothing inside the user is changed,only return true or false.
	private boolean isSelfTagged(User user){
		for(int j=0;j<user.getPhoto().size();j++){
			if(user.getPhotoTagged().contains(user.getPhoto().get(j))){
				return true;
			}
		}
		return false;	
	}

	// below is to handle the query of asking number of tagged photos of the user.
	// precondition:a user object which has already initialized _photoTagged is passed in.
	// postcondition: nothing inside the user has been changed.
	private void handleTaggedQuery(String name){
		if(_users.get(name)==null){
			System.out.println("No user "+name+" exists.");
			return;
		}
		else{
			User user=_users.get(name);
			System.out.println("User "+name+" is tagged in "+user.getPhotoTagged().size()+" photo(s).");
		}
	}
	// below is to tag user with photo.
	// precondition:a valid integer and string are passed in.
	private void handleTag(int id,String name){
		if(_users.get(name)==null){
			User newuser=new User(name);
			_users.put(name,newuser);
			System.out.println("New user "+name+" created.");
		}
		if(_photos.get(id)==null){
			System.out.println("No photo "+id+" exists.");
			return;
		}
		Photo photo=_photos.get(id);
		User user=_users.get(name);
		if(photo.getUsers().contains(user)){
			System.out.println("User "+name+" is already tagged in photo "+id+".");
		}
		else{
			photo.getUsers().add(user);
			user.getPhotoTagged().add(photo);
			System.out.println("User "+name+" tagged successfully in photo "+id+".");
		}
	}

	// below is to handle the operation of user uploading photos.

	private void handleUpload(String name,int id){
		if(_users.get(name)==null){
			User newUser=new User(name);
			_users.put(name,newUser);
			Photo newPhoto= new Photo(id);	
			_photos.put(id,	newPhoto);
			newUser.getPhoto().add(newPhoto);
			System.out.println("New user "+name+" created.");
			System.out.println("Photo "+id+" uploaded successfully by "+name+".");

		}
		else{
			User user=_users.get(name);
			Photo newPhoto=new Photo(id);
			user.getPhoto().add(newPhoto);
			_photos.put(id,newPhoto);
			System.out.println("Photo "+id+" uploaded successfully by "+name+".");
		}

	} 

	public static void main(String[] args) {
		Website website = new Website();
		website.run();
	}
}

class User {

	// define your own attributes, constructor, and methods here
	private String _userName;
	private ArrayList<Photo> _photoUploaded;//store the photo uploaded 
	private ArrayList<Photo> _photoTagged; // store the photos that are tagged to this user.

	public User(String userName){
		_userName=userName;
		_photoUploaded=new ArrayList<Photo>();//initialization
		_photoTagged=new ArrayList<Photo>();//initialization
	}

	public String getName(){
		return _userName;
	}

	public ArrayList<Photo> getPhoto(){
		return _photoUploaded;
	}
	public ArrayList<Photo> getPhotoTagged(){
		return _photoTagged;
	}	   
}

class Photo {
	// define your own attributes, constructor, and methods here
	private int _photoId;
	private ArrayList<User> _taggedUser; //store the users that is tagged to this photo.


	public Photo(int photoId){
		_photoId=photoId;
		_taggedUser=new ArrayList<User>();
	}
	public int getId(){
		return _photoId;
	}
	public ArrayList<User> getUsers(){
		return _taggedUser;
	}		
}
