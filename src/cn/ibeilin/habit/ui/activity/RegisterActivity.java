package cn.ibeilin.habit.ui.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.ibeilin.habit.R;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;

public class RegisterActivity extends BaseActivity {
	Button registerButton;
	EditText userName;
	EditText userEmail;
	EditText userPassword;
	EditText userPasswordAgain;
	private ProgressDialog progressDialog;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		this.getActionBar().setDisplayHomeAsUpEnabled(true);

		registerButton = (Button) findViewById(R.id.button_i_need_register);
		userName = (EditText) findViewById(R.id.editText_register_userName);
		userEmail = (EditText) findViewById(R.id.editText_register_email);
		userPassword = (EditText) findViewById(R.id.editText_register_userPassword);
		userPasswordAgain = (EditText) findViewById(R.id.editText_register_userPassword_again);

		registerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (userPassword.getText().toString()
						.equals(userPasswordAgain.getText().toString())) {
					if (!userPassword.getText().toString().isEmpty()) {
						if (!userName.getText().toString().isEmpty()) {
							if (!userEmail.getText().toString().isEmpty()) {
								progressDialogShow();
								register();
							} else {
								showError(ctx
										.getString(R.string.error_register_email_address_null));
							}
						} else {
							showError(ctx
									.getString(R.string.error_register_user_name_null));
						}
					} else {
						showError(ctx
								.getString(R.string.error_register_password_null));
					}
				} else {
					showError(ctx
							.getString(R.string.error_register_password_not_equals));
				}
			}
		});
	}

	private void register() {
		AVUser user = new AVUser();
		user.setUsername(userName.getText().toString());
		user.setPassword(userPassword.getText().toString());
		user.setEmail(userEmail.getText().toString());
		user.signUpInBackground(new SignUpCallback() {
			public void done(AVException e) {
				progressDialogDismiss();
				if (e == null) {
					showRegisterSuccess();
					Intent mainIntent = new Intent(ctx, MainActivity.class);
					startActivity(mainIntent);
					ctx.finish();
				} else {
					switch (e.getCode()) {
					case 202:
						showError(ctx
								.getString(R.string.error_register_user_name_repeat));
						break;
					case 203:
						showError(ctx
								.getString(R.string.error_register_email_repeat));
						break;
					default:
						showError(ctx
								.getString(R.string.network_error));
						break;
					}
				}
			}
		});
	}

	private void progressDialogDismiss() {
		if (progressDialog != null)
			progressDialog.dismiss();
	}

	private void progressDialogShow() {
		progressDialog = ProgressDialog
				.show(ctx,
						ctx.getResources().getText(
								R.string.dialog_message_title),
						ctx.getResources().getText(
								R.string.dialog_text_wait), true, false);
	}

	private void showRegisterSuccess() {
		new AlertDialog.Builder(ctx)
				.setTitle(
						ctx.getResources().getString(
								R.string.dialog_message_title))
				.setMessage(
						ctx.getResources().getString(
								R.string.success_register_success))
				.setNegativeButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						}).show();
	}
}
