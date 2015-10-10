package com.whitecross.whitecross;

/**
 * Created by Mrdae on 04.09.2015.
 */
public class BackStackSingletonWrapper {


    companion object {

        private var mInstance : FastStack<Int>? = null

        fun getInstance() : FastStack<Int> {

            if (mInstance == null)
                mInstance = FastStack<Int>()

            return mInstance!!

        }

    }

}
