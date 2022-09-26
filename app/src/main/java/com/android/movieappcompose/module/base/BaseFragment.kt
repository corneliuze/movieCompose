package com.android.movieappcompose.module.base

import androidx.fragment.app.Fragment
import com.android.movieappcompose.module.MainActivity

abstract class BaseFragment : Fragment() {
    
    protected val mainActivity: MainActivity
        get() {
            return activity as? MainActivity ?: throw IllegalStateException("Not attached!")
        }
    
    
}