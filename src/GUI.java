import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * Pattern Find Assigment
 * @author Rabindra Jaiswal
 * Version 1.0
 */
public class GUI extends JPanel{

	private static final long serialVersionUID = 1L;
	public static FileReader fr = new FileReader();
	public static ArrayList<String> PData = new ArrayList<String>();
	public static JFrame frame; 
	public static JPanel panel;
	public static String result = "";
	public static JTextArea re;
	public static String Files = "";
	public static String files = "";
	public static StringBuilder STB;
	
	public static String getFiles() {
		return Files;
	}
	public static void setFiles(String files) {
		Files = files;
	}
	
	/**
	 *Running these code user can load a directory full of files for comparison 
	 */
	public static void LoadDirectory() throws IOException {
		JFileChooser chooser = new JFileChooser();
		int status = chooser.showOpenDialog(null);
		chooser.setFileFilter(new FileNameExtensionFilter("Directory File", "Directory"));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		File[] filesInDirectory = chooser.getCurrentDirectory().listFiles();
		if (status == JFileChooser.APPROVE_OPTION) {
			for ( File file : filesInDirectory ) {
				setFiles("");
				setFiles(file.getAbsolutePath());
				STB.append("File : " + getFiles() + "\n" + "File Name : " + file.getName() + "\n");
				files = getFiles();
				//Gets the file from the dialog box and sends it to the traverse method of FileReader Class
				fr.traverse(files);
				//If no pattern is found in the file then
				if(fr.patternfoundcoun==0) {
					STB.append("Pattern Not Found" + "\n");
					re.setText(STB.toString());
				}
			}
		}
	}	
	
	/**
	 * Running these code user can load a single file into the program
	 */
	public static void LoadFile() throws IOException {
		File file;
		JFileChooser chooser = new JFileChooser();
		int status = chooser.showOpenDialog(null);
		
		if (status == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			setFiles(file.getAbsolutePath());
			System.out.println("File  : " + getFiles());
			System.out.println("File Name : " + file.getName());
			STB.append("File : " + getFiles() + "\n" + "File Name : " + file.getName() + "\n");
			files = getFiles();
			//Gets the path of the file from the dialog box and sends it to the traverse method of FileReader Class
			fr.traverse(files);
			if(fr.patternfoundcoun==0) {
				STB.append("Pattern Not Found" + "\n");
				re.setText(STB.toString());
			}
		}
	}
	/**
	 * Running these code user can defined pattern to be chosen to compare with the files
	 */
	public static void LoadPattern() throws IOException {
		File file;
		JFileChooser chooser = new JFileChooser();
		int status = chooser.showOpenDialog(null);
		
		if (status == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			setFiles(file.getAbsolutePath());
			STB.append("Pattern from file " + file.getName() + " added to the program\n");
			readfile();
		}
	}
	/**
	 * Program to load custom pattern file
	 */
	public static void readfile() throws IOException{
	    try {
			File myObj = new File(getFiles());
			Scanner myReader = new Scanner(myObj);
			int LineCounter = 0;
			//Reading a line at a time
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				Scanner lineReader = new Scanner(data);
				String hexValue = "";
				LineCounter++;
				//Converting a line into separate Strings
				while(lineReader.hasNext()) {
					String temp = lineReader.next();
					String regex = "[^0123456789ABCDEF]";
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(temp);
					int count = 0;
					//if the matcher finds character that are not available in the regex String the count is increased
					while(matcher.find()) {
						count++;
					}
					//If the count is greater than zero then the line of pattern has invalid characters
					if(count>0) {
						STB.append("Invalid Character(s) at line " + LineCounter + "\n");
						System.out.println("Invalid Character(s) at line " + LineCounter);
					}
	
					else if(temp.length()>2){
						STB.append("Invalid Number of character(s) at line " + LineCounter + "\n");
						System.out.println("Invalid Number of character(s) at line " + LineCounter);
					}
					else {
						hexValue = hexValue + temp;
					}
				}
				lineReader.close();
				PData.add(hexValue);
			}
			myReader.close();
	    } 
	    catch (FileNotFoundException e) {
	    	 STB.append("File not Found.");
			 System.out.println("File not Found." + "\n");
			 e.printStackTrace();
	    }
	    re.setText(STB.toString());
	}
	/**
	 * Setting up the graphical interface 
	 */
	public static void main(String[] args) {
       
        frame = new JFrame("Pattern find");
       
        panel = new JPanel();    
        panel.setPreferredSize(new Dimension(680, 320));
       
        JMenuBar menuBar = new JMenuBar();
        JMenu LoadMenu = new JMenu("File");
        LoadMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(LoadMenu);
       
		JMenuItem loadfile = new JMenuItem("Load File");
		LoadMenu.add(loadfile);
		loadfile.setMnemonic(KeyEvent.VK_F);
		loadfile.setToolTipText("File to check for pattern file");
		loadfile.setIcon(new ImageIcon("images/load.jpeg"));
		loadfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LoadFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem loaddir = new JMenuItem("Load Directory");
		LoadMenu.add(loaddir);
		loaddir.setMnemonic(KeyEvent.VK_D);
		loaddir.setToolTipText("Directory to check for pattern file");
		loaddir.setIcon(new ImageIcon("images/load.jpeg"));
		loaddir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LoadDirectory();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem loadpattern = new JMenuItem("Load Pattern");
		LoadMenu.add(loadpattern);
		loadpattern.setMnemonic(KeyEvent.VK_P);
		loadpattern.setToolTipText("Defind Pattern to the program for detection");
		loadpattern.setIcon(new ImageIcon("images/load.jpeg"));
		loadpattern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LoadPattern();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		JMenu helpMenu = new JMenu("Home");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(helpMenu);
	    JMenuItem btnAbout = new JMenuItem("About");
		helpMenu.add(btnAbout);
		btnAbout.setMnemonic(KeyEvent.VK_A);
		btnAbout.setToolTipText("Click to know about the program ALT+A");
		btnAbout.setIcon(new ImageIcon("images/about.png"));
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				About a = new About();
				a.setVisible(true);
			}
		});
		
		
		JMenuItem btnExit = new JMenuItem("Exit");
		LoadMenu.add(btnExit);
		btnExit.setMnemonic(KeyEvent.VK_E);
		btnExit.setToolTipText("Exit the program ALT+E");
		btnExit.setIcon(new ImageIcon("images/exit.png"));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.exit(0);
			}
		});
		
		
		frame.getContentPane().add(panel);
		STB = new StringBuilder();
		re = new JTextArea("", 18, 57);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(panel);
        frame.setSize(600,400);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(re);

    }
}