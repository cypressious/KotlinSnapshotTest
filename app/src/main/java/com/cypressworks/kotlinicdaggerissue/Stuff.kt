package com.cypressworks.kotlinicdaggerissue

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import javax.inject.Inject

abstract class AbstractServiceFragment : Fragment() {
    @Inject lateinit var foo: Foo

    init {
        MyApplication.component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        foo.bar()
    }

    override fun onDestroy() {
        foo.bar()
        super.onDestroy()
    }

}

@Suppress("unused")
interface ServiceFragmentCompanion<T : Fragment> : Taggable {
    val TAG: String

    override val tag: String get() = "Service Companion \"$TAG\""
}

inline fun <reified T : AbstractServiceFragment> ServiceFragmentCompanion<T>.getOrCreate(
        a: FragmentActivity,
        executePending: Boolean = true
): T {
    val fm = a.supportFragmentManager

    val frag = fm.findFragmentByTag(TAG) as? T ?:
            T::class.java.getConstructor().newInstance().apply {
                fm.applyTransaction { add(this@apply, TAG) }

                if (executePending) {
                    fm.executePendingTransactions()
                }
            }

    log { "Got or created: $frag" }

    return frag
}

inline fun FragmentManager.applyTransaction(crossinline block: FragmentTransaction.() -> Unit)
        = beginTransaction().apply(block).commit()
