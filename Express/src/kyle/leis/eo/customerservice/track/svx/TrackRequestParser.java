package kyle.leis.eo.customerservice.track.svx;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class TrackRequestParser {
	
	private String bookId;
	private List<Event> events = new ArrayList<Event>();
	
	public String getBookId() {
		return bookId;
	}

	public List<Event> getEvents() {
		return events;
	}
	
	/**
	 * ½âÎöJSON×Ö·û´®
	 * @param jsonStr
	 */
	public void parser(String jsonStr) throws JSONException{
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		this.bookId = jsonObject.getString("booking_id");
		JSONArray eventsJson = jsonObject.getJSONArray("events");
		Iterator<?> iterator = eventsJson.iterator();
		while(iterator.hasNext()){
			JSONObject eventJson = (JSONObject) iterator.next();
			Event event = new Event();
			event.setDate(eventJson.getString("date"));
			event.setMessage(eventJson.getString("message"));
			events.add(event);
		}
	}
	
	public class Event{
		private String date;
		private String message;
		
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}
}
