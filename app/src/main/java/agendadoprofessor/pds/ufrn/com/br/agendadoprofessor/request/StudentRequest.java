package agendadoprofessor.pds.ufrn.com.br.agendadoprofessor.request;

/**
 * Created by f202359 on 13/04/2018.
 */

public class StudentRequest extends GenericRequest {
    public StudentRequest(){

    }

    public String getEvaluationsAndTasksStudentLoggedIn(String token){
        url = String.format("/student/%s", token);
        String resultado = super.objectRequest();
        return resultado;
    }
}
