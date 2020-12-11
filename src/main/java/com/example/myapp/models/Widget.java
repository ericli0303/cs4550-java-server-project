package com.example.myapp.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "widgets")
public class Widget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    private int widgetOrder;
    private String text; 
    private String src; 
    private int size; 
    private int width; 
    private int height; 
    private String cssClass; 
    private String style; 
    private String value;
    private String topicId;

    public Widget() {
    }
    
    public Widget(int id, String name, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public Widget(int id, String name, String type, String topicId) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.topicId = topicId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidgetOrder() {
        return widgetOrder;
    }

    public void setWidgetOrder(int widgetOrder) {
        this.widgetOrder = widgetOrder;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public void updateAll(Widget widget) {
        this.name = widget.getName();
        this.id = widget.getId();
        this.type = widget.getType();
        this.widgetOrder = widget.getWidgetOrder();
        this.text = widget.getText(); 
        this.src = widget.getSrc(); 
        this.size = widget.getSize(); 
        this.width = widget.getWidth(); 
        this.height = widget.getHeight(); 
        this.cssClass = widget.getCssClass();
        this.style = widget.getStyle(); 
        this.value = widget.getValue();
        this.topicId = widget.getTopicId();
    }



}
