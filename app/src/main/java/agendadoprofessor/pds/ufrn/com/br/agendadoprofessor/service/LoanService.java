package agendadoprofessor.pds.ufrn.com.br.agendadoprofessor.service;

import android.os.AsyncTask;
import android.util.Log;

import agendaufrnfw.ufrn.imd.pds.dto.LoanDTO;
import agendaufrnfw.ufrn.imd.pds.request.LoanRequest;

/**
 * Created by root on 23/04/18.
 */

public class LoanService extends AsyncTask<Void, Void, LoanDTO[]> {
    private String token;
    private String cpf;
    public LoanService(String cpf, String token){
        this.token = token;
        this.cpf = cpf;
    }

    @Override
    protected LoanDTO[] doInBackground(Void... params) {
        LoanRequest loanRequest = new LoanRequest();
        LoanDTO lDto = new LoanDTO();
        LoanDTO[] loans = lDto.toArrayObject(loanRequest.getStudentLoans(cpf, token));
        return loans;
    }

    @Override
    protected void onPostExecute(LoanDTO[] loans){
        Log.v("Loans", String.valueOf(loans.length));
    }
}
