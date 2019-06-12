package mainframecommponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import static panel.Function.*;

public class AdditionalTool extends JToolBar implements ActionListener{
    private static final long serialVersionUID = 1L;
    private JButton []buttons=new JButton[1];
    
    public AdditionalTool(String name, int orientation) {
        super(name, orientation);
        addFuncComponent();
    }
    
    private void addFuncComponent() {
        String []name= {"�������"};
        ImageIcon []shapeIcons=new ImageIcon[1];
        for(int i=0;i<buttons.length;i++) {
          shapeIcons[i]=new ImageIcon("ImageIcons/AdditionalIcons/"+name[i]+".png");
          buttons[i]=new JButton(shapeIcons[i]);
          buttons[i].setToolTipText(name[i]);
          buttons[i].addActionListener(this);
          add(buttons[i]);
        }  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<buttons.length;i++)
            if(e.getSource()==buttons[i]) {
                shapeChoice=i+12;
//                if(shapeChoice==12)
//                    JOptionPane.showMessageDialog(null, "���������������ı�", "����ı�", JOptionPane.INFORMATION_MESSAGE);
            }
    }
    
}
