package shapes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import static java.lang.Math.min;
import static java.lang.Math.max;
import static java.lang.Math.abs;

public class Square extends Shape {
	private static final long serialVersionUID = 1L;

	@Override
	public void draw(Graphics2D p) {
		p.setColor(color);
		p.setStroke(new BasicStroke(thickness));
		p.drawRect(min(x1,x2), min(y1,y2), max(abs(x1-x2),abs(y1-y2)), max(abs(x1-x2),abs(y1-y2)));
	}

}
