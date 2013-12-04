package prova_gioco.apk;

import android.content.Context;
import android.graphics.Canvas;

public class Pallina extends Element {

	public int i;
	private Omino omino;
	private MySurface mysurface;
	
	public Pallina(String str, Context mycontext, Omino omino, MySurface mysurface) {
		super(str, mycontext);
		this.omino = omino;
		i = 0;
		this.mysurface = mysurface;
	}

	@Override
	void draw_sprite(Canvas canvas_buffer) {
		if (i == 0) {
			x = (int) ((Math.random()*1000000)%300);
			y = (int) ((Math.random()*1000000)%460);
			canvas_buffer.drawBitmap(sprite, x, y, null);
		}
		if ((check() == true)&&(i != 0)) {
			mysurface.distrutte++;
			x = 1000;
			y = 1000;
		}
		if (check() == false) {
			canvas_buffer.drawBitmap(sprite, x, y, null);
		}
		i++;

	}
	
	boolean check() {
		boolean a = false;
		boolean b = false;
		true_x = x + (sprite.getWidth()/2);
		true_y = y + (sprite.getHeight()/2);
		if (i == 0) return true;
		if ((omino.true_x() == true_x)||(omino.true_x() == true_x+1)||(omino.true_x() == true_x+2)||(omino.true_x() == true_x+3)||(omino.true_x() == true_x+4)||(omino.true_x() == true_x+5)||(omino.true_x() == true_x-1)||(omino.true_x() == true_x-2)||(omino.true_x() == true_x-3)||(omino.true_x() == true_x-4)||(omino.true_x() == true_x-5)) a = true;
		if ((omino.true_y() == true_y)||(omino.true_y() == true_y+1)||(omino.true_y() == true_y+2)||(omino.true_y() == true_y+3)||(omino.true_y() == true_y+4)||(omino.true_y() == true_y+5)||(omino.true_y() == true_y-1)||(omino.true_y() == true_y-2)||(omino.true_y() == true_y-3)||(omino.true_y() == true_y-4)||(omino.true_y() == true_y-5)) b = true;
		if ((a == true)&&(b == true)) return true;
		return false;
	}

}
