package ru.shchekotina.CallCenterService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shchekotina.CallCenterService.model.Task;
import ru.shchekotina.CallCenterService.service.TaskService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String getStartPage() {
        return "index";
    }

    @GetMapping("/courier")
    public String getCourierPage() {
        return "courier";
    }

    @PostMapping("/courier")
    public String postOrderNumber (@RequestParam String orderNumber, Model model) {
        try {
            taskService.addTask(Integer.parseUnsignedInt(orderNumber));
            model.addAttribute("resultAdded", "По заказу " + orderNumber + " добавлено задание для колл-центра");
        }
        catch (NumberFormatException e) {
            model.addAttribute("resultNotAdded", "Заказа " + orderNumber +" не существует. Номер заказа должен быть целым положительным числом.");
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            model.addAttribute("dbError", "Внесение данных о заказе временно невозможно. Нет соединения с базой данных.");
        }
        return "courier";
    }

    @GetMapping("/list")
    public String getAllTasks(Model model) {
        try {
            List<Task> tasksList = taskService.getAllTasks();
            model.addAttribute("tasksList", tasksList);
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            model.addAttribute("dbError", "Отображение заданий временно недоступно. Нет соединения с базой данных.");
        }
        return "list";
    }

    @PostMapping("/search")
    public String getTaskByOrderNumber(@RequestParam String orderNumber, Model model) {
        if (orderNumber.equals("")){
            getAllTasks(model);
            return "list";
        }
        try {
            int number = Integer.parseUnsignedInt(orderNumber);
            List<Task> tasksList = taskService.getTaskByOrderNumber(number);
            model.addAttribute("tasksList", tasksList);
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            model.addAttribute("dbError", "Поиск временно невозможен. Нет соединения с базой данных.");
        }
        catch(NumberFormatException e) {
            model.addAttribute("tasksList", new ArrayList<>());
        }
        return "list";
    }
}
