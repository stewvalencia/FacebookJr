package model;

import java.util.ArrayList;
/*Wall Apps stores all posts and add posts a Person has
 * 
 */
public class WallApplication extends Application {
    private ArrayList<String> posts = new ArrayList<String>();//Stores posts
    //Constructor
    public WallApplication(){
    	super("Wall", "FacebookJr", "http://www.notreal.really" );
    }
    //Adds posts to its list
    public void addPost(String name, String post){
    	String posting = "Posting by: " + name + "\n" + post;
    	posts.add(posting);
    }
    //returns posts
    public ArrayList<String> getPosts(){
        return posts;
    }

}
