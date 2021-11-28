package com.Views;

import com.Controllers.MyEntitys;

import javax.swing.*;
import java.util.Vector;

public class dinamicComboBox extends JComboBox {

    MyEntitys myEntity;


    public dinamicComboBox(ComboBoxModel aModel, MyEntitys myEntity) {
        super(aModel);
        this.myEntity = myEntity;
    }

    public dinamicComboBox(Object[] items, MyEntitys myEntity) {
        super(items);
        this.myEntity = myEntity;
    }

    public dinamicComboBox(Vector items, MyEntitys myEntity) {
        super(items);
        this.myEntity = myEntity;
    }

    public dinamicComboBox(MyEntitys myEntity) {
        this.myEntity = myEntity;
    }


}
