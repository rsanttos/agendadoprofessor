package agendadoprofessor.pds.ufrn.com.br.agendadoprofessor.service;

import android.os.AsyncTask;
import android.util.Log;

import agendadoprofessor.pds.ufrn.com.br.agendadoprofessor.dto.ProfessorDTO;
import agendaufrnfw.ufrn.imd.pds.request.ProfessorRequest;

/**
 * Created by root on 18/06/18.
 */

public class ProfessorService extends AsyncTask<Void, Void, ProfessorDTO> {
    private String token;
    public ProfessorService(String token){
        this.token = token;
    }

    @Override
    protected ProfessorDTO doInBackground(Void... params) {
        ProfessorRequest professorRequest = new ProfessorRequest();
        ProfessorDTO pDto = new ProfessorDTO();
        pDto = pDto.toObject(professorRequest.getEvaluationsAndTasksProfessorLoggedIn(token));
        return pDto;
    }

    @Override
    protected void onPostExecute(ProfessorDTO professorDTO){
        Log.v("Professor", professorDTO.getNome());
    }

}