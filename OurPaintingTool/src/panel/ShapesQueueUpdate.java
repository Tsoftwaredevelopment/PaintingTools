package panel;

import static panel.Function.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import mainframecommponent.DrawBoard;
import shapes.Curve;
import shapes.Eraser;
import shapes.Spray;

public class ShapesQueueUpdate extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private DrawBoard drawPanel;
	private JButton []toolButtons=new JButton[4];
	
	public ShapesQueueUpdate(DrawBoard paintBoard) {
		drawPanel=paintBoard;
		setLayout(new GridLayout(2,2));
		addToolButtons();
	}
	
	private void addToolButtons() {
		String []name= {"����","�ָ�","ɾ��","��ԭ"};
		ImageIcon []shortCutToolIcons=new ImageIcon[4];
		for(int i=0;i<toolButtons.length;i++) {
			shortCutToolIcons[i]=new ImageIcon("ImageIcons/ShortCutToolIcons/"+name[i]+".png");
			toolButtons[i]=new JButton(shortCutToolIcons[i]);
			toolButtons[i].setToolTipText(name[i]);
			toolButtons[i].addActionListener(this);
			add(toolButtons[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==toolButtons[0])
			revoke();
		else if(e.getSource()==toolButtons[1])
			recover();
		else if(e.getSource()==toolButtons[2])
			delete();
		else if(e.getSource()==toolButtons[3])
			restore();
	}
	//������ǰ��β��ͼ��
	private void revoke() {
		if(index>-1) {
			if(shapesQueue[index].getClass()!=Curve.class&&shapesQueue[index].getClass()!=Eraser.class&&shapesQueue[index].getClass()!=Spray.class)//�ж�����ϵ�ͼ�ε���״
				index--;
			else {
				index--;//��������߻���Ƥ��,��ôĩ�˵Ķ˵�index��rightһ�����,������԰ѱ����ǵ������
				while(shapesQueue[index].left!=index)//ֱ�����������߻���Ƥ����������Ҫ�������ߵ������˵����
					index--;
				index--;//ֻ���Ƶ����߻���Ƥ��ǰһ��ͼ�ε�ĩ�˵�
			}
			drawPanel.repaint();
		}
	}
	//�ָ���һ����ͼ��
	private void recover() {
		if(index<maxShapesCount) {
			if(shapesQueue[index+1].getClass()!=Curve.class&&shapesQueue[index+1].getClass()!=Eraser.class&&shapesQueue[index+1].getClass()!=Spray.class)//�жϺ�һ��ͼ�ε���״
				index++;
			else {
				if(shapesQueue[index+1].left!=index+1) {//˵���������߿��ܲ����Ѿ������ͼ�����,�����������е����ߵĴ���
					int left=index+1,right;
					while(shapesQueue[left].right!=left)
						left++;
					left++;//��������Ҫ�����ߵ��¸�ͼ�ο�ʼ���ƶ���
					for(right=left,left=index+1;right<=maxShapesCount;right++,left++)
						queueUpdate(right,left);
					maxShapesCount=left-1;//�����������ͼ����Ŀ
				}
				if(maxShapesCount>index) {//ֻ����������������߲������һ��ͼ��ʱ��ִ��
				    while(shapesQueue[index+1].right!=index+1)//���������߻���Ƥ��ʱֱ�ӽ���,�������߻���Ƥ���Ҷ˵����
				        index++;
					index++;//������ĩ�˻��һ��ͼ�λ��Ƴ���
				}
			}
			drawPanel.repaint();
		}
	}
	//����ǰ��β��ͼ�δӶ�����ɾ��
	private void delete() {
		if(index>-1) {
			if(shapesQueue[index].getClass()!=Curve.class&&shapesQueue[index].getClass()!=Eraser.class&&shapesQueue[index].getClass()!=Spray.class) {
				for(int i=index;i<=maxShapesCount-1;i++)//���ƶ���
					queueUpdate(i+1,i);
				maxShapesCount--;
			}
			else {
				int left,right=index+1;
				while(shapesQueue[index].left!=index)
					index--;
				for(left=index;right<=maxShapesCount;right++,left++)
					queueUpdate(right,left);
				maxShapesCount=left-1;
			}
			index--;//ֻ���Ƶ�Ҫɾ��ͼ�ε�ǰһ��ͼ�ε�ĩ��
			drawPanel.repaint();
		}
	}
	//��ͼ�θ�ԭΪ���ͼ����
	private void restore() {
		recover();
		index=maxShapesCount;
	}

}
