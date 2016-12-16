package Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.chenghao.mytb.Book;
import com.example.chenghao.mytb.BookManager;

import java.util.ArrayList;
import java.util.List;

public class Serviceook extends Service {

    private final static String TAG="Qin:Serviceook";

    private List<Book>mBooks=new ArrayList<>();

    private final BookManager.Stub mBookManager=new BookManager.Stub(){
        @Override
        public List<Book> getBook() throws RemoteException {
            synchronized (this){
                if(mBooks!=null)
                    return mBooks;
                return new ArrayList<>();
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (this){
                if(mBooks==null){
                    mBooks=new ArrayList<>();
                }
                if(book==null){
                    book=new Book();
                }
                if(!mBooks.contains(book)){
                    mBooks.add(book);
                }
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Book book=new Book();
        book.setName("JAVA");
        book.setPrice(20);
        mBooks.add(book);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");
        return mBookManager;
    }
}
