package prova_gioco.apk;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public abstract class Element {
	
	public Bitmap sprite; 
	public int x;
	public int y;
	public int true_x;
	public int true_y;
	
	public Element (String str, Context mycontext) {
		
		AssetManager myassetmanager = mycontext.getAssets();
		InputStream myinputstream = null;
		try {
			myinputstream = myassetmanager.open(str + ".png");
			sprite = BitmapFactory.decodeStream(myinputstream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (myinputstream != null)
				try {
				myinputstream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
	}
	
	abstract void draw_sprite(Canvas canvas_buffer);
}
