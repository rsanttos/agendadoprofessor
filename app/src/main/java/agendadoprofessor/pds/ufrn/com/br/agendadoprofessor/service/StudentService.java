package agendadoprofessor.pds.ufrn.com.br.agendadoprofessor.service;

import android.os.AsyncTask;
import android.util.Log;

import agendadoprofessor.pds.ufrn.com.br.agendadoprofessor.dto.StudentDTO;
import agendadoprofessor.pds.ufrn.com.br.agendadoprofessor.request.StudentRequest;

/**
 * Created by f202359 on 13/04/2018.
 */

public class StudentService extends AsyncTask<Void, Void, StudentDTO> {
    private String token;
    public StudentService(String token){
        this.token = token;
    }

    @Override
    protected StudentDTO doInBackground(Void... params) {
        StudentRequest studentRequest = new StudentRequest();
        StudentDTO studentDto = StudentDTO.toObject(studentRequest.getEvaluationsAndTasksStudentLoggedIn(token));
        return studentDto;
    }

    @Override
    protected void onPostExecute(StudentDTO studentDto){
        Log.v("Student", studentDto.getNome_discente());
    }
}
