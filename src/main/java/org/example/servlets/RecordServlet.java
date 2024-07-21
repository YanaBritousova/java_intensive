package org.example.servlets;

import com.google.gson.Gson;
import org.example.models.Records;
import org.example.models.Service;
import org.example.service.impl.RecordServiceImpl;
import org.example.service.impl.ServiceServiceImpl;
import org.example.util.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet("/record")
public class RecordServlet extends HttpServlet {
    private final RecordServiceImpl recordService;
    private final ObjectMapper objectMapper;

    public RecordServlet() {
        this.recordService = new RecordServiceImpl();
        this.objectMapper = new ObjectMapper();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            Records record = recordService.get(id);

            if (record != null) {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(objectMapper.writeValueAsString(record));
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam!=null){
            long id = Long.parseLong(idParam);
            String dateParam = req.getParameter("date");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = null;
            try {
                date = formatter.parse(dateParam);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            recordService.update(id,date);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Некорректный запрос
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = req.getReader().readLine()) != null) {
            sb.append(s);
        }
        Gson gson = new Gson();
        Records record = gson.fromJson(sb.toString(),Records.class);
        recordService.save(record);
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            recordService.delete(id);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
