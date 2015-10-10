package com.whitecross.whitecross

import android.app.Activity
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

/**
 * Created by Mrdae on 22.08.2015.
 */
public abstract class PageFragment : Fragment() {

    protected abstract var layoutId : Int

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        (activity as MainActivity)?.onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER))
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(layoutId, container, false)
        return rootView
    }

    override fun onStart() {
        super<Fragment>.onStart()
        initUI()
    }

    protected fun getContainer() : View {
        return getActivity().findViewById(R.id.container)
    }

    //make it abstract
    protected open fun initUI() {

    }

    companion object {

        protected val ARG_SECTION_NUMBER : String = "section_number"

    }

}
