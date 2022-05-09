package project_survey;


public class Survey01Vo{
	private int number;
	private String list;
	
	public Survey01Vo(int number, String list) {
		super();
		this.number = number;
		this.list = list;
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

	@Override
	public String toString() {
		return number + ". " + list ;
	}

	
	
	
	
}