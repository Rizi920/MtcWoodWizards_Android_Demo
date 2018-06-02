package com.example.rizi.mtcwoodwizard.Models;

/**
 * Created by Rizi on 31/08/2017.
 */

public class ModelCategories {

    public String id;
    public String title;
    public String IsParent;

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setIsParent(String isParent){
        this.IsParent=isParent;
    }

    public String getIsParent(){
        return IsParent;
    }


}

