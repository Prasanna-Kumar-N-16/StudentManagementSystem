package StudentManagement;

//import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

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

public class Author extends JFrame {

	private JPanel contentPane;
	private JTextField txtname;
	private JTable table;
	private JScrollPane ScrollPane;
	private DefaultTableModel model;
	private JTextField txtphone;


	
	public Author() {
		
		initComponents();
		connect();
		author_Load();
		
		
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//"jdbc:mysql://localhost:3306/sonoo","root","root");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","rootpassword");//rootpassword
		} catch (ClassNotFoundException e) {
			Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null , e);
			e.printStackTrace();
		} catch (SQLException e) {
			Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null , e);
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
	public void author_Load() {
		int c;
		int d;
		try {
			pst=con.prepareStatement("select * from author");
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
			         String name = rs.getString("name");
			         String job = rs.getString("address");
			         int ph = rs.getInt("phone");
			           Object[] row = { id, name, job,ph};

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
		// TODO Auto-generated method stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 839, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 643, GroupLayout.PREFERRED_SIZE))
		);
		
		JLabel lblNewLabel = new JLabel("Author");
		lblNewLabel.setBounds(72, 43, 120, 35);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		JLabel lblCategoryName = new JLabel("Author Name  :");
		lblCategoryName.setBounds(27, 109, 150, 35);
		lblCategoryName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoryName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel lblStatus = new JLabel("     Address      :");
		lblStatus.setBounds(27, 182, 150, 35);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		txtname = new JTextField();
		txtname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtname.setBounds(195, 109, 161, 27);
		txtname.setColumns(10);
		
		JTextArea txtaddress = new JTextArea();
		txtaddress.setBounds(195, 158, 155, 115);
		panel.add(txtaddress);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  String name = txtname.getText();
			  String address = txtaddress.getText();
			  String phone = txtphone.getText();
				try {
					pst = con.prepareStatement("INSERT INTO author(name,address,phone)"+"VALUES(?,?,?)");//
					pst.setString(1, name);
					pst.setString(2, address);
					pst.setString(3, phone);
					int k = pst.executeUpdate();
					if(k== 1) {
						JOptionPane.showMessageDialog(null,"Author Created", "STATUS", JOptionPane.INFORMATION_MESSAGE);
						txtname.setText("");
						txtaddress.setText("");
						txtphone.setText("");
						txtname.requestFocus();
						clearTable();
						author_Load();
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
		btnAdd.setBounds(52, 351, 115, 33);
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel d1= (DefaultTableModel) table.getModel();
				int selectIndex=table.getSelectedRow();
				int idd=Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
				String names=txtname.getText();
				String addresss=txtaddress.getText();
				String phonee=txtphone.getText();
				
				try {
					pst = con.prepareStatement("UPDATE author set name =? ,address =?,phone =? where id =?");
					pst.setString(1, names);
					pst.setString(2, addresss);
					pst.setString(3, phonee);
					pst.setInt(4, idd);
					//JOptionPane.showMessageDialog(null,"	Author Updated before", "STATUS", JOptionPane.INFORMATION_MESSAGE);
					int k = pst.executeUpdate();
					if(k== 1) {
						JOptionPane.showMessageDialog(null,"	Author Updated", "STATUS", JOptionPane.INFORMATION_MESSAGE);
						txtname.setText("");
						txtaddress.setText("");
						txtphone.setText("");
						txtname.requestFocus();
						clearTable();
						author_Load();
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
		btnUpdate.setBounds(195, 351, 115, 33);
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Main m=new Main();
				m.setVisible(true);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		btnCancel.setBounds(195, 456, 115, 33);
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnUpdate_1_1 = new JButton("Delete");
		btnUpdate_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel d1= (DefaultTableModel) table.getModel();
				int selectIndex=table.getSelectedRow();
				int id=Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
				//JOptionPane.showMessageDialog(null,"Category Created", "STATUS", JOptionPane.ERROR_MESSAGE);
				try {
					pst = con.prepareStatement("DELETE from author where id=?");
					pst.setInt(1, id);
					//JOptionPane.showMessageDialog(null,"Category Created", "STATUS", JOptionPane.INFORMATION_MESSAGE);
					int k = pst.executeUpdate();
					if(k== 1) {
						JOptionPane.showMessageDialog(null,"Author Deleted", "STATUS", JOptionPane.INFORMATION_MESSAGE);
						txtname.setText("");
						txtaddress.setText("");
						txtphone.setText("");
						txtname.requestFocus();
						clearTable();
						author_Load();
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
		btnUpdate_1_1.setBounds(52, 456, 115, 33);
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
		/*model=new DefaultTableModel();
		Object[] column = {"ID","Category Name","Status"};
		@SuppressWarnings("unused")
		Object[] row = new Object[0];
		//Object[] row = new Object[1];
		model.setColumnIdentifiers(column);*/
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel d1= (DefaultTableModel) table.getModel();
				int selectIndex=table.getSelectedRow();
				int id=Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
				txtname.setText(d1.getValueAt(selectIndex, 1).toString());
				txtaddress.setText(d1.getValueAt(selectIndex, 2).toString());
				txtphone.setText(d1.getValueAt(selectIndex, 3).toString());
				//txtstatus.setSelectedItem(d1.getValueAt(selectIndex, 2).toString());
				btnAdd.setEnabled(false);
				
			}
		});
		scrollPane.setBounds(368, 26, 445, 586);
		panel.add(scrollPane);
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Name", "Address", "Phone Number"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Phone Number :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(37, 303, 140, 14);
		panel.add(lblNewLabel_1);
		
		txtphone = new JTextField();
		txtphone.setBounds(197, 299, 159, 27);
		panel.add(txtphone);
		txtphone.setColumns(10);
		contentPane.setLayout(gl_contentPane);
		
	}


	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Author frame = new Author();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

	/**
	 * Create the frame.
	 */
	