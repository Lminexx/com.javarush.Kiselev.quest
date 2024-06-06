package org.example.final3module;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String ip;
    public List<Quest> quests = new ArrayList<>();
    public List<Answer> answers = new ArrayList<>();
    public List<BadEnd> badEnds = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            this.ip = inetAddress.getHostAddress();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("questGame.txt");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("q")) {
                        quests.add(new Quest(quests.size() + 1, line.substring(1)));
                    } else if (line.startsWith("0")) {
                        answers.add(new Answer(answers.size() + 1, line.substring(1)));
                    }else if(line.startsWith("e")){
                        badEnds.add(new BadEnd(line.substring(1),badEnds.size() +2));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Integer counter = (Integer) session.getAttribute("counter");
        if (counter == null) {
            session.setAttribute("counter", 0);
            counter = 0;
        } else {
            session.setAttribute("counter", ++counter);
        }
        String selectedAnswer = req.getParameter("answer");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        int answerId = -1;
        if(selectedAnswer!=null){
            answerId = Integer.parseInt(selectedAnswer);
        }
        if(answerId%2==0){
            if(answerId == 2){
                out.println("<p>" + badEnds.get(answerId-2).getEnd() + "</p>");
                out.println("<h1>" + "Поражение" + "<h1>");
                out.println("</head>");
                out.println("<body>");
            } else if (answerId == 4) {
                out.println("<p>" + badEnds.get(answerId-3).getEnd() + "</p>");
                out.println("<h1>" + "Поражение" + "<h1>");
                out.println("</head>");
                out.println("<body>");
            }else if (answerId == 6) {
                out.println("<p>" + badEnds.get(answerId-4).getEnd() + "</p>");
                out.println("<h1>" + "Поражение" + "<h1>");
                out.println("</head>");
                out.println("<body>");
            }
        }else{
            if(answerId == 5){
                out.println("<h1>" + quests.get(counter).getQuest() + "</h1>");
                out.println("</head>");
                out.println("<body>");
            }else{
                out.println("<title>Quiz Game</title>");

                out.println("<h1>" + quests.get(counter).getQuest() + "</h1>");
                out.println("<form method='post' action='hello-servlet'>");
                int startAnswerIndex = counter * 2;
                int endAnswerIndex = startAnswerIndex + 2;
                for (int i = startAnswerIndex; i < endAnswerIndex && i < answers.size(); i++) {
                    out.println("<input type='radio' name='answer' value='" + answers.get(i).getId() + "'>");
                    out.println("<label>" + answers.get(i).getAnswer() + "</label><br>");
                }
                out.println("<input type='submit' value='Ответить'>");
                out.println("</form>");
                out.println("<div style='position: fixed;" +
                        " bottom: 0; left: 0; margin: 10px;" +
                        " background-color: #f1f1f1; padding: 10px;" +
                        " border: 1px solid #ccc; border-radius: 5px;'>");
                out.println("<div>Name: " + userName + "</div>");
                out.println("<div>ip:" + ip + "</div>");
                out.println("</body>");
                out.println("</html>");
            }
        }


    }

    public void method(){

    }


    public void destroy() {
    }
}