package patterns.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class LibraryControllerAdvice {

    @ExceptionHandler(value = DuplicateBookException.class)
    public ModelAndView duplicateBookException(DuplicateBookException e) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ref", e.getMessage());
        modelAndView.addObject("object", e.getMessage());
        modelAndView.addObject("message", "Cannot add an already existing book");
        modelAndView.setViewName("error-book");
        return modelAndView;
    }
}
