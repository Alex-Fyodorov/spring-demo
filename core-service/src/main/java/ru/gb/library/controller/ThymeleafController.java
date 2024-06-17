package ru.gb.library.controller;

//@Controller
//@RequestMapping("/ui")
//@RequiredArgsConstructor
public class ThymeleafController {
//  private final BookService bookService;
//  private final ReaderService readerService;
//  private final IssueService issueService;
//  private final IssueConverter issueConverter;

//  @GetMapping("/books")
//  public String getAllBooks(Model model) {
//    List<Book> books = bookService.getAllBooks();
//    model.addAttribute("books", books);
//    return "books";
//  }
//
//  @GetMapping("/readers")
//  public String getAllReaders(Model model) {
//    List<Reader> readers = readerService.getAllReaders();
//    model.addAttribute("readers", readers);
//    return "readers";
//  }
//
//  @GetMapping("/issues")
//  public String getAllIssues(Model model) {
//    List<IssueDto> issues = issueService.getAllIssues().stream()
//            .map(issueConverter::entityToDto)
//            .collect(Collectors.toList());
//    model.addAttribute("issues", issues);
//    return "issues";
//  }
//
//  @GetMapping("/personal/{readerId}")
//  public String getPersonalPage(Model model, @PathVariable Long readerId) {
//    Reader reader = readerService.getReaderById(readerId);
//    model.addAttribute("reader", reader);
//    List<IssueDto> issues = issueService.getCurrentIssuesByReader(readerId)
//            .stream()
//            .map(issueConverter::entityToDto)
//            .collect(Collectors.toList());
//    model.addAttribute("issues", issues);
//    return "personal";
//  }
}
