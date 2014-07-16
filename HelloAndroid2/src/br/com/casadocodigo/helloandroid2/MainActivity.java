package br.com.casadocodigo.helloandroid2;

import br.com.casadocodigo.helloandroid2.R;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private EditText nomeEditText;
	private TextView saudacaoTextView;
	private String saudacao;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.nomeEditText = (EditText) findViewById(R.id.nomeEditText);
		this.saudacaoTextView = (TextView) findViewById(R.id.saudacaoTextView);
		this.saudacao = getResources().getString(R.string.saudacao);
	}
	
	public void surpreenderUsuario(View v) {
		Editable texto = nomeEditText.getText();
		String msg = saudacao + " " + texto.toString();
		saudacaoTextView.setText(msg);
	}

}
