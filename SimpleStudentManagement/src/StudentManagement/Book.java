package StudentManagement;

//import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import StudentManagement.Book.CategoryItem;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
//import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class Book extends JFrame {

	private JPanel contentPane;
	private JTextField txtname;
	private JTable table;
	@SuppressWarnings("unused")
	private JScrollPane ScrollPane;
	@SuppressWarnings("unused")
	private DefaultTableModel model;
	private JTextField txtcontents;
	private JTextField txtnop;
	private JTextField txtedition;
	@SuppressWarnings("rawtypes")
	private JComboBox txtcategory;
	@SuppressWarnings("rawtypes")
	private JComboBox txtauthor;
	@SuppressWarnings("rawtypes")
	private JComboBox txtpublisher;
	private JTextField txtname1;
	private JTextField txtpublisher1;
	private JTextField txtauthor1;
	//private JLabel txtcategory1;

	
	public Book() {		
		initComponents();
		connect();
		clearTable();
		book_Load();
		
	}
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	
	
	
	public class CategoryItem{
		String name;
		int id;
		public CategoryItem(int id,String name) {
			this.id=id;
			this.name=name;
		}
		public String toString() {
			return name;
		}
	}
	
	
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","rootpassword");//rootpassword
			
		} catch (ClassNotFoundException e) {
			Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null , e);
			e.printStackTrace();
		} catch (SQLException e) {
			Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null , e);
			e.printStackTrace();
		}
	}	
	public void clearTable()
	{
	    DefaultTableModel dm = (DefaultTableModel) table.getModel();
	    dm.getDataVector().removeAllElements();
	    revalidate();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void book_Load() {
		int c;
		int d;
		try {
			pst=con.prepareStatement("select * from book");
			rs=pst.executeQuery();
			ResultSetMetaData rsd=(ResultSetMetaData) rs.getMetaData();
			c=rsd.getColumnCount();
			DefaultTableModel ds= (DefaultTableModel) table.getModel();
			d=ds.getRowCount();
			//System.out.println(d);
			while(rs.next())
			{
				Vector v2=new Vector();
					int id = rs.getInt("id");
			         String name = rs.getString("bname");
			         String job = rs.getString("category");
			         String job1 = rs.getString("author");
			         String job2 = rs.getString("publisher");
			         String job3 = rs.getString("contents");
			         int ph = rs.getInt("nop");
			         int ph1 = rs.getInt("edition");
			           Object[] row = { id, name, job,job1,job2,job3,ph,ph1};

			            DefaultTableModel model = (DefaultTableModel) table.getModel();

			            model.addRow(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1078, 696);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1058, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 643, GroupLayout.PREFERRED_SIZE))
		);
		
		JLabel lblNewLabel = new JLabel("Book");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(74, 12, 120, 35);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		JLabel lblCategoryName = new JLabel(" Name  :");
		lblCategoryName.setBounds(27, 58, 150, 35);
		lblCategoryName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoryName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel lblStatus = new JLabel("     Category    :");
		lblStatus.setBounds(27, 113, 150, 35);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		txtname = new JTextField();
		txtname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtname.setBounds(195, 64, 161, 27);
		txtname.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				String bname=txtname.getText();
				String citem=txtname1.getText();
				String aitem=txtauthor1.getText();
				String pitem=txtpublisher1.getText();
				/*CategoryItem citem =(CategoryItem)txtcategory.getSelectedItem();
				CategoryItem aitem =(CategoryItem) txtauthor.getSelectedItem();
				CategoryItem pitem =(CategoryItem) txtpublisher.getSelectedItem();*/
				String contents=txtcontents.getText();
				String pages=txtnop.getText();
				String edition=txtedition.getText();
				try {
					pst = con.prepareStatement("INSERT INTO book(bname,category,author,publisher,contents,nop,edition)"+"VALUES(?,?,?,?,?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, citem);
					pst.setString(3, aitem);
					pst.setString(4, pitem);
					pst.setString(5, contents);
					pst.setString(6, pages);
					pst.setString(7, edition);
					int k = pst.executeUpdate();
					if(k== 1) {
						JOptionPane.showMessageDialog(null,"book Created", "STATUS", JOptionPane.INFORMATION_MESSAGE);
						txtname.setText("");
						txtname1.setText("");
						txtauthor1.setText("");
						txtpublisher1.setText("");
						txtcontents.setText("");
						txtnop.setText("");
						txtedition.setText("");
						txtname.requestFocus();
						clearTable();
						book_Load();
					}
					else {
						JOptionPane.showMessageDialog(null,"ERORRR", "STATUS", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(62, 539, 115, 33);
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null,"	Author Updated before", "STATUS", JOptionPane.INFORMATION_MESSAGE);
				DefaultTableModel d1= (DefaultTableModel) table.getModel();
				int selectIndex=table.getSelectedRow();
				int id=Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
				String names=txtname.getText();
				String addresss=txtname1.getText();
				String phonee=txtauthor1.getText();
				String phone=txtpublisher1.getText();
				String phoee=txtcontents.getText();
				String idd=txtnop.getText();
				String dd=txtedition.getText();
				try {
					pst = con.prepareStatement("UPDATE book set bname =? ,category =?,author =?,publisher=?,contents=?,nop=?,edition=? where id =?");
					pst.setString(1, names);
					pst.setString(2, addresss);
					pst.setString(3, phonee);
					pst.setString(4, phone);
					pst.setString(5, phoee);
					pst.setString(6, idd);
					pst.setString(7, dd);
					pst.setInt(8, id);
					//JOptionPane.showMessageDialog(null,"	Author Updated before", "STATUS", JOptionPane.INFORMATION_MESSAGE);
					int k = pst.executeUpdate();
					if(k== 1) {
						JOptionPane.showMessageDialog(null,"	book Updated", "STATUS", JOptionPane.INFORMATION_MESSAGE);
						txtname.setText("");
						txtname1.setText("");
						txtauthor1.setText("");
						txtpublisher1.setText("");
						txtcontents.setText("");
						txtnop.setText("");
						txtedition.setText("");
						txtname.requestFocus();
						clearTable();
						book_Load();
						btnAdd.setEnabled(true);
					}
					else {
						JOptionPane.showMessageDialog(null,"ERORRR", "STATUS", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(241, 539, 115, 33);
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main m=new Main();
				//this.Hide();
				m.setVisible(true);
			}
		});
		btnCancel.setBounds(239, 583, 115, 33);
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnUpdate_1_1 = new JButton("Delete");
		btnUpdate_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel d1= (DefaultTableModel) table.getModel();
				int selectIndex=table.getSelectedRow();
				int id=Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
				//JOptionPane.showMessageDialog(null,"Category Created", "STATUS", JOptionPane.ERROR_MESSAGE);
				try {
					pst = con.prepareStatement("DELETE from book where id=?");
					pst.setInt(1, id);
					//JOptionPane.showMessageDialog(null,"Category Created", "STATUS", JOptionPane.INFORMATION_MESSAGE);
					int k = pst.executeUpdate();
					if(k== 1) {
						JOptionPane.showMessageDialog(null,"book Deleted", "STATUS", JOptionPane.INFORMATION_MESSAGE);
						txtname.setText("");
						txtname1.setText("");
						txtauthor1.setText("");
						txtpublisher1.setText("");
						txtcontents.setText("");
						txtnop.setText("");
						txtedition.setText("");
						txtname.requestFocus();
						clearTable();
						book_Load();
						btnAdd.setEnabled(true);
					}
					else {
						JOptionPane.showMessageDialog(null,"ERORRR", "STATUS", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate_1_1.setBounds(62, 583, 115, 33);
		btnUpdate_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel.setLayout(null);
		panel.add(lblNewLabel);
		panel.add(lblCategoryName);
		panel.add(lblStatus);
		panel.add(txtname);
		panel.add(btnAdd);
		panel.add(btnUpdate);
		panel.add(btnCancel);
		panel.add(btnUpdate_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel d1= (DefaultTableModel) table.getModel();
				int selectIndex=table.getSelectedRow();
				int id=Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
				txtname.setText(d1.getValueAt(selectIndex, 1).toString());
				txtname1.setText(d1.getValueAt(selectIndex, 2).toString());
				txtauthor1.setText(d1.getValueAt(selectIndex, 3).toString());
				txtpublisher1.setText(d1.getValueAt(selectIndex, 4).toString());
				txtcontents.setText(d1.getValueAt(selectIndex, 5).toString());
				txtnop.setText(d1.getValueAt(selectIndex, 6).toString());
				txtedition.setText(d1.getValueAt(selectIndex, 7).toString());
				btnAdd.setEnabled(false);
				
			}
		});
		scrollPane.setBounds(377, 12, 671, 620);
		panel.add(scrollPane);
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Category", "Author", "Publisher", "Contents", "No of pages", "Edition"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, String.class, Integer.class, Object.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		
		
		JLabel lblNewLabel_1 = new JLabel(" Contents :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(37, 383, 140, 23);
		panel.add(lblNewLabel_1);
		
		txtcontents = new JTextField();
		txtcontents.setBounds(195, 383, 159, 27);
		panel.add(txtcontents);
		txtcontents.setColumns(10);
		
		JLabel lblAuthor = new JLabel("     Author   :");
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthor.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAuthor.setBounds(27, 199, 150, 35);
		panel.add(lblAuthor);
		
		JComboBox txtcategory = new JComboBox();
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/mydb","root","rootpassword");   
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from category");  
			while(rs.next())  
				txtcategory.addItem(new CategoryItem(rs.getInt(1),rs.getString(2)));
			//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			con.close();
			}catch(Exception e){ System.out.println(e);} 
		txtcategory.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				txtname1.setText(txtcategory.getSelectedItem().toString());
			}
		});
		txtcategory.setBounds(197, 117, 159, 30);
		panel.add(txtcategory);
		
		JComboBox txtauthor = new JComboBox();
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/mydb","root","rootpassword");   
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from author");  
			while(rs.next())  
				txtauthor.addItem(new CategoryItem(rs.getInt(1),rs.getString(2)));
			//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			con.close();  
			}catch(Exception e){ System.out.println(e);} 
		txtauthor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				txtauthor1.setText(txtauthor.getSelectedItem().toString());
			}
		});
		txtauthor.setBounds(197, 203, 159, 30);
		panel.add(txtauthor);
		
		JComboBox txtpublisher = new JComboBox();
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/mydb","root","rootpassword");   
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from publisher");  
			while(rs.next())  
				txtpublisher.addItem(new CategoryItem(rs.getInt(1),rs.getString(2)));
			//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			con.close();  
			}catch(Exception e){ System.out.println(e);}
		txtpublisher.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				txtpublisher1.setText(txtpublisher.getSelectedItem().toString());
			}
		});
		txtpublisher.setBounds(195, 283, 159, 30);
		panel.add(txtpublisher);
		
		JLabel lblStatus_1_1 = new JLabel("    Publisher      :");
		lblStatus_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStatus_1_1.setBounds(44, 279, 150, 35);
		panel.add(lblStatus_1_1);
		
		txtnop = new JTextField();
		txtnop.setColumns(10);
		txtnop.setBounds(195, 436, 159, 27);
		panel.add(txtnop);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(197, 488, 159, 27);
		panel.add(txtedition);
		
		JLabel lblNewLabel_1_1 = new JLabel(" No Of Pages :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(54, 434, 140, 27);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Edition :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(37, 486, 140, 27);
		panel.add(lblNewLabel_1_1_1);
		
		txtname1 = new JTextField();
		txtname1.setBounds(195, 158, 161, 34);
		panel.add(txtname1);
		txtname1.setColumns(10);
		
		txtpublisher1 = new JTextField();
		txtpublisher1.setColumns(10);
		txtpublisher1.setBounds(195, 334, 161, 34);
		panel.add(txtpublisher1);
		
		txtauthor1 = new JTextField();
		txtauthor1.setColumns(10);
		txtauthor1.setBounds(195, 244, 159, 28);
		panel.add(txtauthor1);
		contentPane.setLayout(gl_contentPane);
		
	}


	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book frame = new Book();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}  
	