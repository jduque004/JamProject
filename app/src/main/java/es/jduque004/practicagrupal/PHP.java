package es.jduque004.practicagrupal;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Migue on 30/04/2018.
 */

//LO USO PARA TRABAJAR CON LA BD, SI VAMOS A USAR JSON NO SERAN NECESARIOS

public class PHP extends AsyncTask<Void, Void, String> {

    public PHP(String nombre) throws MalformedURLException {
        Nombre = nombre;
    }

    String Nombre;
    String IP = "http://galan.ehu.eus/mmartin190/WEB";

    String Select = IP + "/select2.php";
    String Insert = IP + "/insert2.php";
    String Update = IP + "/update2.php";



    public PHP() throws IOException {
    }

    @Override
    protected void onPostExecute(String aBoolean) {
        super.onPostExecute(aBoolean);
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL targetURL = new URL(Select+"?Nombre="+Nombre);
            HttpURLConnection urlConnection =(HttpURLConnection) targetURL.openConnection();
            urlConnection = (HttpURLConnection) targetURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type","applications/x-www-form-urlencoded");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            urlConnection.setDoOutput(true);

            PrintWriter out= new PrintWriter(urlConnection.getOutputStream());

            out.close();

            int statusCode = urlConnection.getResponseCode();

            if(statusCode==200){
                BufferedInputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String line,result = "";
                while((line=bufferedReader.readLine()) != null){
                    result +=line;
                }
                inputStream.close();

                System.out.print(result);
                return result;

            }

        }
        catch (ProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
        e.printStackTrace();
        }


        return null;
    }
}
