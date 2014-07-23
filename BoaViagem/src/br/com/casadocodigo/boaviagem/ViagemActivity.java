package br.com.casadocodigo.boaviagem;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class ViagemActivity extends Activity {
	
	private int ano_chegada;
	private int mes_chegada;
	private int dia_chegada;
	private Button dataChegada;
	
	private int ano_saida;
	private int mes_saida;
	private int dia_saida;
	private Button dataSaida;
	
	private boolean isChegada;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viagem);
		Calendar calendar = Calendar.getInstance();
		ano_chegada = calendar.get(Calendar.YEAR);
		mes_chegada = calendar.get(Calendar.MONTH);
		dia_chegada = calendar.get(Calendar.DAY_OF_MONTH);
		ano_saida = calendar.get(Calendar.YEAR);
		mes_saida = calendar.get(Calendar.MONTH);
		dia_saida = calendar.get(Calendar.DAY_OF_MONTH);
		dataChegada = (Button) findViewById(R.id.dataChegada);
		dataSaida = (Button) findViewById(R.id.dataSaida);
		isChegada = false;
	}
	
	@SuppressWarnings("deprecation")
	public void selecionarData(View view) {
		showDialog(view.getId());
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case R.id.dataChegada:
			isChegada = true;
			return new DatePickerDialog(this, listener, ano_chegada, mes_chegada, dia_chegada);
		case R.id.dataSaida:
			isChegada = false;
			return new DatePickerDialog(this, listener, ano_saida, mes_saida, dia_saida);
		default:
			return null;
		}
	}
	
	private OnDateSetListener listener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			if(isChegada){
				ano_chegada = year;
				mes_chegada = monthOfYear;
				dia_chegada = dayOfMonth;
				dataChegada.setText(dia_chegada + " / " + (mes_chegada + 1) + " / " + ano_chegada);
			} else {
				ano_saida = year;
				mes_saida = monthOfYear;
				dia_saida = dayOfMonth;
				dataSaida.setText(dia_saida + " / " + (mes_saida + 1) + " / " + ano_saida);
			}
		}
	};
	
	public void salvarViagem(View view) {

		// Salvar na base de dados
		Toast.makeText(this, "Salvando a viagem", Toast.LENGTH_LONG).show();
	}

}
