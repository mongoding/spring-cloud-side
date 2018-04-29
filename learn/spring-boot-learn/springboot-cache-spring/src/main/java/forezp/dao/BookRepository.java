package topweshare.dao;

import topweshare.entity.Book;

/**
 * Created by mongoding on 2017/4/19.
 */
public interface BookRepository {
    Book getByIsbn(String isbn);
}
