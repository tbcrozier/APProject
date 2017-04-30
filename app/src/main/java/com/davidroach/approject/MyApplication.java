package com.davidroach.approject;

import android.app.Application;

/**
 * Created by droach-dev on 4/29/17.
 */

////// FOR GETTING AND SETTING CURRENT USER VARIABLE /////////

    public class MyApplication extends Application {

        private String currentUser;

        public String getCurrentUser() {
            return currentUser;
        }

        public void setCurrentUser(String currentUserIn) {
            this.currentUser = currentUserIn;
        }
    }

