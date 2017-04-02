package com.example.mohamed.newsreader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Mohamed on 28/03/2017.
 */
// Adapter s'occupe de l'ensemble de liste
// Holder s'occupe des spécifictés d'une cellule
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements XMLAsyncTask.DocumentConsumer {

    private Document _document =null;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(com.example.mohamed.newsreader.R.layout.list_cell,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Element item = (Element) _document.getElementsByTagName("item").item(position);
        holder.setElement(item);
    }

    @Override
    public int getItemCount() {

        if(_document != null){

            return _document.getElementsByTagName("item").getLength();
        }
        else
            return 0;
    }

    @Override
    public void setXMLDocument(Document document) {
        _document = document;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends ViewHolder {

        private final TextView textView;

        private Element _currentElement;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.textView = (TextView) itemView.findViewById(R.id.title);
        }

        public void setElement(Element item) {
            _currentElement = item;
            textView.setText(item.getElementsByTagName("title").item(0).getTextContent());
        }
    }


}
