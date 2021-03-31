package classes;

public class Argument {
	
	private String thesis;
	private String explanation;
	private String evidences;
	private int father_id;
	private User author;
	
	public Argument(String thesis, String explanation, String evidences, int father, User author) {
		super();
		this.thesis = thesis;
		this.explanation = explanation;
		this.evidences = evidences;
		this.father_id = father;
		this.author = author;
	}
	public String getThesis() {
		return thesis;
	}
	public void setThesis(String thesis) {
		this.thesis = thesis;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getEvidences() {
		return evidences;
	}
	public void setEvidences(String evidences) {
		this.evidences = evidences;
	}
	public int getFather() {
		return father_id;
	}
	public void setFather(int father_id) {
		this.father_id = father_id;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
}
