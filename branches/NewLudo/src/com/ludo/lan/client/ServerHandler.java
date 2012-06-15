package com.ludo.lan.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.ludo.lan.observer.ServerObserver;
import com.ludo.lan.observer.ServerSubject;

public class ServerHandler implements ServerSubject{

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private ArrayList<ServerObserver> observer = new ArrayList<ServerObserver>();
	
	public ServerHandler(Socket socket){
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public void registerObserver(ServerObserver o) {
		// TODO Auto-generated method stub
		observer.add(o);
		
	}
	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		for(int i = 0; i < observer.size(); i++){
			ServerObserver so = observer.get(i);
		}
		
	}
}