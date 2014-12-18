package com.neo.dao;

import java.util.List;

import com.neo.domaine.Message;
import com.neo.domaine.Utilisateur;

public interface MessageDAO {
	public Message findMessageById(Long id);
	public void creer(Message message);
	public void supprimer(Message message);
	public List<Message> lister(Utilisateur utilisateur);
}
