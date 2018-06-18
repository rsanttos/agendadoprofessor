package agendadoprofessor.pds.ufrn.com.br.agendadoprofessor.service;

import android.os.AsyncTask;
import android.util.Log;

import agendaufrnfw.ufrn.imd.pds.dto.CalendarDTO;
import agendaufrnfw.ufrn.imd.pds.request.CalendarRequest;

/**
 * Created by root on 24/04/18.
 */

public class CalendarService extends AsyncTask<Void, Void, CalendarDTO> {

    private CalendarDTO calendar;

    public CalendarDTO getCalendar(){
        CalendarRequest calendarRequest = new CalendarRequest();
        CalendarDTO cDto = new CalendarDTO();
        return cDto.toObject(calendarRequest.getCalendar());
    }
    @Override
    protected CalendarDTO doInBackground(Void... params) {
        calendar = getCalendar();
        return calendar;
    }

    @Override
    protected void onPostExecute(CalendarDTO calendar){
        Log.v("Calendar", String.valueOf(calendar.getYear()));
    }

}
