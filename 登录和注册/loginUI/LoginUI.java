package loginUI;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;

import dao.LoginDao;
import register.Register;
import workstation.MainFrame;

public class LoginUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loginTitle = "Login";
	
	private JPanel jp0 = new JPanel();
	private JLabel lab0 = new JLabel(new ImageIcon("ImageIcons/LoginLogo/LoginLOGO.png"));	
	private JLabel labspace = new JLabel(" ");
	
	private JPanel jp1 = new JPanel();
	private JLabel lab1 = new JLabel("用户名:");
	private JTextField jta1 = new JTextField(15);
	
	
	private JPanel jp2 = new JPanel();
	private JLabel lab2 = new JLabel("密码:");
	private JTextField jta2 = new JTextField(15);
	
	private JPanel jp3 = new JPanel();
	private JLabel lab3 = new JLabel("验证码:");
	private JTextField jta3 = new JTextField(15);

	private JPanel jp4 = new JPanel();
	private JButton jbt = new JButton("登录");
	private JButton jbt2 = new JButton("取消");
	private JButton jbt3 = new JButton("新用户注册");
	
	public LoginUI () {
		setTitle(loginTitle);
		setMainFrameImage();
		setLayout(new GridLayout(6,2,50,0));
		setDefaultCloseOperation(3);
		setSize(450,500);
		
		add(jp0);
		jp0.add(lab0);
		
		add(labspace);
		
		add(jp1);
		jp1.add(lab1);
		jp1.add(new JLabel("       "));
		jp1.add(jta1);
		
		add(jp2);
		jp2.add(lab2);
		jp2.add(new JLabel("           "));
		jp2.add(jta2);
		
		add(jp3);
		jp3.add(lab3);
		jp3.add(new JLabel("       "));
		jp3.add(jta3);
		
		add(jp4);
		jp4.add(jbt);
		jp4.add(new JLabel("       "));
		jp4.add(jbt2);
		jp4.add(new JLabel("       "));
		jp4.add(jbt3);
		
		jbt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginDao loginDao = new LoginDao();
				if(loginDao.check(jta1.getText(),jta2.getText()))
				{
					MainFrame mainframe = new MainFrame();
					setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(rootPane, "用户名或密码错误！", "登陆失败！",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		jbt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		jbt3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Register register = new Register();
			}
			
		});
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	private void setMainFrameImage() {
		Toolkit kit=Toolkit.getDefaultToolkit();
		setIconImage(kit.getImage("ImageIcons/MainFrameIcon/TitleIcon.png"));
	}
	
}
