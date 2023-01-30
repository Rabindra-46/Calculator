import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
/**
 * Pattern Find Assigment
 * @author Rabindra Jaiswal
 * Version 1.0
 */
class OutputTest {
	FileReader fr = new FileReader();
	@Test
	public void TestingOutput() throws IOException {
		
		String filepath = "text1.txt";
		
		fr.traverse(filepath);
		
		String tobetested = FileReader.toJUTest;
		
		String testingline = "Pattern Found: 414243, at offset: 57 (0x39)  within the file\n";
		testingline =  testingline + "Pattern Found: 58595A, at offset: 65 (0x41)  within the file\n";
		
		assertEquals(tobetested,testingline);
	}

}
