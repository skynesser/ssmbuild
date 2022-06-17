package com.skynesser.test;

import com.skynesser.pojo.Books;
import com.skynesser.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.print.Book;

public class Test {

    @org.junit.Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = applicationContext.getBean("BookServiceImpl", BookService.class);
        for(Books books : bookService.queryAllBook()){
            System.out.println(books);
        }
    }
}
