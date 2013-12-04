package prova_gioco.apk;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Touch_input implements OnTouchListener {
	
	public float x = 0;
	public float y = 0;
	public boolean verifica = true;
	
	public Touch_input(MySurface mysurface, int w, int h) {
		mysurface.setOnTouchListener(this);
		x = w/2;
		y = h/2;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		verifica = true;
		x = event.getX();
		y = event.getY();
		return false;
	}
}
