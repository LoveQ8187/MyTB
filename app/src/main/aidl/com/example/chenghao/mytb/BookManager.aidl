// IBookManager.aidl
package com.example.chenghao.mytb;

import com.example.chenghao.mytb.Book;
// Declare any non-default types here with import statements

interface BookManager {

    List<Book>getBook();

    //传参时除了Java基本类型以及String，CharSequence之外的类型
    //都需要在前面加上定向tag，具体加什么量需而定
    void addBook(in Book book);
}
