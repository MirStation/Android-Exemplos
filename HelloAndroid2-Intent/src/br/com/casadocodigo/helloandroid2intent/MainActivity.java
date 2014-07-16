package br.com.casadocodigo.helloandroid2intent;

import br.com.casadocodigo.helloandroid2intent.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private EditText nomeEditText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.nomeEditText = (EditText) findViewById(R.id.nomeEditText);
	}
	
	public void surpreenderUsuario(View v) {
		Intent intent = new Intent(SaudacaoActivity.ACAO_EXIBIR_SAUDACAO);
		intent.addCategory(SaudacaoActivity.CATEGORIA_SAUDACAO);
		String texto = nomeEditText.getText().toString();
		intent.putExtra(SaudacaoActivity.EXTRA_NOME_USUARIO, texto);
		startActivity(intent);
	}

}
