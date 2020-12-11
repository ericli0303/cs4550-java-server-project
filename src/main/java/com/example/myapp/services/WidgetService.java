package com.example.myapp.services;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.models.Widget;
import com.example.myapp.repositories.WidgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WidgetService {
    @Autowired
    WidgetRepository widgetRepository;
    List<Widget> widgets = new ArrayList<Widget>();
    {
        widgets.add(new Widget(123, "HTML", "Widget 1", "123"));
        widgets.add(new Widget(124, "HTM", "Widget 2", "123"));
        widgets.add(new Widget(235, "CS", "Widget B", "234"));
        widgets.add(new Widget(236, "C", "Widget C", "234"));
    }

    int count = 100;

    public List<Widget> findAllWidgets() {
        return (List<Widget>) widgetRepository.findAll();
    }

    // check
    public Widget createWidget(String tid, 
        Widget widget) {
            // int newId = count++;
            // widget.setId(newId);
            widget.setTopicId(tid);
            return widgetRepository.save(widget);
            // widgets.add(widget);
            // return widget;
    }

    public List<Widget> findWidgetsForTopic(String tid) {
        return widgetRepository.findWidgetsByTopicId(tid);
        // List<Widget> widgetsForTopic = new ArrayList<Widget>();
        // for(Widget w: widgets) {
        //     if(w.getTopicId().equals(tid)) {
        //         widgetsForTopic.add(w);
        //     }
        // }
        // return widgetsForTopic;
    }

    //check
    public Widget updateWidget(int wid, Widget widget) {
        try{
            Widget oldWidget = widgetRepository.findById(wid).get();
            oldWidget.updateAll(widget);
            widgetRepository.save(oldWidget);
            return oldWidget;
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Update widget failed");
        }
            // for( Widget w: widgets) {
            //     if(w.getId() == wid) {
            //         w.updateAll(widget);
            //         return 1;
            //     }
            // }
            // return 0;
    }

    //check
    public void deleteWidget(int wid) {
        try {
            widgetRepository.deleteById(wid);
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Delete Widget failed");
        }
        // for( Widget widget: widgets) {
        //     if(widget.getId() == wid) {
        //         widgets.remove(widget);
        //         return 1;
        //     }
        // }
        // return 0;
    }

    public Widget findWidgetById(int wid) {
        return widgetRepository.findById(wid).get();
        // for(Widget w: widgets) {
        //     if(w.getId() == (wid)) {
        //         return w;
        //     }
        // }
        // return null;
    }

}
