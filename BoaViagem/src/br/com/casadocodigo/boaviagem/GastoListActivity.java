package br.com.casadocodigo.boaviagem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class GastoListActivity extends ListActivity implements OnItemClickListener {

	private List<Map<String,Object>> gastos;
	
	private String dataAnterior = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] de = {"data", "descricao", "valor", "categoria"};
		int[] para = {R.id.data, R.id.descricao, R.id.valor, R.id.categoria};
		SimpleAdapter adapter = new SimpleAdapter(this, listarGastos(), R.layout.lista_gasto, de, para);
		adapter.setViewBinder(new GastoViewBinder());
		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);
		// registrando o menu de contexto
		registerForContextMenu(getListView());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.gasto_menu, menu);
		return true;
	}
	
//	@Override
//	public boolean onMenuItemSelected(int featureId, MenuItem item) {
//		// remover o gasto da base de dados
//		return true;
//	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// remover o gasto da base de dados
		return true;
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.gasto_menu, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		if(item.getItemId() == R.id.remover){
			gastos.remove(info.position);
			getListView().invalidateViews();
			dataAnterior = "";
			// remover o gasto da base de dados
			return true;
		}
		return super.onContextItemSelected(item);
	}
	
	private List<Map<String,Object>> listarGastos() {
		gastos = new ArrayList<Map<String,Object>>();
		Map<String,Object> item = new HashMap<String,Object>();
		item.put("data", "04/02/2012");
		item.put("descricao","Diária Hotel");
		item.put("valor", "R$ 260,00");
		item.put("categoria", R.color.categoria_hospedagem);
		gastos.add(item);
		item = new HashMap<String,Object>();
		item.put("data", "04/02/2012");
		item.put("descricao","Transporte");
		item.put("valor", "R$ 1000,00");
		item.put("categoria", R.color.categoria_transporte);
		gastos.add(item);
		item = new HashMap<String,Object>();
		item.put("data", "13/09/2014");
		item.put("descricao","Aniversário");
		item.put("valor", "R$ 50,00");
		item.put("categoria", R.color.categoria_outros);
		gastos.add(item);
		return gastos;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Map<String,Object> map = gastos.get(position);
		String descricao = (String) map.get("descricao");
		String mensagem = "Gasto selecionado:" + descricao;
		Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
	}
	
	private class GastoViewBinder implements android.widget.SimpleAdapter.ViewBinder {
		
		@Override
		public boolean setViewValue(View view, Object data, String textRepresentation) {
			TextView textView;
			Integer id;
			if(view.getId() == R.id.data) {
				if(!dataAnterior.equals(data)){
					textView = (TextView) view;
					textView.setText(textRepresentation);
					dataAnterior = textRepresentation;
					view.setVisibility(View.VISIBLE);
				} else {
					view.setVisibility(View.GONE);
				}
				return true;
			}
			if(view.getId() == R.id.categoria) {
				id = (Integer) data;
				view.setBackgroundColor(getResources().getColor(id));
				return true;
			}
			return false;
		}
		
	}

}
