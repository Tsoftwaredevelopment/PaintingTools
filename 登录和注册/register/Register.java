package register;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.RegisterDao;

public class Register extends JFrame{
	private String RegisterTitle = "注册";
	private JPanel jp1 = new JPanel();
	private Label username = new Label("用户名:");
	private JTextField jtf = new JTextField(15);
	
	private JPanel jp2 = new JPanel();
	private Label password = new Label("密码:");
	private JTextField jtf2 = new JTextField (15);
	
	private JPanel jp3 = new JPanel();
	private Label confirm = new Label("请再次输入密码:");
	private JTextField jtf3 = new JTextField(15);
	
	private JPanel jp4 = new JPanel();
	private Label gender = new Label("性别:");
	private JRadioButton jrb = new JRadioButton("男");
	private JRadioButton jrb2 = new JRadioButton("女");
	
	private JPanel jp5 = new JPanel();
	private Label age = new Label("年龄:");
	private JTextField jtf5 = new JTextField(15);
	
	private JPanel jp6 = new JPanel();
	private Label email = new Label("邮箱:");
	private JTextField jtf6 = new JTextField(15);
	
	private JPanel jpb = new JPanel();
	private JButton jbt1 = new JButton("确定");
	private JButton jbt2 = new JButton("取消");
	
	public Register() {
		
		
		
		setTitle(RegisterTitle);
		setSize(450,500);
		setLayout(new GridLayout(7,1));
		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
		setVisible(true);
		
		add(jp1);
		jp1.add(username);
		jp1.add(new JLabel("             "));
		jp1.add(jtf);
		
		add(jp2);
		jp2.add(password);
		jp2.add(new JLabel("                "));
		jp2.add(jtf2);
		
		add(jp3);
		jp3.add(confirm);
		jp3.add(jtf3);
		
		add(jp4);
		jp4.add(gender);
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrb);
		bg.add(jrb2);
		jp4.add(new JLabel("                                              "));
		jp4.add(jrb);
		jp4.add(jrb2);
		
		add(jp5);
		jp5.add(age);
		jp5.add(new JLabel("                "));
		jp5.add(jtf5); 
		
		add(jp6);
		jp6.add(email);
		jp6.add(new JLabel("                "));
		jp6.add(jtf6);
		
		add(jpb);
		jpb.add(jbt1);
		jpb.add(new JLabel("                "));
		jpb.add(jbt2);
		
		jbt1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jtf2.getText().equals(jtf3.getText())) {
					
//					System.out.println(GenderSelection());
					
					RegisterDao register = new RegisterDao();
//					if(false) {
//						System.out.println("testMethod");
//					}
					if(register.insert(jtf.getText(),jtf2.getText(),jtf5.getText(),jtf6.getText(),GenderSelection())==true) {
						
						JOptionPane.showMessageDialog(rootPane, "注册成功","",JOptionPane.PLAIN_MESSAGE);
						setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(rootPane, "用户名重复","",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "两次输入密码不一致，请重新输入","",JOptionPane.ERROR_MESSAGE);
				}
			}

			private String GenderSelection() {
				// TODO Auto-generated method stub
				String gender;
				if(jrb.isSelected()==true) {
					gender = "man";
				}
				else {
					gender = "woman";
				}
				return gender;
			}
			
		});
		
		jbt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
			
		});
	}
}
