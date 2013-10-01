package com.example.test;

import android.os.Bundle;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

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
    	
    	if (numberOfAccounts > 0) {
    		showFragment(new AccountListFragment());
    	} else {
    		showFragment(new CreateAccountFragment());
    	}
	}
	
	public void toggleShowPassword(View view) {
        EditText password = (EditText) this.findViewById(R.id.password);
		CheckBox showPassword = (CheckBox) this.findViewById(R.id.show_password);
		
		if(showPassword.isChecked()) {
			if (password == null) {
				Log.d("Simon", "password is null");
			}
			password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
        	password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
	}
	
	public void createAccount(View view) {
		// TODO: something
	}
	
	public void showCreateAccountFragment() {
		showFragment(new CreateAccountFragment());
	}
	
	private void showFragment(Fragment newFragment) {    	
    	FragmentManager fragmentManager = getFragmentManager();
    	FragmentTransaction transaction = fragmentManager.beginTransaction();

    	// Replace whatever is in the fragment_container view with this fragment,
    	// and add the transaction to the back stack
    	transaction.replace(android.R.id.content, newFragment);
    	transaction.addToBackStack(null);

    	// Commit the transaction
    	transaction.commit();	
	}
}
