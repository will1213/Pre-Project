
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame
{
	JButton insert;
	JButton find;
	JButton browse;
	JButton create;
	TextArea area;
	MyListener listener;
	
	public GUI()
	{
		super("Main window");
		listener = new MyListener(this);
		setLayout(new BorderLayout());
		setSize(400,400);
		
		//pack();
		setVisible(true);
	}
	
	private void buildNorth()
	{
		JLabel label = new JLabel("An Application to Maintain Student Records", SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label.setBackground(Color.RED);
		add("North", label);
	}
	
	private void buildCenter()
	{
		area =  new TextArea("PRESS BROWSE TO SEE LIST");
		add("Center", area);
		
		
	}
	
	private void buildSouth()
	{
		JPanel subPanel = new JPanel();
		insert = new JButton("Insert");
		find = new JButton("Find");
		browse = new JButton("Browse");
		create = new JButton("Create Tree from File");
		
		insert.addActionListener(listener);
		find.addActionListener(listener);
		browse.addActionListener(listener);
		create.addActionListener(listener);
		
		subPanel.add(insert);
		subPanel.add(find);
		subPanel.add(browse);
		subPanel.add(create);
		
		add("South", subPanel);
	}
	
	public void buildAll()
	{
		buildNorth();
		buildCenter();
		buildSouth();
		
		setVisible(true);
	}
}
