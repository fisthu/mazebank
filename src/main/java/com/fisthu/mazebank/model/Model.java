package com.fisthu.mazebank.model;

import com.fisthu.mazebank.view.ViewFactory;

public enum Model {
    INSTANCE;
    private final ViewFactory viewFactory;

    Model() {
        viewFactory = new ViewFactory();
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
