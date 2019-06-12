package workstation;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import mainframecommponent.AdditionalTool;
import mainframecommponent.DrawBoard;
import mainframecommponent.MenuBar;
import mainframecommponent.PositionLabel;
import mainframecommponent.ToolBar;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private String title="Java MiNi Drawing Tool";
	private PositionLabel mousePostion=new PositionLabel();
	public DrawBoard drawPanel=new DrawBoard(mousePostion);
	private MenuBar menuColumn=new MenuBar(this);
	private ToolBar toolPanel=new ToolBar("��ݹ���", JToolBar.HORIZONTAL, drawPanel);
	private AdditionalTool additionalTool=new AdditionalTool("���ӹ���", JToolBar.VERTICAL);
	
	public MainFrame(){
		setTitle(title);
		setMainFrameImage();
		setBounds(310,50,1025,715);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener();
		setJMenuBar(menuColumn);
		add(toolPanel,BorderLayout.NORTH);
		add(additionalTool,BorderLayout.WEST);
		add(drawPanel, BorderLayout.CENTER);
		add(mousePostion, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	private void setMainFrameImage() {
		Toolkit kit=Toolkit.getDefaultToolkit();
		setIconImage(kit.getImage("ImageIcons/MainFrameIcon/TitleIcon.png"));
	}
	
	private void addWindowListener() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				menuColumn.fileOption.isSave(true);
				int res=JOptionPane.showConfirmDialog(drawPanel, "��ȷ��Ҫ�˳�Java MiNi Drawing Tool����?", "�˳���ʾ", JOptionPane.WARNING_MESSAGE);
				if(res==JOptionPane.OK_OPTION)
					System.exit(0);
			}
		});
	}
	
	private void end() {
		System.out.println("Welcome To Use");
	}
	
	public static void main(String []args) {
		int res=JOptionPane.showConfirmDialog(null, "���Ƿ���Ҫ����ϵͳ������?", "���н���ѡ��ҳ��", JOptionPane.YES_NO_OPTION);
		if(res==JOptionPane.YES_OPTION) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		MainFrame drawingPicture =new MainFrame();
		drawingPicture.end();
	}
}
