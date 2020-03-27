package master.akshay.mysampleroomdb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import master.akshay.mysampleroomdb.R;
import master.akshay.mysampleroomdb.constants.Constants;
import master.akshay.mysampleroomdb.database.AppDatabase;
import master.akshay.mysampleroomdb.database.AppExecutors;
import master.akshay.mysampleroomdb.model.Employee;

public class EditActivity extends AppCompatActivity {

    EditText name, salary;
    Button button;
    int mEmployeeId;
    Intent intent;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initViews();

        mDb = AppDatabase.getInstance(getApplicationContext());

        intent = getIntent();

        if (intent != null && intent.hasExtra(Constants.UPDATE_Employee_Id)) {
            button.setText("Update");

            mEmployeeId = intent.getIntExtra(Constants.UPDATE_Employee_Id, -1);

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    Employee employee = mDb.employeeDao().loadEmployeeById(mEmployeeId);
                    populateUI(employee);
                }
            });
        }

    }

    private void populateUI(Employee employee) {

        if (employee == null) {
            return;
        }
        name.setText(employee.getName());
        salary.setText(employee.getSalary());
    }

    private void initViews() {
        name = findViewById(R.id.edit_name);
        salary = findViewById(R.id.edit_salary);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButtonClicked();
            }
        });
    }

    public void onSaveButtonClicked() {
        final Employee employee = new Employee(
                name.getText().toString(),
                salary.getText().toString());

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (!intent.hasExtra(Constants.UPDATE_Employee_Id)) {
                    mDb.employeeDao().insertEmployee(employee);
                } else {
                    employee.setId(mEmployeeId);
                    mDb.employeeDao().updateEmployee(employee);
                }
                finish();
            }
        });
    }
}
