package eds.de.bewerbertest;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


/**
 * Lieber Bewerber,
 *
 * 1. Das Programm hier hat ein Problem. Die simulierte aufwendige
 * Berechnung (Thread.sleep(5000)) wird nicht durchgeführt. Was ist
 * das Problem?
 *
 * 2. Dann: Kannst Du den Absturz beheben und die TextView tv so
 * aktualisieren, dass sie den Status der Berechnung (läuft/ist
 * beendet) richtig anzeigt? Warum ging das vorher nicht?
 *
 * 3. Kannst Du letztendlich den Benutzer mit einer Notification über
 * das Ende der Berechnung benachrichtigen und bei Klick auf die
 * Benachrichtigung ResultActivity zeigen?
 *
 */

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private TextView tv;
    private CheckBox checkbox;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textview);
        button = (Button) findViewById(R.id.button);
        checkbox = (CheckBox) findViewById(R.id.checkbox);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                tv.setText(Boolean.toString(isChecked));
            }
        });
        checkbox.setChecked(true);


        button.setOnClickListener(new View.OnClickListener() {

            // ursprüngliche Version
            @Override
            public void onClick(View v) {

                if(checkbox.isChecked()){

                    Log.i(TAG, "Aufwendige Berechnung durchführen");

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            tv.setText(MainActivity.this.getString(R.string.begin));

                            try {
                                Log.i(MainActivity.TAG, "Berechnung Anfang");
                                Thread.sleep(5000);
                                Log.i(MainActivity.TAG, "Berechnung Ende");
                            } catch (InterruptedException e) {
                                Log.e(MainActivity.TAG, "sleep()", e);
                            }

                            tv.setText(MainActivity.this.getString(R.string.end));
                        }
                    });

                } else {
                    Log.i(TAG, "Keine Berechnung");
                }
            }

        });
    }

}
