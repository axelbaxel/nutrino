import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;
import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.util.Scanner;


public class SerialTest implements SerialPortEventListener {
	SerialPort serialPort;
		/** The port we're normally going to use. */
	private static final String PORT_NAMES[] = {                  "/dev/tty.usbserial-A9007UX1", // Mac OS X
			"/dev/ttyUSB0", // Linux
			"COM4", // Windows
			"COM5",
			"COM6",
	};
	private BufferedReader input;
	private OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;

	public void initialize() {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}


	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine=null;
				if (input.ready()) {
					inputLine = input.readLine();
					System.out.println(inputLine);
					String type = inputLine;
					
					
					File f = new File("c:\\openFile\\infofilmer\\" + inputLine + ".mp4");
						Desktop dt = Desktop.getDesktop();
						dt.open(f);
						//System.out.println("Vekt: " + inputSplit[1]);
					/*String[] inputSplit = inputLine.split("*");
					String type = inputSplit[0];
					int vekt = Integer.parseInt(inputSplit[1]);*/
					/*Scanner s;
					try {
						s = new Scanner(new File("c:\\openFile\\" + type + ".txt"));
						String info = s.nextLine();
						String[] infoA = info.split(",");
						System.out.println();
						System.out.println();
						System.out.println("Type: " + infoA[0]);
						System.out.println("Kalorier: " + (Integer.parseInt(infoA[1])));
						System.out.println("Sukker: " + (Integer.parseInt(infoA[2])));
						System.out.println("Karbohydrater: " + (Integer.parseInt(infoA[3])));
						System.out.println("Vitamin A: " + (Integer.parseInt(infoA[4])));
						System.out.println("Vitamin C: " + (Integer.parseInt(infoA[5])));
						System.out.println("------------------------------------------------------");
						System.out.println();
						System.out.println();
					} catch (Exception e){
						System.out.println("Fil ikke funnet");
					}*/
					
					
					
					
					/*
					if(inputSplit[0].equals("1")) {
						
						Desktop dt = Desktop.getDesktop();
						dt.browse(new URI("http://www.uio.no"));
						System.out.println("Vekt: " + inputSplit[1]);
						System.out.println("kalorier: " inputSplit[1]*3);
					} 
					if(inputSplit[0].equals("2")) {
						
						Desktop dt = Desktop.getDesktop();
						dt.browse(new URI("http://www.ifi.uio.no"));
						System.out.println("Vekt: " + inputSplit[1]);
					}*/
					
					
					/*
					String[] inputSplit = inputLine.split(" ");
					if(inputSplit[0].equals("1")) {
						File f = new File("c:\\openFile\\img1.jpg");
						Desktop dt = Desktop.getDesktop();
						dt.open(f);
						System.out.println("Vekt: " + inputSplit[1]);
					} 
					if(inputSplit[0].equals("2")) {
						File f = new File("c:\\openFile\\img2.jpg");
						Desktop dt = Desktop.getDesktop();
						dt.open(f);
						System.out.println("Vekt: " + inputSplit[1]);
					} */
					
				
				/*
					if(inputLine.equals("1")) {
						File f = new File("c:\\openFile\\img1.jpg");
						Desktop dt = Desktop.getDesktop();
						dt.open(f);
						//System.out.println("Done.");
					} 
					if(inputLine.equals("2")) {
						File f = new File("c:\\openFile\\img2.jpg");
						Desktop dt = Desktop.getDesktop();
						dt.open(f);
						//System.out.println("Done.");
					}*/
				}

			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

	public static void main(String[] args) throws Exception {
		SerialTest main = new SerialTest();
		main.initialize();
		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000    seconds,
				//waiting for events to occur and responding to them    (printing incoming messages to console).
				try {Thread.sleep(1000000);} catch (InterruptedException    ie) {}
			}
		};
		t.start();
		System.out.println("Started");
	}
}