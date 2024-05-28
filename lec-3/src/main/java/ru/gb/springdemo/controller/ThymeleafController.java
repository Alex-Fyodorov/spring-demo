package ru.gb.springdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.springdemo.converters.IssueConverter;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.IssueDto;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.BookService;
import ru.gb.springdemo.service.IssueService;
import ru.gb.springdemo.service.ReaderService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ui")
@RequiredArgsConstructor
public class ThymeleafController {
  private final BookService bookService;
  private final ReaderService readerService;
  private final IssueService issueService;
  private final IssueConverter issueConverter;

  @GetMapping("/books")
  public String getAllBooks(Model model) {
    List<Book> books = bookService.getAllBooks();
    model.addAttribute("books", books);
    return "books";
  }

  @GetMapping("/readers")
  public String getAllReaders(Model model) {
    List<Reader> readers = readerService.getAllReaders();
    model.addAttribute("readers", readers);
    return "readers";
  }

  @GetMapping("/issues")
  public String getAllIssues(Model model) {
    List<IssueDto> issues = issueService.getAllIssues().stream()
            .map(issueConverter::entityToDto)
            .collect(Collectors.toList());
    model.addAttribute("issues", issues);
    return "issues";
  }

  @GetMapping("/personal/{readerId}")
  public String getPersonalPage(Model model, @PathVariable Long readerId) {
    Reader reader = readerService.getReaderById(readerId);
    model.addAttribute("reader", reader);
    List<IssueDto> issues = issueService.getCurrentIssuesByReader(readerId)
            .stream()
            .map(issueConverter::entityToDto)
            .collect(Collectors.toList());
    model.addAttribute("issues", issues);
    return "personal";
  }

//  @GetMapping("/table")
//  public String table(Model model) {
//    List<Person> people = List.of(
//      new Person("John", "Googler", 24, "google.com"),
//      new Person("Alex", "Smith", 32, "jetbrains.com"),
//      new Person("Igor", "Chestnov", 100, "amazon.com")
//    );
//    model.addAttribute("people", people);
//    return "table";
//  }
//
//  @GetMapping("/list")
//  public String list(@RequestParam int count, Model model) {
//    List<Item> items = new ArrayList<>();
//    for (int i = 1; i <= count; i++) {
//      items.add(new Item(i, "Item #" + i));
//    }
//
//    model.addAttribute("items", items);
//    return "list";
//  }
//
//  @GetMapping("/home")
//  public String home(@RequestParam(required = false) String name, @RequestParam(required = false) String color, Model model) {
//    if (name != null) {
//      model.addAttribute("name", name);
//    } else {
//      model.addAttribute("name", "world");
//    }
//    model.addAttribute("myColor", (color == null ? "black" : color));
//    return "home";
//  }

}
