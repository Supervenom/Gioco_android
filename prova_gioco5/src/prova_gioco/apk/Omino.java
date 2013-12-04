package prova_gioco.apk;

import android.content.Context;
import android.graphics.Canvas;

public class Omino extends Element {

	private float touch_x;
	private float touch_y;
	private Touch_input mytouch_input;
	
	public Omino(String str, Context mycontext) {
		super(str, mycontext);
		
	}

	@Override
	void draw_sprite(Canvas canvas_buffer) {
		if (mytouch_input.verifica == true) {
			touch_x = mytouch_input.x - (sprite.getWidth()/2);
			touch_y = mytouch_input.y - (sprite.getHeight()/2);
		}
		mytouch_input.verifica = false;
		if (x < touch_x) x = x + 3;
		if (x > touch_x) x = x - 3;
		if (y < touch_y) y = y + 3;
		if (y > touch_y) y = y - 3;
		canvas_buffer.drawBitmap(sprite, x, y, null);
	}
	
	public void send_touch_input(Touch_input mytouch_input) {
		
		this.mytouch_input = mytouch_input;
	}
	
	public int true_x() {
		true_x = x + (sprite.getWidth()/2);
		return true_x;
	}
	
	public int true_y() {
		true_y = y + (sprite.getHeight()/2);
		return true_y;
	}

}
