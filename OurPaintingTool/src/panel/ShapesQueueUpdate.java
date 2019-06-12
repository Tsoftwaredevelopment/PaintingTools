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
		String []name= {"撤销","恢复","删除","还原"};
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
	//撤销当前队尾的图形
	private void revoke() {
		if(index>-1) {
			if(shapesQueue[index].getClass()!=Curve.class&&shapesQueue[index].getClass()!=Eraser.class&&shapesQueue[index].getClass()!=Spray.class)//判断最后画上的图形的形状
				index--;
			else {
				index--;//如果是曲线或橡皮擦,那么末端的端点index和right一定相等,这里可以把本身是点的跳过
				while(shapesQueue[index].left!=index)//直到遇到非曲线或橡皮擦或者这条要撤销曲线的起点左端点结束
					index--;
				index--;//只绘制到曲线或橡皮擦前一个图形的末端点
			}
			drawPanel.repaint();
		}
	}
	//恢复上一步的图形
	private void recover() {
		if(index<maxShapesCount) {
			if(shapesQueue[index+1].getClass()!=Curve.class&&shapesQueue[index+1].getClass()!=Eraser.class&&shapesQueue[index+1].getClass()!=Spray.class)//判断后一个图形的形状
				index++;
			else {
				if(shapesQueue[index+1].left!=index+1) {//说明这条曲线可能部分已经被别的图形替代,对于这条剪切的曲线的处理
					int left=index+1,right;
					while(shapesQueue[left].right!=left)
						left++;
					left++;//从这条不要的曲线的下个图形开始左移队列
					for(right=left,left=index+1;right<=maxShapesCount;right++,left++)
						queueUpdate(right,left);
					maxShapesCount=left-1;//重新设置最大图形数目
				}
				if(maxShapesCount>index) {//只有这条被替代的曲线不是最后一个图形时才执行
				    while(shapesQueue[index+1].right!=index+1)//遇到非曲线或橡皮擦时直接结束,否则到曲线或橡皮擦右端点结束
				        index++;
					index++;//将曲线末端或后一个图形绘制出来
				}
			}
			drawPanel.repaint();
		}
	}
	//将当前队尾的图形从队列中删除
	private void delete() {
		if(index>-1) {
			if(shapesQueue[index].getClass()!=Curve.class&&shapesQueue[index].getClass()!=Eraser.class&&shapesQueue[index].getClass()!=Spray.class) {
				for(int i=index;i<=maxShapesCount-1;i++)//左移队列
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
			index--;//只绘制到要删除图形的前一个图形的末端
			drawPanel.repaint();
		}
	}
	//将图形复原为最大图形数
	private void restore() {
		recover();
		index=maxShapesCount;
	}

}
