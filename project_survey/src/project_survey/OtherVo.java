package project_survey;

public class OtherVo {
	private int list_number;
	private String input;
	
	public OtherVo(int list_number, String input) {
		super();
		this.list_number = list_number;
		this.input = input;
	}

	public int getList_number() {
		return list_number;
	}

	public void setList_number(int list_number) {
		this.list_number = list_number;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
	
	@Override
	public String toString() {
		return list_number + ". " + input ;
	}
}
