package com.example.myapp.controllers;

import java.util.List;

import com.example.myapp.models.Widget;
import com.example.myapp.services.WidgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {
    @Autowired
    WidgetService service;

    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return service.findAllWidgets();
    }

    @PostMapping("/api/topics/{tid}/widgets")
    public Widget createWidget(
        @PathVariable("tid") String tid, 
        @RequestBody Widget widget) {
        return service.createWidget(tid, widget);
    }

    @GetMapping("/api/topics/{tid}/widgets")
    public List<Widget> findWidgetsForTopic(
        @PathVariable("tid") String tid) {
        return service.findWidgetsForTopic(tid);
    }

    @PutMapping("/api/widgets/{wid}")
    public Widget updateWidget(
        @PathVariable("wid") int wid, 
        @RequestBody Widget widget) {
        return service.updateWidget(wid, widget);
    }

    @DeleteMapping("/api/widgets/{wid}")
    public void deleteWidget(
        @PathVariable("wid") int wid) {
        service.deleteWidget(wid);
    }
    @GetMapping("/api/widgets/{wid}")
    public Widget findWidgetById(
        @PathVariable("wid") int wid) {
        return service.findWidgetById(wid);
    }

}
/*
mysql://bf7694b632ea6e:ed248348@us-cdbr-east-02.cleardb.com/heroku_3b9841605f7ed57?reconnect=true
*/