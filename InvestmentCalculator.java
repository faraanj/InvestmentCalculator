/*
Written By: Faraan Javaid
Date: May 25 2021
Application: This application can calculate different financial problems regarding
investments and loans using the concept of the time value of money
*/
package FinalProject;

// import java API
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

//creates a class that holds the main frame of the calculator
public class InvestmentCalculator extends JFrame implements ActionListener {
	
	//creates labels, buttons, text fields, combo boxes, and panels
	JLabel label_title, label_present, label_future, label_rate, label_period, label_compounding;
	JButton button_present, button_future, button_reset, button_help;
	JTextField txt_present, txt_future, txt_rate, txt_period;
	JComboBox combo_annual, combo_period, combo_compounding;
	JPanel panel, title_panel, present_panel, future_panel, annual_panel, period_panel, compounding_panel, end_panel,
			explanation_panel, link_panel;
	
	//creates an array for the annual and period combo boxes
	String[] time_rate = { "Annually", "Semi-Anually", "Quarterly", "Bi-monthly", "Monthly", "Semi-Monthly",
			"Bi-Weekly", "Weekly", "Daily" };
	//creates an array for the compounding combo box
	String[] compounding_rate = { "Annually", "Semi-Anually", "Quarterly", "Bi-monthly", "Monthly", "Semi-Monthly",
			"Bi-Weekly", "Weekly", "Daily", "No Compounding" };

	//initializes variables for a number for the user's input and required calculation
	double present_num, future_num, rate_num, period_num, rateTerm_num, periodTerm_num, compoundTerm_num;
	
	//checks if there are no errors in the user's input
	boolean noErrors = true;
	
	//holds the code for the main frame with the calculator
	public InvestmentCalculator() {

		//constructs a label for the title and adds it to the panel
		label_title = new JLabel("Investment Calculator");
		title_panel = new JPanel();
		title_panel.add(label_title);
		
		//constructs a text box, label, and button for the present value section of the calculator and adds it to the panel
		label_present = new JLabel("Present Value");
		txt_present = new JTextField(15);
		button_present = new JButton("Calculate PV");
		present_panel = new JPanel();
		present_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		present_panel.add(label_present);
		present_panel.add(Box.createRigidArea(new Dimension(20,0)));
		present_panel.add(txt_present);
		present_panel.add(Box.createRigidArea(new Dimension(10,0)));
		present_panel.add(button_present);
		//adds an action listener for the button to calculate the present value
		button_present.addActionListener(this);

		//constructs a text box, label, and button for the future value section of the calculator and adds it to the panel
		label_future = new JLabel("Future Value");
		txt_future = new JTextField(15);
		button_future = new JButton("Calculate FV");
		future_panel = new JPanel();
		future_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		future_panel.add(label_future);
		future_panel.add(Box.createRigidArea(new Dimension(25,0)));
		future_panel.add(txt_future);
		future_panel.add(Box.createRigidArea(new Dimension(10,0)));
		future_panel.add(button_future);
		//adds an action listener for the button to calculate the future value
		button_future.addActionListener(this);

		//constructs a text box, label, and combo box for the annual rate value section of the calculator and adds it to the panel
		label_rate = new JLabel("Annual Rate (%)");
		txt_rate = new JTextField(15);
		//combo box uses the time rate options and is set as Annually to start
		combo_annual = new JComboBox(time_rate);
		combo_annual.setSelectedIndex(0);
		annual_panel = new JPanel();
		annual_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		annual_panel.add(label_rate);
		annual_panel.add(Box.createRigidArea(new Dimension(10,0)));
		annual_panel.add(txt_rate);
		annual_panel.add(Box.createRigidArea(new Dimension(10,0)));
		annual_panel.add(combo_annual);
		//adds an action listener for the combo box so the user can select whatever time they choose
		combo_annual.addActionListener(this);
		
		//constructs a text box, label, and combo box for the period value section of the calculator and adds it to the panel
		label_period = new JLabel("Periods");
		txt_period = new JTextField(15);
		//combo box uses the time rate options and is set as Monthly to start
		combo_period = new JComboBox(time_rate);
		combo_period.setSelectedIndex(4);
		period_panel = new JPanel();
		period_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		period_panel.add(label_period);
		period_panel.add(Box.createRigidArea(new Dimension(55,0)));
		period_panel.add(txt_period);
		period_panel.add(Box.createRigidArea(new Dimension(10,0)));
		period_panel.add(combo_period);
		//adds an action listener for the combo box so the user can select whatever time they choose
		combo_period.addActionListener(this);

		//constructs a label and combo box for the compounding rate section of the calculator and adds it to the panel
		label_compounding = new JLabel("Compounding");
		//combo box uses the compounding rate options and is set as Monthly to start
		combo_compounding = new JComboBox(compounding_rate);
		combo_compounding.setSelectedIndex(4);
		compounding_panel = new JPanel();
		compounding_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		compounding_panel.add(label_compounding);
		compounding_panel.add(Box.createRigidArea(new Dimension(175,0)));
		compounding_panel.add(combo_compounding);
		//adds an action listener for the combo box so the user can select whatever time they choose
		combo_compounding.addActionListener(this);
		
		//constructs two buttons, one that resets all user input to the original and another that opens a second frame to a help section and adds the buttons to the panel
		button_reset = new JButton("Reset");
		button_help = new JButton("Help");
		end_panel = new JPanel();
		end_panel.add(button_reset);
		end_panel.add(button_help);
		end_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
		//adds an action listener for both buttons
		button_help.addActionListener(this);
		button_reset.addActionListener(this);
		
		//adds the different small panels to a large panel and is added to the content pane, layout is created between the smaller panels
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(title_panel);
		panel.add(Box.createVerticalStrut(10));
		panel.add(present_panel);
		panel.add(Box.createVerticalStrut(10));
		panel.add(future_panel);
		panel.add(Box.createVerticalStrut(10));
		panel.add(annual_panel);
		panel.add(Box.createVerticalStrut(10));
		panel.add(period_panel);
		panel.add(Box.createVerticalStrut(10));
		panel.add(compounding_panel);
		panel.add(Box.createVerticalStrut(10));
		panel.add(end_panel);
		panel.add(Box.createVerticalStrut(10));
		this.getContentPane().add(panel);
		
		//sets the initial values for the combo box selections
		rateTerm_num = 1;
		periodTerm_num = 12;
		compoundTerm_num = 12;
	}
	
