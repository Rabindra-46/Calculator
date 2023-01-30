/**
 * Pattern Find Assigment
 * @author Rabindra Jaiswal
 * Version 1.0
 */
public class PatternCompare {
	private byte[] bytes;
	private int checkPs = 0;
	/**
	 * It returns true if Pattern is matched and false if the pattern is not matched
	 */
	public boolean chNxt(byte value) {
		boolean checkValue = false;
		if(value == -1) {
			checkValue = false;
		} 
		
		try {
			if(value==bytes[checkPs]) {
				checkPs++;
				if(checkPs==bytes.length) {
					checkValue = true;
					checkPs = 0;
				}
			}
			else {
				checkPs = 0;
				checkPs++;
				checkValue = false;
			}
		}
		catch(IndexOutOfBoundsException e ) {
			System.out.println(e.getMessage());
		}
		
		return checkValue;
	}
	public void setBytes(byte bytes[]) {
		this.bytes = bytes;
	}

}
