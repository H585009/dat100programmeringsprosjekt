package no.hvl.dat100ptc.oppgave4;

import static javax.swing.JOptionPane.showMessageDialog;

//import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {
	int i;
	double distance, distanceMax, distanceMin, distanceTot;
	
	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double[] elevation;
		double elevationMax, elevationMin, elevationDif, elevationTot;
		
		elevation = GPSUtils.getElevation(gpspoints);
		
		elevationMax = GPSUtils.findMax(elevation);
		elevationMin = GPSUtils.findMin(elevation);
		
		elevationDif = elevationMax-elevationMin;
		elevationTot = elevationMin + elevationDif;
		
		return elevationTot;
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

		

	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		int i;
		double[] speed = new double [gpspoints.length];
		for (i=0; i<gpspoints.length; i++) {
			speed[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
		}
		return speed;
	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		maxspeed= GPSUtils.findMax(speeds());
		
		return maxspeed;
	}

	public double averageSpeed() {

		double average = 0;
		
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;
		int hour; 
		hour = secs/3600;
		
		if (speedmph >20)
			met = 16;
		else if (speedmph <=20 && speedmph >16)
			met = 12;
		else if (speedmph <=16 && speedmph >14)
			met = 10;
		else if (speedmph <=14 && speedmph >12)
			met = 8;
		else if (speedmph <=12 && speedmph >10)
			met = 6;
		else if (speedmph <10)
			met = 4;
		
		kcal = met*weight*hour;

		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");

		System.out.println("Total Distance: " + totalDistance());
		System.out.println("Total Elevation: " + totalElevation());
		System.out.println("Total Time: " + totalTime());
		System.out.println("Max Speed:  " + maxSpeed());
		System.out.println("Avarage Speed: " + averageSpeed());
		System.out.println("Total Kcal: " + totalKcal(WEIGHT));
		
		System.out.println("==============================================");
		
		
	}

}
