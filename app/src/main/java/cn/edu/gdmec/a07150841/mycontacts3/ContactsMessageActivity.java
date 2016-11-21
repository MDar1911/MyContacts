package cn.edu.gdmec.a07150841.mycontacts3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by 马达 on 2016/11/19.
 */
public class ContactsMessageActivity extends AppCompatActivity{
    private TextView nameEditText;
    private TextView mobileEditText;
    private TextView qqEditText;
    private TextView danweiEditText;
    private TextView addressEditText;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_message);
        nameEditText = (TextView) findViewById(R.id.name);
        mobileEditText = (TextView) findViewById(R.id.mobile);
        qqEditText= (TextView) findViewById(R.id.qq);
        danweiEditText= (TextView) findViewById(R.id.danwei);
        addressEditText= (TextView) findViewById(R.id.address);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"返回");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
