package com.whitecross.whitecross

import android.app.Activity
import android.app.SearchManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.ActionBar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast

public class MainActivity : AppCompatActivity(), NavigationDrawerFragment.NavigationDrawerCallbacks,
        MenuItemCompat.OnActionExpandListener, widget.SearchView.OnQueryTextListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private var mNavigationDrawerFragment: NavigationDrawerFragment? = null

    /**
     * Used to store the last screen title. For use in [.restoreActionBar].
     */
    private var mTitle: CharSequence? = null

    /**
     * Selected items
     */

    var idUniversity = -1
        set(value) {
            if (value >= -1)
                $idUniversity = value
        }

    var idCourse = -1
        set(value) {
            if (value >= -1)
                $idCourse = value
        }

    var idTeacher = -1
        set(value) {
            if (value >= -1)
                $idTeacher = value
        }

    /**
     * go to page id by back pressing
     */
    private var backButtonTarget = BackStackSingletonWrapper.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNavigationDrawerFragment = getSupportFragmentManager().findFragmentById(R.id.navigation_drawer) as NavigationDrawerFragment
        mTitle = getTitle()

        // Set up the drawer.
        mNavigationDrawerFragment!!.setUp(R.id.navigation_drawer, findViewById(R.id.drawer_layout) as DrawerLayout)
    }

    override fun onNavigationDrawerItemSelected(position: Int) {
        // update the main content by replacing fragments
        val fragmentManager = getSupportFragmentManager()
        fragmentManager.beginTransaction().replace(R.id.container, MainActivity.newFragmentInstance(position + 1)).commit()
        when (position+1) {
            1 -> mTitle = getString(R.string.app_name)
            2 -> mTitle = getString(R.string.university)
            3 -> mTitle = getString(R.string.course)
            4 -> mTitle = getString(R.string.teacher)
            5 -> mTitle = getString(R.string.exercise)
        }
        getSupportActionBar().setTitle(mTitle)
    }

    public fun onSectionAttached(number: Int) {
        /*when (number) {
            1 -> mTitle = getString(R.string.app_name)
            2 -> mTitle = getString(R.string.title_university)
            3 -> mTitle = getString(R.string.title_course)
            4 -> mTitle = getString(R.string.title_teacher)
            5 -> mTitle = getString(R.string.title_exercise)
        }*/

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.main, menu)
        var searchMenuItem = menu.findItem(R.id.action_search);
        MenuItemCompat.setOnActionExpandListener(searchMenuItem, this);
        var searchView = android.support.v7.widget.SearchView(getSupportActionBar().getThemedContext())
        searchView.setOnQueryTextListener(this)
        searchMenuItem.setActionView(searchView)

        return super<AppCompatActivity>.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            return true
        }

        return super<AppCompatActivity>.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Toast.makeText(this, "submit: $query", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        return true
    }

    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {

        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {

        return true
    }

    companion object {

        public fun newFragmentInstance(sectionNumber: Int): PageFragment {
            val fragment = when (sectionNumber){
                1 -> StartPageFragment()
                2 -> UniversityFragment()
                3 -> CourseFragment()
                4 -> TeacherFragment()
                5 -> ExerciseFragment()
                else -> null
            }
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment!!.setArguments(args)
            return fragment
        }

        protected val ARG_SECTION_NUMBER : String = "section_number"

    }

    fun clearBackStack() = backButtonTarget.clear()

    fun addToBackStack(value : Int) = backButtonTarget.push(value)

    override fun onBackPressed(){
        if (!backButtonTarget.isEmpty())
            this.selectItem(backButtonTarget.pop())
        else
            super<AppCompatActivity>.onBackPressed()
    }

    fun selectItem(pos : Int) {
        this.mNavigationDrawerFragment!!.selectItem(pos)
    }
}
