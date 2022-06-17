package com.skynesser.controller;

import com.mchange.v1.identicator.IdList;
import com.skynesser.pojo.Books;
import com.skynesser.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @RequestMapping("/allBook")
    public String list(Model model){
        List<Books> books = bookService.queryAllBook();
        model.addAttribute("list",books);
        return "allBook";
    }
    @RequestMapping("toAddBook")
    public String toAddPaper(){
        return "addBook";
    }
    @RequestMapping("/addBook")
    public String addBook(Books books){
        bookService.addBook(books);
        return "redirect:/book/allBook";
    }

    @RequestMapping("toUpdateBook/{id}")
    public String toUpdatePaper(@PathVariable("id") int id,Model model){
        Books books = bookService.queryBookById(id);
        model.addAttribute("QBook",books);
        return "updateBook";
    }

    @RequestMapping("/updateBook")
    public String updateBook(Books books){
        bookService.updateBook(books);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/queryBook")
    public String queryBook(String queryBookName,Model model){
        Books books = bookService.queryBookByName(queryBookName);


        List<Books> list = new ArrayList<>();
        list.add(books);

        if (books == null){
            list = bookService.queryAllBook();
            model.addAttribute("error","未能查找到书籍");
        }

        model.addAttribute("list",list);
        return "allBook";
    }
}
