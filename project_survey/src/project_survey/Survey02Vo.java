package project_survey;

public class Survey02Vo {
	private int number;
	private String list;
	private int vote;
	
	
	public Survey02Vo(int number, String list, int vote) {
		super();
		this.number = number;
		this.list = list;
		this.vote = vote;
	}

	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getList() {
		return list;
	}


	public void setList(String list) {
		this.list = list;
	}


	public int getVote() {
		return vote;
	}


	public void setVote(int vote) {
		this.vote = vote;
	}


	@Override
	public String toString() {
		return number + ". " + list + " ====> " + vote + "ǥ";
	}
	
	
	
}
