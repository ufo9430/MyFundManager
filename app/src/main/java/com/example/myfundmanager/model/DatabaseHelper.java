import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StockApp.db";
    private static final int DATABASE_VERSION = 1;

    // User 테이블 생성 쿼리
    private static final String SQL_CREATE_USER_TABLE = "CREATE TABLE Users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT," +
            "password TEXT," +
            "stock_id INTEGER," +
            "stock_quantity INTEGER," +
            "stock_invest_date TEXT" +
            ")";

    // Stock 테이블 생성 쿼리
    private static final String SQL_CREATE_STOCK_TABLE = "CREATE TABLE Stocks (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "stock_name TEXT," +
            "current_price REAL" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER_TABLE);
        db.execSQL(SQL_CREATE_STOCK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 데이터베이스 업그레이드 시 필요한 작업 수행
    }

    // User 추가
    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("stock_id", user.getStock().getId());
        values.put("stock_quantity", user.getStockQuantity());
        values.put("stock_invest_date", user.getStockInvestDate());
        long result = db.insert("Users", null, values);
        db.close();
        return result;
    }

    // Stock 추가
    public long addStock(Stock stock) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("stock_name", stock.getStockName());
        values.put("current_price", stock.getCurrentPrice());
        long result = db.insert("Stocks", null, values);
        db.close();
        return result;
    }

    // User 수정
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("stock_id", user.getStock().getId());
        values.put("stock_quantity", user.getStockQuantity());
        values.put("stock_invest_date", user.getStockInvestDate());
        int result = db.update("Users", values, "id=?", new String[]{String.valueOf(user.getId())});
        db.close();
        return result;
    }

    // Stock 수정
    public int updateStock(Stock stock) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("stock_name", stock.getStockName());
        values.put("current_price", stock.getCurrentPrice());
        int result = db.update("Stocks", values, "id=?", new String[]{String.valueOf(stock.getId())});
        db.close();
        return result;
    }

    // 모든 User 조회
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Users", null);
    }

    // 모든 Stock 조회
    public Cursor getAllStocks() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Stocks", null);
    }
}
