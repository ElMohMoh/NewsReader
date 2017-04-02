package com.example.mohamed.newsreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import static com.example.mohamed.newsreader.R.id.list;

public class MainActivity extends AppCompatActivity {

    private XMLAsyncTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(list);

        MyAdapter adapter = new MyAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        XMLAsyncTask task = new XMLAsyncTask(adapter);
        task.execute("https://fr.wikipedia.org/w/api.php?hidebots=1&days=7&limit=50&hideWikibase=1&action=feedrecentchanges&feedformat=rss");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(task !=null){
            task.cancel(true);
        }
    }
}
