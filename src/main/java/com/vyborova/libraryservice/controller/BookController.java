package com.vyborova.libraryservice.controller;

import com.vyborova.libraryservice.models.*;
import com.vyborova.libraryservice.services.*;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RequestMapping("/library/book")
@Controller
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final ReviewService reviewService;
    private final AuthorStatisticService authorStatisticService;
    private final GenreStatisticService genreStatisticService;
    private final PurchaseService purchaseService;
    private final PurchaseStatisticService purchaseStatisticService;
    private final KafkaSender kafkaSender;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, GenreService genreService, ReviewService reviewService, AuthorStatisticService authorStatisticService, GenreStatisticService genreStatisticService, PurchaseService purchaseService, PurchaseStatisticService purchaseStatisticService, KafkaSender kafkaSender) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.reviewService = reviewService;
        this.authorStatisticService = authorStatisticService;
        this.genreStatisticService = genreStatisticService;
        this.purchaseService = purchaseService;
        this.purchaseStatisticService = purchaseStatisticService;
        this.kafkaSender = kafkaSender;
    }

    @GetMapping("")
    public String findAllBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showInfo(@PathVariable UUID id,
                           Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("purchase", new Purchase());
        return "books/info";
    }

    @GetMapping("/create")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "books/new";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        Author author = book.getAuthor();
        if (author.getName().isEmpty())
            book.setAuthor(authorService.findById(author.getId()));
        else
            book.getAuthor().setId(null);
        Genre genre = book.getGenre();
        if (genre.getName().isEmpty())
            book.setGenre(genreService.findById(genre.getId()));
        else
            book.getGenre().setId(null);
        bookService.save(book);
        return "redirect:/library/book";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable UUID id) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("authors", authorService.findAll());
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable UUID id,
                             @ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookService.update(id, book);
        return "redirect:/library/book";
    }

    @GetMapping("/{id}/reviews")
    public String reviews(@PathVariable UUID id, Model model) {
        model.addAttribute("reviews", reviewService.findAllByBookId(id));
        model.addAttribute("booId", id);
        return "reviews/index";
    }

    @GetMapping("/{id}/add_review")
    public String addReview(@PathVariable UUID id, Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("bookId", id);
        return "reviews/new";
    }

    @PostMapping("/{id}/add_review")
    public String saveReview(@PathVariable UUID id,
                             Model model,
                             @ModelAttribute("review") @Valid Review review,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bookId", id);
            return "reviews/new";
        }
        review.setBook(bookService.findById(id));
        review.setReviewDate(LocalDate.now());
        reviewService.save(review);
        return "redirect:/library/book/" + id + "/reviews";
    }

    @DeleteMapping("/{id}/delete")
    private String delete(@PathVariable UUID id) {
        bookService.deleteById(id);
        return "redirect:/library/book";
    }

    @GetMapping("/info")
    private String getBookInfoTable(Model model) {
        model.addAttribute("booksInfo", bookService.allBookInfo());
        return "books/listInfo";
    }

    @GetMapping("/reviews_info")
    private String getReviewInfoTable(Model model) {
        model.addAttribute("reviewInfo", reviewService.getReviewInfo());
        return "reviews/listInfo";
    }

    @GetMapping("/statistic")
    private String getStatistic(@PathParam("by") String by,
            Model model) {
        return switch (by) {
            case "author" -> {
                model.addAttribute("statistic", authorStatisticService.findAll());
                yield "authors/listInfo";
            }
            case "genre" -> {
                model.addAttribute("statistic", genreStatisticService.findAll());
                yield "genres/listInfo";
            }
            case "purchase" -> {
                model.addAttribute("statistic", purchaseStatisticService.findAll());
                yield "purchases/listInfo";
            }
            default -> "redirect:/library/book";
        };
    }

    @PostMapping("/{id}/purchase")
    public String purchaseBook(@PathVariable("id") UUID id,
                               @ModelAttribute("purchase") Purchase purchase,
                               BindingResult bindingResult,
                               Model model) {
        Book bought = bookService.findById(id);
        purchase.setBook(bought);
        purchase.setPurchaseDate(LocalDate.now());
        if (bindingResult.hasErrors()) {
            return "books/info";
        }
        purchaseService.save(purchase);
        String message = "Book with id = " + id + " and cost = " + bought.getPrice() + " was bought by " + purchase.getBuyerName();
        kafkaSender.sendMessage(message);

        model.addAttribute("message", "Purchase successful!");
        return "redirect:/library/book";
    }
}
