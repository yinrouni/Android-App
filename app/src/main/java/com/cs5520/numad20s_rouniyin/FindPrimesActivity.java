package com.cs5520.numad20s_rouniyin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FindPrimesActivity extends AppCompatActivity {
    private TextView result;

    private class PrimesCounter extends AsyncTask<String, Integer, String>{
        private boolean isPrime(int n){
            if (n == 2){
                return true;
            }
            for (int i = 2; i < n;i ++){
                if(n % i  == 0){
                    return false;
                }
            }

            return true;
        }
        @Override
        protected void onPreExecute(){}

        @Override
        protected String doInBackground(String... strings) {
            int i = 2;

            while(true){
                try {
                    Thread.sleep(1000);
                    if (isPrime(i)){
                        publishProgress(i, 1);

                    }
                    else {
                        publishProgress(i, 0);
                    }
                    i ++;


                } catch (InterruptedException e) {
                    return(e.getLocalizedMessage());
                }

            }
        }


        @Override
        protected void onProgressUpdate(Integer... values){
            if (values[1] == 1){
                result.setText(values[0] + " is a prime.");

            }
            else {
                result.setText(values[0] + " is not a prime.");
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
    AsyncTask primesFinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primes_finder);
        Button stop = findViewById(R.id.primeStop);
        result = findViewById(R.id.primeView);



        startFinding();
    }

    private void startFinding() {
        primesFinder = new PrimesCounter().execute();

    }


    public void stopFinding(View view){
        primesFinder.cancel(true);

    }
}
