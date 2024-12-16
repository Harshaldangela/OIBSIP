package onlinereservation;

import java.util.Objects;

public class Reservation {
  private String pnr;
  private String username;
  private String trainNumber;
  private String classType;
  private String journeyDate;
  private String source;
  private String destination;
public Reservation(String pnr, String username, String trainNumber, String classType, String journeyDate, String source,
        String destination) {
    this.pnr = pnr;
    this.username = username;
    this.trainNumber = trainNumber;
    this.classType = classType;
    this.journeyDate = journeyDate;
    this.source = source;
    this.destination = destination;
}
public String getPnr() {
    return pnr;
}
public String getUsername() {
    return username;
}
public String getTrainNumber() {
    return trainNumber;
}
public String getClassType() {
    return classType;
}
public String getJourneyDate() {
    return journeyDate;
}
public String getSource() {
    return source;
}
public String getDestination() {
    return destination;
}
public void setPnr(String pnr) {
    this.pnr = pnr;
}
public void setUsername(String username) {
    this.username = username;
}
public void setTrainNumber(String trainNumber) {
    this.trainNumber = trainNumber;
}
public void setClassType(String classType) {
    this.classType = classType;
}
public void setJourneyDate(String journeyDate) {
    this.journeyDate = journeyDate;
}
public void setSource(String source) {
    this.source = source;
}
public void setDestination(String destination) {
    this.destination = destination;
}
  
@Override
public String toString(){
    return "Reservation{" +
               "pnr='" + pnr + '\'' +
               ", username='" + username + '\'' +
               ", trainNumber='" + trainNumber + '\'' +
               ", classType='" + classType + '\'' +
               ", journeyDate='" + journeyDate + '\'' +
               ", source='" + source + '\'' +
               ", destination='" + destination + '\'' +
               '}';
}

@Override
public boolean equals(Object o){
   if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(pnr, that.pnr) &&
               Objects.equals(username, that.username) &&
               Objects.equals(trainNumber, that.trainNumber) &&
               Objects.equals(classType, that.classType) &&
               Objects.equals(journeyDate, that.journeyDate) &&
               Objects.equals(source, that.source) &&
               Objects.equals(destination, that.destination); 
}

@Override
public int hashCode(){
    return Objects.hash(pnr,username,trainNumber,classType,journeyDate,source,destination);
}
}
