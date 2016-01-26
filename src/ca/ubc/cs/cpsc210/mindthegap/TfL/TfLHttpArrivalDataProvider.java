package ca.ubc.cs.cpsc210.mindthegap.TfL;

import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Wrapper for TfL Arrival Data Provider
 */
public class TfLHttpArrivalDataProvider extends AbstractHttpDataProvider {
    private Station stn;

    public TfLHttpArrivalDataProvider(Station stn) {
        super();
        this.stn = stn;
    }

    @Override
    /**
     * Produces URL used to query TfL web service for expected arrivals at
     * station specified in call to constructor.
     *
     * @returns URL to query TfL web service for arrival data
     */
    protected URL getURL() throws MalformedURLException {
        String request = "https://api.tfl.gov.uk/Line/"; //this is constant for all urls
        Set<Line> stnLines = stn.getLines(); // get all the lines of the stn
        for (Line l: stnLines) { //for each line in stnLines, add lineId to end of request followed by ","
            request = request + l.getId() + ",";
        }
        request = request.substring(0,request.length()-1); //takes out "," at end of url
        request = request + "/Arrivals?stopPointId=" + stn.getID(); //add constant to end of url and add stnId to end
        request = request + "&app_id=&app_key=";
        return new URL(request); //return whole constructed url
    }
}
