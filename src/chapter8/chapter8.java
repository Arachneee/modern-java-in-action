package chapter8;

import static java.util.Map.*;

import java.util.*;

public class chapter8 {
	public static void main(String[] args) {
		List<String> friends = Arrays.asList("Hogeon", "Oliva", "Thibaue"); // 고정크기
		friends.set(0,"Richard"); //set가능
//		friends.add("HaHa");          //에러 발생 고정크리고 추가 불가

		List<String> friends1 = List.of("Hogeon", "Oliva", "Thibaue");
//		friends1.set(0,"Richard");   // set 불가
		System.out.println(friends1);

		Set<String> friendsSet = Set.of("Hogeon", "Oliva", "Thibaue");
		Map<String, Integer> friendsMap = Map.of("Hogeon",20, "Oliva",30, "Thibaue",40);
		Map<String, Integer> friendsMap1 = Map.ofEntries(entry("Hogeon",20),
															entry("Oliva",30),
															entry("Thibaue",40));

		friends.replaceAll(i -> Character.toLowerCase(i.charAt(0)) + i.substring(1));
		System.out.println(friends);

		//Map도 forEach 가능
		friendsMap.forEach((name,age) -> System.out.println(name + " : " + age));
		
		friendsMap.entrySet().stream().sorted(Entry.comparingByKey()).forEachOrdered(i -> System.out.println(i.getKey() + " : " + i.getValue()));
		
		Map<String,List<String>> friendsToMovies = new HashMap<>();
		
		// 없으면 초기화해서 추가
		friendsToMovies.computeIfAbsent("Hogeon", name -> new ArrayList<>()).add("Star Wars");

		//Map 도 repalceAll 가능. 바꿀수 있는 Map만
		// friendsMap.replaceAll((friend, String) -> friend.toLowerCase());

	}
}
