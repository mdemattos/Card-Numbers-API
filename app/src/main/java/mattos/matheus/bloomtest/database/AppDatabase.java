package mattos.matheus.bloomtest.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import mattos.matheus.bloomtest.model.Card;

@Database(entities = {Card.class}, version = 1, exportSchema = false )
public abstract class AppDatabase extends RoomDatabase {
    public abstract CardDao cardDao();
    private static AppDatabase instance;

    public static AppDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class,
                                    "cards").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}
