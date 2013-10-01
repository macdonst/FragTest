package com.example.test;

import java.util.ArrayList;
import java.util.List;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AccountListFragment extends ListFragment {
    
    public static AccountListFragment newInstance() {
        return new AccountListFragment();
    }
     
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
 
        TextView textView = new TextView(getActivity());
        textView.setText(getResources().getString(R.string.choose_account));
        textView.setTextAppearance(getActivity(), android.R.style.TextAppearance_Medium);
        textView.setPadding(10, 10, 10, 10);
        getListView().addHeaderView(textView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.account_column, R.id.account);
        setListAdapter(adapter);
        adapter.addAll(createDataList(100));
    }
    
    @Override
    public void onDestroyView() {
    	super.onDestroyView();
    	setListAdapter(null);
    }
 
    private List<String> createDataList(int counts) {
    	Account[] accounts = AccountManager.get(getActivity()).getAccounts();

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < accounts.length; i++) {
            list.add(accounts[i].name);
        }
        list.add(getResources().getString(R.string.other_email));
        return list;
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	// Do something with the data
    	Log.d("Simon", l.getItemAtPosition(position).toString());
    	String itemSelected = l.getItemAtPosition(position).toString();
    	if (getResources().getString(R.string.other_email).equals(itemSelected)) {
    		// create new account
        	Log.d("Simon", "create new account");
        	((MainActivity)getActivity()).showCreateAccountFragment();
    	} else {
    		// save account info
        	Log.d("Simon", "save new info");
    	}
    }
}
