package patterns.example.command.pages;

import patterns.example.command.filter.FilterCommand;
import patterns.example.command.filter.FilterEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class CommandFactory {
    private static final Logger log = Logger.getLogger(CommandFactory.class.getName());

    public static Command getCommand(
            HttpServletRequest req,
            HttpServletResponse resp) {
        String parameter = req.getParameter("command");
        if (parameter != null) {
            try {
                return CommandType.valueOf(parameter).getCommand();
            } catch (IllegalArgumentException e) {
                log.warning(e.getMessage());
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
                log.warning(e.getMessage());
            }
        }
        return FilterEnum.ERROR_PAGE.getFilter();
    }
}
