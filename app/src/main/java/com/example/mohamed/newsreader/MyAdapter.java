package com.example.mohamed.newsreader;

import com.oc.rss.fake.FakeNews;
import com.oc.rss.fake.FakeNewsList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Mohamed on 28/03/2017.
 */
// Adapter s'occupe de l'ensemble de liste
// Holder s'occupe des spécifictés d'une cellule
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(com.example.mohamed.newsreader.R.layout.list_cell,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FakeNews news = FakeNewsList.all.get(position);
        holder.diplay(news);
    }

    @Override
    public int getItemCount() {
        return FakeNewsList.all.size();
    }

    public class MyViewHolder extends ViewHolder  implements OnClickListener{

        private final TextView textView;
        private final Context context;

        private FakeNews fakeNews;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.textView = (TextView) itemView.findViewById(R.id.title);
            context = itemView.getContext();
            textView.setOnClickListener(this);
        }

        public void diplay(FakeNews news){

            String title = news.title;
            String description = news.htmlContent;
            this.fakeNews = news;
            this.textView.setText(title);

            /*this.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyViewHolder.this, DetailActivity.class);
                    intent.putExtra("title",str_title);
                    intent.putExtra("new",description);
                    startActivity(intent);
                }
            });*/
        }


        @Override
        public void onClick(View v) {

            final android.content.Intent intent = new android.content.Intent(context,DetailActivity.class);

            String title = fakeNews.title;
            String news = fakeNews.htmlContent;

            intent.putExtra("title",title);
            intent.putExtra("news",news);


            android.widget.Toast.makeText(context,"Détails", android.widget.Toast.LENGTH_SHORT).show();
            context.startActivity(intent);



        }
    }


}
