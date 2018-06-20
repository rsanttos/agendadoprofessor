package agendadoprofessor.pds.ufrn.com.br.agendadoprofessor.service;

import android.os.AsyncTask;
import android.util.Log;


import agendaufrnfw.ufrn.imd.pds.dto.ProfessorCalendarDTO;
import agendaufrnfw.ufrn.imd.pds.model.calendar.ProfessorCalendar;
import agendaufrnfw.ufrn.imd.pds.request.CalendarRequest;

/**
 * Created by root on 24/04/18.
 */

public class ProfessorCalendarService extends AsyncTask<Void, Void, ProfessorCalendar> {

    private ProfessorCalendar calendar;

    public ProfessorCalendar getCalendar(){
        CalendarRequest calendarRequest = new CalendarRequest();
        ProfessorCalendarDTO pcDto = new ProfessorCalendarDTO();
        ProfessorCalendar pc = pcDto.toObject(calendarRequest.getCalendar());
        return pc;
    }
    @Override
    protected ProfessorCalendar doInBackground(Void... params) {
        calendar = getCalendar();
        return calendar;
    }

    @Override
    protected void onPostExecute(ProfessorCalendar calendar){
        Log.v("Calendar", String.valueOf(calendar.getAno()));
    }

}
