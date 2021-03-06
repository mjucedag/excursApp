package com.example.mj_uc.excursapp.apirest;


import android.os.AsyncTask;


/**
 * The Api connection.
 */
public class APIConnection{

    /**
     * Get connection.
     *
     * @param link     the link
     * @param method   the method
     * @param response the response
     */
    public static void getConnection(final String link, final Integer method, final WebResponse response){
        getConnection(link, method, response, null);
    }

    /**
     * Get connection.
     *
     * @param link     the link
     * @param method   the method
     * @param response the response
     * @param params   the params
     */
    public static void getConnection(final String link, final Integer method, final WebResponse response, final String params){
        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... args) {
                WebRequest webreq = new WebRequest();
                String jsonStr = params == null ? webreq.makeWebServiceCall(link, method) : webreq.makeWebServiceCall(link, method, params);
                return jsonStr;
            }
            @Override
            protected void onPostExecute(String s) {
                response.onResponseService(s);
            }
        };
        task.execute();
    }
}
