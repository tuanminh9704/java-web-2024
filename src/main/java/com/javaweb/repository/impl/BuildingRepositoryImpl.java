package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
    private static String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    private static String USER_NAME = "root";
    private static String PASSWORD = "@M972004abc";
	@Override
	public List<BuildingEntity> findAll(String name) {
		String sql = "SELECT * FROM building";
    	List<BuildingEntity> result = new ArrayList<>();
    	try(Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD); 
    			Statement stmt = conn.createStatement();
    			ResultSet rs = stmt.executeQuery(sql);){
    		while(rs.next()) {
    			BuildingEntity buildingEntity = new BuildingEntity();
    			buildingEntity.setName(rs.getString("name"));
    			buildingEntity.setStreet(rs.getString("street"));
    			buildingEntity.setWard(rs.getString("ward"));
    			buildingEntity.setNumberOfBasement(rs.getString("numberOfBasement"));
    			
    			result.add(buildingEntity);
    		}
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
		// TODO Auto-generated method stub
		return result;
	}
}
