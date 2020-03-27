package master.akshay.mysampleroomdb.database;

import android.app.Person;
import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import master.akshay.mysampleroomdb.model.Employee;

@Database(entities = {Employee.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getCanonicalName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "employeelist";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "Creating new databased instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME).build();

            }
        }
        Log.d(LOG_TAG,"Getting Database Instance");
        return sInstance;
    }

    public abstract EmployeeDao employeeDao();
}
