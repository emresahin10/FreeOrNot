package com.denbase.freeornot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private EditText etxtYil;
    private Button btnOgren;
    private TextView txtSonuc;
    private ImageView imgSonuc;
    private int yas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtYil = findViewById(R.id.etxtYil);
        btnOgren = findViewById(R.id.btnOgren);
        txtSonuc = findViewById(R.id.txtSonuc);
        imgSonuc = findViewById(R.id.imgSonuc);


        btnOgren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean calissinMi;
                if (TextUtils.isEmpty(etxtYil.getText())) {
                    calissinMi = false;
                    imgSonuc.setImageResource(R.drawable.ic_durumyok);
                    txtSonuc.setText("");
                    Toast.makeText(getApplicationContext(), "GEÇERLI YIL GIRINIZ", Toast.LENGTH_SHORT).show();

                } else {
                    yas = yasHesapla(Integer.parseInt(etxtYil.getText().toString()));
                    calissinMi = true;
                }

                int saat = CurrentHour();
                int gun = CurrentDay();
                boolean haftasonumu = HaftaSonumu();

                if (calissinMi) {
                    if (yas <= 20) {

                        if (saat >= 13 && saat < 16) {
                            imgSonuc.setImageResource(R.drawable.ic_serbest);
                            txtSonuc.setText("SERBEST");

                        } else {
                            txtSonuc.setText("YASAK");
                            imgSonuc.setImageResource(R.drawable.ic_yasak);
                        }
                    } else if (yas > 20 && yas < 65) {

                        if (haftasonumu) {

                            if (saat < 10 || saat >= 20) {
                                txtSonuc.setText("YASAK");
                                imgSonuc.setImageResource(R.drawable.ic_yasak);
                            } else {
                                txtSonuc.setText("SERBEST");
                                imgSonuc.setImageResource(R.drawable.ic_serbest);
                            }
                        } else {
                            txtSonuc.setText("SERBEST");
                            imgSonuc.setImageResource(R.drawable.ic_serbest);
                        }
                    } else if (yas > 65) {

                        if (saat >= 10 && saat < 13) {
                            txtSonuc.setText("SERBEST");
                            imgSonuc.setImageResource(R.drawable.ic_serbest);
                        } else {
                            txtSonuc.setText("YASAK");
                            imgSonuc.setImageResource(R.drawable.ic_yasak);
                        }
                    }

                }
            }
        });
    }


    public int CurrentDay(){

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("u");
        String tarih = dateFormat.format(date);
        int tarihint = Integer.parseInt(tarih);

        return tarihint;

    }

    public boolean HaftaSonumu(){

        if (CurrentDay() == 6 || CurrentDay() == 7){
            return true;
        }else {
            return false;
        }
    }

    public int CurrentHour(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("k");
        String tarih = dateFormat.format(date);
        int tarihint = Integer.parseInt(tarih);

        return  tarihint;
    }

    public int CurrentYear(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String tarih = dateFormat.format(date);
        int tarihint = Integer.parseInt(tarih);

        return  tarihint;
    }

    public int yasHesapla(int yil){

        return  CurrentYear()- yil;
    }
}

