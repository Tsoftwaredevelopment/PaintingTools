package panel;

import java.awt.Color;
import javax.swing.JPanel;

import shapes.Shape;
import shapes.SolidCurve;
import shapes.SolidOval;
import shapes.SolidRectangle;
import shapes.Spray;
import shapes.Circle;
import shapes.Curve;
import shapes.Eraser;
import shapes.Line;
import shapes.Oval;
import shapes.Rectangle;
import shapes.Square;
import shapes.Star;
import shapes.Text;

public abstract class Function extends JPanel{
	private static final long serialVersionUID = 1L;
	public static  Shape []shapesQueue=new Shape[10000];
	public static  int index=-1;
	public static int maxShapesCount=-1;
	public static boolean isChanged=false;
	public static  int shapeChoice=0;//默认打开画图工具时画曲线
	protected static int colorChoice;//这里默认选择黑色,为橡皮擦而设
	protected static int strokeChoice;
	private static Color []colorArray = {Color.white,Color.black,Color.red,
			  							 Color.green,Color.blue , Color.orange,
			  							 Color.pink,Color.yellow,Color.gray,
			  							 Color.darkGray, Color.lightGray,
			  							 Color.cyan};
	private static float []width= {1,3,5,10};
	public static Color color=colorArray[1];
	public static float stroke=width[0];
	public static String fontStyle=null,inputContent;
	private static Text tmpText=null;

	protected static void creatAction() {
		switch(shapeChoice) {
			case 0:	shapesQueue[index]=new Curve();break;
			case 1: shapesQueue[index]=new Line();break;
			case 2: shapesQueue[index]=new Eraser();break;
			case 3: shapesQueue[index]=new Rectangle();break;
			case 4: shapesQueue[index]=new Square();break;
			case 5: shapesQueue[index]=new Oval();break;
			case 6: shapesQueue[index]=new Circle();break;
			case 7: shapesQueue[index]=new SolidRectangle();break;
			case 8: shapesQueue[index]=new SolidOval();break;
			case 9: shapesQueue[index]=new SolidCurve();break;
			case 10: shapesQueue[index]=new Star();break;
			case 11: shapesQueue[index]=new Spray();break;
			case 12: tmpText=new Text();tmpText.fontName=fontStyle;tmpText.inputContent=inputContent;
			         shapesQueue[index]=tmpText;break;
		}
		shapesQueue[index].color=color;
		shapesQueue[index].thickness=stroke;
		if(index>maxShapesCount)
			maxShapesCount=index;
	}
	
	protected static void newColor() {
		switch(colorChoice) {
			case 0: color=colorArray[0];break;
			case 1: color=colorArray[1];break;
			case 2: color=colorArray[2];break;
			case 3: color=colorArray[3];break;
			case 4: color=colorArray[4];break;
			case 5: color=colorArray[5];break;
			case 6: color=colorArray[6];break;
			case 7: color=colorArray[7];break;
			case 8: color=colorArray[8];break;
			case 9: color=colorArray[9];break;
			case 10: color=colorArray[10];break;
			case 11: color=colorArray[11];break;
		}
	}
	
	protected static void newStroke() {
		switch(strokeChoice) {
			case 0:stroke=width[0];break;
			case 1:stroke=width[1];break;
			case 2:stroke=width[2];break;
			case 3:stroke=width[3];break;
		}
	}
	
	//对ShapesQueue的更新维护
	public static void queueUpdate(int pastIndex,int newIndex) {
		if(shapesQueue[pastIndex].getClass()==Curve.class||shapesQueue[pastIndex].getClass()==Eraser.class||shapesQueue[pastIndex].getClass()==Spray.class) {
			if(shapesQueue[pastIndex].left==pastIndex)
				shapesQueue[pastIndex].left=newIndex;
			else if(shapesQueue[pastIndex].right==pastIndex)
				shapesQueue[pastIndex].right=newIndex;
		}
		shapesQueue[newIndex]=shapesQueue[pastIndex];
	}
	
}
