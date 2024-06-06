import org.example.final3module.HelloServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class HelloServletTest {

    private HelloServlet helloServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ServletContext context;
    private ServletConfig config;

    @BeforeEach
    void setUp() throws Exception {
        helloServlet = new HelloServlet();


        config = mock(ServletConfig.class);
        context = mock(ServletContext.class);
        when(config.getServletContext()).thenReturn(context);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);


        session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        helloServlet.init(config);
    }

    @Test
    void testInit() {
        assertTrue(helloServlet.quests.size() > 0);
        assertTrue(helloServlet.answers.size() > 0);
        assertTrue(helloServlet.badEnds.size() > 0);
    }

    @Test
    void testDoPostCorrectAnswer() throws Exception {
        when(request.getParameter("username")).thenReturn("TestUser");
        when(request.getParameter("answer")).thenReturn("1");
        when(session.getAttribute("counter")).thenReturn(0);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        helloServlet.doPost(request, response);

        writer.flush();
        String responseOutput = stringWriter.toString();
        assertTrue(responseOutput.contains("Quiz Game"));
        assertTrue(responseOutput.contains("TestUser"));
    }

    @Test
    void testDoPostBadEnd() throws Exception {
        when(request.getParameter("answer")).thenReturn("2");
        when(session.getAttribute("counter")).thenReturn(0);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        helloServlet.doPost(request, response);
        writer.flush();
        String responseOutput = stringWriter.toString();
        assertTrue(responseOutput.contains("Поражение"));
    }
}