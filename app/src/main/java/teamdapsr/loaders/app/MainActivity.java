package teamdapsr.loaders.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{



    private static final String[] ITEMS = {"Progressbar demo", "PiechartDemo", "list3", "list4"};


    @Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		ListView list = new ListView(this);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ITEMS);

		list.setAdapter(adapter);
		list.setOnItemClickListener(this);

		setContentView(list);


	}



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: {
                Intent i = new Intent(this, progressdemo.class);
                startActivity(i);
                break;
            }
            case 1: {
                Intent i = new Intent(this, PieChardDemo.class);
                startActivity(i);
                break;
            }
            case 2: {
//                Intent i = new Intent(this, DoubleImageActivity.class);
//                startActivity(i);
                break;
            }
            case 3: {
//                Intent i = new Intent(this, BoxGridActivity.class);
//                startActivity(i);
                break;
            }
            default:
                break;
        }
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
