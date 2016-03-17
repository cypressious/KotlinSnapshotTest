package com.cypressworks.kotlinicdaggerissue

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var foo: Foo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MyApplication.component.inject(this)

        Toast.makeText(this, foo.toString(), Toast.LENGTH_SHORT).show()

        Obj.pub()

        ServiceFrag.getOrCreate(this)
    }

    class ServiceFrag : AbstractServiceFragment() {
        companion object : ServiceFragmentCompanion<ServiceFrag> {
            override val TAG: String
                get() = "SERVICE"

        }
    }
}
