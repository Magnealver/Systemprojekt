package systemprojekt.client.gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.List.*;
import java.util.*;
import javax.swing.table.*;
import java.sql.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import systemprojekt.server.sql.*;

public class gui extends JFrame {
    JButton b1 = new JButton("Add Safari");
	JButton b2 = new JButton("Search & Update Safari");
	JButton b3 = new JButton("Add Occasion");
	JButton b4 = new JButton("Search Safari Occasion");
	JButton b5 = new JButton("Customers");
	JButton b6 = new JButton("Exit");

	Statement s;
	PreparedStatement sm; 
	ResultSet rs;
	Connection c;
	//DatabaseHandler test = new DatabaseHandler();
	SQL sqlobject = new SQL();
	/*Safari safari = new Safari();
	Customer customer = new Customer();
	Safaribooking booking = new Safaribooking();
	SafariOccasion occasion = new SafariOccasion();*/
	/*Safaribooking booking = new Safaribooking();
	Safari safari = new Safari();
	Customer cust = new Customer();
	SafariOccasion safocc = new SafariOccasion();*/


	
 
 // Här kommer referensen till det nya extra fönstret
    AddSafariWin w2 = new AddSafariWin();
	UpdateSafariWin w3 = new UpdateSafariWin();
	SafariBookingWin w4 = new SafariBookingWin();
	AddOccasionWin w5 = new AddOccasionWin();
	AllCustomersWin w6 = new AllCustomersWin();
	//ListAllCustomersWin w5 = new ListAllCustomersWin();
	//ListAllBookingsWin w6 = new ListAllBookingsWin();

 // En ny klass som används för att beskriva ett nytt
 // självständigt fönster
 	private class AddSafariWin extends JFrame{
	
		JPanel wp2 = new JPanel();
		JPanel wp3 = new JPanel();

		JTable table = new JTable();
		
		JLabel wl1 = new JLabel("Lake:");
		JLabel wl2 = new JLabel("Price:");
		JLabel wl3 = new JLabel("Guide:");
		JLabel wl4 = new JLabel("Minimum # participants:");
		JLabel wl5 = new JLabel("Maximum # participants:");
		
		JTextField wf1 = new JTextField(40);
		JTextField wf2 = new JTextField(10);
		JTextField wf3 = new JTextField(40);
		JTextField wf4 = new JTextField(2);
		JTextField wf5 = new JTextField(2);
		
		JButton wb1 = new JButton("Back");
		JButton wb2 = new JButton("Clear");
		JButton wb3 = new JButton("Save");
		
