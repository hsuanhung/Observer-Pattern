
import java.util.*;

enum Type { // Easy to establish and so on the use of the program
    BUSINESS, VLOG
}

//Abstract goal: There are separate collections of observers and methods to add, delete, and notify observers
abstract class Subject {
    protected List<Observer> observers = new ArrayList<Observer>();   
    //add observer
    public void add(Observer observer) {
        observers.add(observer);
    }    
    //remove observer
    public void remove(Observer observer) {
        observers.remove(observer);
    }   
    public abstract void notifyObserver(Type type); //notify Observer
}
//Entity target B：Implement abstract goals and inform observers.
class YoutuberPeeta extends Subject {
    public void notifyObserver(Type type) {
        String videoType = null;
        switch(type){
            case BUSINESS:
                videoType = " sponsored ";
                break;
            case VLOG:
                videoType = " vlog ";
                break;
        }
        System.out.println("B post a new" + videoType + "video");
        System.out.println("--------------");       
       
        for(Object obs:observers) {
            ((Observer)obs).response(type);
        }
       
    }          
}

//Abstract observer: defines the method when the target is received.
interface Observer {
    void response(Type type); //response
}
//Entity Observer, Fans
class Fans implements Observer {
    public void response(Type type) {
        switch(type){
            case BUSINESS:
                System.out.println("Fans feel sad after seeing a sponsord video :(");
                break;
            case VLOG:
                System.out.println("Fans feel interesting after seeing a vlog :)");
                break;
        }
    }
}
//Entity Observer, firm
class Vendor implements Observer {
    public void response(Type type) {
                switch(type){
            case BUSINESS:
                System.out.println("Firms feel interesting after seeing a sponsord video :)");
                break;
            case VLOG:
                System.out.println("Firms want to see more sponsord videos :)");
                break;
        }
    }
}

// The client creates a target and two observers separately, and the target will notify the two observers at the same time when a new video is released
public class ObserverPattern {
    public static void main(String[] args) {
        Subject subject = new YoutuberPeeta();
        Observer obs1 = new Fans();
        Observer obs2 = new Vendor();
        subject.add(obs1);
        subject.add(obs2);
        subject.notifyObserver(Type.BUSINESS);
        System.out.println();    
        subject.notifyObserver(Type.VLOG);
    }
}
