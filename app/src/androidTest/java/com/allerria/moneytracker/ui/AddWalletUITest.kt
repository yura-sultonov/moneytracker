package com.allerria.moneytracker.ui

import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.DrawerActions
import android.support.test.espresso.contrib.DrawerMatchers.isClosed
import android.support.test.espresso.contrib.NavigationViewActions
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import android.view.Gravity
import com.allerria.moneytracker.R
import com.allerria.moneytracker.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AddWalletUITest {

    @get:Rule
    var activityRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

    @Test
    fun testMenuShow() {
        // navigate to settings page
        navigateToItem(R.id.nav_settings)
        // wait for 1 sec
        Thread.sleep(1000)
        // check item visible
        checkToItemVisible(R.id.wipe_data_text_view)
        // click to wipe all data
        clickToItem(R.id.wipe_data_text_view)
        // wait for 1 sec
        Thread.sleep(1000)
        // check dialog visible
        checkTextVisible("OK")
        // click to OK button
        clickDialogButton("OK")
        // navigate to settings page again :)
        navigateToItem(R.id.nav_settings)
        // wait for 1 sec
        Thread.sleep(1000)
        // check add wallet item show
        checkToItemVisible(R.id.add_wallet_text_view)
        // click to add wallet item
        clickToItem(R.id.add_wallet_text_view)
        // wait for 1 sec
        Thread.sleep(1000)
        // check to wallet name edittext show
        checkToItemVisible(R.id.wallet_name_edit_text)
        // type testAccount on wallet name edittext
        typeOnItem(R.id.wallet_name_edit_text, "testAccount")
        // check to wallet balance edittext show
        checkToItemVisible(R.id.wallet_initial_balance_edit_text)
        // type 123 on wallet balance edittext
        typeOnItem(R.id.wallet_initial_balance_edit_text, "123")
        // hide keyboard on wallet balance
        hideKeyboard()
        // check save button visible
        checkToItemVisible(R.id.add_wallet_button)
        // click to save button
        clickToItem(R.id.add_wallet_button)
        // open a drawer one more time and navigate to balance page :)
        navigateToItem(R.id.nav_balance)
        // wait for 1 sec
        Thread.sleep(1000)
        // check wallet name visible
        checkTextVisible("testAccount")
    }

    private fun hideKeyboard() {
        closeSoftKeyboard()
    }

    private fun clickDialogButton(text: String) {
        onView(withText(text)).inRoot(isDialog()).check(matches(isDisplayed())).perform(click())
    }

    private fun checkTextVisible(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }

    private fun typeOnItem(id: Int, text: String) {
        onView(withId(id)).perform(typeText(text))
    }

    private fun clickToItem(id: Int) {
        onView(withId(id)).perform(click())
    }

    private fun checkToItemVisible(id: Int) {
        onView(withId(id)).check(matches(isDisplayed()))
    }

    private fun navigateToItem(id: Int) {
        // open a drawer
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(DrawerActions.open())
        // click to balance item
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(id))
    }
}