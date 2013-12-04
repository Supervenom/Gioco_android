package prova_gioco.apk;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurface extends SurfaceView implements Runnable {

	private Omino omino;
	private int n_palline = 20;
	private Pallina pallina[] = new Pallina[n_palline];
	public Context mycontext;
	private Thread mythread = null;
	private SurfaceHolder mysurfaceholder;
	volatile boolean running = false;
	public Canvas canvas_buffer;
	private int i;
	public Touch_input mytouch_input;
	private Canvas canvas = new Canvas();
	private int da_disegnare = 5;
	private float contatore = 0;
	public int distrutte = 0;
	private Paint mypaint;
	private Bitmap buffer;
	
	public MySurface(Context mycontext) {
		super(mycontext);
		this.mycontext = mycontext;
		buffer = Bitmap.createBitmap(320,480,Config.RGB_565);
		mysurfaceholder = getHolder();
		omino = new Omino("omino", mycontext);
		for (i = 0; i<n_palline; i++) {
			pallina[i] = new Pallina("pallina", mycontext, omino, this);
		}
		canvas_buffer = new Canvas(buffer);
		canvas_buffer.drawRGB(255, 255, 255);
		mypaint = new Paint();
		mypaint.setColor(Color.BLACK);
		mypaint.setTextSize(28);
		mypaint.setTextAlign(Paint.Align.CENTER);
		
	}
	
	public void resume() {
		running = true;
		mythread = new Thread(this);
		mythread.start();
		mytouch_input = new Touch_input(this, omino.sprite.getWidth(), omino.sprite.getHeight());
		omino.send_touch_input(mytouch_input);
	}
	
	public void run() {
		while(running) {
			if(!mysurfaceholder.getSurface().isValid())
			continue;
			contatore++;
			if (((contatore%150) == 0)&& (contatore>0)) da_disegnare++;
			drawBuffer();
			canvas = mysurfaceholder.lockCanvas();
			canvas.drawBitmap(buffer, 0, 0, null);
			mysurfaceholder.unlockCanvasAndPost(canvas);
			canvas_buffer.drawRGB(255, 255, 255);
		}
	}
	
	private void setDefault() {
		distrutte = 0;
		da_disegnare = 5;
		contatore = 0;
		for (i = 0; i<n_palline; i++) {
			pallina[i].i = 0;
		}
	}
	
	private void drawBuffer () {
		if (da_disegnare == 20) {
			canvas_buffer.drawText("Hai perso!", 100, 50, mypaint);
			contatore = contatore - 4000;
			if (mytouch_input.verifica == true) {
				setDefault();
			}
		}
		else if (da_disegnare == distrutte) {
			canvas_buffer.drawText("Hai vinto!", 100, 50, mypaint);
			contatore--;
			if (mytouch_input.verifica == true) {
				setDefault();
			}
		}
		else if (distrutte != da_disegnare) {
			for (i = 0; i<da_disegnare; i++) {
				pallina[i].draw_sprite(canvas_buffer);
			}
			omino.draw_sprite(canvas_buffer);
		}
	}
	
	public void pause() {
		running = false;
		try {
		mythread.join();
		} catch (InterruptedException e) {
		// retry
		}
	}
	
}
