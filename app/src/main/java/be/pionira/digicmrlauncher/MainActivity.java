package be.pionira.digicmrlauncher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signCmrBtn = (Button) findViewById(R.id.SignButton);
        signCmrBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setExtras("be.pionira.digicmr.action.START_SIGNOFF_CONSIGNMENT_NOTE");
            }
        });

        Button showCmrBtn = findViewById(R.id.ShowButton);
        showCmrBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                setExtras("be.pionira.digicmr.action.START_SIGNOFF_CONSIGNMENT_NOTE");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setExtras(String action)
    {
        Intent intent = new Intent(action);
        EditText textBox = (EditText) findViewById(R.id.MyText);
        EditText textBoxExternalId = (EditText) findViewById(R.id.ExternalId);
        EditText textBoxClientId = (EditText) findViewById(R.id.ClientId);
        EditText textBoxClientSecret = (EditText) findViewById(R.id.ClientSecret);
        EditText textBoxUserId = (EditText) findViewById(R.id.UserId);

        if (textBoxExternalId.getText() != null)
        {
            intent.putExtra("be.pionira.digicmr.extras.CONSIGNMENT_NOTE_EXTERNALID", textBoxExternalId.getText());
        }
        else
        {
            intent.putExtra("be.pionira.digicmr.extras.CONSIGNMENT_NOTE_NUMBER", textBox.getText());
        }

        intent.putExtra("be.pionira.digicmr.extras.USER_ID", textBoxUserId.getText());
        intent.putExtra("be.pionira.digicmr.extras.CLIENT_ID", textBoxClientId.getText());
        intent.putExtra("be.pionira.digicmr.extras.CLIENT_SECRET", textBoxClientSecret.getText());

        startActivityForResult(intent, 0);
    }
}
