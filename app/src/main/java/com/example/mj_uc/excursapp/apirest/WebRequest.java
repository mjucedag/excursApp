package com.example.mj_uc.excursapp.apirest;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class WebRequest {

    public final static int GETRequest = 1;
    public final static int POSTRequest = 2;
    public final static int PUTRequest = 3;
    public final static int DELETERequest = 4;

    //Constructor with no parameter
    public WebRequest() {
    }

    /**
     * Making web service call
     *
     * @url - url to make web request
     * @requestmethod - http request method
     */
    public String makeWebServiceCall(String url, int requestmethod) {
        return this.makeWebServiceCall(url, requestmethod, null);
    }

    /**
     * Making web service call
     *
     * @url - url to make web request
     * @requestmethod - http request method
     * @params - http request params
     */
    public String makeWebServiceCall(String urladdress, int requestmethod,
                                     String params) {
        URL url;
        String response = "";
        try {
            url = new URL(urladdress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            setRequestMethod(requestmethod, conn);

            if (params != null) {
                conn.setDoOutput(true);
                int longitud = params.getBytes("UTF-8").length;
                conn.setFixedLengthStreamingMode(longitud);
                conn.setRequestProperty("Content-Type", "application/json; charset = UTF-8");
                OutputStream out = new BufferedOutputStream(conn.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(params);
                writer.flush();
                writer.close();
                out.close();
            }
            int reqresponseCode = conn.getResponseCode();

            if (reqresponseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Set the request Method to the Connection
     *
     * @param requestmethod
     * @param conn
     * @throws ProtocolException
     */
    private void setRequestMethod(int requestmethod, HttpURLConnection conn) throws ProtocolException {
        if (requestmethod == POSTRequest) {
            conn.setRequestMethod("POST");
        } else if (requestmethod == GETRequest) {
            conn.setRequestMethod("GET");
        }else if (requestmethod == PUTRequest) {
            conn.setRequestMethod("PUT");
        }else if (requestmethod == DELETERequest) {
            conn.setRequestMethod("DELETE");
        }
    }
}