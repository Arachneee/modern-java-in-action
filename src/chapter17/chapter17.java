package chapter17;


public class chapter17 {
	public static void main(String[] args) {
		Observable<TempInfo> observable = getTemperature("New York");
		observable.blockingSubscribe(new TempObserver());
	}

	private static Observable<TempInfo> getTemperature(String town) {
		return Observable.create()
	}

}