		ActionListener wbl = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				if (e.getSource() == wb1) {
					w2.setVisible(false);
					
				} else if (e.getSource() == wb2) {
		    		wf1.setText("");	
		    		wf2.setText("");
		    		wf3.setText("");	
		    		wf4.setText("");	
		    		wf5.setText("");	
		

				} else if (e.getSource() == wb3) {

					if ((wf1.getText().matches("\\D*")) && (wf2.getText().matches("\\d*")) 
						&& (wf3.getText().matches("\\d*")) && (wf4.getText().matches("\\D*")) && (wf5.getText().matches("\\d*"))
						&& (!wf1.getText().isEmpty()) && (!wf2.getText().isEmpty()) && (!wf3.getText().isEmpty())
						&& (!wf4.getText().isEmpty()) && (!wf5.getText().isEmpty())) {
						try {
            			String lake = wf1.getText();
            			int price = Integer.parseInt(wf2.getText());
            			String guide = wf3.getText();            			
            		    int minparticipants = Integer.parseInt(wf4.getText());
            			int maxparticipants = Integer.parseInt(wf5.getText());
						sqlobject.addSafari(lake, minparticipants, maxparticipants, guide, price);
            			} catch (NumberFormatException nfe) {
            				System.out.println(nfe.getMessage());
            			}

							

					} else if (!wf1.getText().matches("\\D*")) {
            			JOptionPane.showMessageDialog(null, "Lake name is not allowed!", "Alert", JOptionPane.ERROR_MESSAGE);
            		} else if (!wf2.getText().matches("\\d*")) {
            			JOptionPane.showMessageDialog(null, "Min participants has to be a number!", "Alert", JOptionPane.ERROR_MESSAGE);
            		} else if (!wf3.getText().matches("\\d*")) {
            			JOptionPane.showMessageDialog(null, "Max participants has to be a number!", "Alert", JOptionPane.ERROR_MESSAGE);
            		} else if (!wf4.getText().matches("\\D*")) {
            			JOptionPane.showMessageDialog(null, "Guide can't have a number in its name!", "Alert", JOptionPane.ERROR_MESSAGE);
            		} else if (!wf5.getText().matches("\\d*")) {
            			JOptionPane.showMessageDialog(null, "Price has to be a number!", "Alert", JOptionPane.ERROR_MESSAGE);
            		} else if ((!wf1.getText().isEmpty()) || (!wf2.getText().isEmpty()) || (!wf3.getText().isEmpty())
            		 || (!wf4.getText().isEmpty()) || (!wf5.getText().isEmpty())) {
            			JOptionPane.showMessageDialog(null, "All fields are mandatory!", "Alert", JOptionPane.ERROR_MESSAGE);
            		} else {
            			JOptionPane.showMessageDialog(null, "Something went wrong!", "Alert", JOptionPane.ERROR_MESSAGE);
            		}


				} else {
					// Impossible alternative
				}
	    	}
		};
		
	private AddSafariWin() {
		setTitle("Add Safari");
		setLayout(new GridBagLayout());
		
		wp2.setLayout(new GridLayout(0,2));
		wp2.setPreferredSize(new Dimension(500, 150));
		wp2.add(wl1); wp2.add(wf1); wp2.add(wl2); wp2.add(wf2); 
		wp2.add(wl3); wp2.add(wf3); wp2.add(wl4); wp2.add(wf4);	wp2.add(wl5); wp2.add(wf5); 
		//add(wp2, c);
		
		wp3.setPreferredSize(new Dimension(100, 150));
		wp3.add(wb1); wp3.add(wb2); wp3.add(wb3);
		wb1.addActionListener(wbl);
		wb2.addActionListener(wbl);
		wb3.addActionListener(wbl);

		

		
		add(wp2); add(wp3);
		pack();
		}
	} // Slut på win2 klassen
 	private class UpdateSafariWin extends JFrame{
	
		JPanel wp2 = new JPanel();
		JPanel wp3 = new JPanel();
		JPanel wp4 = new JPanel();
		JPanel wp5 = new JPanel();
		JPanel wp6 = new JPanel();
		JPanel wp7 = new JPanel();
		JPanel updateSingle = new JPanel();

		JLabel wl2 = new JLabel("Lake:");
		JLabel valueLabel = new JLabel("New Value:");

		JTextField newValue = new JTextField(20);

		JButton wb1 = new JButton("Back");
		JButton wb3 = new JButton("Search");
		JButton wb4 = new JButton("Update");
		JButton wb5 = new JButton("Delete");
		JButton wb6 = new JButton("All Lakes");
		JButton confirm = new JButton("Update Safari");

		JRadioButton rb = new JRadioButton();
		JRadioButton rb2 = new JRadioButton();	
		JRadioButton radio = new JRadioButton();	
		JRadioButton radio2 = new JRadioButton();
		JRadioButton radio3 = new JRadioButton();

		JComboBox<String> cb = new JComboBox<String>();
		JComboBox<String> cb2 = new JComboBox<String>();
		JComboBox<String> cb3 = new JComboBox<String>();

		/*Object[] columnsName = new Object[5];
		columnsName[0] = "Lake";
        columnsName[1] = "Min # Participants";
        columnsName[2] = "Max # Participants";
        columnsName[3] = "Guide";
        columnsName[4] = "Price";
		DefaultTableModel model = new DefaultTableModel();
		Object[] rowData = new Object[5];

*/

		JTable table = new JTable();
		JTable table2 = new JTable();
		JTable table3 = new JTable();
		
		ActionListener wbl = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) { 
				if (e.getSource() == wb1) {
					w3.setVisible(false);

				
		   		} else if (e.getSource() == wb3) {		   				
		   			sqlobject.searchSafari(cb.getSelectedItem().toString());
		   			String selectedLake = cb.getSelectedItem().toString();
		   			arrayListToTableModelSafari(sqlobject.getSafariList(), table2);
		   			JScrollPane scrollPane = new JScrollPane( table2 );
      			    Object [] options = {"Update", "Cancel", "Delete"};
        			int result = JOptionPane.showOptionDialog(null, scrollPane, "Information", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        			switch (result) {
        				case 0: 
        					cb2.addItem(selectedLake);
        					updateSingle.setVisible(true);
        					Object [] options2 = {"Update", "Cancel", "Delete"};
        					int result2 = JOptionPane.showOptionDialog(null, updateSingle, "Update Lake", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, options2, options2[0]);	
        					
        					switch (result2) {
        						case 0:
        						if (cb3.getSelectedItem().toString() == "Min # Participants"){
        							sqlobject.updateSafariMinParticipants(Integer.parseInt(newValue.getText()), cb.getSelectedItem().toString());
        						} else if (cb3.getSelectedItem().toString() == "Max # Participants") {
        							sqlobject.updateSafariMaxParticipants(Integer.parseInt(newValue.getText()), cb.getSelectedItem().toString());
        						} else if (cb3.getSelectedItem().toString() == "Guide") {
        							sqlobject.updateSafariGuide(newValue.getText(), cb.getSelectedItem().toString());
        						} else if (cb3.getSelectedItem().toString() == "Price") {
        							sqlobject.updateSafariPrice(Integer.parseInt(newValue.getText()), cb.getSelectedItem().toString());
        						}
        						break;
        						case 1:
        						break;
        						case 2:
        						sqlobject.removeSafari(selectedLake);
        						break;
        					}

        				case 1:
        				break;
        				case 2:
        				sqlobject.removeSafari(selectedLake);
        			}
		   				
	    		} else if (e.getSource() == wb5) {
	    			//sql.removeSafari(lake); på valt safari        					
       					
	    		} else if (e.getSource() == wb6) {
	    			sqlobject.searchAllSafari();
	    			arrayListToTableModelSafari(sqlobject.getSafariListAll(), table);
	    			JScrollPane scrollPane = new JScrollPane( table );
      			    Object [] options = {"Update", "Cancel",};
        			int result = JOptionPane.showOptionDialog(null, scrollPane, "Information about all lakes", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        			switch (result) {
        				case 0: 
        					for (int i = 0; i < sqlobject.getSafariListAll().size(); i++) {
								cb2.addItem(sqlobject.getSafariListAll().get(i).getLake());
							}
        					updateSingle.setVisible(true);
        					Object [] options2 = {"Update", "Cancel", "Delete"};
        					int result2 = JOptionPane.showOptionDialog(null, updateSingle, "Update Lake", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, options2, options2[0]);	
        					
        					switch (result2) {
        						case 0:
        						if (cb3.getSelectedItem().toString() == "Min # Participants"){
        							sqlobject.updateSafariMinParticipants(Integer.parseInt(newValue.getText()), cb2.getSelectedItem().toString());
        						} else if (cb3.getSelectedItem().toString() == "Max # Participants") {
        							sqlobject.updateSafariMaxParticipants(Integer.parseInt(newValue.getText()), cb2.getSelectedItem().toString());
        						} else if (cb3.getSelectedItem().toString() == "Guide") {
        							sqlobject.updateSafariGuide(newValue.getText(), cb2.getSelectedItem().toString());
        						} else if (cb3.getSelectedItem().toString() == "Price") {
        							sqlobject.updateSafariPrice(Integer.parseInt(newValue.getText()), cb2.getSelectedItem().toString());
        						}
        						break;
        						case 1:
        						break;
        						case 2:
        						sqlobject.removeSafari(cb2.getSelectedItem().toString());
        						break;
        					}

        				case 1:
        				break;
        			}

	    			//System.out.println(sqlobject.getSafariListAll());
	    		}
	    	
	    }
	};
		
		private UpdateSafariWin() {
			setTitle("Update & Search Safari");
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			wp2.setLayout(new GridBagLayout());
			wp2.add(cb);
			c.gridy = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridwidth = GridBagConstraints.REMAINDER;
			add(wp2);

			wp5.add(wb3); wp5.add(wb6); 
			c.gridy = 2;
			c.fill = GridBagConstraints.BOTH;
			c.gridwidth = GridBagConstraints.REMAINDER;
			add(wp5, c);

			wp3.add(wb1);
			wb1.addActionListener(wbl);
			wb3.addActionListener(wbl);
			wb4.addActionListener(wbl);
			wb5.addActionListener(wbl);
			wb6.addActionListener(wbl);

			c.gridy = 5;
			c.fill = GridBagConstraints.BOTH;
			c.gridwidth = GridBagConstraints.REMAINDER;
			add(wp3, c);
			pack();

			updateSingle.setLayout(new GridBagLayout());
			updateSingle.add(cb2); updateSingle.add(cb3); updateSingle.add(valueLabel); updateSingle.add(newValue);
			cb3.addItem("Min # Participants");
			cb3.addItem("Max # Participants");
			cb3.addItem("Guide");
			cb3.addItem("Price");
			updateSingle.setVisible(false);


			//model.setColumnIdentifiers(columnsName);

			
		}

	}

	private class AddOccasionWin extends JFrame{
	
		JPanel wp2 = new JPanel();
		JPanel wp3 = new JPanel();

		JTable table = new JTable();
		
		JLabel wl1 = new JLabel("Lake:");
		JLabel wl2 = new JLabel("Date:");
		
		JTextField wf2 = new JTextField(10);
		
		JButton wb1 = new JButton("Back");
		JButton wb2 = new JButton("Clear");
		JButton wb3 = new JButton("Save");

		JComboBox<String> cb = new JComboBox<String>();
		
		ActionListener wbl = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				if (e.getSource() == wb1) {
					w5.setVisible(false);
					
				} else if (e.getSource() == wb2) {
		    			
		    		wf2.setText("");	
		

				} else if (e.getSource() == wb3) {

					if ((cb.getSelectedItem() != null) && (wf2.getText().matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) 
						&& (!wf2.getText().isEmpty())) {
						try {
            			String lake = cb.getSelectedItem().toString();
            			String date = wf2.getText();
            			
						sqlobject.addSafariOccasion(lake, date);
            			} catch (NumberFormatException nfe) {
            				System.out.println(nfe.getMessage());
            			}
					
            		} else if (!wf2.getText().matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
            			JOptionPane.showMessageDialog(null, "Date is in incorrect format!", "Alert", JOptionPane.ERROR_MESSAGE);
            		} else if (wf2.getText().isEmpty()) {
            			JOptionPane.showMessageDialog(null, "All fields are mandatory!", "Alert", JOptionPane.ERROR_MESSAGE);
            		} else {
            			JOptionPane.showMessageDialog(null, "Something went wrong!", "Alert", JOptionPane.ERROR_MESSAGE);
            		}

				} else {
					// Impossible alternative
				}
	    	}
		};
		
	private AddOccasionWin() {
		setTitle("Add Safari Occasion");
		setLayout(new GridLayout(0,1));
		
		wp2.setLayout(new GridLayout(0,2));
		//wp2.setPreferredSize(new Dimension(500, 150));
		wp2.add(wl1); wp2.add(cb); wp2.add(wl2); wp2.add(wf2); 
		
		//add(wp2, c);
		
		//wp3.setPreferredSize(new Dimension(100, 150));
		wp3.add(wb1); wp3.add(wb2); wp3.add(wb3);
		wb1.addActionListener(wbl);
		wb2.addActionListener(wbl);
		wb3.addActionListener(wbl);

		

		
		add(wp2); add(wp3);
		pack();
		}
	} // Slut på win2 klassen

 	private class SafariBookingWin extends JFrame{
	
		JPanel wp2 = new JPanel();
		JPanel wp3 = new JPanel();
		JPanel wp4 = new JPanel();
		JPanel wp5 = new JPanel();
		JPanel wp6 = new JPanel();
		JPanel wp7 = new JPanel();
		JPanel booking = new JPanel();
		JPanel payment = new JPanel();

		JLabel wl2 = new JLabel("Lake:");
		JLabel wl3 = new JLabel("ID:");
		JLabel custID = new JLabel("Customer ID:");
		JLabel register = new JLabel("If person is already registered, only fill in Customer ID. Otherwise, fill in all other fields.");
		JLabel fname = new JLabel("First Name:");
		JLabel lname = new JLabel("Last Name:");
		JLabel address = new JLabel("Address:");
		JLabel phonenmbr = new JLabel("Phonenumber:");
		JLabel email = new JLabel("Email:");
		JLabel placeholder = new JLabel("");
		JLabel placeholder2 = new JLabel("");
		JLabel placeholder3 = new JLabel("");

		JTextField wf1 = new JTextField(40);
		JTextField wf2 = new JTextField(10);
		JTextField custIDt = new JTextField(10);
		JTextField fnamet = new JTextField(10);
		JTextField lnamet = new JTextField(10);
		JTextField addresst = new JTextField(10);
		JTextField phonenmbrt = new JTextField(10);
		JTextField emailt = new JTextField(10);

		JButton wb1 = new JButton("Back");
		JButton wb2 = new JButton("Clear");
		JButton wb3 = new JButton("Get Dates");
		JButton wb4 = new JButton("Update");
		JButton wb5 = new JButton("Delete");
		JButton wb6 = new JButton("Book");
		JButton wb7 = new JButton("Confirm Booking");

		JRadioButton rb = new JRadioButton();
		JRadioButton rb2 = new JRadioButton();	
		JRadioButton radio = new JRadioButton();	
		JRadioButton radio2 = new JRadioButton();
		JRadioButton radio3 = new JRadioButton();

		JComboBox<String> cb = new JComboBox<String>();
		JComboBox<String> cb2 = new JComboBox<String>();

		JTable table = new JTable();
		JTable table2 = new JTable();
		JTable table3 = new JTable();
		
		ActionListener wbl = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) { 
				if (e.getSource() == wb1) {
					w4.setVisible(false);
					
				} else if (e.getSource() == wb2) {
		    		wf1.setText("");
				} else if (e.getSource() == wb3) {
					cb2.removeAllItems();
					sqlobject.searchOccasion(cb.getSelectedItem().toString());
					for (int i = 0; i < sqlobject.getOccasionList().size(); i++) {
						cb2.addItem(sqlobject.getOccasionList().get(i).getDate());
					}
				} else if ((e.getSource() == wb6) && (cb2.getSelectedItem() != null)) {
					Object [] options = {"Confirm Booking", "Cancel"};
        			int choice = JOptionPane.showOptionDialog(null, booking, "Booking", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        			sqlobject.searchAllCustomers();
        			switch (choice) {
        				case 0: 
        				try {
        					if (isParsable(custIDt.getText()) && sqlobject.checkCustomerID(Integer.parseInt(custIDt.getText()))) {
        						int bookid = sqlobject.createBookingID(); 
        						System.out.println(bookid);       						
        						sqlobject.addSafariBooking(cb.getSelectedItem().toString(), cb2.getSelectedItem().toString(), Integer.parseInt(custIDt.getText()), bookid);
    							Object [] options2 = {"Pay", "Cancel"};
        						int pay = JOptionPane.showOptionDialog(null, payment, "Payment", 
        							JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, options2, options2[0]);
        						switch (pay) {
        							case 0:
        								JOptionPane.showMessageDialog(payment, "Payment received. Booking confirmed. BookingID: "+bookid);
        								break;
        							case 1:
        								JOptionPane.showMessageDialog(payment, "Payment cancelled. Booking cancelled.");
        						}
        					} else if ((!custIDt.getText().isEmpty()) && (!isParsable(custIDt.getText()))) {
        						JOptionPane.showMessageDialog(payment, "Customer ID has to be a number", "Error", JOptionPane.ERROR_MESSAGE);
        					} else if ((!custIDt.getText().isEmpty()) && (!sqlobject.checkCustomerID(Integer.parseInt(custIDt.getText())))) {
        						JOptionPane.showMessageDialog(payment, "Customer ID does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        					} else if ((fnamet.getText().matches("\\D*")) && (lnamet.getText().matches("\\D*")) && (!addresst.getText().isEmpty()) 
        						&& (phonenmbrt.getText().matches("\\d*")) && (!emailt.getText().isEmpty())) {
        						
        						int customerid = sqlobject.createCustomerID();
        						sqlobject.addCustomer(customerid, fnamet.getText(),lnamet.getText(), addresst.getText(),phonenmbrt.getText(), emailt.getText());
        						int bookid = sqlobject.createBookingID(); 
        						sqlobject.addSafariBooking(cb.getSelectedItem().toString(), cb2.getSelectedItem().toString(), customerid, bookid);
        						Object [] options2 = {"Pay", "Cancel"};
        						int pay = JOptionPane.showOptionDialog(null, payment, "Payment", 
        						JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, options2, options2[0]);
        						switch (pay) {
        							case 0:
        								JOptionPane.showMessageDialog(payment, "Customer created. Payment received. Booking confirmed. BookingID: "+bookid);
        								break;
        							case 1:
        								JOptionPane.showMessageDialog(payment, "Payment cancelled. Booking cancelled. Customer not created.");
        						}
        					} else if ((fnamet.getText().isEmpty()) || (lnamet.getText().isEmpty()) || (addresst.getText().isEmpty())
        						|| (phonenmbrt.getText().isEmpty()) || (emailt.getText().isEmpty())) {
        						JOptionPane.showMessageDialog(payment, "All fields need to be completed.", "Error", JOptionPane.ERROR_MESSAGE);
        					} else if ((!fnamet.getText().matches("\\D*")) || (!lnamet.getText().matches("\\D*")) || 
        						(!phonenmbrt.getText().matches("\\d*"))) {
        						JOptionPane.showMessageDialog(payment, "All fields need to use the correct format.", "Error", JOptionPane.ERROR_MESSAGE);
        					}
        					} catch (SQLException sqle) {
   							System.out.println("SQL Error: "+sqle.getMessage());
   						} 
        				case 1:
        					break;

        			}
					
				} else if ((e.getSource() == wb6) && (cb2.getSelectedItem() == null)) {
					JOptionPane.showMessageDialog(booking, "Choose a date!", "Error", JOptionPane.ERROR_MESSAGE);
				}
		    }
		};
		
		private SafariBookingWin() {
			setTitle("Search Safari Occasion");
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			wp2.setLayout(new GridLayout(0,2));
			wp2.add(cb); wp2.add(cb2);
			c.gridy = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridwidth = GridBagConstraints.REMAINDER;
			add(wp2, c);

			wp5.add(wb3); wp5.add(wb6); 
			c.gridy = 2;
			c.fill = GridBagConstraints.BOTH;
			c.gridwidth = GridBagConstraints.REMAINDER;
			add(wp5, c);

			wp3.add(wb1);
			wb1.addActionListener(wbl);
			wb3.addActionListener(wbl);
			wb6.addActionListener(wbl);
			c.gridy = 5;
			c.fill = GridBagConstraints.BOTH;
			c.gridwidth = GridBagConstraints.REMAINDER;
			add(wp3, c);
			pack();
			wp7.setLayout(new GridBagLayout());
			wp7.add(radio);  
			wp7.add(radio2);    
			wp7.add(radio3);  			   
			wp7.add(rb);  wp7.add(rb2);
			ButtonGroup group = new ButtonGroup();
			group.add(rb); group.add(rb2); 
			group.add(radio); group.add(radio2); group.add(radio3);
			wp7.setVisible(false);

			add(wp7);
			
			booking.setLayout(new GridLayout(0,2));
			c.gridy = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridwidth = GridBagConstraints.REMAINDER;
			booking.add(register); booking.add(placeholder3);
			booking.add(custID); booking.add(custIDt); booking.add(placeholder); 
			booking.add(placeholder2); booking.add(fname); booking.add(fnamet); 
			booking.add(lname); booking.add(lnamet); booking.add(address); booking.add(addresst); 
			booking.add(phonenmbr); booking.add(phonenmbrt); booking.add(email); booking.add(emailt);
			//booking.add(wb7);
			

			

			
		}

	}

	private class AllCustomersWin extends JFrame{
	
		JPanel wp2 = new JPanel();
		JPanel wp3 = new JPanel();
		JPanel updateSingle = new JPanel();

		JTable table = new JTable();
		
		JLabel wl1 = new JLabel("ID:");
		JLabel placeholder = new JLabel("");
		JLabel placeholder2 = new JLabel("");
		JLabel wl2 = new JLabel("First Name:");
		JLabel wl3 = new JLabel("Last Name:");
		JLabel valueLabel = new JLabel("New Value:");
		
		JTextField wf1 = new JTextField(10);
		JTextField wf2 = new JTextField(40);
		JTextField wf3 = new JTextField(40);
		JTextField newValue = new JTextField(20);
		
		JButton wb1 = new JButton("Back");
		JButton wb2 = new JButton("Clear");
		JButton wb3 = new JButton("Search");
		JButton confirm = new JButton("Update Customer");

		JTable table2 = new JTable();

		JComboBox<String> cb = new JComboBox<String>();
		JComboBox<String> cb2 = new JComboBox<String>();
		JComboBox<String> cb3 = new JComboBox<String>();

		ActionListener wbl = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				if (e.getSource() == wb1) {
					w6.setVisible(false);
					
				} else if (e.getSource() == wb2) {
		    		wf1.setText("");	
		    		wf2.setText("");
		    		wf3.setText("");	
	
		

				} else if (e.getSource() == wb3) {

					if ((wf1.getText().matches("\\d*")) && (!wf1.getText().isEmpty()) 
						&& (wf2.getText().isEmpty()) && (wf3.getText().isEmpty())) {

						int id = Integer.parseInt(wf1.getText());            			          			
						sqlobject.searchCustomer(id);
						arrayListToTableModelCustomer(sqlobject.getCustomerList(), table2);
						JScrollPane scrollPane = new JScrollPane( table2 );
      			   		Object [] options = {"Update", "Cancel"};
        				int result = JOptionPane.showOptionDialog(null, scrollPane, "Information", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        				switch (result) {
        					case 0:
        						//String selectedCustomer = cb.getSelectedItem().toString();
        						cb2.addItem(sqlobject.getCustomerList().get(0).getFirstName()+" "+ sqlobject.getCustomerList().get(0).getLastName() +" ID: "+wf1.getText()); 
        						updateSingle.setVisible(true);
        						Object [] options2 = {"Update", "Cancel", "Delete"};
        						int result2 = JOptionPane.showOptionDialog(null, updateSingle, "Update Customer", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, options2, options2[0]);	
        						
        						switch (result2) {
        							case 0:
        							if (cb3.getSelectedItem().toString() == "First Name"){
        								sqlobject.updateCustomerFName(newValue.getText(), Integer.parseInt(wf1.getText()));
        							} else if (cb3.getSelectedItem().toString() == "Last Name") {
        								sqlobject.updateCustomerLName(newValue.getText(), Integer.parseInt(wf1.getText()));
        							} else if (cb3.getSelectedItem().toString() == "Address") {
        								sqlobject.updateCustomerAddress(newValue.getText(), Integer.parseInt(wf1.getText()));
        							} else if (cb3.getSelectedItem().toString() == "Phonenumber") {
        								sqlobject.updateCustomerPhone(newValue.getText(), Integer.parseInt(wf1.getText()));
        							} else if (cb3.getSelectedItem().toString() == "Email") {
        								sqlobject.updateCustomerEmail(newValue.getText(), Integer.parseInt(wf1.getText()));
        							}
        							break;
        							case 1:
        							break;
        							case 2:
        							sqlobject.removeCustomer(Integer.parseInt(wf1.getText()));
        							break;
        						}

        					case 1:
        					break;
        					case 2:
        					sqlobject.removeCustomer(Integer.parseInt(wf1.getText()));
        					break;
        				}
							
            		} else if ((wf2.getText().matches("\\D*")) && (wf3.getText().matches("\\D*")) && (!wf2.getText().isEmpty()) && (!wf3.getText().isEmpty())) {
            			
            			String firstName = wf2.getText();
						String lastName = wf3.getText();
						sqlobject.searchCustomer(firstName, lastName);
						arrayListToTableModelCustomer(sqlobject.getCustomerList(), table);
						JScrollPane scrollPane = new JScrollPane( table2 );
      			   		Object [] options = {"Update", "Cancel, Delete"};
        				int result = JOptionPane.showOptionDialog(null, scrollPane, "Information", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        				switch (result) {
        					case 0:
        						//String selectedCustomer = cb.getSelectedItem().toString();
 								cb2.addItem(sqlobject.getCustomerList().get(0).getFirstName()+" "+ sqlobject.getCustomerList().get(0).getLastName()
 									+" ID: "+ sqlobject.getCustomerList().get(0).getID());
        						updateSingle.setVisible(true);
        						Object [] options2 = {"Update", "Cancel", "Delete"};
        						int result2 = JOptionPane.showOptionDialog(null, updateSingle, "Update Customer", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, options2, options2[0]);	
        						
        						switch (result2) {
        							case 0:
        							if (cb3.getSelectedItem().toString() == "First Name"){
        								sqlobject.updateCustomerFName(newValue.getText(), Integer.parseInt(wf1.getText()));
        							} else if (cb3.getSelectedItem().toString() == "Last Name") {
        								sqlobject.updateCustomerLName(newValue.getText(), Integer.parseInt(wf1.getText()));
        							} else if (cb3.getSelectedItem().toString() == "Address") {
        								sqlobject.updateCustomerAddress(newValue.getText(), Integer.parseInt(wf1.getText()));
        							} else if (cb3.getSelectedItem().toString() == "Phonenumber") {
        								sqlobject.updateCustomerPhone(newValue.getText(), Integer.parseInt(wf1.getText()));
        							} else if (cb3.getSelectedItem().toString() == "Email") {
        								sqlobject.updateCustomerEmail(newValue.getText(), Integer.parseInt(wf1.getText()));
        							}
        							break;
        							case 1:
        							break;
        							case 2:
        							sqlobject.removeCustomer(Integer.parseInt(wf1.getText()));
        							break;
        						}

        					case 1:
        					break;
        					case 2:
        					sqlobject.removeCustomer(wf2.getText(), wf3.getText());
        					break;
        				}

					} else if (!wf1.getText().matches("\\d*")) {
            			JOptionPane.showMessageDialog(null, "ID has to be a number!", "Alert", JOptionPane.ERROR_MESSAGE);
            		} else if ((!wf2.getText().matches("\\D*")) || (!wf3.getText().matches("\\D*"))) {
            			JOptionPane.showMessageDialog(null, "Name has to contain letters only!", "Alert", JOptionPane.ERROR_MESSAGE);
            		} else if ((!wf1.getText().isEmpty()) || (!wf2.getText().isEmpty()) || (!wf3.getText().isEmpty())) {
            			JOptionPane.showMessageDialog(null, "Fields are empty!", "Alert", JOptionPane.ERROR_MESSAGE);
            		} else {
            			JOptionPane.showMessageDialog(null, "Something went wrong!", "Alert", JOptionPane.ERROR_MESSAGE);
            		}


				} else {
					// Impossible alternative
				}
	    	}
		};
		
	private AllCustomersWin() {
		setTitle("Customers");
		setLayout(new GridBagLayout());
		
		wp2.setLayout(new GridLayout(0,2));
		wp2.setPreferredSize(new Dimension(500, 150));
		wp2.add(wl1); wp2.add(wf1); wp2.add(placeholder); wp2.add(placeholder2); wp2.add(wl2); wp2.add(wf2); 
		wp2.add(wl3); wp2.add(wf3); 
		//add(wp2, c);
		
		wp3.setPreferredSize(new Dimension(100, 150));
		wp3.add(wb1); wp3.add(wb2); wp3.add(wb3);
		wb1.addActionListener(wbl);
		wb2.addActionListener(wbl);
		wb3.addActionListener(wbl);

		updateSingle.setLayout(new GridBagLayout());
		updateSingle.add(cb2); updateSingle.add(cb3); updateSingle.add(valueLabel); updateSingle.add(newValue);
		cb3.addItem("First Name");
		cb3.addItem("Last Name");
		cb3.addItem("Address");
		cb3.addItem("Phonenumber");
		cb3.addItem("Email");
		updateSingle.setVisible(false);

		
		add(wp2); add(wp3);
		pack();
		}
	} // Slut på win2 klassen


    // Anonymous inner class för det "yttre" fönstret
    ActionListener bl = new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (((JButton)e.getSource()).getText().equals("Add Safari")) {
		    if (w2.isVisible()) {
		    	w2.setVisible(false);
		    }
		    else if (!w2.isVisible()){
		    	w2.setVisible(true);
		    }
		} else if (((JButton)e.getSource()).getText().equals("Search & Update Safari")){
			if (w3.isVisible()) {
		    	w3.setVisible(false);
		    }
		    else if (!w3.isVisible()){
		    	w3.cb.removeAllItems();
		    	sqlobject.searchAllSafari();
		    	for (int i = 0; i < sqlobject.getSafariListAll().size(); i++) {
						w3.cb.addItem(sqlobject.getSafariListAll().get(i).getLake());}
		    	w3.setVisible(true);
		    }
		} else if (((JButton)e.getSource()).getText().equals("Search Safari Occasion")){
			if (w4.isVisible()) {
		    	w4.setVisible(false);
		    }
		    else if (!w4.isVisible()){
		    	w4.cb.removeAllItems();
		    	sqlobject.searchAllOccasions();
		    	for (int i = 0; i < sqlobject.getOccasionListAll().size(); i++) {
						w4.cb.addItem(sqlobject.getOccasionListAll().get(i).getLake());}
		    	w4.setVisible(true);
		    }
	    } else if (((JButton)e.getSource()).getText().equals("Add Occasion")){
	    	if (w5.isVisible()) {
		    	w5.setVisible(false);
		    }
		    else if (!w5.isVisible()){
		    	w5.cb.removeAllItems();
		    	sqlobject.searchAllOccasions();
		    	for (int i = 0; i < sqlobject.getOccasionListAll().size(); i++) {
						w5.cb.addItem(sqlobject.getOccasionListAll().get(i).getLake());}
		    	w5.setVisible(true);
		    }

		} else if (((JButton)e.getSource()).getText().equals("Customers")){
	    	if (w6.isVisible()) {
		    	w6.setVisible(false);
		    }
		    else if (!w6.isVisible()){
		    	w6.setVisible(true);
		    }

	    } else if (((JButton)e.getSource()).getText().equals("Exit")){
			System.exit(0);
	    } 
		}
	};

	public void resultSetToTableModel(ResultSet rs, JTable table) throws SQLException{

        DefaultTableModel tableModel = new DefaultTableModel();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        //Get all column names from meta data and add columns to table model
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        }

        //Create array of Objects with size of column count from meta data
        Object[] row = new Object[columnCount];

        //Scroll through result set
        while (rs.next()){
            for (int i = 0; i < columnCount; i++){
                row[i] = rs.getObject(i+1);
            }

            tableModel.addRow(row);
        }

        //Now add that table model to your table
        table.setModel(tableModel);
    }

    public void arrayListToTableModelSafari (ArrayList<Safari> list, JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] { "Lake", "Min # Participants", "Max # Participants", "Guide", "Price"});
        model.setRowCount(list.size());
        int row = 0;
        for (Safari s : list) {
            model.setValueAt(s.getLake(), row, 0);
            model.setValueAt(s.getMinParticipants(), row, 1);
            model.setValueAt(s.getMaxParticipants(), row, 2);
            model.setValueAt(s.getGuide(), row, 3);
            model.setValueAt(s.getPrice(), row, 4);

            row++;
        }
        table.setModel(model);
    }

    public void arrayListToTableModelCustomer (ArrayList<Customer> list, JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] { "ID", "First Name", "Last Name", "Address", "Phonenumber", "Email"});
        model.setRowCount(list.size());
        int row = 0;
        for (Customer c : list) {
            model.setValueAt(c.getID(), row, 0);
            model.setValueAt(c.getFirstName(), row, 1);
            model.setValueAt(c.getLastName(), row, 2);
            model.setValueAt(c.getAddress(), row, 3);
            model.setValueAt(c.getPhoneNumber(), row, 4);
            model.setValueAt(c.getEmail(), row, 5);

            row++;
        }
        table.setModel(model);
    }




   /* public void arrayListToTableModel() {
    	for (int i = 0; i < sqlobject.safarilistall.size(); i++){
  			String lake = sqlobject.safarilistall.get(i).getLake();
   			int minparticipants = sqlobject.safarilistall.get(i).getMinParticipants();
   			int maxparticipants = sqlobject.safarilistall.get(i).getMaxParticipants();
   			String guide = sqlobject.safarilistall.get(i).getGuide();
   			int price = sqlobject.safarilistall.get(i).getPrice();
  			

  			Object[] data = {lake, minparticipants, maxparticipants, guide, price};

   			tableModel.add(data);
   			}
   		}*/

    
    public gui() {
		b1.addActionListener(bl);
		b2.addActionListener(bl); 
		b3.addActionListener(bl);
		b4.addActionListener(bl);
		b5.addActionListener(bl);
		b6.addActionListener(bl);
		
		setLayout(new GridLayout(0,2));
		add(b1); add(b2); add(b3); add(b4); add(b5); add(b6); pack();
		setVisible(true);
		setTitle("Menu");

		w2.setVisible(false);
		w3.setVisible(false);
		w4.setVisible(false);
		w5.setVisible(false);
		w6.setVisible(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static boolean isParsable(String input) {
    	boolean parsable = true;
    	try{
       		Integer.parseInt(input);
   		}
   		catch (NumberFormatException e) {
        parsable = false;
    	}
    return parsable;
	}


    public static void main(String[] args) {

		gui e1 = new gui();
		
    }
}
