package master.akshay.mysampleroomdb.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import master.akshay.mysampleroomdb.R;
import master.akshay.mysampleroomdb.activities.EditActivity;
import master.akshay.mysampleroomdb.constants.Constants;
import master.akshay.mysampleroomdb.database.AppDatabase;
import master.akshay.mysampleroomdb.model.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder> {

    private Context context;
    private List<Employee> mEmployeeList;

    public EmployeeAdapter(Context context) {
        this.context = context;
    }
 
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(mEmployeeList.get(i).getName());
        myViewHolder.salary.setText(mEmployeeList.get(i).getSalary());
    }

    @Override
    public int getItemCount() {
        if (mEmployeeList == null) {
            return 0;
        }
        return mEmployeeList.size();

    }

    public void setTasks(List<Employee> employeeList) {
        mEmployeeList = employeeList;
        notifyDataSetChanged();
    }


    public List<Employee> getTasks() {
        return mEmployeeList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, salary;
        ImageView editImage;
        AppDatabase mDb;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mDb = AppDatabase.getInstance(context);
            name = itemView.findViewById(R.id.emp_name);
            salary = itemView.findViewById(R.id.emp_salary);

            editImage = itemView.findViewById(R.id.edit_Image);
            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int elementId = mEmployeeList.get(getAdapterPosition()).getId();
                    Intent i = new Intent(context, EditActivity.class);
                    i.putExtra(Constants.UPDATE_Employee_Id, elementId);
                    context.startActivity(i);
                }
            });
        }
    }
}
