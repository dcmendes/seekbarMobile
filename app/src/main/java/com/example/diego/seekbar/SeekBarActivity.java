package com.example.diego.seekbar;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class SeekBarActivity extends AppCompatActivity {


    TextView seekTXT, taxaTXT, totalTXT;
    EditText precoEdit;
    SeekBar bar;
    private static NumberFormat percentFormat = NumberFormat.getPercentInstance();;
    private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    Double taxa, percent ,total;
    AlertDialog.Builder dialog;
    Adapter adapter;
    double value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

        seekTXT = (TextView) findViewById(R.id.tViewSeek);
        bar = (SeekBar) findViewById(R.id.seekBar);
        bar.setOnSeekBarChangeListener(barChangeListener);
        percentFormat.setMinimumFractionDigits(0);
        taxaTXT = (TextView) findViewById(R.id.taxaEdit);
        totalTXT = (TextView) findViewById(R.id.totalEdit);
        precoEdit = (EditText) findViewById(R.id.preco);
        dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Campo Vazio!");
        dialog.setMessage("O campo preço está Vazio!!");

    }

    private final SeekBar.OnSeekBarChangeListener barChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (precoEdit.getText().length() == 0){

                campoVazio();
            }

            syncronyzingSeek();

            taxaTXT.setText( String.valueOf( Double.parseDouble(precoEdit.getText().toString()) * value/100));
            totalTXT.setText(String.valueOf( Double.parseDouble(precoEdit.getText().toString())  +  Double.parseDouble(taxaTXT.getText().toString())));

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    public void syncronyzingSeek(){
        value = bar.getProgress();
       // this.seekTXT.setText(value);

        this.seekTXT.setText(percentFormat.format(value/100));
    }

    public void campoVazio(){
        new AlertDialog.Builder(this)
                .setTitle("Campo vazio")
                .setMessage("O campo preço está vazio")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        zerarSeekBar();
                    }
                }).show();
    }


    public void zerarSeekBar(){
        bar.setProgress(0);
    }

}
