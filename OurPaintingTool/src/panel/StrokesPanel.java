package panel;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class StrokesPanel extends Function implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton []strokeButtons=new JButton[4];
	private JComboBox<String> fontStyles;//���������
	
	public StrokesPanel() {
		setLayout(new GridLayout(5,1));
		addFontStyles();
		addStrokeButtons();
	}
	
  private void addFontStyles() {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      String []styleNames = ge.getAvailableFontFamilyNames();  //����ϵͳ����
      fontStyles=new JComboBox<String>(styleNames);
      fontStyles.setMaximumRowCount(10);
      fontStyles.setMinimumSize(new Dimension(80, 20));
      fontStyles.setToolTipText("����");
      fontStyles.addItemListener(new ItemListener() {//ѡ��һ������
          @Override
          public void itemStateChanged(ItemEvent e) {
              fontStyle=styleNames[fontStyles.getSelectedIndex()];
          }
      });
  }
	
	private void addStrokeButtons() {
		String []name= {"һ��","����","����","�ļ�"};
		ImageIcon []StrokeIcons=new ImageIcon[4];
		for(int i=0;i<strokeButtons.length;i++) {
			StrokeIcons[i]=new ImageIcon("ImageIcons/StrokeIcons/"+name[i]+".png");
			strokeButtons[i]=new JButton(StrokeIcons[i]);
			strokeButtons[i].setToolTipText(name[i]);
			strokeButtons[i].addActionListener(this);
			add(strokeButtons[i]);
		}
		add(fontStyles);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<strokeButtons.length;i++) {
			if(e.getSource()==strokeButtons[i]) {
				strokeChoice=i;
				newStroke();
			}
		}
	}
	
}