	//program action if some action is performed
	public void actionPerformed(ActionEvent evt) {
		//when user interacts with annual rate combo box, a number is assigned based on the number selected
		if (evt.getSource() == combo_annual) {
			if (combo_annual.getSelectedItem() == time_rate[0]) {
				rateTerm_num = 1;
			} else if (combo_annual.getSelectedItem() == time_rate[1]) {
				rateTerm_num = 2;
			} else if (combo_annual.getSelectedItem() == time_rate[2]) {
				rateTerm_num = 4;
			} else if (combo_annual.getSelectedItem() == time_rate[3]) {
				rateTerm_num = 6;
			} else if (combo_annual.getSelectedItem() == time_rate[4]) {
				rateTerm_num = 12;
			} else if (combo_annual.getSelectedItem() == time_rate[5]) {
				rateTerm_num = 24;
			} else if (combo_annual.getSelectedItem() == time_rate[6]) {
				rateTerm_num = 26;
			} else if (combo_annual.getSelectedItem() == time_rate[7]) {
				rateTerm_num = 52;
			} else if (combo_annual.getSelectedItem() == time_rate[8]) {
				rateTerm_num = 365;
			} else {
				rateTerm_num = 1;
			}

		}
		//when user interacts with period rate combo box, a number is assigned based on the number selected
		if (evt.getSource() == combo_period) {
			if (combo_period.getSelectedItem() == time_rate[0]) {
				periodTerm_num = 1;
			} else if (combo_period.getSelectedItem() == time_rate[1]) {
				periodTerm_num = 2;
			} else if (combo_period.getSelectedItem() == time_rate[2]) {
				periodTerm_num = 4;
			} else if (combo_period.getSelectedItem() == time_rate[3]) {
				periodTerm_num = 6;
			} else if (combo_period.getSelectedItem() == time_rate[4]) {
				periodTerm_num = 12;
			} else if (combo_period.getSelectedItem() == time_rate[5]) {
				periodTerm_num = 24;
			} else if (combo_period.getSelectedItem() == time_rate[6]) {
				periodTerm_num = 26;
			} else if (combo_period.getSelectedItem() == time_rate[7]) {
				periodTerm_num = 52;
			} else if (combo_period.getSelectedItem() == time_rate[8]) {
				periodTerm_num = 365;
			} else {
				periodTerm_num = 12;
			}
		}
		//when user interacts with compound rate combo box, a number is assigned based on the number selected
		if (evt.getSource() == combo_compounding) {
			if (combo_compounding.getSelectedItem() == compounding_rate[0]) {
				compoundTerm_num = 1;
			} else if (combo_compounding.getSelectedItem() == compounding_rate[1]) {
				compoundTerm_num = 2;
			} else if (combo_compounding.getSelectedItem() == compounding_rate[2]) {
				compoundTerm_num = 4;
			} else if (combo_compounding.getSelectedItem() == compounding_rate[3]) {
				compoundTerm_num = 6;
			} else if (combo_compounding.getSelectedItem() == compounding_rate[4]) {
				compoundTerm_num = 12;
			} else if (combo_compounding.getSelectedItem() == compounding_rate[5]) {
				compoundTerm_num = 24;
			} else if (combo_compounding.getSelectedItem() == compounding_rate[6]) {
				compoundTerm_num = 26;
			} else if (combo_compounding.getSelectedItem() == compounding_rate[7]) {
				compoundTerm_num = 52;
			} else if (combo_compounding.getSelectedItem() == compounding_rate[8]) {
				compoundTerm_num = 365;
			} else if (combo_compounding.getSelectedItem() == compounding_rate[9]) {
				compoundTerm_num = 0;
			} else {
				compoundTerm_num = 12;
			}
		}
		//when the future button is clicked, the program takes in the user's input and finds the future value
		if (evt.getSource() == button_future) {
			//sets error status to true
			noErrors = true;
			//checks for errors while taking in the user's input from the text boxes
			//if an error exists a message will pop up and the text box will be made blank and the no error check is set to false 
			try {
				try {
					present_num = Double.parseDouble(txt_present.getText());
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid data! Please enter an integer!", "Error in present value",JOptionPane.ERROR_MESSAGE);
					txt_present.setText("");
					noErrors = false;
				}
				try {
					rate_num = Double.parseDouble(txt_rate.getText());
					if (rate_num<0) {
						rate_num*=-1;
						txt_rate.setText(String.valueOf(rate_num));
					}
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid data! Please enter an integer!", "Error in annual rate",JOptionPane.ERROR_MESSAGE);
					txt_rate.setText("");
					noErrors = false;
				}
				try {
					period_num = Double.parseDouble(txt_period.getText());
					if (period_num<0) {
						period_num*=-1;
						txt_period.setText(String.valueOf(period_num));
					}
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid data! Please enter an integer!", "Error in period",JOptionPane.ERROR_MESSAGE);
					txt_period.setText("");
					noErrors = false;
				}
			}
			catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Some exception happened!", "Error", JOptionPane.ERROR_MESSAGE);
					noErrors = false;
			}
			//if there are no errors, the user's input will be passed into a calculation method and the resulting number will be output into the text box
			if (noErrors == true) {
				future_num = calculateFutureValue(present_num, rate_num, period_num, rateTerm_num, periodTerm_num, compoundTerm_num);
				txt_future.setText(String.valueOf(future_num));
			}
			//if there are errors, the text box will be set to blank
			else {
				txt_future.setText("");
			}
			
		}
		//when the present button is clicked, the program takes in the user's input and finds the present value
		if (evt.getSource() == button_present) {
			//sets error status to true
			noErrors = true;
			//checks for errors while taking in the user's input from the text boxes
			//if an error exists a message will pop up and the text box will be made blank and the no error check is set to false 
			try {
				try {
					future_num = Double.parseDouble(txt_future.getText());
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid data! Please enter an integer!", "Error in future value",JOptionPane.ERROR_MESSAGE);
					txt_future.setText("");
					noErrors = false;
				}
				try {
					rate_num = Double.parseDouble(txt_rate.getText());
					if (rate_num<0) {
						rate_num*=-1;
						txt_rate.setText(String.valueOf(rate_num));
					}
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid data! Please enter an integer!", "Error in annual rate",JOptionPane.ERROR_MESSAGE);
					txt_rate.setText("");
					noErrors = false;
				}
				try {
					period_num = Double.parseDouble(txt_period.getText());
					if (period_num<0) {
						period_num*=-1;
						txt_period.setText(String.valueOf(period_num));
					}
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid data! Please enter an integer!", "Error in period",JOptionPane.ERROR_MESSAGE);
					txt_period.setText("");
					noErrors = false;
				}
			}
			catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Some exception happened!", "Error", JOptionPane.ERROR_MESSAGE);
					noErrors = false;
			}
			//if there are no errors, the user's input will be passed into a calculation method and the resulting number will be output into the text box
			if (noErrors == true) {
				present_num = calculateFutureValue(future_num, rate_num, period_num, rateTerm_num, periodTerm_num, compoundTerm_num);
				txt_present.setText(String.valueOf(present_num));
			}
			else {
				//if there are errors, the text box will be set to blank
				txt_present.setText("");
			}
		}
		//when the user clicks the help button a class is called that creates a second frame with help instructions
		if (evt.getSource() == button_help) {
			Help help = new Help();
		}
		//when the user clicks the reset button, all user input will be set to the original
		if (evt.getSource() == button_reset) {
			txt_present.setText("");
			txt_future.setText("");
			txt_rate.setText("");
			txt_period.setText("");
			combo_annual.setSelectedIndex(0);
			combo_period.setSelectedIndex(4);
			combo_compounding.setSelectedIndex(4);
		}
	}
	
	//method that calculates the future value
	public double calculateFutureValue(double present, double rate, double period, double rateTerm, double periodTerm, double compoundTerm) {
		//initializes value
		double future;
		//if there is no compound term, the formula for simple interest is used
		if (compoundTerm == 0) {
			future = -1 * present * (1 + (((rate / 100) * rateTerm) * (period / periodTerm)));
		}
		//if there is a compound term, the formula for compound interest is used
		else {
			future = -1 * present * Math.pow((1 + (((rate / 100) * rateTerm) / compoundTerm)), (period / periodTerm) * compoundTerm);
		}
		future = Math.round(future * 100) / 100.0;
		//the calculated value is returned
		return future;
	}
	
	//method that calculates the present value
	public double calculatePresentValue(double future, double rate, double period, double rateTerm, double periodTerm, double compoundTerm) {
		//initializes value
		double present;
		//if there is no compound term, the formula for simple interest is used
		if (compoundTerm == 0) {
			present = -1 * future / (1 + (((rate / 100) * rateTerm) * (period / periodTerm)));
		}
		//if there is a compound term, the formula for compound interest is used
		else {
			present = -1 * future
					/ Math.pow((1 + (((rate / 100) * rateTerm) / compoundTerm)), (period / periodTerm) * compoundTerm);
		}
		present = Math.round(present * 100) / 100.0;
		//the calculated value is returned
		return present;
	}
	//the main method that constructs the frame and sets the frames size and other properties
	public static void main(String[] args) {
		InvestmentCalculator frm = new InvestmentCalculator();
		frm.setSize(500, 400);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}

