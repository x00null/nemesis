package net.imu24r.cscedit;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.exceptions.RootDeniedException;
import com.stericson.RootTools.execution.CommandCapture;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "net.imu24r.cscedit.MESSAGE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	
	
	public void sendMessage(View view) throws InterruptedException, IOException, TimeoutException, RootDeniedException   {
	
	
	
		CommandCapture command = new CommandCapture(0,"mount -o rw,remount /dev/block/stl9 /system","echo '<CscFeature_Message_ReplaceCRToNewLine>true</CscFeature_Message_ReplaceCRToNewLine>' > /system/hi.txt","ls /system/ > /data/data/net.imu24r.cscedit/files/salida.txt","chmod 777 /data/data/net.imu24r.cscedit/files/salida.txt");
		RootTools.getShell(true).add(command).waitForFinish();
		
	
		String     res  =   "";
		String line = "";
		try {
		 
		       InputStream       in = openFileInput("salida.txt");
		 
		       if (in != null) {
		        // prepare the file for reading
		         InputStreamReader input = new InputStreamReader(in);
		         BufferedReader buffreader = new BufferedReader(input);
		          String salto = "\n";
		          res = "";
		          while (( line = buffreader.readLine()) != null) {
		            res += line + salto;
		            
		            
		          }
		          in.close();
		          
		          Intent intent = new Intent(this, ActivityLS.class);
				  String message = res.toString();
				  intent.putExtra(EXTRA_MESSAGE, message);	
				  startActivity(intent);
		          }
		       
		       
		       else{
		        }
		 
		} catch(Exception e){
		       Toast.makeText(getApplicationContext(),     e.toString() +   e.getMessage(),Toast.LENGTH_SHORT).show();
		}
		

        
	}
	Intent intent = new Intent(this, ActivityLS.class);
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
}
