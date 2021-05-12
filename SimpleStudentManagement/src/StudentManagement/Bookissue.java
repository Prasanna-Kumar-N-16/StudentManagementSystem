package StudentManagement;

//import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

//import StudentManagement.Bookissue.CategoryItem;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.toedter.calendar.JDateChooser;

import StudentManagement.Book.CategoryItem;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Bookissue extends JFrame {

	private JPanel contentPane;
	private JTextField txtid;
	private JTable table;
	@SuppressWarnings("unused")
	private JScrollPane ScrollPane;
	@SuppressWarnings("unused")
	private DefaultTableModel model;
	private JTextField txtname;
	private JTextField txtbook1;
	private JDateChooser txtrdate;
	private JDateChooser txtdate;
	

	
	public Bookissue() {		
		initComponents();
		connect();
		clearTable();
		book_Load();
		
	}
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtdate_2;
	private JTextField txtrdate_1;
	
	public class BookItem{
		String name;
		int id;
		public BookItem(int id,String name) {
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
			Logger.getLogger(Bookissue.class.getName()).log(Level.SEVERE, null , e);
			e.printStackTrace();
		} catch (SQLException e) {
			Logger.getLogger(Bookissue.class.getName()).log(Level.SEVERE, null , e);
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
			pst=con.prepareStatement("select * from bookissue");
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
			         String name = rs.getString("memberid");
			         String mname = rs.getString("membername");
			         String job = rs.getString("bookid");
			         String job1 = rs.getString("issuedate");
			         String job2 = rs.getString("returndate");
			           Object[] row = { id, name,mname, job,job1,job2};

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
		
		JLabel lblNewLabel = new JLabel("Book Issue");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(74, 12, 197, 35);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		JLabel lblCategoryName = new JLabel("Member Id :");
		lblCategoryName.setBounds(27, 114, 150, 35);
		lblCategoryName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoryName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					String mid=txtid.getText();
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","rootpassword");//rootpassword
						pst=con.prepareStatement("select * from member where id=?");
						pst.setString(1, mid);
						rs=pst.executeQuery();
						if(rs.next()==false) {
							JOptionPane.showMessageDialog(null,"Member ID not found", "STATUS", JOptionPane.INFORMATION_MESSAGE);
						}else {
							String memname=rs.getString("mname");
							txtname.setText(memname.trim());
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		txtid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtid.setBounds(195, 120, 161, 27);
		txtid.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mid=txtid.getText();
				String memnam=txtname.getText();
				String booka=txtbook1.getText();
				String issuedate=txtdate_2.getText();
				String issuedat=txtrdate_1.getText();
				/*DateFormat date_1=new SimpleDateFormat("yyyy-MM-dd"); 
				String issuedate=date_1.format(txtdate.getDate());
				DateFormat date_=new SimpleDateFormat("yyyy-MM-dd"); 
				String issuedat=date_.format(txtrdate.getDate());*/
				//System.out.println(issuedate+issuedat);
				try {
					pst = con.prepareStatement("INSERT INTO bookissue(memberid,membername,bookid,issuedate,returndate)"+"VALUES(?,?,?,?,?)");
					pst.setString(1, mid);
					pst.setString(2, memnam);
					pst.setString(3, booka);
					pst.setString(4, issuedate);
					pst.setString(5, issuedat);
					int k = pst.executeUpdate();
					if(k== 1) {
						JOptionPane.showMessageDialog(null,"book Issued", "STATUS", JOptionPane.INFORMATION_MESSAGE);
						txtid.setText("");
						txtname.setText("");
						txtbook1.setText("");
						txtdate_2.setText("");
						txtrdate_1.setText("");
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
		btnAdd.setBounds(62, 487, 115, 33);
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null,"	Author Updated before", "STATUS", JOptionPane.INFORMATION_MESSAGE);
				DefaultTableModel d1= (DefaultTableModel) table.getModel();
				int selectIndex=table.getSelectedRow();
				int id=Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
				String mid=txtid.getText();
				String memnam=txtname.getText();
				String booka=txtbook1.getText();
				String issuedate=txtdate_2.getText();
				String issuedat=txtrdate_1.getText();
				try {
					pst = con.prepareStatement("UPDATE bookissue set memberid =? ,membername =?,bookid =?,issuedate=?,returndate=? where id =?");
					pst.setString(1, mid);
					pst.setString(2, memnam);
					pst.setString(3, booka);
					pst.setString(4, issuedate);
					pst.setString(5, issuedat);
					pst.setInt(6, id);
					//JOptionPane.showMessageDialog(null,"	Author Updated before", "STATUS", JOptionPane.INFORMATION_MESSAGE);
					int k = pst.executeUpdate();
					if(k== 1) {
						JOptionPane.showMessageDialog(null,"Issued book Updated", "STATUS", JOptionPane.INFORMATION_MESSAGE);
						txtid.setText("");
						txtname.setText("");
						txtbook1.setText("");
						txtdate_2.setText("");
						txtrdate_1.setText("");
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
		btnUpdate.setBounds(239, 487, 115, 33);
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main m=new Main();
				//this.Hide();
				m.setVisible(true);
			}
		});
		btnCancel.setBounds(239, 561, 115, 33);
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnUpdate_1_1 = new JButton("Delete");
		btnUpdate_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel d1= (DefaultTableModel) table.getModel();
				int selectIndex=table.getSelectedRow();
				int id=Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
				//JOptionPane.showMessageDialog(null,"Category Created", "STATUS", JOptionPane.ERROR_MESSAGE);
				try {
					pst = con.prepareStatement("DELETE from bookissue where id=?");
					pst.setInt(1, id);
					//JOptionPane.showMessageDialog(null,"Category Created", "STATUS", JOptionPane.INFORMATION_MESSAGE);
					int k = pst.executeUpdate();
					if(k== 1) {
						JOptionPane.showMessageDialog(null,"Issued book Deleted", "STATUS", JOptionPane.INFORMATION_MESSAGE);
						txtid.setText("");
						txtname.setText("");
						txtbook1.setText("");
						txtdate_2.setText("");
						txtrdate_1.setText("");
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
		btnUpdate_1_1.setBounds(62, 561, 115, 33);
		btnUpdate_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel.setLayout(null);
		panel.add(lblNewLabel);
		panel.add(lblCategoryName);
		panel.add(txtid);
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
				txtid.setText(d1.getValueAt(selectIndex, 1).toString());
				txtname.setText(d1.getValueAt(selectIndex, 2).toString());
				txtbook1.setText(d1.getValueAt(selectIndex, 3).toString());
				txtdate_2.setText(d1.getValueAt(selectIndex, 4).toString());
				txtrdate_1.setText(d1.getValueAt(selectIndex, 5).toString());
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
				"ID", "Member Id", "Member Name", "Book", "Issue Date", "Return Date"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(132);
		table.getColumnModel().getColumn(3).setPreferredWidth(98);
		table.getColumnModel().getColumn(4).setPreferredWidth(84);
		table.getColumnModel().getColumn(5).setPreferredWidth(82);
		
		JLabel lblAuthor = new JLabel("   Book   :");
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthor.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAuthor.setBounds(37, 254, 150, 35);
		panel.add(lblAuthor);
	
		
		JComboBox txtbook = new JComboBox();
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/mydb","root","rootpassword");   
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from book");  
			while(rs.next())  
				txtbook.addItem(new BookItem(rs.getInt(1),rs.getString(2)));
			//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			con.close();
			}catch(Exception e){ System.out.println(e);} 
		txtbook.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				txtbook1.setText(txtbook.getSelectedItem().toString());
			}
		});
		txtbook.setBounds(195, 254, 159, 30);
		panel.add(txtbook);
		JLabel lblNewLabel_1_1 = new JLabel(" Date   :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(37, 346, 140, 27);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Return Date :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(27, 415, 140, 27);
		panel.add(lblNewLabel_1_1_1);
		
		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(195, 184, 159, 27);
		panel.add(txtname);
		
		JLabel lblMemberName = new JLabel("   Member Name   :");
		lblMemberName.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemberName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMemberName.setBounds(10, 178, 167, 35);
		panel.add(lblMemberName);
		
		txtbook1 = new JTextField();
		txtbook1.setColumns(10);
		txtbook1.setBounds(195, 295, 159, 27);
		panel.add(txtbook1);
		
		txtdate_2 = new JTextField();
		txtdate_2.setColumns(10);
		txtdate_2.setBounds(197, 348, 159, 27);
		panel.add(txtdate_2);
		
		txtrdate_1 = new JTextField();
		txtrdate_1.setColumns(10);
		txtrdate_1.setBounds(195, 417, 159, 27);
		panel.add(txtrdate_1);
		contentPane.setLayout(gl_contentPane);
		
	}


	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bookissue frame = new Bookissue();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}  
	