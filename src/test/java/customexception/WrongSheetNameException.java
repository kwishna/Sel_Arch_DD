package customexception;

public class WrongSheetNameException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public WrongSheetNameException(String exception) {
		super(exception);
	}
}
