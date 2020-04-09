import java.io.*;

/**
 * Main class that takes in a command line argument for a filename containing
 * commands. It uses a parser object to parse the file and then execute the 
 * commands.
 * 
 * @author catsquillante, ramyanandigam
 *
 */
public class Rectangle1 {
	public static void main (String[] args) throws IOException {
		String fileName = args[0];
		
		//arg[0] = '/Users/catsquillante/eclipse-workspace/Rectangle1/src/input.txt'

		Parser parser = new Parser();
		parser.parseFile(fileName);

	}
}




