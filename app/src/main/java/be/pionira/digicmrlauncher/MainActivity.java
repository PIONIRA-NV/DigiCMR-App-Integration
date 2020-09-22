package be.pionira.digicmrlauncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signCmrBtn = (Button) findViewById(R.id.SignButton);
        signCmrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setExtras("be.pionira.digicmr.action.START_SIGNOFF_CONSIGNMENT_NOTE");
            }
        });

        Button showCmrBtn = findViewById(R.id.ShowButton);
        showCmrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setExtras("be.pionira.digicmr.action.START_SIGNOFF_CONSIGNMENT_NOTE");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setExtras(String action) {
        Intent intent = new Intent(action);
        EditText txtCmrNumber = (EditText) findViewById(R.id.CmrNumber);
        EditText txtCmrId = (EditText) findViewById(R.id.CmrId);
        EditText txtExternalId = (EditText) findViewById(R.id.ExternalId);
        EditText txtClientId = (EditText) findViewById(R.id.ClientId);
        EditText txtClientSecret = (EditText) findViewById(R.id.ClientSecret);
        EditText txtUserId = (EditText) findViewById(R.id.UserId);
        EditText txtTenantKey = (EditText) findViewById(R.id.TenantKey);

        if (!EditTextIsNullOrWhiteSpace(txtExternalId)) {
            intent.putExtra("be.pionira.digicmr.extras.CONSIGNMENT_NOTE_EXTERNALID", txtExternalId.getText());
        } else if (!EditTextIsNullOrWhiteSpace(txtCmrNumber)) {
            intent.putExtra("be.pionira.digicmr.extras.CONSIGNMENT_NOTE_NUMBER", txtCmrNumber.getText());
        } else if (!EditTextIsNullOrWhiteSpace(txtCmrId)) {
            intent.putExtra("be.pionira.digicmr.extras.CONSIGNMENT_NOTE_ID", txtCmrId.getText());
        } //Should probably throw an exception...

        if (!EditTextIsNullOrWhiteSpace(txtTenantKey)) {
            intent.putExtra("be.pionira.digicmr.extras.TENANT_KEY", txtTenantKey.getText());
        } //Should probably throw an exception...

        if(!EditTextIsNullOrWhiteSpace(txtUserId)) {
            intent.putExtra("be.pionira.digicmr.extras.USER_ID", txtUserId.getText());
        } //Should probably throw an exception...

        if(!EditTextIsNullOrWhiteSpace(txtClientId)) {
            intent.putExtra("be.pionira.digicmr.extras.CLIENT_ID", txtClientId.getText());
        } //Should probably throw an exception...

        if(!EditTextIsNullOrWhiteSpace(txtClientSecret)) {
            intent.putExtra("be.pionira.digicmr.extras.CLIENT_SECRET", txtClientSecret.getText());
        } //Should probably throw an exception...

        startActivityForResult(intent, 0);

        //Where it should throw an exception code is not implemented for brevity... example code...
    }

    private boolean EditTextIsNullOrWhiteSpace(EditText txt) {
        Editable content = txt.getText();
        if (content == null) {
            return true;
        }
        String text = content.toString();
        if (text == null || text.trim().isEmpty()) {
            return true;
        }
        return false;
    }
}
