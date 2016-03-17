package com.cypressworks.kotlinicdaggerissue;

import android.app.Application;

import dagger.Component;

public class MyApplication extends Application {

    @Component(modules = {Module.class})
    interface MyComponent {
        void inject(MainActivity a);
        void inject(AbstractServiceFragment a);
    }

    public static MyComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerMyApplication_MyComponent.builder().build();
    }
}
