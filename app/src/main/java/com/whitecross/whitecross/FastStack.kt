package com.whitecross.whitecross

/**
 * Created by Mrdae on 25.08.2015.
 */
public class FastStack <T> {

    private class FastStackElem <T> (val value : T, var next : FastStackElem<T>?)

    private var head : FastStackElem<T>? = null

    public fun isEmpty() : Boolean = head == null

    public fun clear() {head = null}

    public fun push(value: T) {head = FastStackElem(value, head)}

    public fun pop() : T {
        val result = head!!.value
        head = head!!.next
        return result
    }
}
