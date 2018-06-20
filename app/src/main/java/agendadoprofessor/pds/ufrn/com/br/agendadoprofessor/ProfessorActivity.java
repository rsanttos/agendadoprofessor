package agendadoprofessor.pds.ufrn.com.br.agendadoprofessor;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import agendadoprofessor.pds.ufrn.com.br.agendadoprofessor.service.ProfessorService;
import agendaufrnfw.ufrn.imd.pds.dto.ClassDTO;
import agendaufrnfw.ufrn.imd.pds.model.calendar.Evaluation;
import agendaufrnfw.ufrn.imd.pds.model.calendar.Task;
import agendaufrnfw.ufrn.imd.pds.model.user.Professor;
import agendaufrnfw.ufrn.imd.pds.util.DateUtil;

public class ProfessorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        TextView tvNome = (TextView) findViewById(R.id.tvNome);
        TextView tvCpf = (TextView) findViewById(R.id.tvCpf);
        TextView tvCargo = (TextView) findViewById(R.id.tvCargo);
        TextView tvSiape = (TextView) findViewById(R.id.tvSiape);
        TextView tvUnidade = (TextView) findViewById(R.id.tvUnidade);
        TextView tvDataAdmissao = (TextView) findViewById(R.id.tvDataAdmissao);
        ListView lvTarefas = (ListView) findViewById(R.id.lvTarefas);
        ListView lvAvaliacoes = (ListView) findViewById(R.id.lvAvaliacoes);

        criarBarraMenu();

        if(getIntent().hasExtra("token")){
            String token = getIntent().getStringExtra("token");
            ProfessorService professorService = new ProfessorService(token);
            try {
                Professor pDto = professorService.execute().get();
                tvNome.setText(pDto.getNome());
                tvCpf.setText(pDto.getCpf());
                tvCargo.setText(pDto.getCargo());
                tvSiape.setText(pDto.getSiape());
                tvUnidade.setText(pDto.getUnidade());
                tvDataAdmissao.setText(DateUtil.format(pDto.getData_admissao()));
                List<Task> allTasks = new ArrayList<Task>();
                List<Evaluation> allEvaluations = new ArrayList<Evaluation>();
                for(ClassDTO classe : pDto.getClasses()){
                    allTasks.addAll(classe.getTasks());
                    allEvaluations.addAll(classe.getEvaluations());
                }

                ArrayAdapter<Task> arrayAdapterTarefas = new ArrayAdapter<Task>(this,
                        android.R.layout.simple_list_item_1, allTasks);
                lvTarefas.setAdapter(arrayAdapterTarefas);

                ArrayAdapter<Evaluation> arrayAdapterAvaliacoes = new ArrayAdapter<Evaluation>(this,
                        android.R.layout.simple_list_item_1, allEvaluations);
                lvAvaliacoes.setAdapter(arrayAdapterAvaliacoes);



            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public void criarBarraMenu() {
        Toolbar menuSIGAgenda = (Toolbar) findViewById(R.id.menu);
        setSupportActionBar(menuSIGAgenda);
        menuSIGAgenda.setLogo(R.mipmap.ic_launcher);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        String token = "";
        int id = item.getItemId();
        switch (id){
            case R.id.item_calendario_compromissos:
                intent = new Intent();
                intent.setClass(this, CalendarCustomActivity.class);
                token = getIntent().getStringExtra("token");
                intent.putExtra("token", token);
                startActivity(intent);
                return true;
            case R.id.item_tarefas_avaliacoes:
                intent = new Intent();
                intent.setClass(this, ProfessorActivity.class);
                token = getIntent().getStringExtra("token");
                intent.putExtra("token", token);
                startActivity(intent);
                return true;
            case R.id.item_calendario:
                intent = new Intent();
                intent.setClass(this, CalendarActivity.class);
                token = getIntent().getStringExtra("token");
                intent.putExtra("token", token);
                startActivity(intent);
                return true;
            case R.id.item_emprestimos:
                intent = new Intent();
                intent.setClass(this, LoanActivity.class);
                token = getIntent().getStringExtra("token");
                intent.putExtra("token", token);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
