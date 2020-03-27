package master.akshay.mysampleroomdb.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "employee")
public class Employee {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Ignore
    public Employee(String name, String salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee(int id, String name, String salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}
