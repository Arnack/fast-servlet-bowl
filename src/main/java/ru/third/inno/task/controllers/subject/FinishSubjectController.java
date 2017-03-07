package ru.third.inno.task.controllers.subject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.third.inno.task.models.dao.iBoardDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yy on 07.03.17.
 */
@Controller
public class FinishSubjectController {
    Logger logger = Logger.getLogger(FinishSubjectController.class);

    iBoardDao boardDao;

    @Autowired
    public void boardDao(iBoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @RequestMapping(value = "/finishsubject")
    protected ModelAndView finishsubject(HttpServletRequest req,
                                         @RequestParam(name="id") String id) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();

        HttpSession session = req.getSession(false);
        String userId = session.getAttribute("id").toString();
        String subjectId = id;

        if(boardDao.deleteBoard(userId, subjectId)){
            modelAndView.addObject("message", "You finished learning the subject");
            modelAndView.setViewName("redirect: /subjects");
        }else {
            modelAndView.setViewName("redirect: /errorwithrights.jsp");
        }
        //modelAndView.setViewName("/subjects");
        return modelAndView;
    }
}