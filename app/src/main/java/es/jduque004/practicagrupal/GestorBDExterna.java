package es.jduque004.practicagrupal;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class GestorBDExterna extends AsyncTask<String,Void,String> {

    private Context contexto;

    GestorBDExterna(){
    }
    GestorBDExterna(Context ct){
        contexto=ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String param = "funcion="+strings[0]+"&"+strings[1];
        String elegirResult=strings[0];
        //if(strings.length!=0)param = "funcion="+strings[0]+"&"+strings[1];
        String line;
        StringBuilder result= new StringBuilder();
        String st = "http://galan.ehu.eus/jduque004/WEB/DAS/usuarios.php";
        if(elegirResult.equals("enviarFCM")) st = "http://galan.ehu.eus/jduque004/WEB/DAS/proyectofcm.php";
        try {
            URL targetURL = new URL(st);
            HttpURLConnection urlConnection= (HttpURLConnection) targetURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            urlConnection.setDoOutput(true);

            PrintWriter out= new PrintWriter(urlConnection.getOutputStream());
            out.print(param);
            out.close();
            int statusCode= urlConnection.getResponseCode();

            if(statusCode== 200){
                BufferedInputStream inputStream= new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                while((line = bufferedReader.readLine()) != null){
                    result.append(line);
                }
                Log.i("DebugMio","GestorBDExterna.EstatusCodeOk. Resultado: "+result);
                inputStream.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(contexto!=null) {
            ProgressDialog proceso = new ProgressDialog(contexto);
            proceso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            proceso.setMessage("Cargando");
            proceso.setCancelable(false);
            proceso.show();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
