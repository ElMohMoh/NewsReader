package com.example.mohamed.newsreader;

import org.w3c.dom.Document;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;

import android.util.Log;


/**
 * Created by Mohamed on 02/04/2017.
 */

public class XMLAsyncTask extends android.os.AsyncTask<String, Object, Document> {

    interface DocumentConsumer {
        void setXMLDocument(Document document);
    }

    private XMLAsyncTask.DocumentConsumer _consumer;

    public XMLAsyncTask(DocumentConsumer consumer){

        this._consumer = consumer;
    }

    @Override
    protected Document doInBackground(String... params) {

        try {
            Thread.sleep(5000);
            // Récupération de l'URL
            URL url = new URL(params[0]);
            // Ouverture de connexion
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Récupération du flux
            InputStream stream = connection.getInputStream();
            try {
                return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
            }
            finally {
                stream.close();
            }
        }
        catch (InterruptedException ex){
            Log.e("XMLAsyncTask", "Interrupted");
            return null;
        }
        catch (Exception ex) {
            Log.e("XMLAsyncTask","Exception while downloading", ex);
            throw new RuntimeException();
        }
    }

    @Override
    protected void onPostExecute(Document result) {
        Log.e("XMLAsyncTask","Finished");
        this._consumer.setXMLDocument(result);
    }
}
