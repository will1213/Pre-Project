import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class MyListener implements ActionListener 
{
	private GUI frame;
	private BinSearchTree tree;
	
	public MyListener(GUI f)
	{
		frame = f;
		tree = new BinSearchTree();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == frame.create)
		{
			String fileName = JOptionPane.showInputDialog("Enter the file name (*.txt): ");
			try
			{
				FileReader fr = new FileReader(fileName);
				Scanner file = new Scanner(fr);
				ArrayList<String> initialList = new ArrayList<String>();
				tree = new BinSearchTree();
				
				while(file.hasNext())
				{
					initialList.add(file.nextLine());
				}
				
				for(String s: initialList)
				{
					String[] sArray = s.split("\\s+"); // Tokenizes line by taking away any amount of spaces.
					tree.insert(sArray[1], sArray[2], sArray[3], sArray[4]);
					
					//System.out.println(sArray[4]);
					/*
					for(String t : sArray)
					{
						System.out.print(t + "");
					}
					System.out.println();*/
				}
				JOptionPane.showMessageDialog(null, fileName + " has been inserted into a tree.");
			}
			catch(IOException er)
			{
				JOptionPane.showMessageDialog(null, "File not found.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == frame.browse)
		{
			if(tree.empty())
			{
				JOptionPane.showMessageDialog(null, "Cannot browse. The tree is empty!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				StringWriter buffer = new StringWriter ();
				PrintWriter writer = new PrintWriter(buffer);
				try 
				{
					tree.print_tree(tree.root, writer);
					String contents = buffer.toString();
					frame.area.setText(contents);
				} 
				catch (IOException e1) 
				{
					JOptionPane.showMessageDialog(null, "Error printing to text area.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(e.getSource() == frame.insert)
		{
			JTextField id = new JTextField();
			JTextField faculty = new JTextField();
			JTextField major = new JTextField();
			JTextField year = new JTextField();
			
			JComponent[] inputs = new JComponent[] {new JLabel("Student id: "), id, new JLabel("Faculty: "), faculty
					, new JLabel("Major: "), major, new JLabel("Year: "), year };
			Object[] options= {"Insert","Return to main menu"};
			int result = JOptionPane.showOptionDialog(null,  inputs, "Insert new node",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
			if(result==JOptionPane.YES_OPTION)
			tree.insert(id.getText(), faculty.getText(), major.getText(), year.getText());
		}
		else if(e.getSource() == frame.find)
		{
			String id = JOptionPane.showInputDialog("Please enter student's id: ");
			if(id!=null) {
			Node n = tree.find(tree.root, id);
			if(n == null)
			{
				JOptionPane.showMessageDialog(null, "Student was not found.");
			}
			else
			{
				JOptionPane.showMessageDialog(null, n);
			}
			}
		}
	}
	
}
