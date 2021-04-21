package classes;

import java.util.HashMap;
import java.util.ArrayList;

public class Organisation {
	
	private String name;
	private User head;
	private HashMap<String, String> address;
	private ArrayList<String> tags;
	
	
	public Organisation(String name, User head, HashMap<String, String> address, ArrayList<String> tags) {
		this.name = name;
		this.head = head;
		this.address = address;
		this.tags = tags;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public User getHead() {
		return head;
	}


	public void setHead(User head) {
		this.head = head;
	}


	public HashMap<String, String> getAddress() {
		return address;
	}


	public void setAddress(HashMap<String, String> address) {
		this.address = address;
	}


	public ArrayList<String> getTags() {
		return tags;
	}


	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
}
