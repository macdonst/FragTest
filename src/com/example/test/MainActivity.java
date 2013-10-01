package com.example.test;

import android.os.Bundle;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
            RegisterFragment register = new RegisterFragment();
            register.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(android.R.id.content, register).commit();
        }
	}

	public void closeFragment(View view) {
		this.finish();
	}
	
	public void registerAccount(View view) {
    	final int numberOfAccounts = AccountManager.get(this.getBaseContext()).getAccounts().length;
    	
    	Log.d("Simon", "number of accounts = " + numberOfAccounts);
    	
    	// Create new fragment and transaction
    	Fragment newFragment = null;

    	if (numberOfAccounts > 0) {
    		newFragment = new AccountListFragment();
    	} else {
    		newFragment = new CreateAccountFragment();
    	}
    	
    	FragmentManager fragmentManager = getFragmentManager();
    	FragmentTransaction transaction = fragmentManager.beginTransaction();

    	// Replace whatever is in the fragment_container view with this fragment,
    	// and add the transaction to the back stack
    	transaction.replace(R.id.ask, newFragment);
    	transaction.addToBackStack(null);

    	// Commit the transaction
    	transaction.commit();
	}
}