//class that creates a second frame with help instruction 
class Help extends JFrame {

	//creates labels and panels
	JLabel label_explanation, label_helplink;
	JPanel panel, explanation_panel, link_panel;
	//creates the title of the video link
	String text = "Here is a helpful video";

	//holds the code for the second frame
	public Help() {
		//creates the properties of the second frame
		this.setSize(600, 800);
		this.setVisible(true);
		this.setLocation(500, 0);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		//creates a label containing the text for an explanation and adds it to the panel
		label_explanation = new JLabel("<html>  "
				+ "<p style=\"margin-left: 200px\">Help</p><br>"
				+ "<p style=\"margin-left: 30px\">This calculator is based on the time value of money. The time value of money (TVM) </p>"
				+ " is the concept that money you have now is worth more than the identical sum in the future <br>"
				+ "The time value of money draws from the idea that rational investors prefer to receive money <br>"
				+ " today rather than the same amount of money in the future because of money's potential to  <br>"
				+ "grow in value over a given period of time. For example, money deposited into a savings account <br>"
				+ " earns a certain interest rate and is therefore said to be compounding in value. <br><hr>"
				+ "<p style=\"margin-left: 30px\">A comparison of present value with future value (FV) best illustrates the principle of the time </p>"
				+ "value of money and the need for charging or paying additional risk-based interest rates. Simply put,<br>"
				+ "the money today is worth more than the same money tomorrow because of the passage of time. <br>"
				+ "Future value can relate to the future cash inflows from investing today's money, or the future payment<br>"
				+ " required to repay money borrowed today.<br>"
				+ "<p style=\"margin-left: 30px\">Future value (FV) is the value of a current asset at a specified date in the future based on an </p>"
				+ "assumed rate of growth. The FV equation assumes a constant rate of growth and a single upfront <br>"
				+ "payment left untouched for the duration of the investment. The FV calculation allows investors to<br>"
				+ " predict, with varying degrees of accuracy, the amount of profit that can be generated <br>"
				+ "by different investments.<br>"
				+ "<p style=\"margin-left: 30px\">Present value (PV) is the current value of a future sum of money or stream of cash flows given </p>"
				+ "a specified rate of return. Present value takes the future value and applies a discount rate or<br>"
				+ "the interest rate that could be earned if invested. Future value tells you what an investment is<br>"
				+ "worth in the future while the present value tells you how much you'd need in today's dollars<br>"
				+ "to earn a specific amount in the future.<br><hr>"
				+ "<p style=\"margin-left: 30px\">This calculator calculates a user's loans or investments based on values that are positive </p>"
				+ "or negative. For example, if the present value is negative, this indicates an investment <br>"
				+ "deposited into a user's saving account. Although money is lost at present, the deposited money<br>"
				+ "earns interest resulting in a gain of wealth in the future. Similarly, if the present value <br>"
				+ "is positive, money has been withdrawn as a loan. Although money is gained at present, the <br>"
				+ "amount needed to be paid back earns interest resulting in a loss of wealth in the future as <br>"
				+ "a negative value.<br><hr>"
				+ "<p style=\"margin-left: 30px\">Depending on the exact situation in question, the time value of money formula may change</p>"
				+ "slightly. But in general, the most fundamental TVM formula takes into account the following variables:<br>"
				+ "<p style=\"margin-left: 30px\">FV = Future value of money</p>"
				+ "<p style=\"margin-left: 30px\">PV = Present value of money</p>"
				+ "<p style=\"margin-left: 30px\">i = interest rate</p>"
				+ "<p style=\"margin-left: 30px\">n = number of compounding periods per year</p>"
				+ "<p style=\"margin-left: 30px\">t = number of years</p>"
				+ "Based on these variables, the formula for TVM is:<br>"
				+ "<p style=\"margin-left: 30px\">FV = PV x [ 1 + (i / n) ] (n x t)</p><hr>"
				+ " </html>");
		explanation_panel = new JPanel();
		explanation_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		explanation_panel.add(label_explanation);
		
		//constructs a label that holds the link to the video and adds it to the panel
		label_helplink = new JLabel(text);
		label_helplink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		label_helplink.setForeground(Color.BLUE.darker());
		link_panel = new JPanel();
		link_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		link_panel.add(label_helplink);
		
		//program action when the mouse is used
		label_helplink.addMouseListener(new MouseAdapter() {
		//if the mouse is clicked, the program opens up the video
		public void mouseClicked(MouseEvent e) {
			try {
				Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=Vk8-ezhw6YE"));
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		}
		//if the mouse hovers over the video link, the link is ready to activate
		public void mouseEntered(MouseEvent e) {
			label_helplink.setText("<html><a href=''>" + text + "</a></html>");
		}

		});
		
		//creates a panel and adds the smaller panel to the larger panel which is added to the content pane
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(explanation_panel);
		panel.add(link_panel);
		panel.add(Box.createVerticalStrut(400));
		this.getContentPane().add(panel);
		
	}
	
}