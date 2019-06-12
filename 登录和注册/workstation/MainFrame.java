package workstation;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import mainframecommponent.DrawBoard;
import mainframecommponent.MenuBar;
import mainframecommponent.PositionLabel;
import mainframecommponent.ToolBar;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private String title="Java MiNi Drawing Tool";
	private PositionLabel mousePostion=new PositionLabel();
	private DrawBoard drawPanel=new DrawBoard(mousePostion);
	private MenuBar menuColumn=new MenuBar(drawPanel);
	private ToolBar toolPanel=new ToolBar("快捷工具", JToolBar.HORIZONTAL, drawPanel);
	
	public MainFrame(){
		setTitle(title);
		setMainFrameImage();
		setBounds(310,50,930,715);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener();
		setJMenuBar(menuColumn);
		add(toolPanel,BorderLayout.NORTH);
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
				int res=JOptionPane.showConfirmDialog(drawPanel, "您确定要退出Java MiNi Drawing Tool了吗?", "退出提示", JOptionPane.WARNING_MESSAGE);
				if(res==JOptionPane.OK_OPTION)
					System.exit(0);
			}
		});
	}
	
}
