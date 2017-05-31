package muti_thread_timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean isRunning = true;
		List<Counter> running = new ArrayList<>();
		
		System.out.println("Enter your commands (start 'xyz', check 'all|xyz', stop 'xyz', or q to exit!");

		while (isRunning) {
			System.out.println("Command?:");
			String[] input = sc.nextLine().split(" ");
			if (input[0].equals("q")) {
				isRunning = false;
			}

			if (isRunning) {

				if (input.length != 2) {
					System.out.println("Incorrect input!");
				} else {
					if (input[0].equalsIgnoreCase("start")) {
						Counter c = new Counter(input[1], running.size());
						Thread t = new Thread(c);
						running.add(c);
						t.start();
					}

					if (input[0].equalsIgnoreCase("check")) {
						if (input[1].equalsIgnoreCase("all")) {
							running.stream().forEach(c -> System.out.print(c.check()));
						}
						running.stream().filter(c -> c.getName().equals(input[1]))
								.forEach(c -> System.out.println(c.check()));

					}

					if (input[0].equalsIgnoreCase("stop")) {
						running.stream().filter(c -> c.getName().equals(input[1])).forEach(c -> c.stop());
						running.remove(running.stream().filter(c -> c.getName().equals(input[1]))
								.collect(Collectors.toList()).get(0));
					}
				} 
			} else {
				System.out.println("Program terminated");
			}
		}
	}
}
