package workstation;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import loginUI.LoginUI;

public class Main {

	public static void main(String []args) {
		int res=JOptionPane.showConfirmDialog(null, "您是否想要加载系统界面风格?", "运行界面选择页面", JOptionPane.YES_NO_OPTION);
		if(res==JOptionPane.YES_OPTION) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		LoginUI login = new LoginUI();
//		MainFrame drawingPicture =new MainFrame();
//		drawingPicture.end();
	}

}
