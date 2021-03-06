package v.alice.android2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button clearBtn, addBtn;
    EditText fNameInput, lNameInput;
    ArrayList<String> names;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clearBtn = findViewById(R.id.clearBtn);
        addBtn = findViewById(R.id.addBtn);
        fNameInput = findViewById(R.id.fNameInput);
        lNameInput = findViewById(R.id.lNameInput);

        listView = findViewById(R.id.listView);
        names = new ArrayList<String>(asList("Aleksei Alex", "Viktor Klp", "Ivan Ivanov"));

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, names);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });

    }

    public void add(View v) {

        String lastName = lNameInput.getText().toString();
        String firstName = fNameInput.getText().toString();
        int n = 0;


        if (!fNameInput.getText().toString().matches("") && !lNameInput.getText().toString().matches("") &&
                (lastName.matches("[a-zA-Z]+")) && firstName.matches("[a-zA-Z]+")) {


            for (String string : names) {

                if (string.matches(firstName + " " + lastName)) {

                    Toast.makeText(this, "This name has already been added to listView", Toast.LENGTH_SHORT).show();
                    n = 1;

                }
            }
            if (n == 0) {
                names.add(fNameInput.getText().toString() + " " + lNameInput.getText().toString());
                adapter.notifyDataSetChanged();

            }


        } else {

            if (stringContainsNumber(firstName + " " + lastName)) {

                Toast.makeText(this, "No numbers allowed!", Toast.LENGTH_SHORT).show();


            } else if (!fNameInput.getText().toString().matches("") && !lNameInput.getText().toString().matches("")) {

                Toast.makeText(this, "No symbols allowed!", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "First name or last name (or both?) is missing", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void clearName(View v) {

        int i = 0;

        for (String string : names) {
            if(string.matches(fNameInput.getText().toString() + " " + lNameInput.getText().toString())) {
                names.remove(i);
                adapter.notifyDataSetChanged();
                break;
            }
            i++;
        }

    }

    public boolean stringContainsNumber( String s )
    {
        return Pattern.compile( "[0-9]" ).matcher( s ).find();
    }
}
