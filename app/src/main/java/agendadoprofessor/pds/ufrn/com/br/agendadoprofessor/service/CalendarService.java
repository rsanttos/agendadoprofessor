package agendadoprofessor.pds.ufrn.com.br.agendadoprofessor.service;

import android.os.AsyncTask;
import android.util.Log;


import agendaufrnfw.ufrn.imd.pds.model.calendar.CalendarUFRN;
import agendaufrnfw.ufrn.imd.pds.dto.CalendarDTO;
import agendaufrnfw.ufrn.imd.pds.request.CalendarRequest;

/**
 * Created by root on 24/04/18.
 */

public class CalendarService extends AsyncTask<Void, Void, CalendarUFRN> {

    private CalendarUFRN calendar;

    public CalendarUFRN getCalendar(){
        CalendarRequest calendarRequest = new CalendarRequest();
        CalendarDTO cDto = new CalendarDTO();
        CalendarUFRN c = cDto.toObject(calendarRequest.getCalendar());
        return c;
    }
    @Override
    protected CalendarUFRN doInBackground(Void... params) {
        calendar = getCalendar();
        return calendar;
    }

    @Override
    protected void onPostExecute(CalendarUFRN calendar){
        Log.v("Calendar", String.valueOf(calendar.getAno()));
    }

}
