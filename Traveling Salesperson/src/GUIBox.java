
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

//decided to not use GUI, so this can be ignored
public class GUIBox extends JFrame implements ActionListener {
	JPanel myPanel;
	JButton myButton;
	JTextField inputField;
	JTextArea citiesArea;
	JTextArea pathArea;
	JTextArea costArea;
	JTextArea timeArea;
	JLabel inputLabel;
	JLabel citiesLabel;
	JLabel pathLabel;
	JLabel costLabel;
	JLabel timeLabel;
	

	public GUIBox() { 	//constructor for GUIBox
		setSize(600,500);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		myPanel = new JPanel(); 
		
		myButton = new JButton("Find best path"); 
		myButton.addActionListener(this); 
		myButton.setBounds(100,70,250,40);
		
		inputField = new JTextField(); 
		inputField.addActionListener(this); 
		inputField.setBounds(100,10,200,20); //coordinates of upper left corner, width, height
		
		citiesArea = new JTextArea();
		citiesArea.setBounds(100, 120, 400, 40);
		
		pathArea = new JTextArea();
		pathArea.setBounds(100, 210, 400, 40);
		
		costArea = new JTextArea();
		costArea.setBounds(100, 300, 400, 40);
		
		timeArea = new JTextArea();
		timeArea.setBounds(100, 390, 400, 40);
				
		//this text field will display the word/character count and result of pressing buttons
		inputLabel = new JLabel("Enter file path here");	
		inputLabel.setBounds(100, 35, 200, 20);

		citiesLabel = new JLabel("Number of cities"); 
		citiesLabel.setBounds(100,175,200,20);
		
		pathLabel = new JLabel("Best path");
		pathLabel.setBounds(100, 215, 50, 20);
		
		costLabel = new JLabel("Lowest cost");
		costLabel.setBounds(150, 305, 50, 20);
		
		timeLabel = new JLabel("Time to run");
		timeLabel.setBounds(100, 395, 50, 20);

		myPanel.setLayout(null); 
		myPanel.add(myButton); 
		myPanel.add(inputField);
		myPanel.add(citiesArea);
		myPanel.add(pathArea);
		myPanel.add(costArea);
		myPanel.add(timeArea);
		myPanel.add(myButton);
		myPanel.add(inputLabel);
		myPanel.add(pathLabel);
		myPanel.add(costLabel);
		myPanel.add(timeLabel);
		
		add(myPanel); 
		setVisible(true);
	}//end of GUIBox constructor
		
	public void actionPerformed(ActionEvent evt) 
	{ 
		if(evt.getSource() == myButton)	{
			try { 
				double startTime = System.nanoTime();
				Scanner fileReader = new Scanner(new File(inputField.getText()));
				fileReader.nextLine();
				String sFirstLine = fileReader.nextLine();		//reads first line of file
				String[] aFirstLine = sFirstLine.split(" ");	//creates String array of first line
				int length = aFirstLine.length;					//determines length of line, and matrix as well
				int[][] matrix = new int[length][length];		//creates empty matrix
				for(int col = 0; col < length; col++) {			//populates first line of matrix
					matrix[0][col] = Integer.parseInt(aFirstLine[col]);
				}
				int[] cities = new int[length];		//new array of cities to be visited (0, 1, 2, up to length of first line - 1)
				for(int i = 0; i < length; i++) {
					cities[i]= i;
				}
				for(int row = 1; row < length; row++) {			//populates entire matrix
					for(int col = 0; col < length; col++) {
						matrix[row][col] = fileReader.nextInt();
					}
				}
				//Permutator p = new Permutator(cities, matrix);	//without branch and bound
				BranchBound p = new BranchBound(cities, matrix);	//with branch and bound
				p.perm(1);
				citiesArea.setText("Number of cities: " + length);
				pathArea.setText("Best path: " + p.getBestPath());
				costArea.setText("Minimum cost: " + p.getMinCost());
				double endTime = System.nanoTime();
				timeArea.setText("Total time to run: " + ((endTime - startTime) / 1000000000.0) + " seconds");
			} catch(Exception e) {
				System.out.println(e.toString());
			}
		} else {		
		}
	}//end of actionPerformed method

}
