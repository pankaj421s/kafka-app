package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Data;
import com.example.demo.repositories.DataRepository;
@RestController
public class HomeController {
	@Autowired
	public KafkaTemplate<String,String> kafkaTemplate;
	@Autowired
	public DataRepository dataRepository;
	@CrossOrigin("http://localhost:4200")
	@PostMapping("/save")
	public Data save(@RequestBody Data data)
	{
		System.out.println(data.getName());
		kafkaTemplate.send("my-topic",data.getName());
		//dataRepository.save(data);
		return data;
	}
	@CrossOrigin
	@GetMapping("getAll")
	public Data[] getAll()
	{
		List<Data> dataList=new ArrayList<>();
		dataList=dataRepository.findAll();
		System.out.println(dataList.size());
		Data[] datas=new Data[dataList.size()];
		dataList.toArray(datas);
		return datas;
	}
	@KafkaListener(topics = "my-topic",groupId = "mygroup")
	public void listen(String message)
	{
		System.out.println("received message "+message);
		Data data=new Data();
		data.setName(message);
		dataRepository.save(data);

	}
	

}
