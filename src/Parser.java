import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Parses the input file based on command.
 * 
 * @author ramyanandigam, catherinesquillante
 */
public class Parser {

	/**
	 * Parses the command file. Separates the command from the parameters
	 * and then uses a manager object to call the appropriate command with 
	 * given parameters.
	 * 
	 * @param filename file to parse
	 */
	public void parseFile(String filename) {

		try {
			Scanner sc = new Scanner(new File(filename));
			Manager manager = new Manager();

			while (sc.hasNextLine()) {

				String line = sc.nextLine();
				while (line == null || line.isEmpty() || line.trim().equals("") && sc.hasNextLine()) {
					try {
						line = sc.nextLine();
					}
					catch (NoSuchElementException e){
						return;
					}
				}
				line = line.trim();
				line = line.replaceAll("[\\n\\t]", " ");
				if (line.substring(0, 1).equals(" ")) {
					line = line.substring(1);
				}

				String[] splitLine = line.split("\\s+");

				String cmd = splitLine[0].trim();

				if(cmd.contentEquals("insert")) {
					String insertName = splitLine[1];
					Integer iXCoord = Integer.parseInt(splitLine[2]);
					Integer iYCoord = Integer.parseInt(splitLine[3]);
					Integer iWidth = Integer.parseInt(splitLine[4]);
					Integer iHeight = Integer.parseInt(splitLine[5]);

					manager.insert(insertName, iXCoord, iYCoord,iWidth, iHeight);
				}
				else if(cmd.contentEquals("remove")) {
					// include both remove name & remove x,y,w,h
					if(splitLine[1].matches("-?\\d+")) {
						Integer x = Integer.parseInt(splitLine[1]);
						Integer y = Integer.parseInt(splitLine[2]);
						Integer w = Integer.parseInt(splitLine[3]);
						Integer h = Integer.parseInt(splitLine[4]);
						manager.remove(x,y,w,h);;
					}
					else {
						String removeName = splitLine[1];
						manager.remove(removeName);
					}
				}

				else if(cmd.contentEquals("regionsearch")) {
					Integer rsX = Integer.parseInt(splitLine[1]);
					Integer rsY = Integer.parseInt(splitLine[2]);
					Integer rsWidth = Integer.parseInt(splitLine[3]);
					Integer rsHeight = Integer.parseInt(splitLine[4]);
					manager.regionsearch(rsX, rsY, rsWidth, rsHeight);

				}
				else if(cmd.contentEquals("intersections")) {

					manager.intersections();
				}

				else if(cmd.contentEquals("search")) {
					String searchName = splitLine[1];
					manager.search(searchName);

				}

				else if(cmd.contentEquals("dump")) {

					manager.dump();
				}
				
			}
			sc.close();
		}

		catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}

}