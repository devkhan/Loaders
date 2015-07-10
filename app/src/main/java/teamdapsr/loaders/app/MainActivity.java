package teamdapsr.loaders.app;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Array;

import teamdapsr.loaders.lib.CustomProgressDialog;


public class MainActivity extends AppCompatActivity
{


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		getSupportActionBar().setTitle("Loaders");
		setContentView(R.layout.activity_main);

		ListView list = (ListView) findViewById(R.id.loaders_list);

		String[] loader = new String[]{"Crossword Grid", "Windows", "Numbers", "Circular",
				"Sqare", "Fade", "Rotate"};

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, loader);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position)
				{
					case 0 :
						DialogFragment dialog = new Cross();
						dialog.show(getSupportFragmentManager(), "Cross");
						break;

					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
						Toast.makeText(getApplicationContext(), "Coming soon" , Toast.LENGTH_LONG).show();
						break;

					case 6:
						DialogFragment ripple = new Ripple();
						ripple.show(getSupportFragmentManager(), "Ripple");
						break;
					default:
						Toast.makeText(getApplicationContext(), "wrong choice" , Toast.LENGTH_LONG).show();
						break;

				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings)
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
