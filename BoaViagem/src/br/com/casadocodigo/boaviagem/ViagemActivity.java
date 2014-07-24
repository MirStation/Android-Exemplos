package br.com.casadocodigo.boaviagem;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ViagemActivity extends Activity {
	
	private int ano_chegada;
	private int mes_chegada;
	private int dia_chegada;
	private Button dataChegadaButton;
	
	private int ano_saida;
	private int mes_saida;
	private int dia_saida;
	private Button dataSaidaButton;
	
	private boolean isChegada;
	
	// Atributos referentes à base de dados
	private DatabaseHelper helper;
	private EditText destino, quantidadePessoas, orcamento;
	private Date  dataChegada, dataSaida;
	private RadioGroup radioGroup;
	
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
		dataChegada = new Date(calendar.getTimeInMillis());
		dataSaida = new Date(calendar.getTimeInMillis());
		dataChegadaButton = (Button) findViewById(R.id.dataChegada);
		dataSaidaButton = (Button) findViewById(R.id.dataSaida);
		isChegada = false;
		
		// Recuperando os dados da viagem
		destino = (EditText) findViewById(R.id.destino);
		quantidadePessoas = (EditText) findViewById(R.id.quantidadePessoas);
		orcamento = (EditText) findViewById(R.id.orcamento);
		radioGroup = (RadioGroup) findViewById(R.id.tipoViagem);
		// Acesso à base de dados
		helper = new DatabaseHelper(this);
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
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, monthOfYear, dayOfMonth);
			if(isChegada){
				ano_chegada = year;
				mes_chegada = monthOfYear;
				dia_chegada = dayOfMonth;
				dataChegada = calendar.getTime();
				dataChegadaButton.setText(dia_chegada + " / " + (mes_chegada + 1) + " / " + ano_chegada);
			} else {
				ano_saida = year;
				mes_saida = monthOfYear;
				dia_saida = dayOfMonth;
				dataSaida = calendar.getTime();
				dataSaidaButton.setText(dia_saida + " / " + (mes_saida + 1) + " / " + ano_saida);
			}
		}
	};
	
	public void salvarViagem(View view) {
		SQLiteDatabase db = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("destino", destino.getText().toString());
		values.put("data_chegada", dataChegada.getTime());
		values.put("data_saida", dataSaida.getTime());
		values.put("orcamento", orcamento.getText().toString());
		values.put("quantidade_pessoas", quantidadePessoas.getText().toString());
		
		int tipo = radioGroup.getCheckedRadioButtonId();
		if(tipo == R.id.lazer) {
			values.put("tipo_viagem", Constantes.VIAGEM_LAZER);
		} else {
			values.put("tipo_viagem", Constantes.VIAGEM_NEGOCIOS);
		}
		
		long resultado = db.insert("viagem", null, values);
		if(resultado != -1 ) {
			Toast.makeText(this, getString(R.string.registro_salvo),Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, getString(R.string.erro_salvar),Toast.LENGTH_SHORT).show();
		}

	}
	
	@Override
	protected void onDestroy() {
		helper.close();
		super.onDestroy();
	}
}
