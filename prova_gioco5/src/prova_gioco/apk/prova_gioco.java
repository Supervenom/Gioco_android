package prova_gioco.apk;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class prova_gioco extends Activity {
    /** Called when the activity is first created. */
	
	public MySurface mysurface;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mysurface = new MySurface(this);
        setContentView(mysurface);
    }
    
	public void onResume() {
    	super.onResume();
    	mysurface.resume();
    }
    
	public void onPause() {
		super.onPause();
		mysurface.pause();
		}
	
}