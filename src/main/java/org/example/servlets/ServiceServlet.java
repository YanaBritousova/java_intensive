package org.example.servlets;

import com.google.gson.Gson;
import org.example.models.Client;
import org.example.models.Service;
import org.example.service.ClientService;
import org.example.service.ServiceService;
import org.example.service.ServiceServiceImpl;
import org.example.util.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/services")
public class ServiceServlet extends HttpServlet {
    private final ServiceServiceImpl serviceService;
    private final ObjectMapper objectMapper;

    public ServiceServlet() {
        this.serviceService = new ServiceServiceImpl();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id"); // Получаем часть URL-адреса
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            Service service = serviceService.get(id);

            if (service != null) {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(objectMapper.writeValueAsString(service)); // Конвертируем объект в JSON и отправляем
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
        Service service = gson.fromJson(sb.toString(),Service.class);
        serviceService.save(service);
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id"); // Получаем часть URL-адреса
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            serviceService.delete(id);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Некорректный запрос
        }
    }
}
