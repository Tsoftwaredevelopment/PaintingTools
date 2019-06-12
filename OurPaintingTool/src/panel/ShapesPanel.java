package panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ShapesPanel extends Function implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton []shapeButtons=new JButton[12];
	
	public ShapesPanel() {
		setLayout(new GridLayout(2,6));
		addShapeButtons();
	}
	
	private void addShapeButtons() {
		String []name= {"���ɻ���","ֱ��","��Ƥ��","����","������","��Բ","Բ","ʵ�ľ���","ʵ����Բ","ʵ��Բ","�����","����"};
		ImageIcon []shapeIcons=new ImageIcon[12];
		for(int i=0;i<shapeButtons.length;i++) {
			shapeIcons[i]=new ImageIcon("ImageIcons/ShapeIcons/"+name[i]+".png");
			shapeButtons[i]=new JButton(shapeIcons[i]);
			shapeButtons[i].setToolTipText(name[i]);
			shapeButtons[i].addActionListener(this);
			add(shapeButtons[i]);
		}  
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<shapeButtons.length;i++) {
			if(e.getSource()==shapeButtons[i]) {
				shapeChoice=i;
			}
		}
	}
	
}
