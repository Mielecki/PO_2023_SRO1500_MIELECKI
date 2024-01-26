package agh.ics.oop.model;

import agh.ics.oop.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements Runnable{
    private final List<Simulation> simulations;

    public SimulationEngine(List<Simulation> simulations){
        this.simulations = simulations;
    }

    public void runSync() {
        for (Simulation simulation : simulations){
            simulation.run();
        }
    }

    public void runAsync(){
        List<Thread> threads = new ArrayList<>();
        for (Simulation simulation : simulations){
            Thread thread = new Thread(()-> simulation.run());
            threads.add(thread);
            thread.start();
        }
        for(Thread thread : threads){
            try{
                thread.join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void awaitSimulationsEnd(ExecutorService executorService) throws InterruptedException {
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    public void runAsyncInThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (Simulation simulation : simulations){
            executorService.submit(()-> simulation.run());
        }

        awaitSimulationsEnd(executorService);
    }

    @Override
    public void run() {
        System.out.println("Thread started");
    }
}
