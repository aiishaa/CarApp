package com.example.CarApp;

import com.example.CustomerSubsystem.Admin;
import com.example.DatabaseManager.Database;
import com.example.RideSubsystem.Area;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarAppApplication {

	public static void main(String[] args) {
            Database manager = Database.getInstance();
            Admin admin = new Admin("Ibrahim", "010", "Ibrahimfathy@gmail.com", "admin");
            manager.addAdmin(admin);
            SpringApplication.run(CarAppApplication.class, args);
	}

}
