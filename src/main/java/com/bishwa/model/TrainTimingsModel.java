package com.bishwa.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainTimingsModel {
	
	private int id;
	private String startLocation;
	private String destination;
	private String startTime;
	private String endTime;
	private List<String> halts;
}
