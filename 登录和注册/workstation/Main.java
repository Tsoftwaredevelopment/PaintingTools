package workstation;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import loginUI.LoginUI;

public class Main {

	public static void main(String []args) {
		int res=JOptionPane.showConfirmDialog(null, "���Ƿ���Ҫ����ϵͳ������?", "���н���ѡ��ҳ��", JOptionPane.YES_NO_OPTION);
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
