package panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ColorsPanel extends Function implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton []colorButtons=new JButton[12];
	
	public ColorsPanel() {
		setLayout(new GridLayout(2,6));
		addColorButtons();
	}
	
	private void addColorButtons() {
		String []name= {"白色","黑色","红色","绿色",
				  "蓝色","橙色","粉红色","黄色",
				  "灰色","暗灰色","浅灰色","青色"};
		ImageIcon []colorIcons=new ImageIcon[13];
		for(int i=0;i<colorButtons.length;i++) {
			colorIcons[i]=new ImageIcon("ImageIcons/ColorIcons/"+name[i]+".png");
			colorButtons[i]=new JButton(colorIcons[i]);
			colorButtons[i].setToolTipText(name[i]);
			colorButtons[i].addActionListener(this);
			add(colorButtons[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<colorButtons.length;i++) {
			if(e.getSource()==colorButtons[i]) {
				colorChoice=i;
				newColor();
			}
		}
	}

}
