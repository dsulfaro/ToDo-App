package com.example.daniel.listoshit;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> todoList = new ArrayList();
    EditText userInput;
    ListView mainTODO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTODO = (ListView)findViewById(R.id.mainTODO);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {
            AlertDialog.Builder newTask = new AlertDialog.Builder(MainActivity.this);
            newTask.setMessage("What do you need to get done?");

            userInput = new EditText(this);
            newTask.setView(userInput);

            newTask.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String text = userInput.getText().toString();
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                    todoList.add(text);
                }
            });
            newTask.create().show();
            update();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onCheckboxClicked(View view){
        View v = (View) view.getParent();
        TextView victimHome = (TextView) v.findViewById(R.id.tv);
        String victim = victimHome.getText().toString();
        Toast.makeText(this, "You did it!", Toast.LENGTH_SHORT).show();
        int i = todoList.indexOf(victim);
        todoList.remove(i);
        update();
    }

    public void update(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.list_entry,
                R.id.tv,
                todoList);
        mainTODO.setAdapter(arrayAdapter);
    }
}
