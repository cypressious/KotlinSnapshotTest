package com.cypressworks.kotlinicdaggerissue;

import dagger.Provides;

@dagger.Module
public class Module {

    @Provides Foo provideFoo() {
        return new Foo();
    }
}
