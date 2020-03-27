package master.akshay.mysampleroomdb.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import master.akshay.mysampleroomdb.model.Employee;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM EMPLOYEE ORDER BY ID")
    List<Employee> loadAllEmployee();

    @Insert
    void insertEmployee(Employee employee);

    @Update
    void updateEmployee(Employee employee);

    @Delete
    void deleteEmployee(Employee employee);

    @Query("SELECT * FROM EMPLOYEE WHERE id = :id")
    Employee loadEmployeeById(int id);

}
