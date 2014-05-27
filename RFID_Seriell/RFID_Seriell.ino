#include <SoftwareSerial.h>
SoftwareSerial RFID(2, 3); // RX and TX
int newtag[14] = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0};
int data1 = 0;
int i;
String sender = "";
boolean sendnow = false;
String sendersjekk = "";
boolean readtime = true;

void setup()
{
  RFID.begin(9600);    // start serial to RFID reader
  Serial.begin(9600);  // start serial to PC 
  pinMode(4, OUTPUT);
  //digitalWrite(4, LOW);
}

void loop() {
  digitalWrite(4, LOW);
  if (RFID.available() > 0 && readtime == true) 
  {
    readtime = false;
    
    // read tag numbers
    delay(100); // needed to allow time for the data to come in from the serial buffer.
    tone(8, 800, 150);
    digitalWrite(4, HIGH);
    for (int z = 0 ; z < 14 ; z++) // read the rest of the tag
    {
      
      data1 = RFID.read();
      newtag[z] = data1;
      
    }
    
    
    
    
    
    //if (newtag[3] > 0 && newtag[7] > 0) {
    sendnow = true;
    //}
    if (sendnow == true) {
      
    sendnr();
    
    }
    delay(250);
    digitalWrite(4, LOW);
    delay(3750);
    readtime = true;
    
  }
  

}
void sendnr() {
  
  for (int z = 0 ; z < 14 ; z++) // read the rest of the tag
    {
      sender += newtag[z];
    }
    //if (!sendersjekk.equals(sender)) { 
    Serial.println(sender);
    
   // }
    sendersjekk = sender;
    sender = "";
    sendnow = false;
}
