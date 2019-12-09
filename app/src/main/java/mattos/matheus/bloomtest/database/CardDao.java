package mattos.matheus.bloomtest.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import mattos.matheus.bloomtest.model.Card;

@Dao
public interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Card card);


    @Query("SELECT * FROM card")
    List<Card> getAllCards();

    @Query("SELECT * FROM card WHERE number = :number")
    Card getCardByNumber(int number);

}
