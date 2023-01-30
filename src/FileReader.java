import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
/**
 * Pattern Find Assigment
 * @author Rabindra Jaiswal
 * Version 1.0
 */
public class FileReader {
	PatternCompare pn;
	byte[] bytes = null;
	public static ArrayList<String> hexValue = new ArrayList<String>();
	public static long fp = 0;
	int patternfoundcoun = 0;
	StringBuilder sb = new StringBuilder();
	public static String toJUTest = "";
	
	/**
	 * Pattern loaded from the file or runs two times if no file is loaded on
	 * (byte pattern 414243 and 58595A) 
	 */
	public void traverse(String Filepath) throws IOException {
		pn = new PatternCompare();
		String hexOffset = "";
		//Checks for the pattern and if there is valid patterns it loads it into this porgram
		if(GUI.PData.size() != 0) {
			hexValue.clear();
			for(int i = 0; i<GUI.PData.size(); i++) {
			    hexValue.add(GUI.PData.get(i));
			}
		}
		//If no pattern is loaded in program 
		else {
			hexValue.clear();
			hexValue.add("414243");
			hexValue.add("58595A");
		}
		//This loop runs for the number of pattern loaded by the use or 2 times by default
		for(int i = 0; i<hexValue.size() ; i++) {
			bytes = new BigInteger(hexValue.get(i),16).toByteArray();
			pn.setBytes(bytes);
			
			try {
				InputStream IS = new BufferedInputStream(new FileInputStream(Filepath));
	            int next=0;	
				while((next = IS.read()) != -1) {
					//converting into a 8 bit byte
					byte nextByte = (byte)next;	
					boolean flag = pn.chNxt(nextByte);
					//Pattern is matched form the Patterncomparision Class when flag set is true
					if(flag) {
						hexOffset = Integer.toHexString((int) (fp+1-bytes.length));
						patternfoundcoun++;
						sb.append("Pattern Found: " + hexValue.get(i) + ", at offset: "+ (fp+1-bytes.length) + " (0x"+hexOffset + ") "  + " within the file" + "\n");
						GUI.STB.append("Pattern Found: " + hexValue.get(i) + ", at offset: "+ (fp+1-bytes.length) + " (0x"+hexOffset + ") "  + " within the file" + "\n");
					}
					fp++;
				}
			
				GUI.re.setText(GUI.STB.toString());
				IS.close();
				fp = 0;
			}
			catch(Exception e){
		
			}
			hexOffset = "";
			fp = 0;
		}
		//testing Class
		toJUTest = sb.toString();
	}
	public static void main(String... aArgs) throws IOException {
	}
}
