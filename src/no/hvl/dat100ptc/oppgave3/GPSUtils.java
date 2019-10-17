package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] LatListe = new double [gpspoints.length];
		
		int i;
		for (i=0; i<gpspoints.length ; i++) {
		LatListe[i] = gpspoints[i].getLatitude();
		
		}
		return LatListe;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] LongListe = new double [gpspoints.length];
		
		int i;
		for (i=0; i<gpspoints.length ; i++) {
		LongListe[i] = gpspoints[i].getLongitude();
		
		}
		return LongListe;

	}
	
	public static double[] getElevation(GPSPoint[] gpspoints) {

		double[] ElevListe = new double [gpspoints.length];
		
		int i;
		for (i=0; i<gpspoints.length ; i++) {
		ElevListe[i] = gpspoints[i].getElevation();
		
		}
		return ElevListe;

	}

	private static int R = 6371000; // jordens radius
	

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d, a, c;
		double latitude1, longitude1, latitude2, longitude2;
		
		latitude1 = gpspoint1.getLatitude();
		latitude2 = gpspoint2.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		longitude2 = gpspoint2.getLongitude();
		
		double latRad1 = latitude1 * (Math.PI/180);
		double latRad2 = latitude2 * (Math.PI/180);
		double longRad1 = longitude1 * (Math.PI/180);
		double longRad2 = longitude2 * (Math.PI/180);
		
		double latRadDelta = latRad2 - latRad1;
		double longRadDelta = longRad2 - longRad1;
		
		a=(Math.pow(Math.sin(latRadDelta/2), 2)) + Math.cos(latRad1)*Math.cos(latRad2)*Math.pow((Math.sin(longRadDelta/2)), 2);
		c=2*Math.atan2(sqrt(a), sqrt((1-a)));
		d=R*c;
		
		return d;
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int sec1, sec2, secs;
		double speed;
		double lat1, lat2, long1, long2, delLat, delLong, d, speedKMT; 
		
		lat1 = gpspoint1.getLatitude();
		lat2 = gpspoint2.getLatitude();
		long1 = gpspoint1.getLongitude();
		long2 = gpspoint2.getLongitude();
		sec1 = gpspoint1.getTime();
		sec2 = gpspoint2.getTime();
		
		delLat = lat2 - lat1; 
		delLong = long2 - long1;
		
		d = sqrt((Math.pow(delLat, 2)) + (Math.pow(delLong, 2))); 
		
		secs = sec2 - sec1; 
		
		speed = d / secs;
		
		speedKMT = speed * 3.6;
	
		return speedKMT;
	
	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";
		int hour, min, sec;
		hour = secs/3600;
		min = (secs%3600)/60;
		sec = (secs%3600)%60;
		timestr = " "+hour+TIMESEP+min+TIMESEP+sec+" ";
		return timestr;
	}

	public static String formatDouble(double d) {

		double twoDec;
		String space = " ";
		String str = " ";
		
		twoDec = Math.floor(d*100)/100;
		
		while (str.length() < 10) {
		space += space;
		str = space + twoDec;
			}
		return str;
	}
}
