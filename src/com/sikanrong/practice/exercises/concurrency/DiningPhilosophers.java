//TODO: come up with a longer and even more pompous package name...
package com.sikanrong.practice.exercises.concurrency;

public class DiningPhilosophers {
	private Object forks[];
	public Philosopher philosophers[]; 
	private int tableSize;
	
	public DiningPhilosophers(int tableSize) {
		this.tableSize = tableSize;
		this.philosophers = new Philosopher[tableSize];
		this.forks = new Object[tableSize];
	}
	
	public static class Philosopher extends Thread{
		public long eaten = 0;
		private Object fork1, fork2;
		public int index;
		public Philosopher(int index, Object fork1, Object fork2) {
			this.index = index;
			this.fork1 = fork1;
			this.fork2 = fork2;
		}
		
		public void run() {
			while(true)
				eat();
		}
		
		public void eat() {
			synchronized(fork1) {
				synchronized(fork2) {
					for(int i = 0; i < (int)(Math.random() * 1000); i++) { 
						//nom nom nom...
						this.eaten += 1;
					}
				}
			}
			
		}
	}
	
	public void startDining() {
		for(int i = 0; i < this.tableSize; i++) {
			//here I just init all the forks
			this.forks[i] = new Object();
		}
		
		for(int i = 0; i < this.tableSize; i++) {
			//here I init the philosophers and decide
			//which fork gets picked up first
			int leftFork = i;
			int rightFork = i + 1;
			
			if(i == (this.tableSize - 1)) {
				rightFork = 0;
			}
			
			//results in starving the 4th philosopher
			//philosophers[i] = new Philosopher(i, forks[leftFork], forks[rightFork]);
			
			//this also results in no starved philosophers, however it produces
			//a very uneven balance between the philosophers at indices 0 and 4.
			//if(leftFork < rightFork){
			
			if(leftFork%2 == 0) {
				philosophers[i] = new Philosopher(i, forks[leftFork], forks[rightFork]);
			} else {
				philosophers[i] = new Philosopher(i, forks[rightFork], forks[leftFork]);
			}

			philosophers[i].start();
		}
	}
}
