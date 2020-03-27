package master.akshay.mysampleroomdb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import master.akshay.mysampleroomdb.R;
import master.akshay.mysampleroomdb.database.AppDatabase;
import master.akshay.mysampleroomdb.database.AppExecutors;
import master.akshay.mysampleroomdb.model.Employee;

public class MainActivity_old extends AppCompatActivity {

    private AppDatabase mDb;
    private EditText editTextID;
    private EditText editTextName;
    private EditText editTextSalary;
    private Button buttonSave;
    private Button buttonView;

    private int empID;
    private String empName;
    private String empSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* editTextID = (EditText) findViewById(R.id.txtID);
        editTextName = (EditText) findViewById(R.id.txtName);
        editTextSalary = (EditText) findViewById(R.id.txtSalary);

        buttonSave = (Button) findViewById(R.id.btnSave);

        buttonView = (Button) findViewById(R.id.btnView);*/

        mDb = AppDatabase.getInstance(getApplicationContext());

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewButtonClicked();
            }
        });
    }

    public void onViewButtonClicked() {
        retrieveTasks();

        Intent myIntent = new Intent( MainActivity_old.this, EditActivity.class);
        myIntent.putExtra("key", "abc"); //Optional parameters
        MainActivity_old.this.startActivity(myIntent);

    }


    public void onSaveButtonClicked() {
        final Employee employee = new Employee(
        editTextName.getText().toString(),
                editTextSalary.getText().toString());


        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.employeeDao().insertEmployee(employee);

                finish();

                //Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                Log.d("Data", "Inserted");
            }
        });
    }

    private void retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                final List<Employee> employees = mDb.employeeDao().loadAllEmployee();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                       for( int i =0 ; i < employees.size(); i++){
                           System.out.println("Employee list = " + employees.get(i).getId() + " " + employees.get(i).getName() + " " + employees.get(i).getSalary());
                       }
                    }
                });
            }
        });
    }
}
