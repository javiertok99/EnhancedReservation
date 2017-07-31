package sg.edu.rp.c346.p04ps;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText Name;
    EditText Number;
    EditText Pax;
    EditText date;
    EditText time;
    CheckBox smoke;
    Button Confirm;
    Button Reset;
    String smoketext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.Name);
        Number = (EditText)findViewById(R.id.Number);
        date = (EditText)findViewById(R.id.date);
        time = (EditText)findViewById(R.id.time);
        Pax = (EditText)findViewById(R.id.amt);
        smoke = (CheckBox)findViewById(R.id.cbSmoke);
        Confirm = (Button)findViewById(R.id.confirm);
        Reset = (Button)findViewById(R.id.reset);
String text = "";
        Calendar now = (Calendar.getInstance());
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int min = now.get(Calendar.MINUTE);
        int Year = now.get(Calendar.YEAR);
        int Month = now.get(Calendar.MONTH);
        int Day = now.get(Calendar.DAY_OF_MONTH);



        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creating the Listener to set the date
                Calendar now = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                    }
                };
                int Year = now.get(Calendar.YEAR);
                int Month = now.get(Calendar.MONTH);
                int Day = now.get(Calendar.DAY_OF_MONTH);
                // Create the Date Picker Dialog
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this,
                        myDateListener, Year, Month, Day);

                myDateDialog.show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creating the Listener to set the time
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        time.setText(hourOfDay + ":" + minute);
                    }
                };

                // Create the Time Picker Dialog
                /*TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,
                        myTimeListener, 20, 00, true);*/

                Calendar now = (Calendar.getInstance());
                int hour = now.get(Calendar.HOUR_OF_DAY);
                int min = now.get(Calendar.MINUTE);
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this, myTimeListener , hour,min,true);
                myTimeDialog.show();
            }
        });

        smoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(smoke.isChecked()){
                    smoketext = "Smoking Area";
                }else{
                    smoketext = "Non-smoking Area";
                }

            }
        });


        Confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Inflate the input.xml layout file
                final String wooo = "Name : " + Name.getText() + "\nNumber : " + Number.getText()  + "\nNumber of People : " + Pax.getText() + "\nDate : " + date.getText() + "\nTime : " + time.getText() + "\n" + smoketext;
                LayoutInflater inflater =(LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.input, null);

                // Obtain the UI component in the input.xml layout
                final TextView input = (TextView) viewDialog.findViewById(R.id.textViewInput);
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                // Set the view of the dialog
                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Confirm Reservation");
                input.setText(wooo);
                myBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Update the Text to TextView

                    }
                });

                myBuilder.setNegativeButton("Cancel", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            };
        }
        );
        Reset.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v){
                 Name.setText("");
                 Number.setText("");
                 date.setText("");
                 Pax.setText("");
                 time.setText("");
            }
        }
        );

    }
}
