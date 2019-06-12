package menuoption;

import static panel.Function.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class EditMenu extends JMenu implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JMenuItem []editItem=new JMenuItem[2];
	
	public EditMenu() {
		super("±à¼­");
		addEditItem();
	}
	
	private void addEditItem() {
		String []name= {"±à¼­»­±ÊÑÕÉ«","±à¼­»­±Ê´ÖÏ¸"};
		ImageIcon []editIcons=new ImageIcon[2];
		int []keyBoard= {KeyEvent.VK_E,KeyEvent.VK_R};
		for(int i=0;i<editItem.length;i++) {
		    editIcons[i]=new ImageIcon("ImageIcons/MenuBarIcons/EditIcons/"+name[i]+".png");
	      editItem[i]=new JMenuItem(name[i], editIcons[i]);
	      editItem[i].setAccelerator(KeyStroke.getKeyStroke(keyBoard[i], InputEvent.CTRL_MASK));
	      editItem[i].addActionListener(this);
	      add(editItem[i]);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==editItem[0]) {
			color=JColorChooser.showDialog(null,"Ñ¡Ôñ»­±ÊÑÕÉ«",color);
		}
		else if(e.getSource()==editItem[1]) {
			String input=JOptionPane.showInputDialog("ÇëÊäÈë»­±Ê´ÖÏ¸");
			if(input!=null)//·ÀÖ¹ÓÃ»§µã»÷È¡Ïû,Ôì³ÉµÄ¿ÕÖ¸ÕëÒì³£
				stroke=Float.parseFloat(input);
		}
	}

}
