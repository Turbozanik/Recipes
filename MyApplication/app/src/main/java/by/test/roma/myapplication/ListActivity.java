package by.test.roma.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Roma on 28.08.2015.
 */
public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    public List<Recipe> recipes;

    static int count = 0;

    private ProgressDialog progressDialog;

    NetworkInfo mWifi;
    ConnectivityManager connManager;
    NetworkInfo mInternet;

    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipes = new ArrayList<Recipe>();
        connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        mInternet = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        //Parse.enableLocalDatastore(getApplicationContext());

        // Enable Local Datastore.
        if(mWifi.isConnected() && count == 0) {
            Log.d("Invoke order","first if");
            //Parse.initialize(getApplicationContext(), "HX6n9WMdhKg5BPhC7d22IKHV34jyTst5OHQxtxUD", "U9DotgCbByVP4eZ9oFae61w3Hnno0xFUgefYJOq4");
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Recipes");
            progressDialog = ProgressDialog.show(ListActivity.this, "",
                    "Update", true);

            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> recipesList, ParseException e) {
                    if (e == null) {

                        ParseObject.pinAllInBackground(recipesList);
                        Log.d("score", "Retrieved " + recipesList.size() + " recipes");
                        for ( ParseObject obj : recipesList) {
                            final ParseObject object = obj;
                            ParseFile image = (ParseFile) obj.get("Picture");
                            image.getDataInBackground(new GetDataCallback() {
                                public void done(byte[] data, ParseException e) {
                                    Log.d("picture",Integer.toString(data.length));
                                    if (e == null) {
                                        bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        initializeData(object, bmp);
                                    } else {
                                        Log.d("mytag", "coldnt load picture");
                                    }
                                }
                            });
                            //initializeData(obj, bmp);
                        }
                    } else {
                        Log.d("score", "Error: " + e.getMessage());
                    }
                    initializeAdapter();
                    progressDialog.dismiss();
                }
            });
            count++;
        }if(!mWifi.isConnected() && count != 0) {//error on image
            Log.d("Invoke order","second if");
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Recipes");
            query.fromLocalDatastore();
            query.findInBackground(new FindCallback<ParseObject>() {

                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if(e == null){
                        Log.d("score", "Retrieved " + list.size() + " recipes");
                        for(ParseObject obj : list){
                            final ParseObject object = obj;
                            ParseFile image = (ParseFile)obj.get("Picture");
                            image.getDataInBackground(new GetDataCallback() {
                                public void done(byte[] data, ParseException e) {
                                    Log.d("picture",Integer.toString(data.length));
                                    if (e == null) {
                                        bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        initializeData(object, bmp);
                                    } else {
                                        Log.d("mytag", "coldnt load picture");
                                    }
                                }
                            });
                            //initializeData(obj, bmp);
                        }
                    }else {
                        Log.d("score", "Error: " + e.getMessage());
                    }
                    initializeAdapter();
                }
            });
        }


        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);

        //added to avoid no adapter set exception
        //recipes = new ArrayList<>();
        RVAdapter adapter = new RVAdapter(recipes);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(recipes);
        recyclerView.setAdapter(adapter);
    }
    private void initializeData(ParseObject obj,Bitmap bmp){
        recipes.add(new Recipe(obj.getString("Description"), obj.getString("shortDescription"), obj.getString("Name"), bmp));
        Log.d("score", Integer.toString(recipes.size()));
    }
}
