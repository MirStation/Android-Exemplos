package br.com.casadocodigo.boaviagem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ViagemActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viagem);
	}
	
	public void selecionarData(View view) {

		/* 1ª versão: */
		Toast.makeText(this, "Selecionando a data", Toast.LENGTH_LONG).show();
	}
	
	public void salvarViagem(View view) {

		/* 1ª versão: */
		Toast.makeText(this, "Salvando a viagem", Toast.LENGTH_LONG).show();
	}

}
