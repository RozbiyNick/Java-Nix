package classes;

public class Discussion {
	private String question;
	private User author;
	private Argument root;
	
	public Discussion(String question, User author, Argument root) {
		super();
		this.question = question;
		this.author = author;
		this.root = root;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Argument getRoot() {
		return root;
	}
	public void setRoot(Argument root) {
		this.root = root;
	}
}
