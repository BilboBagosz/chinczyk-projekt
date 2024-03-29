package com.ludo.lan.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.ludo.app.control.Player;
import com.ludo.lan.head.Head;
import com.ludo.lan.head.HeadConst;
import com.ludo.lan.head.RedPlayerHead;
import com.ludo.lan.observer.ClientObserver;
import com.ludo.lan.observer.ClientSubject;
import com.ludo.lan.task.Task;

public class ClientHandler extends Task implements ClientSubject{
	
	private ArrayList<ClientObserver> observer = new ArrayList<ClientObserver>();
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private int i = 0;
	public ClientHandler(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void registerObserver(ClientObserver o) {
		// TODO Auto-generated method stub
		observer.add(o);
		
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		}

	@Override
	protected void task() {
		// TODO Auto-generated method stub
		
		Head h;

		try {
			h = (Head) in.readObject();

			int value = h.getID();
			switch(value){
			
			case HeadConst.redPlayer: 
				Player p = (Player) h.getObject();
				System.out.println("Przyszedl player");
				notifyyy(p);
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	public void notifyyy(Player p){
		for(ClientObserver ob: observer)
			ob.joinRedPlayer(p);
	}
	public void addPlayer(Player p){
		try {
			Head h = new RedPlayerHead();
			h.setObject(p);
			out.writeObject(h);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void taskStream() {
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
