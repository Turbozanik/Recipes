package by.test.roma.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button recipesButton;
    Button menuButton;
    Button aboutButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        recipesButton = (Button)findViewById(R.id.allRecipes);
        menuButton = (Button)findViewById(R.id.menuButton);
        aboutButton = (Button)findViewById(R.id.about);


        recipesButton.setOnClickListener(this);
        menuButton.setOnClickListener(this);
        aboutButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.allRecipes :{
                Intent intent = new Intent(MenuActivity.this,ListActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menuButton :{
                break;
            }
            case R.id.about :{
                break;
            }
        }

    }
}
