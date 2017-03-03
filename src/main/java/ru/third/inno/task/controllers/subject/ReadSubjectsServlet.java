package ru.third.inno.task.controllers.subject;

import org.apache.log4j.Logger;
import ru.third.inno.task.models.pojo.Subject;
import ru.third.inno.task.services.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by yy on 24.02.17.
 */
public class ReadSubjectsServlet extends HttpServlet {
    Logger logger = Logger.getLogger(ReadSubjectsServlet.class);

    /**
     * Gets All user's subjects and others subjects
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Subject> subjects = null;

        /**
         * gets all subjects
         * subjects = SubjectService.getAllSubjects();
         */
        HttpSession session = req.getSession(false);

        int id = (int) session.getAttribute("id");

        logger.trace("get session id in the logger servlet: " + id);

        /**
         * gets not users subjects
         */
        subjects = SubjectService.getAllNotUserSubjects(id);
        req.setAttribute("subjects", subjects);

        List<Subject> userSubjects = null;
        /**
         * all user subjects
         */
        userSubjects = SubjectService.getAllUserSubjects(id);


        req.setAttribute("userSubjects", userSubjects);

        req.getRequestDispatcher("/subjects.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
