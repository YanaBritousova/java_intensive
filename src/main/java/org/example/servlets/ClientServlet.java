package org.example.servlets;

import com.google.gson.Gson;
import org.example.models.Client;
import org.example.service.ClientService;
import org.example.service.impl.ClientServiceImpl;
import org.example.util.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/clients")
public class ClientServlet extends HttpServlet {
    private final ClientService clientService;
    private final ObjectMapper objectMapper;

    public ClientServlet() {
        this.clientService = new ClientServiceImpl(); // или используйте DI, если необходимо
        this.objectMapper = new ObjectMapper();    // Jackson для конвертации в JSON
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id"); // Получаем часть URL-адреса
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            Client client = clientService.get(id);

            if (client != null) {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(objectMapper.writeValueAsString(client)); // Конвертируем объект в JSON и отправляем
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // Отправляем код 404, если клиент не найден
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Некорректный запрос
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
        Client client = gson.fromJson(sb.toString(),Client.class);
        clientService.save(client);
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id"); // Получаем часть URL-адреса
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            clientService.delete(id);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Некорректный запрос
        }
    }
}
