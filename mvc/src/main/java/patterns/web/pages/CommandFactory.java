package patterns.web.pages;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import patterns.web.filter.FilterCommand;
import patterns.web.filter.FilterEnum;

@Slf4j
public class CommandFactory {

    public static Command getCommand(
            HttpServletRequest req,
            HttpServletResponse resp) {
        String parameter = req.getParameter("command");
        if (parameter != null) {
            try {
                return CommandType.valueOf(parameter).getCommand();
            } catch (IllegalArgumentException e) {
                log.warn(e.getMessage());
            }
        }
        return CommandType.ERROR.getCommand();
    }

    public static FilterCommand getFilter(
            HttpServletRequest req,
            HttpServletResponse resp) {
        String command = req.getParameter("command");
        if (command != null) {
            try {
                return FilterEnum.valueOf(command).getFilter();
            } catch (IllegalArgumentException e) {
                log.warn(e.getMessage());
            }
        }
        return FilterEnum.ERROR_PAGE.getFilter();
    }
}
